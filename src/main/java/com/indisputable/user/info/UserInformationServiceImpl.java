package com.indisputable.user.info;

import com.indisputable.user.ErrorMessages;
import com.indisputable.user.exception.ServiceException;
import com.indisputable.user.library.DevUtil;
import com.indisputable.user.library.UniversalConstants;
import com.indisputable.user.otp.UserOTPDetails;
import com.indisputable.user.otp.UserOTPDetailsRepository;
import com.indisputable.user.otp.ValidateOTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private UserOTPDetailsRepository userOTPDetailsRepository;

    public List<UserInformation> getAllUserInformation(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        if (userInformation.getUserType() != UserUtil.USER_TYPE_SUPER_ADMIN) {
            throw new ServiceException(ErrorMessages.ACCESS_DENIED);
        }
        return userInformationRepository.findAll();
    }

    public UserInformation getUserInformation(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        if (userInformation.isDeletedUser()) {
            throw new ServiceException(ErrorMessages.USER_ACCOUNT_DELETED);
        }
        if (userInformation.isBlocked()) {
            throw new ServiceException(ErrorMessages.USER_ACCOUNT_BLOCKED);
        }
        if (!userInformation.isMobileVerified()) {
            throw new ServiceException(ErrorMessages.USER_ACCOUNT_NOT_VERIFIED);
        }
        return userInformation;
    }

    public UserInformation createUser(UserInformation userInformation) {
        if (userInformation == null) {
            throw new ServiceException(ErrorMessages.USER_INFORMATION_OBJECT_NOT_FOUND);
        }
        if (DevUtil.isNullOrEmpty(userInformation.getName())) {
            throw new ServiceException(ErrorMessages.USER_NAME_IS_MANDATORY);
        }
        if (userInformation.getUserType() == null) {
            throw new ServiceException(ErrorMessages.USER_TYPE_IS_MANDATORY);
        }
        if (userInformation.getUserType() == UserUtil.USER_TYPE_INDIVIDUAL) {
            userInformation.setBusinessOrAgencyName("");
        } else {
            if (DevUtil.isNullOrEmpty(userInformation.getBusinessOrAgencyName())) {
                throw new ServiceException(ErrorMessages.USER_BUSINESS_NAME_IS_MANDATORY);
            }
        }
        if (Long.toString(userInformation.getMobile()).length() != 10) {
            throw new ServiceException(ErrorMessages.USER_INVALID_MOBILE_NUMBER);
        }
        if (userInformation.getUserAuthentication() == null) {
            throw new ServiceException(ErrorMessages.USER_AUTHENTICATION_OBJECT_NOT_FOUND);
        }
        if (DevUtil.isNullOrEmpty(userInformation.getUserAuthentication().getPassword())) {
            throw new ServiceException(ErrorMessages.USER_PASSWORD_IS_MANDATORY);
        }
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUserInformation(userInformation);
        String password = userInformation.getUserAuthentication().getPassword();
        password = DevUtil.encryptPassword(password);
        userAuthentication.setPassword(password);
        userInformation.setUserAuthentication(userAuthentication);

        userInformation.setMobileVerified(false);
        userInformation.setEmailVerified(false);
        userInformation.setDeletedUser(false);
        userInformation.setBlocked(false);
        userInformation.setAssured(false);
        userInformation.setVerified(false);
        userInformation = userInformationRepository.save(userInformation);
        resendOTP(userInformation);
        return userInformation;
    }

    public UserInformation updateUserInformation(UserInformation userInformation, String userId) {
        if (userInformation == null) {
            throw new ServiceException(ErrorMessages.USER_INFORMATION_OBJECT_NOT_FOUND);
        }
        UserInformation oldUserInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        if (!DevUtil.isNullOrEmpty(userInformation.getName())
                && !userInformation.getName().equals(oldUserInformation.getName())) {
            oldUserInformation.setName(userInformation.getName());
        }
        if (userInformation.getUserType() != UserUtil.USER_TYPE_INDIVIDUAL
                && !DevUtil.isNullOrEmpty(userInformation.getBusinessOrAgencyName())
                && !userInformation.getBusinessOrAgencyName().equals(oldUserInformation.getBusinessOrAgencyName())) {
            oldUserInformation.setBusinessOrAgencyName(userInformation.getBusinessOrAgencyName());
        }
        if (!DevUtil.isNullOrEmpty(userInformation.getEmail())
                && !userInformation.getEmail().equals(oldUserInformation.getEmail())) {
            oldUserInformation.setEmail(userInformation.getEmail());
            oldUserInformation.setEmailVerified(false);
        }
        if (userInformation.getMobile() != null
                && userInformation.getMobile() != oldUserInformation.getMobile()) {
            if (Long.toString(userInformation.getMobile()).length() != 10) {
                throw new ServiceException(ErrorMessages.USER_INVALID_MOBILE_NUMBER);
            }
            oldUserInformation.setMobile(userInformation.getMobile());
            oldUserInformation.setMobileVerified(false);
        }
        return userInformationRepository.save(oldUserInformation);
    }

    public UserInformation deleteUser(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        userInformation.setDeletedUser(true);
        return userInformationRepository.save(userInformation);
    }

    public UserInformation reactivateUser(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        userInformation.setDeletedUser(false);
        return userInformationRepository.save(userInformation);
    }

    public UserInformation blockUser(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        userInformation.setBlocked(true);
        return userInformationRepository.save(userInformation);
    }

    public UserInformation unBlockUser(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        userInformation.setBlocked(false);
        return userInformationRepository.save(userInformation);
    }

    public UserInformation assuredUser(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        userInformation.setAssured(true);
        return userInformationRepository.save(userInformation);
    }

    public UserInformation unAssuredUser(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        userInformation.setAssured(false);
        return userInformationRepository.save(userInformation);
    }

    /**
     * OTP Validation Related Methods
     */
    public boolean validateOTP(ValidateOTP validateOTP) {
        UserInformation userInformation = userInformationRepository.findById(validateOTP.getUserId())
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        UserOTPDetails userOTPDetails = userOTPDetailsRepository.findByUserInformation(userInformation);
        Long generatedOn = userOTPDetails.getGeneratedOn() + UniversalConstants.MILLIES_IN_30_MIN;
        if (System.currentTimeMillis() > generatedOn) {
            userOTPDetailsRepository.delete(userOTPDetails);
            throw new ServiceException(ErrorMessages.OTP_EXPIRED);
        }
        if (userOTPDetails.getOtp() == validateOTP.getOtp()) {
            return true;
        }
        return false;
    }

    public boolean resendOTP(String userId) {
        UserInformation userInformation = userInformationRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST));
        resendOTP(userInformation);
        return true;
    }

    public boolean resendOTP(UserInformation userInformation) {
        /**
         * Delete Old OTP Then Generate New OTP
         */
        UserOTPDetails userOTPDetails = userOTPDetailsRepository.findByUserInformation(userInformation);
        if (userOTPDetails != null) {
            userOTPDetailsRepository.delete(userOTPDetails);
        }
        /**
         * OTP Generation Code
         */
        int otp = DevUtil.generateRandomNumber();
        userOTPDetails = new UserOTPDetails();
        userOTPDetails.setOtp(otp);
        userOTPDetails.setGeneratedOn(System.currentTimeMillis());
        userOTPDetails.setUserInformation(userInformation);
        userOTPDetailsRepository.save(userOTPDetails);
        return true;
    }


    public UserInformation userLogin(Long mobile, String password) {
        UserInformation userInformation = userInformationRepository.findByMobile(mobile);
        if (userInformation == null) {
            throw new ServiceException(ErrorMessages.USER_DOES_NOT_EXIST);
        }
        password = DevUtil.encryptPassword(password);
        if (!password.equals(userInformation.getUserAuthentication().getPassword())) {
            throw new ServiceException(ErrorMessages.INVALID_USER_NAME_OR_PASSWORD);
        }
        return userInformation;
    }

}

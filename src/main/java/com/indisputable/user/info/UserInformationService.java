package com.indisputable.user.info;

import com.indisputable.user.otp.ValidateOTP;

import java.util.List;

public interface UserInformationService {

    List<UserInformation> getAllUserInformation(String userId);

    UserInformation getUserInformation(String userId);

    UserInformation createUser(UserInformation userInformation);

    UserInformation updateUserInformation(UserInformation userInformation, String userId);

    UserInformation deleteUser(String userId);

    UserInformation reactivateUser(String userId);

    UserInformation blockUser(String userId);

    UserInformation unBlockUser(String userId);

    UserInformation assuredUser(String userId);

    UserInformation unAssuredUser(String userId);


    boolean validateOTP(ValidateOTP validateOTP);

    boolean resendOTP(String userId);

    boolean resendOTP(UserInformation userInformation);

//    UserInformation userLogin(Long mobile, String password);


}

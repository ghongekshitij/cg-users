package com.indisputable.user.otp;

import com.indisputable.user.info.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOTPDetailsRepository extends JpaRepository<UserOTPDetails, String> {

    public UserOTPDetails findByUserInformation(UserInformation userInformation);
}

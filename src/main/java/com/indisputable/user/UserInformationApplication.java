package com.indisputable.user;

import com.indisputable.user.info.UserInformation;
import com.indisputable.user.info.UserInformationRepository;
import com.indisputable.user.info.UserInformationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User API", version = "1.0", description = "User Micro Service"))
public class UserInformationApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserInformationApplication.class, args);

//        UserInformationService userInformationService = new UserInformationService();
//        UserInformation userInformation = new UserInformation();
//        userInformation.setUserName("ghongekshitij");
//        userInformationService.createUser(userInformation);

    }

}

package com.indisputable.user.info;

import com.indisputable.user.otp.ValidateOTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserInformationService userInformationService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserInformation>> getAUserInformation(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.getAllUserInformation(userId), HttpStatus.OK);
    }

    @GetMapping("/user/get/{userId}")
    public ResponseEntity<UserInformation> getUserInformation(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.getUserInformation(userId), HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public ResponseEntity<UserInformation> createUserInformation(@RequestBody UserInformation userInformation) {
        return new ResponseEntity<>(userInformationService.createUser(userInformation), HttpStatus.OK);
    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<UserInformation> updateUserInformation(@RequestBody UserInformation userInformation, @PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.updateUserInformation(userInformation, userId), HttpStatus.OK);
    }

    @PutMapping("/user/delete/{userId}")
    public ResponseEntity<UserInformation> deleteUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.deleteUser(userId), HttpStatus.OK);
    }

    @PutMapping("/user/reactivate/{userId}")
    public ResponseEntity<UserInformation> reactivateUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.reactivateUser(userId), HttpStatus.OK);
    }

    @PutMapping("/user/block/{userId}")
    public ResponseEntity<UserInformation> blockUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.blockUser(userId), HttpStatus.OK);
    }

    @PutMapping("/user/unblock/{userId}")
    public ResponseEntity<UserInformation> unBlockUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.unBlockUser(userId), HttpStatus.OK);
    }

    @PutMapping("/user/assured/{userId}")
    public ResponseEntity<UserInformation> assuredUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.assuredUser(userId), HttpStatus.OK);
    }

    @PutMapping("/user/unassured/{userId}")
    public ResponseEntity<UserInformation> unAssuredUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userInformationService.unAssuredUser(userId), HttpStatus.OK);
    }

    @PostMapping("/user/verifyotp")
    public ResponseEntity<Boolean> verifyOTP(@RequestBody ValidateOTP validateOTP) {
        return new ResponseEntity<>(userInformationService.validateOTP(validateOTP), HttpStatus.OK);
    }

    @PostMapping("/user/resendotp/{userId}")
    public ResponseEntity<Boolean> resendOTP(@PathVariable("userId") String userId) {
        return new ResponseEntity<Boolean>(userInformationService.resendOTP(userId), HttpStatus.OK);
    }
}

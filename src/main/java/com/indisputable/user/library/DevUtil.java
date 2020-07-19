package com.indisputable.user.library;

import com.indisputable.user.ErrorMessages;
import com.indisputable.user.exception.ServiceException;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class DevUtil {

    public static boolean isNullOrEmpty(String str) {
        return (str == null || str == "") ? true : false;
    }

    public static int generateRandomNumber() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(999999);
        return randomNumber;
    }

    public static String encryptPassword(String password) {
        /**
         * Encryption Logic For Password.
         */
        try {
            MessageDigest md = MessageDigest.getInstance("SHA3-512");
            byte[] result = md.digest(password.getBytes(Charset.forName(UniversalConstants.CHARACTER_ENCODING_UTF8)));
            password = result.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new ServiceException(ErrorMessages.SOME_PROBLEM_OCCURRED_AT_SERVER_SIDE);
        }
        return password;
    }
}

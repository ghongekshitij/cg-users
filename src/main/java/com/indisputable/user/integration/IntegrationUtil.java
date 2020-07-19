package com.indisputable.user.integration;

import com.indisputable.user.exception.ServiceException;
import com.indisputable.user.library.DevUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class IntegrationUtil {


    public static String urlEncodeString(String str) throws ServiceException {
        return urlEncodeString(str, null);
    }

    public static String urlEncodeString(String str, String charset) throws ServiceException {
        String returnStr = null;
        try {
            if (str != null) {
                if (DevUtil.isNullOrEmpty(charset)) {
                    charset = StandardCharsets.UTF_8.toString();
                }
                returnStr = URLEncoder.encode(str, charset);
            }
        } catch (UnsupportedEncodingException exception) {
            throw new ServiceException("String could not be encoded properly.");
        }
        return returnStr;
    }

    public static String urlDecodeString(String str) throws ServiceException {
        return urlDecodeString(str, null);
    }

    public static String urlDecodeString(String str, String charset) throws ServiceException {
        String returnStr = null;
        try {
            if (str != null) {
                if (DevUtil.isNullOrEmpty(charset)) {
                    charset = StandardCharsets.UTF_8.toString();
                }
                returnStr = URLDecoder.decode(str, charset);
            }
        } catch (UnsupportedEncodingException exception) {
            throw new ServiceException("String could not be decoded properly.");
        }
        return returnStr;
    }

}

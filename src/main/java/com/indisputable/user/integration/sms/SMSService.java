package com.indisputable.user.integration.sms;

import com.indisputable.user.exception.ServiceException;
import org.json.JSONException;
import org.json.JSONObject;

public interface SMSService {

    public boolean sendSMS(Long mobileNumber, String Message) throws ServiceException;
}

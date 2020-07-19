package com.indisputable.user.integration.sms;

import com.indisputable.user.exception.ServiceException;
import com.indisputable.user.integration.IntegrationConstants;
import com.indisputable.user.integration.IntegrationUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SMSHorizonService implements SMSService {

    @Override
    public boolean sendSMS(Long mobileNumber, String message) throws ServiceException {
        message = IntegrationUtil.urlEncodeString(message);
        String URL = "http://smshorizon.co.in/api/sendsms.php?user=ashok9999&apikey=lxsEb4qVj1R6bTZRrtbD&" +
                "mobile=" + Long.toString(mobileNumber) + "&" +
                "message=" + message + "&senderid=CampCn&type=txt";
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(URL);
            HttpResponse httpresponse = httpclient.execute(httpget);
        } catch (ClientProtocolException exception) {

        } catch (IOException exception) {

        }
        return false;
    }
}

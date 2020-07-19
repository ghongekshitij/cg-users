package com.indisputable.user.integration.sms;

public class SMSServiceFactory {

    public static SMSService getSMSService() {
        return new SMSHorizonService();
    }
}

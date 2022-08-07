package com.twilio.phonenumber_verification_system.service;

import org.springframework.stereotype.Service;

@Service
public interface PhoneNumberVerificationService {

    public void verifyPhoneNumber();

    public void sendOTP();

}

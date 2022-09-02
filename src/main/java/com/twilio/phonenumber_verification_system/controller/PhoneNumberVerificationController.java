package com.twilio.phonenumber_verification_system.controller;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.rest.lookups.v1.PhoneNumber;
import com.twilio.rest.verify.v2.service.entity.Factor;
import com.twilio.rest.verify.v2.service.entity.NewFactor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.twilio.example.ValidationExample.ACCOUNT_SID;
import static com.twilio.example.ValidationExample.AUTH_TOKEN;


@RestController
@RequestMapping(path = "api/phoneNumber")
@Slf4j
public class PhoneNumberVerificationController {

    @GetMapping(value = "/generateTOTP")
    public ResponseEntity<String> generateTOTP(){

        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

        Verification verification = Verification.creator(
                        "VA954765dc76826bc3895c459da6744b6f",
                        "+2348102578725",
                        "sms")
                .create();

        System.out.println(verification.getStatus());

      log.info("TOTP has been successfully generated, and awaits your verification {}", LocalDateTime.now());

       return new ResponseEntity<>("Your TOTP has been sent to your verified phone number", HttpStatus.OK);
    }

    @GetMapping("/verifyTOTP")
    public ResponseEntity<String> verifyUserTOTP(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        VerificationCheck verificationCheck = VerificationCheck.creator(
                        "VA954765dc76826bc3895c459da6744b6f")
                .setTo("+2348102578725")
                .setCode("486578")
                .create();

        System.out.println(verificationCheck.getStatus());

        return new ResponseEntity<>("This user’s verification has been completed successfully", HttpStatus.OK);
    }

}

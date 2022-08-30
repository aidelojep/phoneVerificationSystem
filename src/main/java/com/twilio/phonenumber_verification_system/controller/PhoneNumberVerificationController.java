package com.twilio.phonenumber_verification_system.controller;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import com.twilio.rest.verify.v2.service.entity.Factor;
import com.twilio.rest.verify.v2.service.entity.NewFactor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;



@RestController
@RequestMapping(path = "api/phoneNumber")
@Slf4j
public class PhoneNumberVerificationController {

    @GetMapping(value = "/createTOTP")
    public ResponseEntity<String> createTOTP(){

        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));


            NewFactor createNewFactorAuthentication = NewFactor.creator(
                            "VAbe46ca1c655070b77d11c2798f7cccf1",
                            "ff483d1ff591898a9942916050d2ca3f",
                            "PEROZON'S VERIFICATION SERVICE",

                            NewFactor.FactorTypes.TOTP).setConfigCodeLength(6).setConfigTimeStep(30)
                    .create();
        System.out.println(createNewFactorAuthentication.toString())git;
            String response = String.valueOf(createNewFactorAuthentication.getBinding());
            System.out.println(response);

        //Message.creator(new PhoneNumber("+2348102578725"), new PhoneNumber("+19896629625"), "Hello from Perozon").create();
      log.info("TOTP has been successfully generated {}", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verifyTOTP")
    public ResponseEntity<String> verifyUserTOTP(){

        Factor factor = Factor.updater(
                        "VAbe46ca1c655070b77d11c2798f7cccf1",
                        "ff483d1ff591898a9942916050d2ca3f",
                        "AQRQUQ2E53S5PSPIZX4URFL3AWOCPP4X")
                .setAuthPayload("12345").update();

        System.out.println(factor.getStatus());

        return new ResponseEntity<>("userVerification Done", HttpStatus.OK);
    }



}

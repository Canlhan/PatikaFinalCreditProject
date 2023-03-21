package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.Customer;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Primary
@Slf4j
public class SmsServiceImpl implements SmsService<Object> {


    private final String TWILIO_ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private final String TWILIO_AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");


    @Override
    public Object sendNotifications(Object... args) {

        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);

        Customer customer = (Customer) args[0];
        log.info("sms gönderildi");

        try {
            String telephoneOfCustomer = "+90" + customer.getTelephonNumber();
            Message.creator(new PhoneNumber(telephoneOfCustomer),
                    new PhoneNumber("+12765799601"), "credit applications is done");
        } catch (Exception e) {
            return "There are a exception while sending notifications";
        }
        return "mesaj gönderildi";

    }


}

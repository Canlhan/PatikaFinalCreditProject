package com.can.PatikaFinalCreditProject.service;

import com.can.PatikaFinalCreditProject.entity.Customer;
import com.can.PatikaFinalCreditProject.entity.LoanApplication;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;


@Service
public class SmsServiceImpl implements SmsService<Object>{


    private final String TWILIO_ACCOUNT_SID=System.getenv("TWILIO_ACCOUNT_SID");
    private final String TWILIO_AUTH_TOKEN=System.getenv("TWILIO_AUTH_TOKEN");




    @Override
    public Object sendNotifications(Object... args) {
        Twilio.init(TWILIO_ACCOUNT_SID,TWILIO_AUTH_TOKEN);

        Customer customer=(Customer) args[0];


        try {
            String telephoneOfCustomer="+90"+customer.getTelephonNumber();
            Message.creator(new PhoneNumber(telephoneOfCustomer),
                    new PhoneNumber("+12765799601"), "Pornhub hesabınız onaylanmıştır"
            ).create();
        }catch (Exception e){
            return "There are a exception while sending notifications";
        }
        return "mesaj gönderildi";

    }
}

package com.can.PatikaFinalCreditProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Qualifier("email")
@Slf4j
public class EmailService implements NotificationsService<Object>{


    @Override
    public Object sendNotifications(Object... args) {
        log.info("email gönderildi");
        return null;
    }
}

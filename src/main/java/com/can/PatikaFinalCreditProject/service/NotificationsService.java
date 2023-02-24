package com.can.PatikaFinalCreditProject.service;

import jakarta.persistence.Entity;

public interface NotificationsService<T>
{

    T sendNotifications(T... args);
}

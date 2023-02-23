package com.can.PatikaFinalCreditProject.repository;

import com.can.PatikaFinalCreditProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {

    Customer findCustomerByIdentifyNo(String identifyNo);
}

package com.can.PatikaFinalCreditProject.repository;

import com.can.PatikaFinalCreditProject.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {


    List<LoanApplication> findAllByCustomer_CustomerId(Long customerId);
}

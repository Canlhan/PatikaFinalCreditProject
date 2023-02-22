package com.can.PatikaFinalCreditProject.entity;

import jakarta.persistence.*;

@Entity
public class LoanApplication
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;


    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @Column(name = "is_approval")
    private Boolean isApproval;
}

package com.can.PatikaFinalCreditProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private boolean isApproval;

    @Column(name = "credit_limit")
    private Long creditLimit;

}

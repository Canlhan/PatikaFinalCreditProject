package com.can.PatikaFinalCreditProject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "firts_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "telephone_number")
    private String telephonNumber;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "quarantee")
    private Long quarantee;

    @OneToOne
    @JoinColumn(name = "credit_id",referencedColumnName = "creditId")
    private CreditScore creditScore;


    @OneToMany(mappedBy = "customer")
    private Set<LoanApplication> loanApplications;

}

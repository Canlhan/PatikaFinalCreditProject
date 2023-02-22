package com.can.PatikaFinalCreditProject.entity;

import jakarta.persistence.*;

@Entity
public class CreditScore
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditId;


    @Column(name = "credit_score")
    private Long creditScore;





}

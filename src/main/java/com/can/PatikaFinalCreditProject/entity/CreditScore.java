package com.can.PatikaFinalCreditProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditScore
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditId;


    @Column(name = "credit_score")
    private Long creditScore;





}

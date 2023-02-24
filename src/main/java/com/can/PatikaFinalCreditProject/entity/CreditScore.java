package com.can.PatikaFinalCreditProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CreditScore
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditId;


    @Column(name = "credit_score")
    private Long creditScore;





}

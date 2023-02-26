package com.can.PatikaFinalCreditProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
public class LoanApplication
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;



    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonIgnore
     private Customer customer;

    @Column(name = "is_approval")
    private boolean isApproval;

    @Column(name = "credit_limit")
    private Long creditLimit;

}

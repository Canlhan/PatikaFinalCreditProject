package com.can.PatikaFinalCreditProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
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

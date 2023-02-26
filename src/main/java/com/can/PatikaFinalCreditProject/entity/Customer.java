package com.can.PatikaFinalCreditProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table

public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;


    @Column(name = "identify_no",unique = true)
    @Pattern(regexp = "^\\d{11}$")
    @NonNull
    private String identifyNo;

    @Column(name = "firts_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;



    @Column(name = "telephone_number")
    @Pattern(regexp = "^\\d{10}$")
    private String telephonNumber;


    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "quarantee")
    private Long quarantee= 0l;


    @Column(name = "salary")
    private Long salary;

    @OneToOne
    @JoinColumn(name = "credit_id",referencedColumnName = "creditId")
    private CreditScore creditScore;


    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<LoanApplication> loanApplications;

}

package com.govind.worksphere.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.govind.worksphere.entity.enums.Gender;
import com.govind.worksphere.entity.enums.EmploymentStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String employeeCode;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate joiningDate;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @Column(nullable = false)
    private String department;

}
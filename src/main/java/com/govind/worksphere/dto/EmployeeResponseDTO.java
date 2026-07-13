package com.govind.worksphere.dto;

import com.govind.worksphere.entity.enums.EmploymentStatus;
import com.govind.worksphere.entity.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {

    private Long id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Gender gender;

    private LocalDate joiningDate;

    private EmploymentStatus employmentStatus;

    private Long departmentId;

    private String departmentName;

    private String profileImage;

    private String resumeFile;
}
package com.govind.worksphere.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponseDTO {

    private Long id;

    private String departmentCode;

    private String departmentName;

    private String description;
}
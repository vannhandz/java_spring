package com.techzen.academy_n1224.employees.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {
    String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo;
    String gender;
    String salaryRange;
    String phone;
    Integer departmentId;
}

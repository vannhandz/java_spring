package com.techzen.academy_n1224.employees.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
     int id;
     String name;
     LocalDate birth;
     String gender;
     Double salary;
     String phone;
     Integer departmentId;


}

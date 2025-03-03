package com.techzen.academy_n1224.employees.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Employee {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     int id;
     String name;
     LocalDate birth;
     String gender;
     Double salary;
     String phone;
     @ManyToOne
     Department idDepartment;



}

package com.techzen.academy_n1224.nv.nhanvien;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanVien {
     int id;
     String name;
     LocalDate bỉth;
     String gender;
     Double salary;
     String phone;
}

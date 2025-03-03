package com.techzen.academy_n1224.T_Marketplac.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatBang {
    int maMatBang;
    String tenMatBang;
    String diaChi;
    String dienTich;
    double giaThue;
    String ngayBatDauThue;
    int loaiMatBang;
}

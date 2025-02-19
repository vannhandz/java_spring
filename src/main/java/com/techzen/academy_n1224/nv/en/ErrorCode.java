package com.techzen.academy_n1224.nv.en;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    NhanVien_NOT_EXIST(404,"nhan vien khong co", HttpStatus.NOT_FOUND),
    ;
    int code;
    String message;
    HttpStatus httpStatus;
}

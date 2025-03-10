package com.techzen.academy_n1224.test.en;


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
    Employees_NOT_EXIST(40401,"Employee is not existed", HttpStatus.NOT_FOUND),
    Department_NOT_EXIST(40401,"Department is not existed", HttpStatus.NOT_FOUND),
    MattBang_Not_Exist(40401,"MattBang is not existed", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus httpStatus;
}

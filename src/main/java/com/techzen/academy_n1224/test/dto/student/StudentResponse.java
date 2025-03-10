package com.techzen.academy_n1224.test.dto.student;

import com.techzen.academy_n1224.test.dto.clazz.ClazzResponse;
import com.techzen.academy_n1224.test.dto.clazz.ClazzResquest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StudentResponse {

    int id;
    private String name;
    private Double score;
    ClazzResponse clazz;

}

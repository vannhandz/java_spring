package com.techzen.academy_n1224.test.dto.student;

import com.techzen.academy_n1224.test.dto.clazz.ClazzResquest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StudentRequest {

        private String name;
        private Double score;
        ClazzResquest clazz;

}

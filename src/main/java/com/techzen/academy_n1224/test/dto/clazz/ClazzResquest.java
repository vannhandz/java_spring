package com.techzen.academy_n1224.test.dto.clazz;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ClazzResquest {
    private int id;
    private String name;
}

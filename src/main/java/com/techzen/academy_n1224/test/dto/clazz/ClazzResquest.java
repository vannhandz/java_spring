package com.techzen.academy_n1224.test.dto.clazz;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ClazzResquest {
    @NotNull(message = "phia chon lop")
     Integer id;
     String name;
}

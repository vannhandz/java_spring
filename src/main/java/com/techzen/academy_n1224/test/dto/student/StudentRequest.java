package com.techzen.academy_n1224.test.dto.student;

import com.techzen.academy_n1224.test.dto.clazz.ClazzResquest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StudentRequest {
        @NotNull (message = "Tên không hiển thị")
        @NotEmpty(message = "khong dc phem empty")
        @NotBlank(message = "khong duoc phep blank")
        @Size(max=30,message = "tôi qua 30 ki tu")
        @Pattern(regexp = "[a-zA-ZÀ-ỹ]*",message = "ten khong chua ki tu dac biet")
         String name;
         Double score;
         @Valid
         @NotNull(message = "phai chon lop")
        ClazzResquest clazz;

}

package com.techzen.academy_n1224.test.dto.page;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageCustom<T> {
    int page;
    int size;
    int totalElements;
    int totalPages;


    public PageCustom(Page<T> page) {
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getNumberOfElements();
    }
}

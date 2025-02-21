package com.techzen.academy_n1224.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DictionaryController {


    private final Map<String, String> dictionary= Map.ofEntries(
            Map.entry("hello", "Xin chào"),
            Map.entry("goodbye", "Tạm biệt"),
            Map.entry("thank you", "Cảm ơn")
    );



    @GetMapping("/dictionary")
    public ResponseEntity<String> getDictionary(@RequestParam(defaultValue = "") String word) {
        String result = dictionary.get(word.trim().toLowerCase());

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("khong co trong tu dien");
        }
        return ResponseEntity.ok(result);
    }
}

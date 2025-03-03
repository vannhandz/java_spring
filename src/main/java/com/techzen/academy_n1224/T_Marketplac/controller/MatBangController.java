package com.techzen.academy_n1224.T_Marketplac.controller;

import com.techzen.academy_n1224.T_Marketplac.model.MatBang;
import com.techzen.academy_n1224.T_Marketplac.service.IMatBangService;
import com.techzen.academy_n1224.employees.en.ApiResponse;
import com.techzen.academy_n1224.employees.en.JsonResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/matbangs")
public class MatBangController {

    IMatBangService matBangService;
    
    @GetMapping("/all")
    private ResponseEntity<?> getAll() {
        return JsonResponse.ok(matBangService.getAll());
    }

    @PostMapping()
    private ResponseEntity<?> add(@RequestBody MatBang matBang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matBangService.save(matBang));
    }
    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(ApiResponse.builder().data(matBangService.findById(id)).code(404).build());
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        matBangService.delete(id);
        return JsonResponse.noContent();
    }



}

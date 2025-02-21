package com.techzen.academy_n1224;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorCtll {

    @GetMapping("/calculator")
    private ResponseEntity<String> calculator(@RequestParam(defaultValue = "") String firstNumStr,
                                              @RequestParam(defaultValue = "") String secondNumStr,
                                              @RequestParam(defaultValue = " ") String operator) {

        if (firstNumStr.isEmpty() || secondNumStr.isEmpty()) {
            return ResponseEntity.badRequest().body("thieu so ");
        } else if (!isDouble(firstNumStr) || !isDouble(secondNumStr)) {
            return ResponseEntity.badRequest().body("so thuc");
        }
        double firstNum = Double.parseDouble(firstNumStr);
        double secondNum = Double.parseDouble(secondNumStr);
        double result = 0;


        switch (operator) {
            case "+" -> result = firstNum + secondNum;
            case "-" -> result = firstNum - secondNum;
            case "*" -> result = firstNum * secondNum;
            case "/" -> {
                if (secondNum == 0) {
                    return ResponseEntity.badRequest().body("Division by zero is not allowed");
                } else {
                    result = firstNum / secondNum;
                }
            }
            default -> {
                return ResponseEntity.badRequest().body("Invalid operator");
            }
        }
        return ResponseEntity.ok().body("Result : " + result);
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

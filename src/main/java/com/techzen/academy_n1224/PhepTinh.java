package com.techzen.academy_n1224;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhepTinh {

    @GetMapping("/tinh")
    private ResponseEntity<String> phepTinh(@RequestParam(defaultValue = "") String num1,
                                            @RequestParam(defaultValue = "") String num2,
                                            @RequestParam(defaultValue = " ")String pheptinh)
    {

        if(num1.isEmpty() || num2.isEmpty())
        {
            return ResponseEntity.badRequest().body("thieu so ");
        }else if(!isDouble(num1) || !isDouble(num2)){
            return ResponseEntity.badRequest().body("so thuc");
        }
        double n1 = Double.parseDouble(num1);
        double n2 = Double.parseDouble(num2);
        double result =0;



        switch(pheptinh)
        {
            case "+" ->result = n1 + n2;
            case "-" ->result = n1 - n2;
            case "*" ->result = n1 * n2;
            case "/" ->{
                if(n2==0)
                {
                    return ResponseEntity.badRequest().body("ko ho le");
                }else {
                    result = n1 / n2;
                }
            }
            default ->{
                return ResponseEntity.badRequest().body("Phép tinh sai");
            }
        }
        return ResponseEntity.ok().body("Result : " + result);
    }

    private boolean isDouble(String str)
    {
        try{
            Double.parseDouble(str);
            return true;
        }catch(NumberFormatException e)
        {
            return false;
        }
    }
}

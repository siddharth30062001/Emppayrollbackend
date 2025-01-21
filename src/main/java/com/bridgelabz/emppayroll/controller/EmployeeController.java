package com.bridgelabz.emppayroll.controller;


import com.bridgelabz.emppayroll.dto.RequestDTO;
import com.bridgelabz.emppayroll.dto.ResponseDTO;
import com.bridgelabz.emppayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @PostMapping("/addEmp")
    public ResponseDTO addEmployee(@RequestParam("imageUrl") String imageUrl,@RequestBody RequestDTO requestDTO){
        return employeeService.addEmployee(imageUrl,requestDTO);
    }

    @GetMapping("/getAllEmp")
    public List<ResponseDTO> getAllEmployees(){

        return employeeService.getAllEmployee();
    }

    @GetMapping("/getEmp/{id}")
    public ResponseDTO getEmployeeById(@PathVariable Long id){

        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/deleteEmp/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id){

        String message=employeeService.deleteById(id);

        return ResponseEntity.ok(message);
    }

    @PutMapping("/updateEmp/{id}")
    public ResponseDTO updateEmployee(@PathVariable Long id, @RequestBody RequestDTO requestDTO,@RequestParam("imageUrl") String imageUrl){

        return  employeeService.updateById(id,requestDTO,imageUrl);

    }
}

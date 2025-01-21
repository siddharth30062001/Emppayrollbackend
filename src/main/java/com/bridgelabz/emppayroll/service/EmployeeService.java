package com.bridgelabz.emppayroll.service;


import com.bridgelabz.emppayroll.dto.RequestDTO;
import com.bridgelabz.emppayroll.dto.ResponseDTO;
import com.bridgelabz.emppayroll.model.Employee;
import com.bridgelabz.emppayroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    public UserRepository userRepository;

    public ResponseDTO addEmployee(String imageUrl,RequestDTO requestDTO) {

        Employee employee=new Employee();
        employee.setName(requestDTO.getName());
        employee.setProfileImage(imageUrl);
        employee.setGender(requestDTO.getGender());
        employee.setSalary(requestDTO.getSalary());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setStartDate(requestDTO.getStartDate());
        employee.setNotes(requestDTO.getNotes());

        return mapToDTO(userRepository.save(employee));

    }

    private ResponseDTO mapToDTO(Employee employee) {
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setId(employee.getId());
        responseDTO.setName(employee.getName());
        responseDTO.setProfileImage(employee.getProfileImage());
        responseDTO.setGender(employee.getGender());
        responseDTO.setStartDate(employee.getStartDate());
        responseDTO.setSalary(employee.getSalary());
        responseDTO.setNotes(employee.getNotes());
        responseDTO.setDepartment(employee.getDepartment());

        return responseDTO;
    }

    public List<ResponseDTO> getAllEmployee() {

        return userRepository.findAll().stream().map((emp)->new ResponseDTO(emp.getId(), emp.getName(), emp.getProfileImage(), emp.getGender(), emp.getSalary(), emp.getDepartment(),emp.getStartDate(),emp.getNotes()))
                .collect(Collectors.toList());
    }

    public ResponseDTO getEmployeeById(Long id) {
        Employee employee= userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id not found"));
        return mapToDTO(employee);
    }

    public String deleteById(Long id) {

        Employee employee= userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id not found"));
        userRepository.deleteById(id);
        return "Employee Deleted!!!";

    }

    public ResponseDTO updateById(Long id, RequestDTO requestDTO,String imageUrl) {
        Employee employee= userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id not found"));
        employee.setName(requestDTO.getName());
        employee.setProfileImage(imageUrl);
        employee.setDepartment(requestDTO.getDepartment());
        employee.setStartDate(requestDTO.getStartDate());
        employee.setNotes(requestDTO.getNotes());
        employee.setSalary(requestDTO.getSalary());
        employee.setGender(requestDTO.getGender());
        userRepository.save(employee);

        return mapToDTO(employee);

    }
}

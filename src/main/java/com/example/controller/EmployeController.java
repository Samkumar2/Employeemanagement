package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Exception.UserNotFoundException;
import com.example.entity.EmployeEntity;
import com.example.service.EmployeService;
@RequestMapping("/employee")
@RestController
public class EmployeController {

    @Autowired
    private EmployeService employeService;

     @PostMapping("/addemploye")
    public EmployeEntity create(@RequestBody EmployeEntity employe) {
        return employeService.create(employe);
    }

    @GetMapping("/get")
    public List<EmployeEntity> get(){
        return employeService.get();
    }
    @GetMapping("/get/{id}")
    
        public Optional<EmployeEntity> getEmployeEntityById(@PathVariable("id")Long id){
            try{
                return employeService.getemEmployeEntityById(id);
            } catch(UserNotFoundException ex ){
                throw new  ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
            }
                  
        }
    
    

    @GetMapping("/name/{name}/age/{age}") 
    public List<EmployeEntity> getNameAndAge(@PathVariable("name") String name,@PathVariable("age") Integer age){
        return employeService.getNameAndAge(name,age);
    
    }
    @PutMapping("/update/{id}")
public EmployeEntity update(@PathVariable("id") Long id, @RequestBody EmployeEntity updatedEmploye) {
    try{
         return employeService.update(id, updatedEmploye);
    }
    catch(UserNotFoundException ex){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
   
}
@DeleteMapping("/delete/{id}")
 public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        boolean deleted = employeService.delete(id);

        if (deleted) {
            return new ResponseEntity<>("Employee with ID " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}


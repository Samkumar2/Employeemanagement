package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exception.UserNotFoundException;
import com.example.entity.EmployeEntity;
import com.example.repository.EmployeRepository;
@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;
    
    public EmployeEntity create(EmployeEntity employe) {
        return employeRepository.save(employe);
    }

    public List<EmployeEntity> get(){
        return employeRepository.findAll();
    }
    
    public Optional<EmployeEntity> getemEmployeEntityById(Long id ) throws UserNotFoundException{
        Optional<EmployeEntity> employeEntity = employeRepository.findById(id);

            if(!employeEntity.isPresent()){
                throw new UserNotFoundException("User not found in the Repository");
            }
        return employeEntity;
    }
    

    public List<EmployeEntity> getNameAndAge(String name,Integer age){
        return employeRepository.findByNameAndAge(name,age);
    }

    public EmployeEntity update(Long id, EmployeEntity employeEntity)throws UserNotFoundException {
       Optional<EmployeEntity> OptionalEmployeEntity = employeRepository.findById(id);

            if(!OptionalEmployeEntity.isPresent()){
                throw new UserNotFoundException("User not found in the Repository enter the correct id enter correct id");
            }
            employeEntity.setId(id);
            return employeRepository.save(employeEntity);

    }
  


public boolean delete(Long id) {
        try {
            employeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            
            return false;
        }
    }
}

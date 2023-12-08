package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public Optional<EmployeEntity> getemEmployeEntityById(Long id ){
        Optional<EmployeEntity> employeEntity = employeRepository.findById(id);

        return employeEntity;
    }
    

    public List<EmployeEntity> getNameAndAge(String name,Integer age){
        return employeRepository.findByNameAndAge(name,age);
    }

    public EmployeEntity update(Long id, EmployeEntity updatedEmploye) {
        EmployeEntity existingEmploye = employeRepository.findById(id).orElse(null);
        if (existingEmploye != null) {
            
            existingEmploye.setName(updatedEmploye.getName());
            existingEmploye.setAge(updatedEmploye.getAge());

            return employeRepository.save(existingEmploye);

    }
    return null;

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

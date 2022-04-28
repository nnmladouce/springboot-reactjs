package de.ladouce.customerapi.controller;

import org.springframework.web.bind.annotation.*;
import de.ladouce.customerapi.entity.CustVPI;
import de.ladouce.customerapi.repository.CustVIPRepository;
import de.ladouce.customerapi.exception.CustNotFoundException;


import java.util.List;

@RestController
@RequestMapping("/custVPI")
public class CustVPIController {

    private CustVIPRepository  custVIPRepository;

    public CustVPIController(CustVIPRepository custVIPRepository) {
        this.custVIPRepository = custVIPRepository;
    }

    @GetMapping("")
    public List<CustVPI> all(){
        return custVIPRepository.findAll();
    }

    @GetMapping("{id}")
    CustVPI oneCustVPI(@PathVariable Long id){
        return custVIPRepository.findById(id).orElseThrow(() -> new CustNotFoundException(id));
    }

    @PostMapping("")
    CustVPI newCustVPI(@RequestBody CustVPI newCustVPI){
        return custVIPRepository.save(newCustVPI);
    }

    @PutMapping("/{id}")
    CustVPI replaceCustVPI(@RequestBody CustVPI newCustVPI, @PathVariable Long id){
        return custVIPRepository.findById(id).map(custVPI -> {
           custVPI.setName(newCustVPI.getName());
           custVPI.setPriority(newCustVPI.getPriority());
           return custVIPRepository.save(custVPI);
        }).orElseGet(() -> {
            newCustVPI.setId(id);
            return  custVIPRepository.save(newCustVPI);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        custVIPRepository.deleteById(id);
    }




}

package de.ladouce.customerapi.controller;

import de.ladouce.customerapi.entity.Customer;
import de.ladouce.customerapi.exception.CustNotFoundException;
import de.ladouce.customerapi.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public int customerNumber(){
        return customerRepository.findAll().size();
    }

    @GetMapping("")
    public List<Customer> findAll(){
        return  customerRepository.findAll();
    }

    @GetMapping("{id}")
    public Customer findOne(@PathVariable Long id){
        return customerRepository.findById(id).orElseThrow(() -> new CustNotFoundException(id));
    }

    @PostMapping("")
    public Customer createNewCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomer){
        return customerRepository.findById(id).map(customer -> {
           //customer.setId(newCustomer.getId());
           customer.setFirstname(newCustomer.getFirstname());
           customer.setLastname(newCustomer.getLastname());
           return customerRepository.save(customer);
        }).orElseGet(() -> {
            newCustomer.setId(id);
            return customerRepository.save(newCustomer);
        });

    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
    }

}

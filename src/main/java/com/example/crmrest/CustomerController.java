package com.example.crmrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    public CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        try{
            return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        try{
            if(customerList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerList,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
            List<Customer> customerList ;
            customerList = customerRepository.findAll();
            ResponseEntity<Customer> return__ = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            try{
                if (customerList.isEmpty())
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                for (Customer c : customerList) {
                    if (c.getId() == id) {
                        return__ = new ResponseEntity<>(c, HttpStatus.OK);

                    }
                }

                return return__;
            }catch(Exception e){
                return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable("id") int id){
        try{
            Optional<Customer> customerOptional = customerRepository.findById(id);
            if(customerOptional.isPresent()){
                customerOptional.get().setName(customer.getName());
                customerOptional.get().setAge(customer.getAge());
                customerOptional.get().setLocation(customer.getLocation());
                customerOptional.get().setPhone(customer.getPhone());
            }
            return new ResponseEntity<>(customerRepository.save(customerOptional.get()), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        ResponseEntity<Customer> return__ ;
        if(customerOptional.isPresent()){
            customerRepository.delete(customerOptional.get());
            return__ = new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return__ = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return return__;
    }
}

package com.venkatesh.ai_crm_platform.Controller;


import com.venkatesh.ai_crm_platform.Service.CustomerService;
import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id){
        return customerService.getById(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id,
                           @RequestBody Customer customer){
        return customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        customerService.delete(id);
        return "Customer Deleted Successfully";
    }
}
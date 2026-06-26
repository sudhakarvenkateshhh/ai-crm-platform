package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.CustomerRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer getById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer Not Found"));
    }

    public Customer update(Long id, Customer customer){

        Customer existing = getById(id);

        existing.setName(customer.getName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        existing.setCompanyName(customer.getCompanyName());
        existing.setAddress(customer.getAddress());

        return customerRepository.save(existing);
    }

    public void delete(Long id){
        customerRepository.deleteById(id);
    }
}
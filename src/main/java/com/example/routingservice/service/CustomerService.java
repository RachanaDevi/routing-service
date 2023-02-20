package com.example.routingservice.service;

import com.example.routingservice.entity.ConsultantAvailability;
import com.example.routingservice.entity.Customer;
import com.example.routingservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).orElse(dummyCustomer());
    }

    private Customer dummyCustomer() {
        return new Customer(101L, "dummy customer", "Pune", "1234567890");
    }
}

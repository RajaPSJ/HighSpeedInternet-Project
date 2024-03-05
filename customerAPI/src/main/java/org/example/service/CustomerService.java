package org.example.service;

import org.example.entity.Customer;

import java.util.List;

public interface CustomerService {

    String checkEligibility(Customer customer);
    List<Customer> getAll();
}

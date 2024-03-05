package org.example.service.impl;

import org.example.entity.AreaServiceProvider;
import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.AreaServiceProviderService;
import org.example.service.CustomerService;
import org.example.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private AreaServiceProviderService areaServiceProviderService;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String checkEligibility(Customer customer) {
        try {
            ValidationUtil.validateCustomer(customer);
            AreaServiceProvider areaServiceProvider = areaServiceProviderService.getServiceProvider(customer.getZipcode());
            if (areaServiceProvider != null) {
                customerRepository.save(customer);
                return areaServiceProvider.getLogo();
            } else {
                return null;
            }
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}

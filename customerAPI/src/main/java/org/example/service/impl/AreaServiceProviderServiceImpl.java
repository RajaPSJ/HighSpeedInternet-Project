package org.example.service.impl;

import org.example.entity.AreaServiceProvider;
import org.example.repository.AreaServiceProviderRepository;
import org.example.service.AreaServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceProviderServiceImpl implements AreaServiceProviderService {

    @Autowired
    private AreaServiceProviderRepository areaServiceProviderRepository;

    @Override
    public AreaServiceProvider getServiceProvider(Long zipcode) {
        return areaServiceProviderRepository.getById(zipcode);
    }
}

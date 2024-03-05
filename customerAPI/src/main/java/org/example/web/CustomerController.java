package org.example.web;

import org.example.entity.Customer;
import org.example.exception.CustomerApiException;
import org.example.service.AreaServiceProviderService;
import org.example.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

/**
 * This rest controller provides end points related to customer services
 */
@RestController
@RequestMapping({"/customer"})
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AreaServiceProviderService areaServiceProviderService;

    /**
     * Check internet eligibility of the provided customer
     *
     * @param customer Customer details
     * @return logo of the ISP if not null
     */
    @CrossOrigin
    @PostMapping(value = "/check", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> checkEligibility(@RequestBody Customer customer) {
        LOGGER.info("Request received to check customer eligibility. [{}]", customer);
        try {
            String serviceProviderLogo = customerService.checkEligibility(customer);
            LOGGER.info("Successfully retrieved the ISP logo. [{}]", serviceProviderLogo);

            if (serviceProviderLogo != null) {
                // Load the image from the resource directory
                ClassPathResource classPathResource = new ClassPathResource(serviceProviderLogo);
                // Check if the resource exists
                if (!classPathResource.exists()) {
                    throw new IOException("Image not found in the resource directory");
                }

                // Read the image content into a byte array
                byte[] imageBytes;
                try (InputStream inputStream = classPathResource.getInputStream()) {
                    imageBytes = FileCopyUtils.copyToByteArray(inputStream);
                }

                return new ResponseEntity<>(Base64.getEncoder().encodeToString(imageBytes), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (CustomerApiException | IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "")
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }
}

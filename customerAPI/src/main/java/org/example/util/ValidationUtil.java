package org.example.util;

import org.example.entity.Customer;
import org.example.exception.CustomerApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ValidationUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtil.class);
    public static void validateCustomer(Customer customer) {
        LOGGER.info("Validating the customer info..");
        if (customer == null) {
            throw new CustomerApiException(HttpStatus.BAD_REQUEST, "Invalid customer info");
        }
        validateCustomerName(customer);
        validatePhoneNumber(customer);
    }

    private static void validateCustomerName(Customer customer) {
        String name = customer.getName();
        String regex = "^[a-zA-Z'-]+$";
        if (name == null || !name.matches(regex)) {
            LOGGER.error("Invalid customer name is provided.");
            throw new CustomerApiException(HttpStatus.BAD_REQUEST, "Customer name is invalid. name can contains only letters, '-' and '''");
        }
    }

    private static void validatePhoneNumber(Customer customer) {
        if (customer.getPhoneNumber().length() != 10 ) {
            LOGGER.error("Phone number is invalid.");
            throw new CustomerApiException(HttpStatus.BAD_REQUEST, "Phone number is invalid.");
        }
    }
}

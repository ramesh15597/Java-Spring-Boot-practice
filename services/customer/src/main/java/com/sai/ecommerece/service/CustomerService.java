package com.sai.ecommerece.service;

import com.sai.ecommerece.dto.CustomerRequest;
import com.sai.ecommerece.dto.CustomerResponse;
import com.sai.ecommerece.exceptionhandler.CustomerNotFoundException;
import com.sai.ecommerece.model.Customer;
import com.sai.ecommerece.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public String createCustomer(CustomerRequest request) {

        Customer customer = Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
        repository.save(customer);

        return request.id();

    }

    public String updateCustomer(@Valid CustomerRequest request) {

        Customer customer = repository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
                format("Customer id " + request.id() + " Not Found")

        ));

        mergeCustomer(customer, request);
        repository.save(customer);
        return "Customer id " + request.id() + "Updated";
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest request) {

        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (null != request.address()) {
            customer.setAddress(request.address());
        }

    }

    public List<CustomerResponse> getCustomers() {


        return repository.findAll().stream().map(

                a -> new CustomerResponse(a.getId(), a.getFirstName(),
                        a.getLastName(),
                        a.getEmail(),
                        a.getAddress()
                )

        ).toList();
    }

    public Boolean existsById(String customerId) {
        return repository.existsById(customerId);
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId).map(
                a -> new CustomerResponse(
                        a.getId(),
                        a.getFirstName(),
                        a.getLastName(),
                        a.getEmail(),
                        a.getAddress()

                )


        ).orElseThrow(() -> new CustomerNotFoundException(
                format("Customer id " + customerId + " Not Found")

        ));
    }

    public String deleteCustById(String customerId) {
        Boolean flag = existsById(customerId);

        if(flag){
            repository.deleteById(customerId);
            return format("Customer id " + customerId + " deleted !!!");
        }else {
            return format("Customer id " + customerId + " Not Found");
        }

    }
}

package com.sai.ecommerece.repository;

import com.sai.ecommerece.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {

}

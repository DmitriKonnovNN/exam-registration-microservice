package io.dmitrikonnov.customer;

import io.dmitrikonnov.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    }

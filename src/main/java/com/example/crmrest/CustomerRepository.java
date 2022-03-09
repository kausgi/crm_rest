package com.example.crmrest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public List<Customer> findByName(String name);
}

package com.example.spring08fpt.service;

import com.example.spring08fpt.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements InterCustomerService {
    private static Map<Integer, Customer> customers;

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
}

package lk.ijse.dep11.SpringAngular.service;

import lk.ijse.dep11.SpringAngular.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();
}

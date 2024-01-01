package lk.ijse.dep11.SpringAngular.service;

import lk.ijse.dep11.SpringAngular.model.Customer;
import lk.ijse.dep11.SpringAngular.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {

        //Check if the customer with the given ID already exists
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());

        if(existingCustomer.isPresent()){
            throw new RuntimeException("Customer with ID " + customer.getId() +  " already exists");
        }
        return customerRepository.save(customer);
    }
}

package lk.ijse.dep11.SpringAngular.service;

import lk.ijse.dep11.SpringAngular.model.Customer;
import lk.ijse.dep11.SpringAngular.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        //Check contact number is unique
        if(customerRepository.findByContact(customer.getContact()).isPresent()){
            throw new RuntimeException("Customer with ID " + customer.getContact() +  " already exists");
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void updateCustomer(String id, Customer customer) {
        //Check if the customer with the given ID exists
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isEmpty()){
            throw new RuntimeException("Customer with ID " + id + " not found");
        }
        //Check if the updated contact number is unique(excluding the current customer)
        Optional<Customer> customerWithUpdateContact = customerRepository.findByContact(customer.getContact());
        if(customerWithUpdateContact.isPresent() && !customerWithUpdateContact.get().getId().equals(id)){
            throw new RuntimeException("Another customer with contact number " + customer.getContact() + " already exists");
        }
        //Update only non-null fields
        Customer currentCustomer = existingCustomer.get();
        if(customer.getName() != null){
            currentCustomer.setName(customer.getName());
        }
        if(customer.getAddress() != null){
            currentCustomer.setAddress(customer.getAddress());
        }
        if(customer.getContact() != null){
            currentCustomer.setContact(customer.getContact());
        }

        customerRepository.save(customer);


    }

    @Override
    public void deleteCustomer(String id) {
        //Check if the customer is exists
        Optional<Customer> existingCustomer = customerRepository.findById(id);

        if(existingCustomer.isEmpty()){
            throw new RuntimeException("Customer with ID " + id + " not found");
        }
        customerRepository.deleteById(id);

    }
}

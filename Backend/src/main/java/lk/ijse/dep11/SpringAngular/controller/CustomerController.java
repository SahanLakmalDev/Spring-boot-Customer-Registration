package lk.ijse.dep11.SpringAngular.controller;

import lk.ijse.dep11.SpringAngular.model.Customer;
import lk.ijse.dep11.SpringAngular.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    //Save a new customer
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Customer saveCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.saveCustomer(customer);
        return savedCustomer;
    }

    //Get all the customers
    @GetMapping(produces = "application/json")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    //Update the customer
    @PatchMapping(value = "/{id}", consumes = "application/json")
    public void updateCustomer(@PathVariable String id, @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
    }

    //Delete customer
    @DeleteMapping(value = "/{id}")
    public void deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
    }


}

package lk.ijse.dep11.SpringAngular.controller;

import lk.ijse.dep11.SpringAngular.model.Customer;
import lk.ijse.dep11.SpringAngular.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}

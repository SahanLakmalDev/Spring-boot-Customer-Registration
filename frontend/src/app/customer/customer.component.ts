import { Component, OnInit } from '@angular/core';
import { Customer, CustomerService } from '../customer.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.scss'
})
export class CustomerComponent implements OnInit{

  customers:Customer[] = [];
  newCustomer:any = {};
  editingCustomer:any = null;
  customerForm: FormGroup;

  constructor(private customerService: CustomerService, private fb: FormBuilder) {
    this.customerForm = this.fb.group({
      customer_id: ['', [Validators.required, Validators.minLength(4), Validators.pattern('C[0-9]+')]],
      customer_name: ['', [Validators.required, Validators.pattern('[A-Za-z ]+')]],
      customer_address: ['', [Validators.required, Validators.minLength(4)]],
      customer_contact: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers():void{
    this.customerService.getAllCustomers().subscribe((data:any) => {
      console.log('Data received');
      this.customers = data;
    });
  }

  addCustomer():void {
    if(this.editingCustomer){
      // Edit the existing customer details
      this.customerService.updateCustomer(this.editingCustomer.id, this.newCustomer).subscribe(() => {
        window.alert("Customer updated successfully");
        this.resetForm();
        this.loadCustomers();
      },
      error => {
        alert("Error updating customer ");
      }
    );

    }else{
      // Add a new customer to the database
      this.customerService.addCustomer(this.newCustomer).subscribe(()=>{
        window.alert("Customer added successfully");
        this.resetForm();
        this.loadCustomers();

      },
      error => {
        alert("Error adding customer ");
      }
    );
    }
  }
  editCustomer(customer:Customer):void {
    this.editingCustomer = {...customer};
    this.newCustomer = this.editingCustomer;
  }
  deleteCustomer(customer:Customer):void {
    let confirmation = window.confirm("Are you sure you want to delete this customer?");
    if (confirmation == true) {
      this.customerService.deleteCustomer(customer.id).subscribe(()=>{
        this.loadCustomers();
      });
    }
  }
  resetForm() :void {
    this.newCustomer = {};
    this.editingCustomer=null;
  }



}

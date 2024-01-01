import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export type Customer = {
  id: string;
  name: string;
  address:string,
  contact:string
}

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiUrl = 'http://localhost:8080/api/v1/customers';
  
  constructor(private http:HttpClient) { }

  getAllCustomers():Observable<Customer[]>{
    return this.http.get<Customer[]>(this.apiUrl);
  }
  addCustomer(customer:Customer):Observable<Customer>{
    return this.http.post<Customer>(this.apiUrl, customer);
  }
  updateCustomer(id:string, customer:Customer):Observable<Customer>{
    return this.http.patch<Customer>(`${this.apiUrl}/${id}`, customer);
  }
  deleteCustomer(id:string):Observable<any>{
    return this.http.delete(`${this.apiUrl}/${id}`);
  }


}

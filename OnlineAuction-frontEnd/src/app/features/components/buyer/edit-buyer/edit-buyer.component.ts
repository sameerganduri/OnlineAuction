import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BuyerService } from 'src/app/features/services/buyer.service'; 
import { CustomerInfo } from 'src/app/features/models/customer-info.model'; 

@Component({
  selector: 'app-edit-buyer',
  templateUrl: './edit-buyer.component.html',
//  styleUrls: ['./edit-buyer.component.css']
})
export class EditBuyerComponent implements OnInit {
  customerForm!: FormGroup;
  customerId!: number;
  customerInfo!:CustomerInfo;
  constructor(private fb: FormBuilder, private customerService: BuyerService) { }

  ngOnInit(): void {
    this.customerForm = this.fb.group({
      customerId: [0],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
   
    this.customerId=Number(localStorage.getItem('customerId'))
    this.loadCustomerDetails(this.customerId);

    
  }

  loadCustomerDetails(customerId: number): void {
    this.customerService.getCustomerByCustomerId(customerId).subscribe(
      (customerInfo: CustomerInfo) => {
        this.customerInfo = customerInfo;
        this.customerForm.patchValue(this.customerInfo)
        
      },
      (error: any) => {
        console.error('Error fetching customer details:', error);
      }
    );
  }
  


  onSubmit() {
    if (this.customerForm.valid) {
      const customerInfo: CustomerInfo = this.customerForm.value;
      this.customerService.createOrUpdateCustomer(customerInfo).subscribe((response) => {
        console.log('Customer created/updated successfully:', response);
        
      }, (error) => {
        console.error('Error while creating/updating customer:', error);
      
      });
    }
  }
}

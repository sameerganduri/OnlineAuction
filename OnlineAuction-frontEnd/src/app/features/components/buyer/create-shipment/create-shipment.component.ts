import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ShippingInfo } from 'src/app/features/models/shipment_info.model';

@Component({
  selector: 'app-create-shipment',
  templateUrl: './create-shipment.component.html',
  styleUrls: ['./create-shipment.component.scss']
})
export class CreateShipmentComponent implements OnInit {
  shipmentForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.shipmentForm = this.formBuilder.group({
      shippingAddress: ['', [Validators.required]],
      shippingMethod: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {



  }

  onSubmit(): void {
    if (this.shipmentForm.valid) {
      const shippingInfo: ShippingInfo = this.shipmentForm.value;
     
      console.log(shippingInfo);
    }
  }

  redirectToShipping(productId: number): void {
    this.router.navigate(['Buyer/Shipping'], { queryParams: { productId: productId } });
  }



}

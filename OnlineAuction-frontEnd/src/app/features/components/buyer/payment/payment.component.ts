import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BuyerService } from 'src/app/features/services/buyer.service';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { PaymentInfo } from 'src/app/features/models/payment-info';
import { SellerService } from 'src/app/features/services/seller.service';
import { ProductInfo } from 'src/app/features/models/product-info.model';
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {
  paymentForm!: FormGroup;
  customerId: any;
routeProd!:Number;
products:ProductInfo[]=[];
  constructor(private formBuilder: FormBuilder, private buyerService: BuyerService, private sellerService:SellerService,private toastr: ToastrService, private route: ActivatedRoute,private router :Router) { }

  ngOnInit(): void {
    this.paymentForm = this.formBuilder.group({
      paymentId: [0],
      productId: [],
      customerId: [],
      paymentMethod: ['', Validators.required],
      paymentStatus: ['', Validators.required],
    
    });
    this.customerId=localStorage.getItem('customerId')
    this.route.queryParams.subscribe(params => {
      const productId = params['productId'];
      // this.routeProd=productId;
      this.getProducts();
      this.paymentForm.patchValue({
        'productId': productId,
        'customerId': this.customerId
      });
      console.log(productId,this.customerId)
    });
    
  }

  onSubmit(): void {

    this.paymentForm.patchValue({
      'customerId':this.customerId
    })
    if (this.paymentForm.valid) {
      const paymentInfo: PaymentInfo = this.paymentForm.value;
      console.log(paymentInfo);

      this.buyerService.createOrUpdatePayment(paymentInfo).subscribe(
        response => {
          console.log(response);
          this.toastr.success('Payment processed successfully!');
         this.redirectToShipping()
        },
        error => {
          console.error(error);
          this.toastr.error('Error while processing payment. Please try again!');
        }
      );
    }
  }

  redirectToShipping(): void {
    const product = this.routeProd
        this.router.navigate(['Buyer/Shipment'], { queryParams: { productId: product } });
  }
    
  getProducts(): void {
    //const buyerId = localStorage.getItem('customerId');
    this.buyerService.getProducts().subscribe(
      (response: any) => {
        this.products = response.data;
        console.log("in getprod", this.products);
      },
      (error) => {
        console.log(error);
      }
    );
  }


}

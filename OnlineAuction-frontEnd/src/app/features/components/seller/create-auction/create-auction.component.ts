import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormControl, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CategoryInfo } from 'src/app/features/models/category-info.model';
import { ProductInfo } from 'src/app/features/models/product-info.model';
import { BuyerService } from 'src/app/features/services/buyer.service';
import { SellerService } from 'src/app/features/services/seller.service';

@Component({
  selector: 'app-create-auction',
  templateUrl: './create-auction.component.html',
  styleUrls: ['./create-auction.component.scss']
})
export class CreateAuctionComponent implements OnInit {

auctionForm!:FormGroup;
auctionId!:Number;
productId!:Number;
sellerId!:Number;
dateTime = new Date();
products:ProductInfo[]=[];
constructor(private sellerService:SellerService,private fb:FormBuilder,private activatedRoute:ActivatedRoute,private buyerService:BuyerService){


}


  ngOnInit(): void {
    
   
    
    
    this.productId= this.activatedRoute.snapshot.queryParams['productId']
    this.sellerId = Number(localStorage.getItem('sellerId'))
    this.createAuctionValidation()
    this.auctionForm.patchValue({
      'productId':this.productId
    })
    this.getProducts();


    console.log(this.products)
  }

  createAuctionValidation() {
    this.auctionForm = this.fb.group({
      auctionId: [0],
      productId: [0],
      auctionEndTime: new FormControl(new Date()), 
      auctionStartTime:new FormControl(new Date()),
           auctionStatus: ['Y'],
    });
  }
  

  onSubmit(): void {
 //   this.auctionForm.value['productId'] = 0
  const prodId=Number(this.auctionForm.get('productId'))  
  const endTime=new Date(this.auctionForm.get('auctionEndTime')?.value)
 
   this.auctionForm.patchValue({
  'sellerId':this.sellerId,
  'productId':4,
  'auctionEndTime':endTime

 })
 console.log("auction from value",this.auctionForm.value)
    this.sellerService.createOrUpdateAuction(this.auctionForm.value).subscribe(response => {
      console.log(response);
    });
  }


  getProducts(): void {
    const sellerId = localStorage.getItem('sellerId');
    this.sellerService.getProductBySellerId(Number(sellerId)).subscribe(
      (response: ProductInfo[]) => {
        this.products = response;
        console.log("in getprod",this.products)
      },
      (error) => {
        console.log(error);
      }
    );
  }


 

}




import { Component } from '@angular/core';
import { ProductInfo } from '../../models/product-info.model';
import { ActivatedRoute, Router } from '@angular/router';
import { SellerService } from '../../services/seller.service';
import { ToastrService } from 'ngx-toastr';
import { SellerInfo } from '../../models/seller-info.model';
@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.scss']
})
export class SellerComponent {


  products: ProductInfo[] = [];
  searchTerm: string = '';
  selectedProduct: ProductInfo | null = null;
  showProductDetails: boolean = false;
  bidAmount!:number;
  sellerEmail!:String;
  bidAmounts: Map<number, number> = new Map<number, number>();
  user_data:any;
  customerId!: number;
  bidAmountMap: {[productId: number]: number} = {};
  loggedInSeller !:SellerInfo;
  sellerId!:number;
  constructor(private sellerService: SellerService, private route: ActivatedRoute,private toastr: ToastrService,private router:Router,) { }

  ngOnInit():void{
    
    this.user_data = localStorage.getItem('user_data');
    console.log(this.user_data)
    const user = JSON.parse(this.user_data);
    this.sellerEmail = user.userName
    console.log(this.sellerEmail)
    this.getSeller()
    this.sellerId=user.sellerId
   
//this.getSeller();
  }


  getSeller():void{
    this.sellerService.getSellerByEmailId(this.sellerEmail).subscribe(seller=>{
      this.products = seller.products
      localStorage.setItem('sellerId',seller.sellerId.toString())
   
    })
  }


  createOrEditProduct(productId:number):void{
    const email= this.sellerEmail
  this.router.navigate(['Seller'+'/CreateOrEditProduct'],{
    queryParams:{productId:productId,email:email}
  })
  
  }
  createOrEditAuction(productId:number):void{
    const email= this.sellerEmail
  this.router.navigate(['Seller'+'/CreateAuction'],{
    queryParams:{productId:productId,email:email}
  })}
  
}


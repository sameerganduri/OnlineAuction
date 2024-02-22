import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CategoryInfo } from 'src/app/features/models/category-info.model';
import { ProductInfo } from 'src/app/features/models/product-info.model';
import { SellerService } from 'src/app/features/services/seller.service';
@Component({
  selector: 'app-create-edit-product',
  templateUrl: './create-edit-product.component.html',
  styleUrls: ['./create-edit-product.component.scss']
})

export class CreateEditProductComponent implements OnInit {
  productForm!: FormGroup;
    categoriesList: CategoryInfo[]=[];
    productId!:number;
    sellerEmail!:String;
    user_data:any;
    products!: ProductInfo[];
    product?:ProductInfo;
    categoryId!:number
sellerId!:Number

  constructor(private toastr: ToastrService,private sellerService: SellerService,private fb:FormBuilder,private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
   this.productId= this.activatedRoute.snapshot.queryParams['productId']
   this.user_data = localStorage.getItem('user_data');
  //  this.categoryId =
   this.sellerEmail = this.activatedRoute.snapshot.queryParams['email']
   
   this.sellerId = Number(localStorage.getItem('sellerId'))
   console.log(this.sellerId)




   if(this.productId!=0){
    console.log(this.productId)
   
    console.log( 'current product',this.product)
    this.getCategories();
    this.getSeller();
 
  }

    this.createProductValidation();
    // this.getCategories();
    // this.getSeller();

  }

  getCategories(): void {
     this.sellerService.getAllCategorys()
       .subscribe(

        {
          next:(resp:any)=>{
            this.categoriesList = resp.data
          console.log(this.categoriesList)
          }
        }
      
       )
  
      
}


getSeller(): void {
  this.sellerService.getSellerByEmailId(this.sellerEmail).subscribe(
{ next:(resp:any)=>{

  let sellerDetails = resp
  let productDetails =sellerDetails.products
  let productId = this.productId
  this.sellerId = sellerDetails.sellerId
  console.log(productDetails)
  productDetails.forEach(
    function(valueById:any){
if(productId == valueById['productId']){
// console.log(productId)
// console.log(valueById['productId'])
  productDetails = valueById;

  }
  
})


this.productForm.patchValue(productDetails)


} });
  };

createProductValidation(){

  this.productForm=this.fb.group(
    {productId:[0],
    productName:[],
    description:[],
    startingPrice:[],
    imageUrl:[],
    categoryId:[0],
    sellerId:[0],
    categories:[],
    
    }
  )
  

}

  onSubmit(): void {
    
 
    this.productForm.patchValue({productId:this.productId})
    this.productForm.patchValue({sellerId:this.sellerId})
    
    console.log("form values sent",this.productForm.value)
    
    this.sellerService.createOrUpdateProduct(this.productForm.value).subscribe(response => {
      console.log(response);
    });

    this.toastr.success("YOU HAVE WINNING BIDS PLEASE PAY")

    this.router.navigate(['/'+'/Seller']
    )
  }



}

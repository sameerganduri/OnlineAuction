import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { BuyerComponent } from './features/components/buyer/buyer.component';
import { HomeComponent } from './general/home/home/home.component';
import { HeaderComponentComponent } from './general/header/header-component/header-component.component';
import { BuyerGuard } from './shared/guards/buyer.guard';
import { SellerComponent } from './features/components/seller/seller.component';
import { CreateEditProductComponent } from './features/components/seller/create-edit-product/create-edit-product.component';
//import { PaymentComponent } from './features/components/payment/payment.component';
import { CreateAuctionComponent } from './features/components/seller/create-auction/create-auction.component';
import { AdminComponent } from './features/components/admin/admin.component';
import { CreateEditCategoryComponent } from './features/components/seller/create-category/create-category.component';
import { CreateEditSellerComponent } from './features/components/seller/create-edit-seller/create-edit-seller.component';
import { EditBuyerComponent } from './features/components/buyer/edit-buyer/edit-buyer.component';
import { PaymentComponent } from './features/components/buyer/payment/payment.component';
import { CreateShipmentComponent } from './features/components/buyer/create-shipment/create-shipment.component';
const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path: ''  ,component:LoginComponent },
  
  
  
  {path:"Buyer",component:HomeComponent,
  children:[{
 path:'', component:BuyerComponent,
  },
  {
 path:'CreateOrEditBuyer',component:EditBuyerComponent,
  }
  ,{
    path:'Payment',component:PaymentComponent
  },{
    path:'Shipment',component:CreateShipmentComponent
  }
]
 
  
  //canActivate:[BuyerGuard],
},
 
{path :'CreateOrEditSeller', component:LoginComponent},
 { path:'home',component:HomeComponent,
  },

  {

    path:'Admin',
component:HomeComponent,children:[
{ 
  path:'CreateAuction',
  component:AdminComponent



},
{path:"CreateAuction",component:CreateAuctionComponent},
{path:"CreateOrEditProduct",component:CreateEditProductComponent}
,
{path:"CreateOrEditCategory",component:CreateEditCategoryComponent},
{path:"CreateOrEditSeller",component:CreateEditSellerComponent}


,{
  path:'CreateOrEditSeller'
  ,
  component:CreateEditSellerComponent
},


{
  path:'CreateOrEditBuyer',
  component:EditBuyerComponent
}
,
{
  path:'Payment',component:PaymentComponent
},{
  path:'Shipment',component:CreateShipmentComponent
}
]

  },
  
{
  path:"CreateOrEditProduct/:id",component:CreateEditProductComponent
}
,  {
    path:'header',component:HeaderComponentComponent
      }
,

{
  path:"Seller",component:HomeComponent,
  children:[
    { path:'',
      component:SellerComponent
    },
    {path:"CreateAuction",component:CreateAuctionComponent},
    {path:"CreateOrEditProduct",component:CreateEditProductComponent}
    ,
    {path:"CreateOrEditCategory",component:CreateEditCategoryComponent},
    {path:"CreateOrEditSeller",component:CreateEditSellerComponent}
 
  ]


},


 
// {
//   path:"Payment",component:PaymentComponent
// },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

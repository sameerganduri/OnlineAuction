import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import {HttpClientModule,HTTP_INTERCEPTORS} from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule,ToastContainerModule } from 'ngx-toastr';
import { NgxSpinnerModule } from 'ngx-spinner';
import { DatePipe } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SidenavComponentComponent } from './general/sidenav/sidenav-component/sidenav-component.component';
import { BuyerComponent } from './features/components/buyer/buyer.component';
import { CommonModule } from '@angular/common';
import { DEFAULT_TIMEOUT, TokenInterceptorInterceptor } from './shared/services/token-interceptor';
import { AuthService } from './shared/services/auth-service';
import { SellerComponent } from './features/components/seller/seller.component';
import { CreateEditProductComponent } from './features/components/seller/create-edit-product/create-edit-product.component';
import { CreateAuctionComponent } from './features/components/seller/create-auction/create-auction.component';
import { HomeComponent } from './general/home/home/home.component';
import { HeaderComponentComponent } from './general/header/header-component/header-component.component';
import { CreateEditCategoryComponent } from './features/components/seller/create-category/create-category.component';
import { CreateEditSellerComponent } from './features/components/seller/create-edit-seller/create-edit-seller.component';
import { EditBuyerComponent } from './features/components/buyer/edit-buyer/edit-buyer.component';
import { PaymentComponent } from './features/components/buyer/payment/payment.component';
import { CreateShipmentComponent } from './features/components/buyer/create-shipment/create-shipment.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SidenavComponentComponent,
    BuyerComponent
    ,SellerComponent,
    CreateEditProductComponent,
    CreateAuctionComponent,
    HomeComponent,
    HeaderComponentComponent,
    CreateEditCategoryComponent,
    CreateEditSellerComponent,
    EditBuyerComponent,
    PaymentComponent,
    CreateShipmentComponent
  ],
  imports: [
    
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    ToastContainerModule,
    NgxSpinnerModule,
    ToastrModule.forRoot({
      timeOut:1500,
      closeButton:true,
      positionClass:'toast-bottom-right',
      preventDuplicates:true,
      titleClass:'center',
      messageClass:'center'
    }),
  ],
  providers: [
    [
      {
        provide: DEFAULT_TIMEOUT,useValue:10000
      }
    ],
    AuthService,{
      provide:HTTP_INTERCEPTORS,
useClass:TokenInterceptorInterceptor,
multi:true
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

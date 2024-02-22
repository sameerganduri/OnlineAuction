import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductInfo } from '../models/product-info.model';
import { BidInfo } from '../models/bid-info.model';
import { ResponseStatus } from '../models/response-status';
import { CustomerInfo } from '../models/customer-info.model';
import { AuctionInfo } from '../models/auction-info.model';
import { PaymentInfo } from '../models/payment-info';
@Injectable({
  providedIn: 'root'
})
export class BuyerService {
  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) { }

  getProductsByCategoryId(categoryId: number): Observable<ProductInfo[]> {
    return this.http.get<ProductInfo[]>(`${this.apiUrl}/getProductsByCategoryId?categoryId=${categoryId}`);
  }

  searchProducts(searchTerm: string): Observable<ProductInfo[]> {
    const searchUrl = `${this.apiUrl}/search?searchTerm=${searchTerm}`;
    return this.http.get<ProductInfo[]>(searchUrl);
  }


  getProductDetails(productId: number): Observable<ProductInfo> {
    const productDetailsUrl = `${this.apiUrl}/getProductsById?productId=${productId}`;
    return this.http.get<ProductInfo>(productDetailsUrl);
  }

  getProducts(): Observable<ProductInfo[]> {
    const productDetailsUrl = `${this.apiUrl}/getProducts`;
    return this.http.get<ProductInfo[]>(productDetailsUrl);
  }
  

  createOrUpdateBid(bidInfo: BidInfo): Observable<ResponseStatus<BidInfo>> {
    const bidUrl = `${this.apiUrl}/createOrUpdateBid`;
    return this.http.post<ResponseStatus<BidInfo>>(bidUrl, bidInfo);
  }



  getCustomerByEmailId(emailId: String): Observable<CustomerInfo> {
    const url = `${this.apiUrl}/getCustomerByEmailId?emailId=${emailId}`;
    return this.http.get<CustomerInfo>(url);
  }
  
  getWinningBids(customerId: number): Observable<BidInfo[]> {
    const url = `${this.apiUrl}/winningBids?customerId=${customerId}`;
    return this.http.get<BidInfo[]>(url);
  }
  
  getAllActiveAuctions(): Observable<AuctionInfo[]> {
    const activeAuctionsUrl = `${this.apiUrl}/getActiveAuctions`;
    return this.http.get<AuctionInfo[]>(activeAuctionsUrl);
  }
  
  createOrUpdateCustomer(customer: CustomerInfo): Observable<CustomerInfo> {
    return this.http.post<CustomerInfo>(`${this.apiUrl}/createOrUpdateCustomerDetails`, customer);
  }

  getCustomerByCustomerId(customerId: number): Observable<CustomerInfo> {
    const url = `${this.apiUrl}/getCustomersByCustomerId?customerId=${customerId}`;
    return this.http.get<CustomerInfo>(url);
  }
  

  createOrUpdatePayment(payment: PaymentInfo): Observable<PaymentInfo> {
    const url = `${this.apiUrl}/createOrUpdatePaymentDetails`;
    return this.http.post<PaymentInfo>(url, payment);
  }


  
}

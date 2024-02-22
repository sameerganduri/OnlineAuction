import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoryInfo } from '../models/category-info.model';
import { BidInfo } from '../models/bid-info.model';
import { ResponseStatus } from '../models/response-status';
import { CustomerInfo } from '../models/customer-info.model';
import { AuctionInfo } from '../models/auction-info.model';
import { SellerInfo } from '../models/seller-info.model';
import { ProductInfo } from '../models/product-info.model';
@Injectable({
  providedIn: 'root'
})
export class SellerService {
  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) { }


  createOrUpdateCategory(category: CategoryInfo): Observable<CategoryInfo> {
    return this.http.post<CategoryInfo>(`${this.apiUrl}/createOrUpdateCategoryDetails`, category);
  }

  getAllCategorys(): Observable<CategoryInfo[]> {
    return this.http.get<CategoryInfo[]>(`${this.apiUrl}/getCategorys`);
  }


  getSellerByEmailId(emailId: String): Observable<SellerInfo> {
    const url = `${this.apiUrl}/getSellerByEmailId?emailId=${emailId}`;
    return this.http.get<SellerInfo>(url);
  }
  
  createOrUpdateProduct(product: ProductInfo): Observable<ProductInfo> {
    return this.http.post<ProductInfo>(`${this.apiUrl}/createOrUpdateProductDetails`, product);
  }

  createOrUpdateAuction(auction: AuctionInfo): Observable<AuctionInfo> {
    return this.http.post<AuctionInfo>(`${this.apiUrl}/createOrUpdateAuctionDetails`, auction);
  }

  getProductBySellerId(sellerId: number): Observable<ProductInfo[]> {
    return this.http.get<ProductInfo[]>(`${this.apiUrl}/getProductsBySellerId?sellerId=${sellerId}`);
  }
  

  createOrUpdateSeller(seller: SellerInfo): Observable<SellerInfo> {
    return this.http.post<SellerInfo>(`${this.apiUrl}/createOrUpdateSellerDetails`, seller);
  }
  


}

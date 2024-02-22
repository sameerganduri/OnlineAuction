import { ProductInfo } from "./product-info.model";

export class SellerInfo {
    sellerId!: number;
    firstName!: string;
    lastName!: string;
    email!: string;
    password!: string;
    products!: ProductInfo[];
    sellerRating!: number;
  }
  
import { AuctionInfo } from "./auction-info.model";

export class ProductInfo {
    productId!: number;
    productName!: string;
    description!: string;
    startingPrice!: number;
    imageUrl!: string;
    categoryId!: number;
    auctions!:AuctionInfo[]
}

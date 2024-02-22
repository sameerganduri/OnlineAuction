import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductInfo } from '../../models/product-info.model';
import { BuyerService } from '../../services/buyer.service';
import { CustomerInfo } from '../../models/customer-info.model';
import { BidInfo } from '../../models/bid-info.model';
import { AuctionInfo } from '../../models/auction-info.model';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
@Component({
  selector: 'app-buyer',
  templateUrl: './buyer.component.html',
  styleUrls: ['./buyer.component.scss']
})
export class BuyerComponent implements OnInit {
  products: ProductInfo[] = [];
  searchTerm: string = '';
  selectedProduct: ProductInfo | null = null;
  showProductDetails: boolean = false;
  bidAmount!:number;
  customerEmail!:string;
  loggedInCustomer!:CustomerInfo;
  bidAmounts: Map<number, number> = new Map<number, number>();
  user_data:any;
  customerId!: number;
  bidAmountMap: {[productId: number]: number} = {};
  winningBids : BidInfo[] = [];
  activeAuctions: AuctionInfo[] = [];

  constructor(private buyerService: BuyerService, private route: ActivatedRoute, private toastr: ToastrService, private router: Router) { }
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {

      this.customerId=Number(localStorage.getItem('customerId'))
      this.searchTerm = params['searchTerm'] || '';
      this.searchProducts();
      this.getAllActiveAuctions();
      this.getWinningBids();

      
if(this.winningBids.length != 0){
this.toastr.success("YOU HAVE WINNING BIDS PLEASE PAY")
}else{
  this.toastr.warning("NO WINNING BIDS YET")
}
    
    });


    this.user_data=localStorage.getItem('user_data');

    const user = JSON.parse(this.user_data);
    this.customerEmail =user.userName
    this.getCustomer();
  
  


  }

  searchProducts(): void {
    this.buyerService.searchProducts(this.searchTerm).subscribe(products => {
      this.products = products;
    });
  }

  viewProductDetails(productId: number): void {
    this.buyerService.getProductDetails(productId).subscribe(product => {
      this.selectedProduct = product;
      this.showProductDetails = true;
    });
  }

  closeProductDetails(): void {
    this.showProductDetails = false;
  }

  

  getCustomer(): void {
   
    this.buyerService.getCustomerByEmailId(this.customerEmail).subscribe(customer => {
      this.loggedInCustomer = customer;
      this.customerId = this.loggedInCustomer.customerId
      localStorage.setItem('customerId',this.customerId.toString())
      console.log(this.customerId)
    });





}


placeBid(productId: number) {
  const bidInfo: BidInfo = {
    bidId: 0,
    productId: productId,
    customerId: this.customerId,
    bidAmount: this.bidAmountMap[productId],
    status: 'N'
  };
  this.buyerService.createOrUpdateBid(bidInfo).subscribe(response => {
   console.log(response);
   if(response.status === 200) {
    this.toastr.success('Your bid has been placed successfully!');
  } else {
    this.toastr.error('Error while placing bid. Please check and place bid!');
  }
  });
}

getWinningBids(){

  this.buyerService.getWinningBids(this.customerId).subscribe(bids=>{
    this.winningBids =bids;
  })
}

checkForPayments(product: ProductInfo): boolean {
  const productBid = this.winningBids.find(bid => bid.productId === product.productId && bid.status === 'Y');
  return productBid !== undefined;
}


getAllActiveAuctions(){

  this.buyerService.getAllActiveAuctions().subscribe(auctions => {
    console.log(auctions)
    this.activeAuctions = auctions;
  });
}

isAuctionActive(productId: number): boolean {

  const activeAuctionProducts = this.activeAuctions.map(auction => auction.productId);
  return activeAuctionProducts.includes(productId);
}




redirectToPayment(productId: Number): void {
  const product = productId
  this.router.navigate(['Buyer/Payment'], { queryParams: { productId: product } });
}



}

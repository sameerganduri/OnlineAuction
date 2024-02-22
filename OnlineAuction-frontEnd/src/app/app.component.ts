import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'OnlineAuctionUI';


static roleMap = new Map([
  [ '1','Admin'],
  [ '2','Buyer'],
  [ '3','Seller']


]
)


}

import { Component, Input, OnInit } from '@angular/core';
import { faBoxes, faEdit, faGavel, faIcicles, faMoneyCheckDollar, faShip, faSitemap, faUserEdit } from '@fortawesome/free-solid-svg-icons';
import {
  faUserAlt,
  faAddressCard,
  faWrench,
  faFile,
  faNewspaper,
  faTruck,
  faMapMarker,
  faPencilSquare,
  faFolderOpen,
  faBoxOpen,
  faShoppingCart,
  faCloudDownload,
  faRefresh,
  faArrowRight,
  faArrowLeft,
  faChartLine,
  faCloudUploadAlt,
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-sidenav-component',
  templateUrl: './sidenav-component.component.html',
  styleUrls: ['./sidenav-component.component.scss']
})
export class SidenavComponentComponent implements OnInit{
  @Input() sideNavStatus: boolean = false;

  sideNavList!:Array<any>;

 

  roleId!: any;
  faArrowRight = faArrowRight;
  faArrowLeft = faArrowLeft;
  faUserAlt = faUserAlt;
  faAddressCard = faAddressCard;
  faWrench = faWrench;
  faFile = faFile;
  faNewspaper = faNewspaper;
  faTruck = faTruck;
  faMapMarker = faMapMarker;
  faPencilSquare = faPencilSquare;
  faFolderOpen = faFolderOpen;
  faBoxOpen = faBoxOpen;
  faShoppingCart = faShoppingCart;
  faCloudDownload = faCloudDownload;
  faRefresh = faRefresh;
  faChartLine = faChartLine;
  faCloudUploadAlt = faCloudUploadAlt;

  dataValues: any;
  convertedUserData: any;

  
  showProductDetails:boolean=false;
  showShipping: boolean = false;
  showTracking: boolean = false;
  showAuction: boolean = false;
  showCategory: boolean = false;
  showEditSeller: boolean = false;
  showPayment:boolean=false;


showProducts:boolean=false;
showCreateOrEditProduct:boolean=false;
  showEditBuyer: boolean=false;;





ngOnInit(){


  this.getData();
   this.ShowIcons();

}
getData() {
  this.dataValues = localStorage.getItem('user_data');
  this.convertedUserData = JSON.parse(this.dataValues);
  this.roleId = this.convertedUserData.roles;
}
ShowIcons() {
  switch (this.roleId) {
    case "1": {
      this.showEditSeller=true;
      this.showEditBuyer=true;
     
         
      break;
    }
    case "2": {
      this.showProductDetails=true;
      this.showEditBuyer=true;
      this.showPayment=true;
      this.showShipping=true;
      break;
    }
    case "3": {
  this.showProducts=true;
  this.showAuction = true;
  this.showCreateOrEditProduct=true;
  this.showCategory=true;
  this.showEditSeller=true;

  
      break;
    }
    
    default:
      console.log('No Role Found');
      break;
  }

  this.sideNavList = [
    {
      _name: 'Products',
      _icon: faBoxes,
      _show: this.showProducts,
      _route: '/Seller',
    },
    {
      _name: 'Create Products',
      _icon: faEdit,
      _show: this.showCreateOrEditProduct,
      _route: 'CreateOrEditProduct',
    },
    {
      _name: 'Auction',
      _icon: faGavel,
      _show: this.showAuction,
      _route: 'CreateAuction',
    },
    {
      _name: 'Create Categories',
      _icon: faSitemap,
      _show: this.showCategory,
      _route: 'CreateOrEditCategory',
    },
    {
      _name: 'Create Edit Seller',
      _icon: faUserEdit,
      _show: this.showEditSeller,
      _route: 'CreateOrEditSeller',
    },

  {
    _name: 'Product Details',
      _icon: faBoxes,
      _show: this.showProductDetails,
      _route: '/Buyer',

  },
  {
    _name: 'Create Edit Buyer',
    _icon: faUserEdit,
    _show: this.showEditBuyer,
    _route: 'CreateOrEditBuyer',
  }
,{
  
  _name:'Payment',
  _icon:faMoneyCheckDollar,
  _show:this.showPayment,
  _route:'Payment'

},{
  _name:'Shipping',
  _icon:faShip,
  _show:this.showShipping,
  _route:'Shipment'
}
    
  ];
}

}

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SellerInfo } from 'src/app/features/models/seller-info.model';
import { SellerService } from 'src/app/features/services/seller.service';

@Component({
  selector: 'app-create-edit-seller',
  templateUrl: './create-edit-seller.component.html',
  styleUrls: ['./create-edit-seller.component.scss']
})
export class CreateEditSellerComponent implements OnInit {
  sellerForm!: FormGroup;
  sellerEmail!: string;
  user_data: any;
  sellerId!:number;
  constructor(
    private toastr: ToastrService,
    private sellerService: SellerService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user_data = localStorage.getItem('user_data');
    // = this.user_data['userName']
    const user_data = JSON.parse(localStorage.getItem('user_data') as string);
    this.sellerEmail = user_data.userName;
    

    this.createSellerForm();
this.getSeller()
    if (this.sellerEmail) {
      this.getSeller();
    }
  }

  createSellerForm() {
    this.sellerForm = this.fb.group({
      sellerId: [0],
      firstName: [''],
      lastName: [''],
      email: [''],
      password: [''],
      sellerRating: [0],
    });
  }

  getSeller(): void {
    
    this.sellerService.getSellerByEmailId(this.sellerEmail).subscribe((resp) => {
      console.log(resp)
      this.sellerForm.patchValue(resp);
    });
  }

  onSubmit(): void {
    this.sellerId = Number(localStorage.getItem('sellerId'))
    this.sellerForm.patchValue({ sellerId: this.sellerId });
    console.log(this.sellerForm.value)
    this.sellerService.createOrUpdateSeller(this.sellerForm.value).subscribe((response) => {
      console.log(response,this.sellerForm.value);
      
      this.toastr.success('Seller details updated successfully');
      //this.router.navigate(['/']);
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CategoryInfo } from 'src/app/features/models/category-info.model';
import { SellerService } from 'src/app/features/services/seller.service';

@Component({
  selector: 'app-create-edit-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.scss']
})
export class CreateEditCategoryComponent implements OnInit {
  categoryForm!: FormGroup;
  categoriesList: CategoryInfo[] = [];

  constructor(
    private toastr: ToastrService,
    private sellerService: SellerService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.createCategoryValidation();
    this.getCategories();
  }

  createCategoryValidation() {
    this.categoryForm = this.fb.group({
      categoryId: [0],
      categoryName: [],
      categoryDescription: [],
      parentCategoryId: [0],
    });
  }

  getCategories(): void {
    this.sellerService.getAllCategorys().subscribe((resp: any) => {
      this.categoriesList = resp.data;
    });
  }

  onSubmit(): void {
    console.log('Form values sent', this.categoryForm.value);
    this.sellerService
      .createOrUpdateCategory(this.categoryForm.value)
      .subscribe(response => {
        console.log(response);
      });

    this.toastr.success('Category created/updated successfully');
    this.router.navigate(['/Seller']); 
  }
}

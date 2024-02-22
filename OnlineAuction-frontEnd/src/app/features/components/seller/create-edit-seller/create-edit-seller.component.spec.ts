import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateEditSellerComponent } from './create-edit-seller.component';

describe('CreateEditSellerComponent', () => {
  let component: CreateEditSellerComponent;
  let fixture: ComponentFixture<CreateEditSellerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateEditSellerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateEditSellerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import {
  faEye,
  faEyeSlash,
  faSignIn,
  faUserLock,
} from '@fortawesome/free-solid-svg-icons';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { AppComponent } from '../app.component';
import { AuthService } from '../shared/services/auth-service';

declare var window: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginFormValues!: FormGroup;
  submitted: boolean = false;
  formModal: any;
  faEye = faEye;
  faEyeSlash = faEyeSlash;
  faSignIn = faSignIn;
  faUserLock = faUserLock;
  eyeslash!: boolean;
  show!: boolean;
  responseToken: string = '';
  userInfo!: any;
  user_Name!: string;
  pass_Word!: string;
  forgetPasswordFormValues!: FormGroup;
  tokenError: string = '';
  role!: any;
  authResp!:any;

  constructor(
    private router: Router,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService,
    private authService:AuthService
  ) {}

  ngOnInit(): void {
    this.loginValidations();
    this.submitted = true;
    this.eyeslash = true;
    // this.formModal = new window.bootstrap.Modal(
    //   document.getElementById('forgetPasswordModal')
    // );
  }

  showPassword() {
    this.show = !this.show;
    this.eyeslash = !this.eyeslash;
  }

  loginValidations() {
    this.loginFormValues = new FormGroup({
      userName: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });

    this.forgetPasswordFormValues = new FormGroup({
      user_name: new FormControl('', Validators.required),
      email_id: new FormControl('', [
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
        Validators.maxLength(50),
      ]),
    });
  }

  onLogin() {
    console.log("entered")
    this.user_Name = this.loginFormValues.value.userName;
    this.pass_Word = this.loginFormValues.value.password;
    localStorage.clear();
    this.spinner.show();
    this.authService
      .login(
        this.loginFormValues.value.userName,
        this.loginFormValues.value.password
      )
      .subscribe({
        next: (data: any) => {
          this.authResp = data;
          console.log("response from auth",this.authResp)
          this.responseToken = this.authResp.access_token;
          localStorage.setItem('token', this.authResp.access_token);
          localStorage.setItem('refresh_token', this.authResp.refresh_token);
          localStorage.setItem(
            'expires_in',
            this.authResp.expires_in.toString()
          );
          if (data.access_token) {
            this.getUserDetails(this.user_Name);
          } else {
            this.tokenError = data.error_description;
          }
          this.spinner.hide();
          this.router.navigate(['/header']);
        },
        error: (error) => {
          if (this.tokenError !== '') {
            this.toastr.error(this.tokenError);
          } else if (error.error.error_description) {
            this.toastr.error(error.error.error_description);
          } else {
            this.toastr.error('Error in Communicating the Service');
          }
          this.spinner.hide();
          this.router.navigate(['login']);
        },
      });
    this.loginFormValues.reset();
  }

  openModel() {
    this.formModal.show();
    this.loginFormValues.reset();
    this.forgetPasswordFormValues.reset();
  }

  closeModel() {
    this.formModal.hide();
    this.loginFormValues.reset();
    this.forgetPasswordFormValues.reset();
  }

  validateUser() {
    // this.spinner.show();
    // this.logInService
    //   .validateUserDetails(
    //     this.forgetPasswordFormValues.value.user_name,
    //     this.forgetPasswordFormValues.value.email_id
    //   )
    //   .subscribe({
    //     next: (response: any) => {
    //       if (200 == response.code) {
    //         this.forgetPasswordFormValues.reset();
    //         this.formModal.hide();
    //         this.loginFormValues.reset();
    //         this.spinner.hide();
    //         this.toastr.success(response.message);
    //       }
    //     },
    //     error: (error) => {
    //       this.forgetPasswordFormValues.reset();
    //       this.loginFormValues.reset();
    //       this.formModal.hide();
    //       this.spinner.hide();
    //       this.toastr.error(error.error.status);
    //     },
    //   });
  }

  getUserDetails(userName: string) {
    this.spinner.show();
    this.authService.getUserDetails(userName).subscribe({
      next: (resp: any) => {
        this.userInfo = resp.data;
        console.log(this.userInfo)
        localStorage.setItem('user_data', JSON.stringify(this.userInfo));
        sessionStorage.setItem('user_data', JSON.stringify(this.userInfo));
        sessionStorage.setItem(
          'roleId',
          JSON.stringify(this.userInfo.roles)
        );
         this.role = AppComponent.roleMap.get((this.userInfo.roles.toString()));
         let routeurl: string = '/' + this.role;
        localStorage.setItem('role', this.role);
        console.log("role is",this.role)
        this.router.navigate([routeurl]);
        setTimeout(() => {
          this.spinner.hide();
        }, 1000);
        this.toastr.success(resp.message);
      },
      error: (error) => {
        setTimeout(() => {
          this.spinner.hide();
        }, 1000);
        if (error.error.status) {
          this.toastr.error(error.error.status);
        } else {
          this.toastr.error('API Communication Failed');
        }
      },
    });
   }
}

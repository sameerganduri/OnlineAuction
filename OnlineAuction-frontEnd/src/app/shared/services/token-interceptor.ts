import { Inject, Injectable, InjectionToken } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { catchError, Observable, throwError, timeout } from 'rxjs';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
export const DEFAULT_TIMEOUT = new InjectionToken<number>('defaultTimeout');
@Injectable()
export class TokenInterceptorInterceptor implements HttpInterceptor {
  dataValues: any = '';
  convertedUserData!: any;
  userName: any;
  constructor(
    private router: Router,
    private toastr: ToastrService,
    @Inject(DEFAULT_TIMEOUT) protected defaultTimeout: number
  ) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const timeoutValueNumeric = Number(this.defaultTimeout);
    if (localStorage.getItem('token')) {
      const accessToken = localStorage.getItem('token');
      this.dataValues = localStorage.getItem('refresh_token');
      console.log("accessToken",accessToken)
      if (this.dataValues != null) {
     //   this.convertedUserData = JSON.parse(this.dataValues);
     //   this.userName = this.convertedUserData.userInfo.userName;
        request = request.clone({
          headers: request.headers
            .set('Authorization', 'Bearer ' + accessToken)
          
        });
      } else {
        request = request.clone({
          headers: request.headers.set(
            'Authorization',
            'Bearer ' + accessToken
          ),
        });
      }
      return next.handle(request).pipe(
        timeout(timeoutValueNumeric),
        catchError((error: HttpErrorResponse) => {
          if (error.status === 403 || error.status === 401) {
            this.toastr.error('Session Expired');
            localStorage.clear();
            this.router.navigate(['/login']);
          } else {
            this.toastr.error(
              'Webservice taking long time to respond, please try after sometime'
            );
          }
          return throwError(error);
        })
      );
    } else {
      return next.handle(request);
    }
  }
}

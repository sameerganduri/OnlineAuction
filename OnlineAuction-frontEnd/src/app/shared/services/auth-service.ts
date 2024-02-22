import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UrlSegment } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  headers!:HttpHeaders;
  constructor(private http: HttpClient) { 


   
}
 login(userName:string,password:string): Observable<any> {
  const url = `http://localhost:8080/oauth/token`;
  let ClientId:string =`onlineauction`;
  let ClientSecret:string =`onlineauction`;
  var formData:any = new FormData();
  formData.append('grant_type','password');
  formData.append('username',userName);
  formData.append('password',password);

  return this.http.post<any>(url,formData,{
    headers:{
      Authorization : 'Basic ' + btoa(ClientId+':'+ClientSecret)
    }
  });
}

getUserDetails(userName : string):Observable<any>{
  let params = {userName:userName}

  let queryParams = new  HttpParams({fromObject:params})

  let url = `http://localhost:8080/login?`
  this.headers = new HttpHeaders().set('Authorization','Bearer ' +localStorage.getItem('token'));
  return this.http.get(url+queryParams,{headers:this.headers})
}

}

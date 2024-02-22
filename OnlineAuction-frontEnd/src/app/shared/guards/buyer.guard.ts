import { CanActivate } from "@angular/router";
import {Injectable } from '@angular/core';

@Injectable({
    providedIn:'root',
})

export class BuyerGuard implements CanActivate{
    canActivate(){
        const token = localStorage.getItem('token');
        var role = localStorage.getItem('role')
        if(role==='Buyer' && token){
            return true;
        }
        alert('No Access')
        return false;
   
    }
}
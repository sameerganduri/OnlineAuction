import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faBars } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header-component',
  templateUrl: './header-component.component.html',
  styleUrls: ['./header-component.component.scss']
})
export class HeaderComponentComponent {

  faBars = faBars;
  dataValues: any;
  convertedUserData: any;
  userName!: any;
  role!: any;
  version!: string;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.dataValues = localStorage.getItem('user_data');
    this.convertedUserData = JSON.parse(this.dataValues);
    this.userName = this.convertedUserData.userName;
    this.role = localStorage.getItem('role');
  }

  logOut() {
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigate(['login']);
  }
}

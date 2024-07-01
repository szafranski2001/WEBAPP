import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserCredentials} from "../../model/User";
import {LoginService} from "../../services/login.service";
import {LogOutService} from "../../services/log-out.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

    protected readonly localStorage = localStorage;


  constructor( private service :LogOutService, private loginService : LoginService) {}

  logout() {
    this.service.doLogOut();
  }

  isUserAdmin() : boolean{
    return Number(this.localStorage.getItem("type")) == 1;
  }

  getToken(){
    return localStorage.getItem("user-token");
  }

  OperationLoading(){   
    return this.loginService.loggingIn || this.service.loggingOut;
  }
}

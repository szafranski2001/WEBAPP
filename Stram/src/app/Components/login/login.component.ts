import {Component, OnInit} from '@angular/core';
import {User, UserCredentials} from '../../model/User';
import {NgForm} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  user : UserCredentials;
  constructor( private service :LoginService, private router : Router) {

  }
  ngOnInit(): void {

  }
  onSubmit(form: NgForm) {
    const username = form.value['username'];
    const password = form.value['password'];

    this.user = { username: username, password: password };

    console.log(username, password);
    this.service.doLogin(this.user);
    form.reset();
  }

}

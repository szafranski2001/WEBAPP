import {Component, OnInit} from '@angular/core';
import {User, UserCredentials} from '../../model/User';
import {NgForm} from "@angular/forms";
import {SignupService} from "../../services/signup.service";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  user : UserCredentials;
  constructor( private service :LoginService) {

  }
  ngOnInit(): void {

  }
  onSubmit(form: NgForm)
  {
    const username= form.value.username
    const password = form.value.password

    this.user = {password: password, username: username}
    //controllare input utente se valido
    this.service.doLogin(this.user)
    form.reset()
  }

}

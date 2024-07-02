import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {UserCredentials} from "../model/User";
import {UserDTO} from "../model/UserDTO";
import {TokenManager} from "../model/TokenManager";
import {videogame} from "../model/Videogame";
import { Router } from '@angular/router';
import { LoginError } from '../model/Message';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  tokenM : TokenManager = new TokenManager()
  private url = "http://localhost:8080"

  loggingIn=false;

  constructor(private http : HttpClient, private router : Router) { }
  doLogin(user:  UserCredentials )
  {
    this.loggingIn=true;
    return this.http.post<UserDTO>(this.url+"/authenticate/login",user).subscribe({
      next : (response) => {
        this.tokenM.setToken(response.token.toString());
        localStorage.setItem("user", response.user.username);
        localStorage.setItem("type", JSON.stringify(response.type));
        this.router.navigate(["/"]);
        this.loggingIn=false;
      },
      error : () => {
        this.loggingIn=false;
        alert(LoginError)
      }
    })
  }
}

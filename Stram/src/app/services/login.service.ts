import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {UserCredentials} from "../model/User";
import {UserDTO} from "../model/UserDTO";
import {TokenManager} from "../model/TokenManager";
import {videogame} from "../model/Videogame";
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  tokenM : TokenManager = new TokenManager()
  private url = "http://localhost:8080"
  constructor(private http : HttpClient, private router : Router) { }
  doLogin(user:  UserCredentials )
  {
    console.log(user.password, user.username)
    return this.http.post<UserDTO>(this.url+"/authenticate/login",user).subscribe({
      next : (response) => {
        this.tokenM.setToken(response.token.toString());
        console.log(response.token);
        localStorage.setItem("user", response.user.username);
        localStorage.setItem("type", JSON.stringify(response.type))
        this.router.navigate(["/"]);
      },
      error : () => {
        alert("Login fallito")
      }

    })
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserCredentials} from "../model/User";
import {UserDTO} from "../model/UserDTO";
import {TokenManager} from "../model/TokenManager";
import {videogame} from "../model/Videogame";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  tokenM : TokenManager = new TokenManager()
  private url = "http://localhost:8080"
  constructor(private http : HttpClient) { }
  doLogin(user:  UserCredentials )
  {
    console.log(user.password, user.username)
    this.http.post<UserDTO>(this.url+"/authenticate/login",user).subscribe(Response=>{
      this.tokenM.setToken(Response.token.toString());
      console.log(Response.token);
      Response.user.password=""; //nice remove
      localStorage.setItem("user", JSON.stringify(Response.user));
      localStorage.setItem("type", JSON.stringify(Response.type))
      return this.http.get("/");

    })
  }
}

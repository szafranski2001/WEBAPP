import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserCredentials} from "../model/User";
import {UserDTO} from "../model/UserDTO";
import {TokenManager} from "../model/TokenManager";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url = "http://localhost:8080"
  tokenM : TokenManager = new TokenManager()
  constructor(private http : HttpClient) { }
  doLogin(user:  UserCredentials )
  {
    console.log(user.password, user.username)
    this.http.post<UserDTO>(this.url+"/authenticate/login",user).subscribe(Response=>{
      this.tokenM.setToken(Response.token.toString());
      console.log(Response.token);
      Response.user.password=""; //nice remove
      localStorage.setItem("user", JSON.stringify(Response.user));
      //DA AGGIUNGERE IL PASSAGGIO ALL:A HOMEPAGE es.this.router.navigate(["/homepage"])

    })
  }
}

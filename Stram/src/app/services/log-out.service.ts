import { Injectable } from '@angular/core';
import {TokenManager} from "../model/TokenManager";
import {videogame} from "../model/Videogame";
import {HttpClient} from "@angular/common/http";
import { Router } from '@angular/router';
import {User} from "../model/User";

@Injectable({
  providedIn: 'root'
})
export class LogOutService {

  private url = "http://localhost:4200/"
  private tokenM: TokenManager;

  constructor(private http : HttpClient, private router : Router) {
    this.tokenM = new TokenManager(); // Inizializza TokenManager
  }

  doLogOut(user : User)
  {
    this.tokenM.removeToken()
    this.http.post(this.url+  "authenticate/logout", localStorage.getItem("user"))
    this.router.navigate(["/"]);
  }
}

import { Injectable } from '@angular/core';
import {TokenManager} from "../model/TokenManager";
import {videogame} from "../model/Videogame";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LogOutService {

  private url = "http://localhost:4200/"
  private tokenM: TokenManager;

  constructor(private http : HttpClient) {
    this.tokenM = new TokenManager(); // Inizializza TokenManager
  }

  doLogOut()
  {
    this.tokenM.removeToken()
    return this.http.get(this.url);
  }
}

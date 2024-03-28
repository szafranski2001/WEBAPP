import { Injectable } from '@angular/core';
import {TokenManager} from "../model/TokenManager";

@Injectable({
  providedIn: 'root'
})
export class LogOutService {

  constructor() { }
  tokenM : TokenManager
  doLogOut()
  {
    this.tokenM.removeToken()
    //portare alla HOMEPAGE
  }
}

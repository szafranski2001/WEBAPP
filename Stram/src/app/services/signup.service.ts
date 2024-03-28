import { Injectable } from '@angular/core';
import {User} from "../model/User";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private url = "http://localhost:8080"
  constructor(private http : HttpClient) { }
  doSignUp(user : User){
    console.log(user)
       return this.http.post<boolean>(this.url+"/authenticate/signUp",user)
  }
}

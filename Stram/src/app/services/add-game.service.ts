import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {OptionsForAddNewVideogame} from "../Components/model/OptionsForAddNewVideogame";

@Injectable({
  providedIn: 'root'
})
export class AddGameService {
  private backend = "http://localhost:8080/";
  private userToken = "userTokenXXX"
  constructor(private http : HttpClient) { }


  getOptionsForAddNewVideoGame() {
    const body = { "userToken": this.userToken };
    return this.http.post<OptionsForAddNewVideogame>(this.backend+"getOptionsForAddNewVideogame", body);
  }


}

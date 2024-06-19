import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {SingleGameInfo} from '../model/SingleGameInfo';

@Injectable({
  providedIn: 'root'
})
export class UserListsService {

  private BackEndURL="http://localhost:8080/api";

  User = localStorage.getItem("user");

  constructor(private http : HttpClient) { }

  getUserFavoriteList(){
    return this.http.get<SingleGameInfo[]>(this.BackEndURL+"/GetVideogameInPreferredList/");
  }

  getUserWishList(){
    return this.http.get<SingleGameInfo[]>(this.BackEndURL+"/GetVideogameInWishList/");
  }

  isVideogameInFavoriteList(videogameId : number){
    //verifica che il videogioco si trovi nella lista dei favoriti dell'utente corrente
    return this.http.post<boolean>(this.BackEndURL+"/GetVideogameInPreferredList/"+videogameId,this.User);
  }

  isVideogameInWishList(videogameId : number){
    //verifica che il videogioco si trovi nella lista dei desideri dell'utente corrente
    return this.http.post<boolean>(this.BackEndURL+"/GetVideogameInWishList/"+videogameId,this.User);
  }

  addVideoGameToFavoriteList(videogameId : number){
    return this.http.post(this.BackEndURL+"/AddVideogameInPreferredList/"+videogameId,this.User);
  }

  addVideoGameToWishList(videogameId : number){
    return this.http.post(this.BackEndURL+"/AddVideogameInWishList/"+videogameId,this.User);
  }

  removeVideogameFromFavoriteList(videogameId : number){
    const options = { body : this.User }
    return this.http.delete(this.BackEndURL+"/RemoveVideogameInPreferredList/"+videogameId,options);
  }

  removeVideogameFromWishList(videogameId : number){
    const options = { body : this.User }
    return this.http.delete(this.BackEndURL+"/RemoveVideogameInWishList/"+videogameId,options);
  }

}

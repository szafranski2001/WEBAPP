import { HttpClient } from '@angular/common/http';
import { videogame } from '../Components/model/Videogame';
import { Injectable } from '@angular/core';
import { VideogameReviewsService } from './videogame-reviews.service';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class VideogameDataService {

  BackEndURL="http://localhost:8080/api";
  selectedVideogame: videogame;
  isEditable = false;

  constructor(private http : HttpClient, private ReviewService : VideogameReviewsService){ }

  isEditMode(){
    return this.isEditable;
  }

  toggleEditMode(){
    this.isEditable=!this.isEditable;
  }

  UpdateRating(){
    this.selectedVideogame.valutazione=this.ReviewService.getNewAverageVoto();
  }

  SetSelectedVideogame(videogame : videogame){
    this.selectedVideogame=videogame;
  }

  GetVideogameImage(id: number){
    return this.http.get("http");
  }

  GetVideogameDetails(id: number){
    return this.http.get<videogame>(this.BackEndURL+"/Videogame/"+id);
  }

  RemoveVideogameData(){
    return this.http.delete(this.BackEndURL+"/DeleteVideogameData");
    //alert db to remove videogame
  }

  AddVideogameData(videogame : videogame){
    return this.http.post(this.BackEndURL+"/AddVideogameData",videogame);
    //send videogame to db
  }

  EditVideogameDetails(){
    return this.http.post(this.BackEndURL+"/EditVideogame",this.selectedVideogame);
    //send data edited to db
  }

}

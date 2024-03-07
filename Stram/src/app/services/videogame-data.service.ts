import { HttpClient } from '@angular/common/http';
import { videogame } from '../Components/model/Videogame';
import { Injectable } from '@angular/core';
import { VideogameReviewsService } from './videogame-reviews.service';

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

  GetSelectedVideogame(){
    return this.selectedVideogame;
  }

  SetSelectedVideogame(videogame : videogame){
    this.selectedVideogame=videogame;
  }

  GetVideogameDetails(id: number){
    return this.http.get<videogame>(this.BackEndURL+"/Videogame/"+id);
    //get videogame from backEnd
  }

  RemoveVideogameData(id : number){
    return this.http.delete(this.BackEndURL+"/DeleteVideogame/"+id);
    //alert backEnd to remove videogame
  }

  AddVideogameData(videogame : videogame){
    return this.http.post(this.BackEndURL+"/AddVideogame",videogame);
    //send videogame to backEnd
  }

  EditVideogameDetails(id : number){
    return this.http.put(this.BackEndURL+"/EditVideogame/"+id,this.selectedVideogame);
    //send data edited to backEnd
  }

}

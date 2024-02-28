import { HttpClient } from '@angular/common/http';
import { videogame,genere } from '../Components/model/Videogame';
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
  
  getVideogameId(){
    return this.selectedVideogame.id;
  }

  getVideogameTitle(){
    return this.selectedVideogame.titolo;
  }

  GetVideogameDetails(id: number){
    let videogame: videogame={
      id: 0,
      titolo:'Minecraft safasgag ag ag ag ag asg asg ag a gsdsd sh sh s hsh sf',
      genere: genere.Sandbox,
      durata: 0,
      anno:2009,
      casaP:'Mojang',
      descrizione:'ddddddddddddddddgggggggggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ',
      valutazione:4,
      trailer:'https://www.youtube.com/watch?v=dQw4w9WgXcQ'
    }
    this.selectedVideogame=videogame;
    if(videogame.id == id)
      return videogame;

    else{
      let videogame: videogame={
        id: 2,
        titolo:'Sto cazzo',
        genere: genere.MMORPG,
        durata: 15,
        anno:2020,
        casaP:'Test',
        descrizione:'gioco MMORPG ',
        valutazione:4,
        trailer:'https://www.youtube.com/watch?v=dQw4w9WgXcQ'
      }
      this.selectedVideogame=videogame;
      return videogame;
    }
  }

  UpdateRating(){
    this.selectedVideogame.valutazione=this.ReviewService.getNewAverageVoto();
  }

  RemoveVideogameData(){
    this.http.delete(this.BackEndURL+"/DeleteVideogameData");
    //alert db to remove videogame
  }

  AddVideogameData(videogame : videogame){
    this.http.post(this.BackEndURL+"/AddVideogameData",videogame);
    //send videogame to db
  }

  EditVideogameDetails(){
    this.http.post(this.BackEndURL+"/EditVideogame",this.selectedVideogame);
    //send data edited to db
  }



}

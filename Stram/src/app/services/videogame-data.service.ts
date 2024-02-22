import { HttpClient } from '@angular/common/http';
import { videogame,genere } from '../Components/model/Videogame';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class VideogameDataService {

  BackEndURL="http://localhost:8080";
  selectedVideogame: videogame;
  isEditable = false;

  constructor(private http : HttpClient){ }

  isEditMode(){
    return this.isEditable;
  }

  toggleEditMode(){
    this.isEditable=!this.isEditable;
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
        id: 3,
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

  RemoveVideogameData(){
    //alert db to remove videogame
  }

  AddVideogameData(videogame : videogame){
    //send videogame to db
  }

  EditVideogameDetails(){
    this.http.post(this.BackEndURL+"/EditVideogame",this.selectedVideogame);
    //send data edited to db
  }



}

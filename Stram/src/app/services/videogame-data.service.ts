import { videogame,genere } from '../model/Videogame';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class VideogameDataService {
  
  selectedVideogame: videogame;

  constructor(){ }

  getData(id: number){
    let videogame: videogame={
      id: 0,
      titolo:'Minecraft',
      genere: genere.Sandbox,
      durata: 15,
      anno:2009,
      casaP:'Mojang',
      descrizione:'gioco Sandbox ',
      valutazione:4
    }
    this.selectedVideogame=videogame;
    if(videogame.id == id)
      return videogame;

    else{
      let videogame: videogame={
        id: 1,
        titolo:'Sto cazzo',
        genere: genere.MMORPG,
        durata: 15,
        anno:2020,
        casaP:'Test',
        descrizione:'gioco MMORPG ',
        valutazione:4
      }
      this.selectedVideogame=videogame;
      return videogame;
    }
  }
}

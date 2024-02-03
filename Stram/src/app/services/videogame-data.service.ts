import { videogame,genere } from '../model/Videogame';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class VideogameDataService {
  
  id: number;
  titolo: string;

  constructor(){ }

  getData(id: number){
    let videogame: videogame={
      id: 1,
      titolo:'BDO',
      genere: genere.MMORPG,
      durata: 15,
      anno:2020,
      casaP:'Test',
      descrizione:'gioco MMORPG ',
      valutazione:4
    }

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
      return videogame;
    }
  }
}

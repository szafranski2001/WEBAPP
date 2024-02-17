import { videogame,genere } from '../Components/model/Videogame';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class VideogameDataService {
  
  selectedVideogame: videogame;
  isEditable = false;

  constructor(){ }

  isEditMode(){
    return this.isEditable;
  }

  toggleEditMode(){
    this.isEditable=!this.isEditable;
  }

  getData(id: number){
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

  UpdateData(){
    let dataElements=document.getElementsByClassName("data-element");
    
    this.selectedVideogame.titolo=String((dataElements[0] as HTMLElement).innerText);
    this.selectedVideogame.casaP=String((dataElements[1] as HTMLElement).innerText);

    let durata=Number((dataElements[2] as HTMLElement).innerText);
    (Number.isNaN(durata) || durata <= 0 ) ? this.selectedVideogame.durata = 0 : this.selectedVideogame.durata=durata;

    let anno=Number((dataElements[3] as HTMLElement).innerText);
    (Number.isNaN(anno) || anno > 9999 || anno <= 1950 ) ? this.selectedVideogame.anno= 9999 : this.selectedVideogame.anno=anno;

    this.selectedVideogame.descrizione=String((dataElements[4] as HTMLElement).innerText);
  }

  RemoveVideogame(){}
  AddVideogame(){}
  EditVideogame(){
    console.log(this.selectedVideogame);
  }



}

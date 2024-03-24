import {Component, NgIterable, OnInit} from '@angular/core';
import {videogame} from "../../model/Videogame";
import {core} from "@angular/compiler";
import {dateTimestampProvider} from "rxjs/internal/scheduler/dateTimestampProvider";
import {Observable, Subject, tap} from "rxjs";

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrl: './add-game.component.css'
})
export class AddGameComponent implements OnInit {


  constructor() { }

  ngOnInit() {
    this.generateAnnoOptions();
  }

  isSetted = {
    id: 0,
    titolo: 0,
    genere: 0,
    durata: 0,
    anno: 0,
    casaP: 0,
    descrizione: 0,
    valutazione: 0,
    trailer: 0
  };

  newVideogame: videogame;
  genereOptions = [
    "Azione",
    "Avventura",
    "Sopravvivenza"
  ];

  annoOptions: number[];



  onChangeInput() {

  }

  onChangeSelect() {

  }




  generateAnnoOptions() {

    for (let i = Date.parse("YYYY"); i > 1940; i--) {
      this.annoOptions.push(i);
    }
    console.log(this.annoOptions);
  }





}

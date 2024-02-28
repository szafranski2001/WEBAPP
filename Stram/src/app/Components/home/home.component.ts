import { Component } from '@angular/core';
import {SingleGameInfo} from "../model/SingleGameInfo";
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {ResultStatusOnly} from "../model/ResultStatusOnly";
import {HomeService} from "../../services/home.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

    constructor(private home: HomeService) {}



    clickBtnFavoriteGame(game: SingleGameInfo) {
      let result: ResultStatusOnly

      let request = this.home.changeFavoriteFlag(game);

      if (request == undefined) {
        console.log("request undefined from clickBtnFavoriteGame in home.component.ts")
        return;
      }

      request.subscribe((res) => {
          result = res;
          console.log('Richiesta favorite ok:');

          // @ts-ignore
          if (result.getok() != 200) {
            //errore nella prenotazione
            alert("Non è stato possibile effettuare la prenotazione. Riprova in seguito. Errore dal db o server");
            return;
          }
      });
    }
}


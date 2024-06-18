import { Component, ElementRef, HostListener, ViewChild, OnInit, AfterViewInit, QueryList, ViewChildren } from '@angular/core';
import { SingleGameInfo } from "../../model/SingleGameInfo";
import { ResultStatusOnly } from "../../model/ResultStatusOnly";
import { HomeService } from "../../services/home.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(protected home: HomeService) {}

  isLogged = true;
  topGame = this.home.getTopGame();
  firstSlider = this.home.getFirstSlider();
  secondSlider = this.home.getSecondSlider();
  firstTitle: String = "Top Games"
  secondTitle: String = "Adventures"




  clickBtnFavoriteGame(game: SingleGameInfo) {
    let result: ResultStatusOnly;

    let request = this.home.changeFavoriteFlag(game);

    if (request == undefined) {
      console.log("request undefined from clickBtnFavoriteGame in home.component.ts")
      return;
    }

    request.subscribe((res) => {
      result = res;
      console.log('Richiesta favorite ok:');

      if (result.getok() != 200) {
        //errore nella prenotazione
        alert("Non è stato possibile effettuare l'operazione");
        return;
      }
    });
  }
}

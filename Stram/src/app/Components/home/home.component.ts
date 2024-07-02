import {
  Component,
  ElementRef,
  HostListener,
  ViewChild,
  OnInit,
  AfterViewInit,
  QueryList,
  ViewChildren,
  NgIterable
} from '@angular/core';
import { SingleGameInfo } from "../../model/SingleGameInfo";
import { ResultStatusOnly } from "../../model/ResultStatusOnly";
import { HomeService } from "../../services/home.service";
import { videogame } from "../../model/Videogame";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})




export class HomeComponent implements OnInit {

  protected sliders: SingleGameInfo[][];
  firstTitle: String = "Top Games"
  get10: (NgIterable<unknown> & NgIterable<any>) | undefined | null;

  ngOnInit() {
    this.home.getSliders().subscribe(
      (data: SingleGameInfo[][]) => {
        console.log(data);
        this.sliders = data;
        this.get10 = data;
      },

      error => {
        console.error('Errore nel recuperare i dati', error);
      }
    );
  }
  constructor(protected home: HomeService) {
  }




}

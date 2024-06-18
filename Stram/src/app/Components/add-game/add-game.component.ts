import {Component, NgIterable, OnInit} from '@angular/core';
import {genere, videogame} from "../../model/Videogame";
import {core} from "@angular/compiler";
import {dateTimestampProvider} from "rxjs/internal/scheduler/dateTimestampProvider";
import {Observable, Subject, tap} from "rxjs";
import {AddGameService} from "../../services/add-game.service";
import {HttpErrorResponse} from "@angular/common/http";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrl: './add-game.component.css'
})
export class AddGameComponent implements OnInit {

  constructor(private addGameService: AddGameService) { }
  ngOnInit() {
    this.requestOptionsToTheServer();

  }


  newVideogame: videogame = {} as videogame;


  genereOptions: string[] = [];
  annoOptions: number[] = [];
  caseProduttriciOptions: string[] = [];




  private requestOptionsToTheServer() {

    this.addGameService.getOptionsForAddNewVideoGame().subscribe(
      {
        next: (data) => {

          let fromYear: number = parseInt(data.fromYear[0]);
          let currentYear: number = new Date().getFullYear();

          for (let i= fromYear; i <= currentYear; i++) {
            this.annoOptions.push(i);
          }

          this.annoOptions.reverse();
          this.genereOptions = data.genres;
          this.caseProduttriciOptions = data.caseProduttrici;
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      }
    );


  }


  onSubmit($event: SubmitEvent) {

  }
}

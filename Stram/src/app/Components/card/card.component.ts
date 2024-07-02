import {Component, Input} from '@angular/core';
import {SingleGameInfo} from "../../model/SingleGameInfo";
import {videogame} from "../../model/Videogame";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {

    @Input() game!: SingleGameInfo;
    @Input() rank!: number;
    @Input() link!: String;

    constructor() {
    }



}

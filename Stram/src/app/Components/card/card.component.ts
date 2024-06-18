import {Component, Input} from '@angular/core';
import {SingleGameInfo} from "../../model/SingleGameInfo";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {

    @Input() game!: SingleGameInfo;
    constructor() {}

}

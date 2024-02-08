import { Component,Input, OnInit } from '@angular/core';
import { tipologiaUser} from '../model/TipologiaUtente';
import { videogame } from '../model/Videogame';

@Component({
  selector: 'app-videogame-toolbar',
  templateUrl: './videogame-toolbar.component.html',
  styleUrl: './videogame-toolbar.component.css'
})
export class VideogameToolbarComponent{

  @Input() videogameData : videogame;

  tipologiaUser = tipologiaUser;
  isLoggedIn: boolean = true;
  CurrentUserTipologia = tipologiaUser.Admin;

  constructor(){ }
}

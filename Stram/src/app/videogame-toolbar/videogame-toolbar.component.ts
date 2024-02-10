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

  constructor(){ 
    //getCurrentUser
  }

  AddRemovePressed(event : Event){
    //If CurrentUser != null
      let IconId=(<HTMLButtonElement>event.currentTarget).firstElementChild!.id;
      let element=document.getElementById(IconId);
      let elementProperty=window.getComputedStyle(element!).getPropertyValue('font-variation-settings').substring(7,8);
      
      if(Number(elementProperty) == 0){
        element!.style.setProperty('font-variation-settings',"'FILL' 1,'wght' 700, 'GRAD' 0, 'opsz' 48");
        //RemoveFromList
      }
      else{
        element!.style.setProperty('font-variation-settings',"'FILL' 0,'wght' 500, 'GRAD' 0, 'opsz' 48");
        //AddToList
      }
  }
}

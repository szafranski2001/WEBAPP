import { Component,EventEmitter,OnInit,Output } from '@angular/core';
import { tipologiaUser } from '../model/TipologiaUtente';
import { VideogameDataService } from '../services/videogame-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-videogame-toolbar',
  templateUrl: './videogame-toolbar.component.html',
  styleUrl: './videogame-toolbar.component.css'
})
export class VideogameToolbarComponent{

  editButton : string='edit';
  isEditable = false;

  @Output() EditableInfo = new EventEmitter<boolean>()

  //da modificare quando avremo il service per il currentUser
  tipologiaUser = tipologiaUser;
  CurrentUserTipologia = tipologiaUser.Admin;

  constructor(private ManagerService : VideogameDataService,private router : Router){ 
    //getCurrentUser
  }

  AddRemoveListPressed(event : Event){
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

  RemoveVideogame(){
    //Chiamata a service per rimozione videogame e redirect della pagina
    if(confirm("Sei sicuro di voler cancellare "+this.ManagerService.selectedVideogame.titolo+" dal catalogo?\n Una volta rimosso non sarà più recuperabile. ")){
      this.ManagerService.RemoveVideogame();
      alert(this.ManagerService.selectedVideogame.titolo+" è stato rimosso con successo! \n Verrai rendirizzato verso la homepage.")
      this.router.navigate(['/']);
    }
    //Un po bruttino ma al momento voglio concetrarmi sul resto della webapp anche perchè senno dovrei creare dei component apposta
  }

  EditVideogame(){
    if(this.isEditable){
      this.ManagerService.EditVideogame();
      this.editButton="edit";
    }
    else
      this.editButton="done_outline";

    this.isEditable=!this.isEditable;
    this.EditableInfo.emit(this.isEditable);
  }
}

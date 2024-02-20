import { Component,EventEmitter, OnInit, Output } from '@angular/core';
import { tipologiaUser } from '../model/TipologiaUtente';
import { VideogameDataService } from '../../services/videogame-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-videogame-toolbar',
  templateUrl: './videogame-toolbar.component.html',
  styleUrl: './videogame-toolbar.component.css'
})
export class VideogameToolbarComponent implements OnInit {

  editButton : string='edit';
  @Output() UpdateData = new EventEmitter<void>();

  //Da prendere dal DB tramite il service all'init
    isFavorite=false;
    isWished=false;

  //da modificare quando avremo il service per il currentUser
  tipologiaUser = tipologiaUser;
  CurrentUserTipologia = tipologiaUser.Admin;

  constructor(private VideogameManagerService : VideogameDataService,private router : Router){ }

  ngOnInit(): void {
    //getCurrentUser
    //Controllo se fa parte della wishlist e favorite list e impostazione variabili
  }

  AddFavoriteListPressed(){
    this.isFavorite=!this.isFavorite;
    if(this.isFavorite)
      console.log("AddToList");
      //AddToList
    else
      console.log("RemoveFromList");
      //RemoveFromList
  }
  
  AddWishListPressed(){
    this.isWished=!this.isWished;
    if(this.isWished)
      console.log("AddToList");
      //AddToList
    else
      console.log("RemoveFromList");
      //RemoveFromList
  }

  RemoveVideogame(){
    //Chiamata a service per rimozione videogame e redirect della pagina
    if(confirm("Sei sicuro di voler cancellare "+this.VideogameManagerService.selectedVideogame.titolo+" dal catalogo?\n Una volta rimosso non sarà più recuperabile. ")){
      this.VideogameManagerService.RemoveVideogameData();
      alert(this.VideogameManagerService.selectedVideogame.titolo+" è stato rimosso con successo! \n Verrai rendirizzato verso la homepage.")
      this.router.navigate(['/']);
    }
    //Un po bruttino ma al momento voglio concetrarmi sul resto della webapp anche perchè senno dovrei creare dei component apposta
  }

  EditVideogame(){

    this.VideogameManagerService.toggleEditMode();

    if(this.VideogameManagerService.isEditMode()){
      this.editButton="done_outline";
    }
    else {
      this.editButton="edit";
      this.UpdateData.emit();
      this.VideogameManagerService.EditVideogameDetails();
    }
  }

}

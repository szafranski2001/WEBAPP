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
    this.GetVideogameListDetails();
  }

  GetVideogameListDetails(){
    //this.isFavorite=getCurrentUser.list.find() ? true : false;
    //this.isWished=getCurrentUser.list.find() ? true : false;
  }

  AddFavoriteListPressed(){
    this.isFavorite=!this.isFavorite;
    if(this.isFavorite){}
      //AddVideogameToList
    else{}
      //RemoveVideogameFromList
  }
  
  AddWishListPressed(){
    this.isWished=!this.isWished;
    if(this.isWished){}
      //AddVideogameToList
    else{}
      //RemoveVideogameFromList
  }

  RemoveVideogame(){
    if(confirm("Sei sicuro di voler cancellare "+this.VideogameManagerService.getVideogameTitle()+" dal catalogo?\n Una volta rimosso non sarà più recuperabile. ")){
      this.VideogameManagerService.RemoveVideogameData();
      alert(this.VideogameManagerService.getVideogameTitle()+" è stato rimosso con successo! \n Verrai rendirizzato verso la homepage.")
      this.router.navigate(['/']);
    }
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

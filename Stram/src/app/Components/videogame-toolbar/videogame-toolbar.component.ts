import { Component,EventEmitter, OnInit, Output,Input } from '@angular/core';
import { tipologiaUser } from '../model/TipologiaUtente';
import { VideogameDataService } from '../../services/videogame-data.service';
import { Router } from '@angular/router';
import { ConfirmVideogameDeleteMessage, RedirectToHomeMessage, SuccessfulVideogameDeleteMessage } from '../model/Message';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-videogame-toolbar',
  templateUrl: './videogame-toolbar.component.html',
  styleUrl: './videogame-toolbar.component.css'
})
export class VideogameToolbarComponent implements OnInit {

  editButton : string='edit';
  @Input() videogameId : number;
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
    if(confirm(ConfirmVideogameDeleteMessage)){
      this.VideogameManagerService.RemoveVideogameData(this.videogameId);
      alert(SuccessfulVideogameDeleteMessage+RedirectToHomeMessage)
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
      this.VideogameManagerService.EditVideogameDetails(this.videogameId).subscribe({
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      });
    }
  }

}

import { Component,EventEmitter, OnInit, Output,Input } from '@angular/core';
import { tipologiaUser } from '../../model/TipologiaUtente';
import { VideogameDataService } from '../../services/videogame-data.service';
import { Router } from '@angular/router';
import { ConfirmVideogameDeleteMessage, RedirectToHomeMessage, SuccessfulVideogameDeleteMessage } from '../../model/Message';
import { HttpErrorResponse } from '@angular/common/http';
import { UserListsService } from '../../services/user-lists.service';

@Component({
  selector: 'app-videogame-toolbar',
  templateUrl: './videogame-toolbar.component.html',
  styleUrl: './videogame-toolbar.component.css'
})
export class VideogameToolbarComponent implements OnInit {

  editButton : string='edit';
  @Input() videogameId : number;
  @Output() UpdateData = new EventEmitter<void>();

  isFavorite=false;
  isWished=false;

  tipologiaUser = tipologiaUser;
  CurrentUserTipologia = Number(localStorage.getItem("type"));
  User = localStorage.getItem("user");

  constructor(private VideogameManagerService : VideogameDataService,private router : Router, private UserListService : UserListsService){ }

  ngOnInit(): void {
    if(this.User != undefined)
      this.GetVideogameListDetails();
  }

  GetVideogameListDetails(){
    this.UserListService.isVideogameInFavoriteList(this.videogameId).subscribe(response => {
      this.isFavorite = response;
    })

    this.UserListService.isVideogameInWishList(this.videogameId).subscribe(response => {
      this.isWished = response;
    })
  }

  AddFavoriteListPressed(){
    this.isFavorite ?
      this.UserListService.removeVideogameFromFavoriteList(this.videogameId).subscribe({
        next: () => {
          this.isFavorite = !this.isFavorite;
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      })
      :
      this.UserListService.addVideoGameToFavoriteList(this.videogameId).subscribe({
        next: () => {
          this.isFavorite=!this.isFavorite;
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      });
  }

  AddWishListPressed(){
    this.isWished ?
    this.UserListService.removeVideogameFromWishList(this.videogameId).subscribe({
      next: () => {
        this.isWished = !this.isWished;
      },
      error: (error : HttpErrorResponse) => {
        alert(error.error);
      }
    })
    :
    this.UserListService.addVideoGameToWishList(this.videogameId).subscribe({
      next: () => {
        this.isWished=!this.isWished;
      },
      error: (error : HttpErrorResponse) => {
        alert(error.error);
      }
    });
  }

  RemoveVideogame(){
    if(confirm(ConfirmVideogameDeleteMessage)){
      this.VideogameManagerService.RemoveVideogameData(this.videogameId).subscribe({
        next: () => {
          alert(SuccessfulVideogameDeleteMessage+RedirectToHomeMessage)
          this.router.navigate(['/']);
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error)
        }
      });
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

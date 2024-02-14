import { Component,Input, OnInit, Output,EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { tipologiaUser } from '../model/TipologiaUtente';
import { VideogameReviewsService } from '../../services/videogame-reviews.service';


@Component({
  selector: 'app-review-toolbar',
  templateUrl: './review-toolbar.component.html',
  styleUrl: './review-toolbar.component.css'
})
export class ReviewToolbarComponent{

  @Input() reviewId:number; //Da cambiare, ora non so quale sia la chiave primaria di review


  //Da far inserire il valore ricavato dal DB
  isLiked=false;
  isReported=false;
  ///////////////////////////////////////////

  //DA CAMBIARE CON IL SERVICE NON VOGLIO STO SCHIFO DENTRO OGNI COMPONENT A CUI SERVE SAPERE LA TIPOLOGIA USER
  tipologiaUser = tipologiaUser;
  CurrentUserTipologia = tipologiaUser.Admin;
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////


  constructor(private ReviewService : VideogameReviewsService){}

  DeleteReview(){
    if(confirm("Sei sicuro di voler rimuovere questa recensione? \n Una volta eliminata non sarà più recuperabile.")){
      alert("Recensione eliminata con successo!")
    }
  }

  ReportReview(){
    this.isReported=!this.isReported;
    //stessa roba del like sotto ma con il report
  }

  LikeReview(){
    this.isLiked=!this.isLiked;

    if(this.isLiked)
      console.log("chiama il service per inserire una tupla dove si mette che il currentUser ha messo like a questa review");
    else
      console.log("chiama il service per cancellare il like che era stato impostato dal currentUser");

  }
}

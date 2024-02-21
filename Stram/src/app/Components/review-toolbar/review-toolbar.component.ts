import { Component,Input, OnInit, Output,EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { tipologiaUser } from '../model/TipologiaUtente';
import { VideogameReviewsService } from '../../services/videogame-reviews.service';
import { review } from '../model/Review';


@Component({
  selector: 'app-review-toolbar',
  templateUrl: './review-toolbar.component.html',
  styleUrl: './review-toolbar.component.css'
})
export class ReviewToolbarComponent{

  @Input() review:review;

  //Da far inserire il valore ricavato dal DB
  isLiked=false;
  isReported=false;
  ///////////////////////////////////////////

  //DA CAMBIARE CON IL SERVICE NON VOGLIO STO SCHIFO DENTRO OGNI COMPONENT A CUI SERVE SAPERE LA TIPOLOGIA USER
  tipologiaUser = tipologiaUser;
  CurrentUserTipologia = 0;
  User = "stocazzo";
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////


  constructor(private ReviewService : VideogameReviewsService){}

  DeleteReview(){
    if(confirm("Sei sicuro di voler rimuovere questa recensione? \n Una volta eliminata non sarà più recuperabile.")){
      this.ReviewService.DeleteReviewData(this.ReviewService.SearchUserReview());
      alert("Recensione eliminata con successo!")
    }
  }

  ReportReview(){
    this.isReported=!this.isReported;
    this.isReported ? this.ReviewService.AddReportToReview(this.review) : this.ReviewService.RemoveReportToReview(this.review);
  }

  LikeReview(){
    this.isLiked=!this.isLiked;
    this.isLiked ? this.ReviewService.AddLikeToReview(this.review) : this.ReviewService.RemoveLikeToReview(this.review);
  }
}

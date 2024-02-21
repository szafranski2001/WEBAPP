import { Component,Input, OnInit } from '@angular/core';
import { tipologiaUser } from '../model/TipologiaUtente';
import { VideogameReviewsService } from '../../services/videogame-reviews.service';
import { review } from '../model/Review';


@Component({
  selector: 'app-review-toolbar',
  templateUrl: './review-toolbar.component.html',
  styleUrl: './review-toolbar.component.css'
})
export class ReviewToolbarComponent implements OnInit{

  @Input() review:review;
  @Input() reviewInfo : boolean[];

  isLiked:boolean;
  isReported:boolean;

  //DA CAMBIARE CON IL SERVICE NON VOGLIO STO SCHIFO DENTRO OGNI COMPONENT A CUI SERVE SAPERE LA TIPOLOGIA USER
  tipologiaUser = tipologiaUser;
  CurrentUserTipologia = 0;
  User = "stocazzo";
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////


  constructor(private ReviewService : VideogameReviewsService){}

  ngOnInit(): void {
    this.isLiked=this.reviewInfo[0];
    this.isReported=this.reviewInfo[1];
  }

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

import { Component,Input, OnInit } from '@angular/core';
import { tipologiaUser } from '../model/TipologiaUtente';
import { VideogameReviewsService } from '../../services/videogame-reviews.service';
import { review } from '../model/Review';
import { VideogameDataService } from '../../services/videogame-data.service';
import { HttpErrorResponse } from '@angular/common/http';


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
  CurrentUserTipologia = 1;
  User = "stocazzo";
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////


  constructor(private ReviewService : VideogameReviewsService, private  VideogameManagerService : VideogameDataService){}

  ngOnInit(): void {
    this.isLiked=this.reviewInfo[0];
    this.isReported=this.reviewInfo[1];
  }

  DeleteReview(){
    if(confirm("Sei sicuro di voler rimuovere questa recensione? \n Una volta eliminata non sarà più recuperabile.")){
      let revIndex=this.ReviewService.SearchUserReview(this.review.username);
      this.ReviewService.DeleteReviewData(this.review).subscribe({
        next: () => {
          this.ReviewService.DeleteFromReviewList(revIndex);
          this.VideogameManagerService.UpdateRating();
          alert("Recensione eliminata con successo!");
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      });
    }
  }

  ReportReview(){
    !this.isReported ? 
      this.ReviewService.ManageReportToReview(this.review,true).subscribe({
        next: () => {
          this.isReported=!this.isReported;
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      })
      : 
      this.ReviewService.ManageReportToReview(this.review,false).subscribe({
        next: () => {
          this.isReported=!this.isReported;
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      });
  }

  LikeReview(){
    !this.isLiked ? 
      this.ReviewService.ManageLikeToReview(this.review,true).subscribe({
        next: () => {
          this.review.likes++;
          this.isLiked=!this.isLiked;
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      })
      : 
      this.ReviewService.ManageLikeToReview(this.review,false).subscribe({
        next: () => {
          this.review.likes--;
          this.isLiked=!this.isLiked;
        },
        error: (error : HttpErrorResponse) => {
          alert(error.error);
        }
      })
  }
}

import { Component,Input, OnInit } from '@angular/core';
import { review } from '../model/Review';
import { VideogameReviewsService } from '../../services/videogame-reviews.service';
import { reviewLikeInfo, reviewReportInfo } from '../model/ReviewInfo';

@Component({
  selector: 'app-videogame-reviews',
  templateUrl: './videogame-review.component.html',
  styleUrl: './videogame-review.component.css'
})
export class VideogameReviewsComponent implements OnInit {
  
  @Input() videogameId : number;

  //Da cambiare con il getCurrentUser non appena abbiamo il service dell'utente corrente
  //Cerca tutti i suoi riferimenti
  User="stocazzo";
  
  ReviewList : review[];
  ReviewLikeInfos : reviewLikeInfo[];
  ReviewReportInfos : reviewReportInfo[];

  constructor(private ReviewService : VideogameReviewsService) {}

  ngOnInit(): void {
    this.ReviewList=this.ReviewService.getReviewListByVideogameId(this.videogameId);
    this.ReviewLikeInfos=this.ReviewService.getReviewLikeInfo(this.videogameId);
    this.ReviewReportInfos=this.ReviewService.getReviewReportInfo(this.videogameId);
  }

  getSingleReviewInfo(review : review){
    let likeInfo=this.ReviewLikeInfos.find( info => info.mittente == this.User && info.destinatario == review.username) ? true : false;
    let reportInfo=this.ReviewReportInfos.find( info => info.mittente == this.User && info.destinatario == review.username) ? true : false;
    return [likeInfo,reportInfo];
  }

}

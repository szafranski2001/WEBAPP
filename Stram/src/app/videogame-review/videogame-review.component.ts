import { Component,Input, OnInit, Output } from '@angular/core';
import { review } from '../model/Review';
import { VideogameReviewsService } from '../services/videogame-reviews.service';

@Component({
  selector: 'app-videogame-reviews',
  templateUrl: './videogame-review.component.html',
  styleUrl: './videogame-review.component.css'
})
export class VideogameReviewsComponent implements OnInit {
  
  @Input() videogameId: number;
  Removed=false;

  ReviewList : review[];

  constructor(private reviewService : VideogameReviewsService) {}

  ngOnInit(): void {
    this.ReviewList=this.reviewService.getReviewListByVideogameId(this.videogameId);
  }

  IsRemoved(value : boolean){
    this.Removed=value;
  }
}

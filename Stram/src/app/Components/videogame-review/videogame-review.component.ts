import { Component,Input, OnInit } from '@angular/core';
import { review } from '../model/Review';
import { VideogameReviewsService } from '../../services/videogame-reviews.service';
import { VideogameDataService } from '../../services/videogame-data.service';

@Component({
  selector: 'app-videogame-reviews',
  templateUrl: './videogame-review.component.html',
  styleUrl: './videogame-review.component.css'
})
export class VideogameReviewsComponent implements OnInit {
  
  @Input() videogameId : number;
  ReviewList : review[];

  constructor(private reviewService : VideogameReviewsService,private videogameDataService : VideogameDataService) {}

  ngOnInit(): void {
    this.ReviewList=this.reviewService.getReviewListByVideogameId(this.videogameId);
  }

}

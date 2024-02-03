import { Component,Input } from '@angular/core';
import { review } from '../model/Review';

@Component({
  selector: 'app-videogame-reviews',
  templateUrl: './videogame-review.component.html',
  styleUrl: './videogame-review.component.css'
})
export class VideogameReviewsComponent {
  
  @Input() videogameId: number;

  reviewList: review[]=[
    {idVideogame: 0,title:'Titolo', username:'stocazzo', comment:'bel videogioco', rating:5, likes:104},
    {idVideogame: 0,title:'Daje roma', username:'aridaje', comment:'nice game', rating:5, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:5, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:5, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:5, likes:104}
  ];

  //constructor(private reviewService : ReviewListService) {}

  getReviewList(videogameId :number){
    //fare la query solo se non si hanno dati già in cache
    return this.reviewList;
  }
}

import { Injectable } from '@angular/core';
import { review } from '../Components/model/Review';

@Injectable({
  providedIn: 'root'
})
export class VideogameReviewsService {
  

  //questa lista poi andrà rimossa e si dovrà ritornare il contenuto del backend
  reviewsList: review[]=[
    {reviewId:0,idVideogame: 0,title:'Titolo', username:'stocazzo', comment:'bel videogioco', rating:1, likes:104},
    {reviewId:1,idVideogame: 0,title:'Daje roma', username:'supercalisdfghjkloiuytrewqasdf', comment:'nice game', rating:4, likes:104},
    {reviewId:2,idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:3, likes:104},
    {reviewId:3,idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:2, likes:104},
    {reviewId:4,idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:1, likes:104}
  ];

  constructor() {}

  getReviewListByVideogameId(id : number){
    //effettua qui la tua chiamata al back-end per la richiesta degli oggetti reviews
    return this.reviewsList;
  }

  DeleteReview(id : number){
  }
}

import { Injectable } from '@angular/core';
import { review } from '../model/Review';

@Injectable({
  providedIn: 'root'
})
export class VideogameReviewsService {
  
  reviewsList: review[]=[
    {idVideogame: 0,title:'Titolo', username:'stocazzo', comment:'bel videogioco', rating:5, likes:104},
    {idVideogame: 0,title:'Daje roma', username:'aridaje', comment:'nice game', rating:5, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:5, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:5, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:5, likes:104}
  ];

  constructor() {}

  getReviewListByVideogameId(id : number){
    //effettua qui la tua chiamata al back-end per la richiesta degli oggetti reviews
    return this.reviewsList;
  }
}

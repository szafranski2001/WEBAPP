import { Injectable } from '@angular/core';
import { review } from '../Components/model/Review';

@Injectable({
  providedIn: 'root'
})
export class VideogameReviewsService {

  //faccio una variabile user ma dovremmo prendere quello dal service UtenteManager
  User="stocazzo";

  //questa lista poi andrà rimossa e si dovrà ritornare il contenuto del backend
  reviewsList: review[]=[
    {idVideogame: 0,title:'Titolo', username:'stocazzo', comment:'bel videogioco', rating:1, likes:104},
    {idVideogame: 0,title:'Daje roma', username:'supercalisdfghjkloiuytrewqasdf', comment:'nice game', rating:4, likes:104},
    {idVideogame: 0,title:'sium', username:'CR1', comment:'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab', rating:3, likes:104},
    {idVideogame: 0,title:'sium', username:'CR2', comment:'gg', rating:2, likes:104},
    {idVideogame: 0,title:'sium', username:'CR3', comment:'gg', rating:3, likes:104},
    {idVideogame: 0,title:'sium', username:'CR4', comment:'gg', rating:2, likes:104},
    {idVideogame: 0,title:'sium', username:'CR5', comment:'gg', rating:3, likes:104},
    {idVideogame: 0,title:'sium', username:'CR6', comment:'gg', rating:2, likes:104},
    {idVideogame: 0,title:'sium', username:'CR7', comment:'gg', rating:3, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:2, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:3, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:2, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:3, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:2, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:3, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:2, likes:104},
    {idVideogame: 0,title:'sium', username:'CR8', comment:'gg', rating:1, likes:104}
  ];

  constructor() {}

  SearchUserReview(){
    return this.reviewsList.findIndex((item) => item.username == this.User);
  }

  getReviewListByVideogameId(id : number){
    //effettua qui la tua chiamata al back-end per la richiesta degli oggetti reviews
    return this.reviewsList;
  }

  getReviewLikeInfo( videogameId : number){
    //effettua chiamata al back-end per prendere tutte i riferimenti a cui il nostro utente ha messo like
    return [{mittente:"stocazzo",destinatario:"CR2", filmId: 0},
            {mittente:"stocazzo",destinatario:"daje", filmId: 0},
            {mittente:"stocazzo",destinatario:"sium", filmId: 0},
            {mittente:"stocazzo",destinatario:"CR1", filmId: 1}];
  }

  getReviewReportInfo( videogameId : number){
    //effettua chiamata al back-end per prendere tutte i riferimenti cui il nostro utente ha segnalato
    return [{mittente:"stocazzo",destinatario:"CR4",filmId: 0, stato:"in corso"},
            {mittente:"stocazzo",destinatario:"CR3",filmId: 0, stato:"conclusa"}];
  }

  AddReviewData(review : review){
    this.reviewsList.unshift(review);
    //chiamata al backEnd per aggiungere recensione al db
  }

  DeleteReviewData(index : number){
    let review=this.reviewsList.splice(index,1);
    //Chiamata al backEnd per la rimozione della recensione dal db
  }

  //Poi vediamo se è possibile unire questi 4 eventi in 2 

  AddLikeToReview(review : review){
    review.likes++;
    //chiamata al backEnd per inserire aggiunta like nel db
  }

  RemoveLikeToReview(review : review){
    review.likes--;
    //chiamata al backEnd per rimuovere like nel db
  }

  AddReportToReview(review : review){
    //chiamata al backEnd per aggiungere segnalazione nel db
  }
  
  RemoveReportToReview(review : review){
    //chiamata al backEnd per rimuovere segnalazione nel db
  }
}

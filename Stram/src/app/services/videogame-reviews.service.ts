import { Injectable } from '@angular/core';
import { review } from '../Components/model/Review';
import { HttpClient } from '@angular/common/http';
import { reviewLikeInfo, reviewReportInfo } from '../Components/model/ReviewInfo';
import { ReportStatus } from '../Components/model/ReportStatus';

@Injectable({
  providedIn: 'root'
})
export class VideogameReviewsService {

  private BackEndURL="http://localhost:8080";
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

  constructor(private http : HttpClient) {}

  SearchUserReview(){
    return this.reviewsList.findIndex((item) => item.username == this.User);
  }

  // GET DATA

  getReviewListByVideogameId(id : number){
    //effettua qui la tua chiamata al back-end per la richiesta degli oggetti reviews
    return this.reviewsList;
  }

  getReviewLikeInfo( videogameId : number){
    //effettua chiamata al back-end per prendere tutte i riferimenti a cui il nostro utente ha messo like
    return [{mittente:"stocazzo",destinatario:"CR2", videogameId: 0},
            {mittente:"stocazzo",destinatario:"daje", videogameId: 0},
            {mittente:"stocazzo",destinatario:"sium", videogameId: 0},
            {mittente:"stocazzo",destinatario:"CR1", videogameId: 1}];
  }

  getReviewReportInfo( videogameId : number){
    //effettua chiamata al back-end per prendere tutte i riferimenti cui il nostro utente ha segnalato
    return [{mittente:"stocazzo",destinatario:"CR4",videogameId: 0, stato: ReportStatus.open},
            {mittente:"stocazzo",destinatario:"CR3",videogameId: 0, stato: ReportStatus.closed}];
  }


  // PUT/PATCH DATA

  AddReviewData(review : review){
    this.reviewsList.unshift(review);
    this.http.post(this.BackEndURL+"/getReviews",review);
  }

  AddLikeToReview(review : review){
    review.likes++;
    let reviewInfo : reviewLikeInfo = {mittente:this.User,destinatario:review.username, videogameId: review.idVideogame};
    this.http.post(this.BackEndURL+"/addLike",reviewInfo);
  }

  AddReportToReview(review : review){
    let reviewInfo : reviewReportInfo = {mittente : this.User, destinatario: review.username, videogameId: review.idVideogame,stato:ReportStatus.open};
    this.http.post(this.BackEndURL+"/addReport",reviewInfo); 
  }

  //DELETE DATA

  RemoveLikeToReview(review : review){
    review.likes--;
    //chiamata al backEnd per rimuovere like nel db
  }


  RemoveReportToReview(review : review){
    //chiamata al backEnd per rimuovere segnalazione nel db
  }

  
  DeleteReviewData(index : number){
    let review=this.reviewsList.splice(index,1);
    //Chiamata al backEnd per la rimozione della recensione dal db
  }
}

import { Injectable } from '@angular/core';
import { review } from '../Components/model/Review';
import { HttpClient } from '@angular/common/http';
import { reviewLikeInfo, reviewReportInfo } from '../Components/model/ReviewInfo';
import { StateSegnalazioni } from '../Components/model/ReportStatus';

@Injectable({
  providedIn: 'root'
})
export class VideogameReviewsService {

  private BackEndURL="http://localhost:8080/api";
  //faccio una variabile user ma dovremmo prendere quello dal service UtenteManager
  User="stocazzo";

  //questa lista poi andrà rimossa e si dovrà ritornare il contenuto del backend
  private reviewsList: review[]=[
    {videogioco: 0,titolo:'Titolo', username:'stocazzo', commento:'bel videogioco', voto:1, likes:104},
    {videogioco: 0,titolo:'Daje roma', username:'supercalisdfghjkloiuytrewqasdf', commento:'nice game', voto:4, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR1', commento:'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab', voto:3, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR2', commento:'gg', voto:2, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR3', commento:'gg', voto:3, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR4', commento:'gg', voto:2, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR5', commento:'gg', voto:3, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR6', commento:'gg', voto:2, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR7', commento:'gg', voto:3, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR8', commento:'gg', voto:2, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR8', commento:'gg', voto:3, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR8', commento:'gg', voto:2, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR8', commento:'gg', voto:3, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR8', commento:'gg', voto:2, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR8', commento:'gg', voto:3, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR8', commento:'gg', voto:2, likes:104},
    {videogioco: 0,titolo:'sium', username:'CR8', commento:'gg', voto:1, likes:104}
  ];

  constructor(private http : HttpClient) {}

  SearchUserReview( reviewUser : string){
    return this.reviewsList.findIndex((item) => item.username == reviewUser);
  }

  getNewAverageVoto() : number{
    let sum=0;
    this.reviewsList.forEach((item ) => {sum+=item.voto;});
    return sum/this.reviewsList.length;
  }

  AddToReviewList(review : review){
    this.reviewsList.unshift(review);
  }

  DeleteFromReviewList(index : number){
     return this.reviewsList.splice(index,1);
  }

  /////Http Methods calls

  getReviewListByVideogameId(id : number){
    //effettua qui la tua chiamata al back-end per la richiesta degli oggetti reviews
    return this.reviewsList;
  }

  getReviewLikeInfo( videogameId : number){
    //effettua chiamata al back-end per prendere tutte i riferimenti a cui il nostro utente ha messo like
    return [{usernameMittente:"stocazzo",usernameDestinatario:"CR2", idVideogioco: 0},
            {usernameMittente:"stocazzo",usernameDestinatario:"daje", idVideogioco: 0},
            {usernameMittente:"stocazzo",usernameDestinatario:"sium", idVideogioco: 0},
            {usernameMittente:"stocazzo",usernameDestinatario:"CR1", idVideogioco: 1}];
  }

  getReviewReportInfo( videogameId : number){
    //effettua chiamata al back-end per prendere tutte i riferimenti cui il nostro utente ha segnalato
    return [{mittente:"stocazzo",destinatario:"CR4",idVideogioco: 0, stato: StateSegnalazioni.open},
            {mittente:"stocazzo",destinatario:"CR3",idVideogioco: 0, stato: StateSegnalazioni.closed}];
  }


  ///////

  ManageReportToReview(review : review, state : boolean){
    let reviewInfo : reviewReportInfo = {mittente : this.User, destinatario: review.username, idVideogioco: review.videogioco,stato:StateSegnalazioni.open};
    const options={ body : reviewInfo };

    return state ?  this.http.post(this.BackEndURL+"/AddReport",reviewInfo) : this.http.delete(this.BackEndURL+"RemoveReport",options);
  }
  
  ManageLikeToReview(review : review, state : boolean){
    let reviewInfo : reviewLikeInfo = {usernameMittente:this.User,usernameDestinatario:review.username, idVideogioco: review.videogioco};
    const options={ body : reviewInfo};

    return state ? this.http.post(this.BackEndURL+"/AddLike",reviewInfo) : this.http.delete(this.BackEndURL+"/RemoveLike",options);
  }

  AddReviewData(review : review){
    return this.http.post(this.BackEndURL+"/AddReview",review);
  }

  DeleteReviewData(review : review){
    const options={ body : review };
    return this.http.delete(this.BackEndURL+"/DeleteReview",options);
  }

}

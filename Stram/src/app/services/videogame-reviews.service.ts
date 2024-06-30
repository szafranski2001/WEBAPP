import { Injectable } from '@angular/core';
import { review } from '../model/Review';
import { HttpClient } from '@angular/common/http';
import { TypeInfo, reviewInfo } from '../model/ReviewInfo';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VideogameReviewsService {

  private BackEndURL="http://localhost:8080/api";
  
  User=localStorage.getItem("user"); 

  private reviewsList: review[];

  constructor(private http : HttpClient) {}

  SearchUserReview(reviewUser : string){
    if(this.reviewsList)
      return this.reviewsList.findIndex((item) => item.username == reviewUser);
    else
      return 0;
  }

  getNewAverageVoto() : number{
    let sum=0;
    this.reviewsList.forEach((item) => {sum+=item.voto;});

    let average=sum/this.reviewsList.length;
    let round= (sum/this.reviewsList.length) - Math.floor(sum/this.reviewsList.length);

    return round >= 0.5 ? Math.ceil(average) : Math.floor(average);
    
  }

  AddToReviewList(review : review){
    this.reviewsList.unshift(review);
  }

  DeleteFromReviewList(index : number){
     return this.reviewsList.splice(index,1);
  }

  setReviewList(reviewList : review[]){
    this.reviewsList=reviewList;
  }

  /////Http Methods calls


  getReviewListByVideogameId(id : number){
    //effettua qui la tua chiamata al back-end per la richiesta degli oggetti reviews
    return this.http.get<review[]>(this.BackEndURL+"/GetReviews/"+id);
  }

  ///// 

  getReviewInfo( videogameId : number, type : TypeInfo){
    //effettua chiamata al back-end per prendere tutte i riferimenti cui il nostro utente ha segnalato
    return this.http.post<reviewInfo[]>(this.BackEndURL+type+videogameId,this.User);
  }

  /////

  ManageReportToReview(review : review, state : boolean){
    let reviewInfo : reviewInfo = { mittente : this.User, destinatario: review.username, idVideogioco: review.videogioco };
    const options={ body : reviewInfo };

    return state ? this.http.post(this.BackEndURL+"/AddReport",reviewInfo) : this.http.delete(this.BackEndURL+"/RemoveReport",options);
  }
  
  ManageLikeToReview(review : review, state : boolean){
    let reviewInfo : reviewInfo = { mittente : this.User, destinatario:review.username, idVideogioco: review.videogioco };
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

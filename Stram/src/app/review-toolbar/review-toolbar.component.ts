import { Component,Input, OnInit, Output,EventEmitter } from '@angular/core';
import { tipologiaUser } from '../model/TipologiaUtente';
import { VideogameReviewsService } from '../services/videogame-reviews.service';


@Component({
  selector: 'app-review-toolbar',
  templateUrl: './review-toolbar.component.html',
  styleUrl: './review-toolbar.component.css'
})
export class ReviewToolbarComponent{

  @Input() review_id:string; //Da cambiare, ora non so quale sia la chiave primaria di review
  @Output() RemovedInfo = new EventEmitter<boolean>()

  Reported=false;

  tipologiaUser = tipologiaUser;
  CurrentUserTipologia = tipologiaUser.Admin;


  constructor(private ReviewService : VideogameReviewsService){}

  DeleteReview(){
    if(confirm("Sei sicuro di voler rimuovere questa recensione? \n Una volta eliminata non sarà più recuperabile.")){
      this.RemovedInfo.emit(true);
      alert("Recensione eliminata con successo!")
    }
  }

  ReportReview(){
    if(this.Reported==false){
      alert("Recensione segnalata con successo!")
      
    }
    else{
      alert("Segnalazione rimossa con successo!")
    }
    this.Reported=!this.Reported;
  }

  LikeReview(event : Event){
      let IconId=(<HTMLButtonElement>event.currentTarget).firstElementChild!.id;
      let element=document.getElementById(IconId);
      let elementProperty=window.getComputedStyle(element!).getPropertyValue('font-variation-settings').substring(7,8);
      
      if(Number(elementProperty) == 0){
        element!.style.setProperty('font-variation-settings',"'FILL' 1,'wght' 700, 'GRAD' 0, 'opsz' 48");
      }
      else{
        element!.style.setProperty('font-variation-settings',"'FILL' 0,'wght' 500, 'GRAD' 0, 'opsz' 48");
      }
  }
}

import { Component, OnInit,Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { GeneralTasksService } from '../../services/general-tasks.service';
import { VideogameReviewsService } from '../../services/videogame-reviews.service';
import { review } from '../model/Review';
import { VideogameDataService } from '../../services/videogame-data.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrl: './add-review.component.css'
})
export class AddReviewComponent implements OnInit {

  numberOfStars=5;
  currentRating = {value : 1, stars : Array.from({length : this.numberOfStars}, (_,i) => i < 1 ? 'starFull' : 'starEmpty')};
  UserImage : number;

  constructor(private generalTasks : GeneralTasksService, private ReviewService : VideogameReviewsService, private VideogameManagerService : VideogameDataService) {}

  ngOnInit(): void {
    this.UserImage=this.generalTasks.getRandomValue(6); //possibile cosa carina da fare, settare un valore per User qui nel front per dargli sempre la stessa immagine
  }

  ResetForm(form : NgForm){
    form.resetForm();
    this.currentRating={value : 1, stars: Array.from({length : this.numberOfStars}, (_,i) => i < 1 ? 'starFull' : 'starEmpty')};
  }

  SearchForReview(){
    return this.ReviewService.SearchUserReview() > -1 ? true : false;
  }

  SubmitReview(form : NgForm){
    //Prendi dati, crei review e invia a ReviewList e poi backEnd
    let review : review = 
    { idVideogame : this.VideogameManagerService.selectedVideogame.id, 
      title : form.value['title'],
      username : 'stocazzo',//da cambiare con il valore del currentUser
      comment : form.value['description'],
      rating : this.currentRating.value,
      likes : 0
    }
    //QUANDO GUARDI QUESTO METODO RICORDATI DI ANDARE A CAMBIARE GLI INPUT CON IL SERVICE CHRI
    this.ReviewService.AddReviewData(review);
    this.ResetForm(form);
  }


}

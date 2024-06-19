import { Component, OnInit,Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { GeneralTasksService } from '../../services/general-tasks.service';
import { VideogameReviewsService } from '../../services/videogame-reviews.service';
import { review } from '../../model/Review';
import { VideogameDataService } from '../../services/videogame-data.service';
import { HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrl: './add-review.component.css'
})
export class AddReviewComponent implements OnInit {

  numberOfStars=5;
  currentRating = {value : 1, stars : Array.from({length : this.numberOfStars}, (_,i) => i < 1 ? 'starFull' : 'starEmpty')};
  UserImage : number;
  User=localStorage.getItem("user");
  @Input() videogameId : number;

  constructor(private generalTasks : GeneralTasksService, private ReviewService : VideogameReviewsService, private VideogameManagerService : VideogameDataService) {}

  ngOnInit(): void {
    this.UserImage=this.generalTasks.getRandomValue(6); //possibile cosa carina da fare, settare un valore per User qui nel front per dargli sempre la stessa immagine
  }

  ResetForm(form : NgForm){
    form.resetForm();
    this.currentRating={value : 1, stars: Array.from({length : this.numberOfStars}, (_,i) => i < 1 ? 'starFull' : 'starEmpty')};
  }

  SearchForReview(){
    if(this.User != undefined)
      return this.ReviewService.SearchUserReview(this.User) > -1 ? true : false;
    return false;
  }

  SubmitReview(form : NgForm){
    //Prendi dati, crei review e invia a ReviewList e poi backEnd
    let user;
    if(this.User != undefined)
      user = this.User;
    else
      user = "NotLogged";

    let review : review = 
    { videogioco : this.videogameId, 
      username :  user,
      voto : this.currentRating.value,
      commento : form.value['description'],
      likes : 0,
      titolo : form.value['title'].trim()
    }
    
    this.ReviewService.AddReviewData(review).subscribe({
      next: () => {
        this.ReviewService.AddToReviewList(review);
        this.VideogameManagerService.UpdateRating();
      },
      error: (error : HttpErrorResponse) => {
        alert(error.error);
      }
    });

    this.ResetForm(form);
  }


}

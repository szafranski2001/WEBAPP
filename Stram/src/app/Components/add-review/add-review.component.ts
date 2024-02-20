import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { GeneralTasksService } from '../../services/general-tasks.service';
import { review } from '../model/Review';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrl: './add-review.component.css'
})
export class AddReviewComponent implements OnInit {

  numberOfStars=5;
  currentRating = {value : 1, stars : Array.from({length : this.numberOfStars}, (_,i) => i < 1 ? 'starFull' : 'starEmpty')};
  UserImage : number;

  constructor(private generalTasks : GeneralTasksService) {}

  ngOnInit(): void {
    this.UserImage=this.generalTasks.getRandomValue(6);
  }

  ResetForm(form : NgForm){
    form.resetForm();
    this.currentRating={value : 1, stars: Array.from({length : this.numberOfStars}, (_,i) => i < 1 ? 'starFull' : 'starEmpty')};
  }

  SubmitReview(form : NgForm){
    //Prendi dati, crei review e invia a ReviewList e poi backEnd
    this.ResetForm(form);
  }


}

import { Component, OnInit } from '@angular/core';
import { Form } from '@angular/forms';
import { GeneralTasksService } from '../../services/general-tasks.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrl: './add-review.component.css'
})
export class AddReviewComponent implements OnInit {

  currentRating : number = 1;
  UserImage : number;

  constructor(private generalTasks : GeneralTasksService) {}

  ngOnInit(): void {
    this.UserImage=this.generalTasks.getRandomValue(6);
  }

  SubmitReview(form : Form){
    console.log(form);
  }

}

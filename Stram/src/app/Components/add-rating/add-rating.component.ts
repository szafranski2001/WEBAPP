import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-add-rating',
  templateUrl: './add-rating.component.html',
  styleUrl: './add-rating.component.css'
})
export class AddRatingComponent{

  currentRating : number = 1;
  Stars : string[]=['starFull','starEmpty','starEmpty','starEmpty','starEmpty'];
  

  HoverStars(rating : number) {
    for(let i=0; i<5; i++)
      i < rating ? this.Stars[i]='starFull' : this.Stars[i]='starEmpty';
  }

  Rate(rating : number){
    this.currentRating=rating;
  }

}

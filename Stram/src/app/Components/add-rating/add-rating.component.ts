import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-rating',
  templateUrl: './add-rating.component.html',
  styleUrl: './add-rating.component.css'
})
export class AddRatingComponent implements OnInit {

  currentRating : number = 0;
  Stars : string[]=['starFull','starEmpty','starEmpty','starEmpty','starEmpty'];
  

  ngOnInit(): void {

  }

  HoverStars(index: number) {
    for(let i=0; i<5; i++)
      i <= index ? this.Stars[i]='starFull' : this.Stars[i]='starEmpty';
  }

  Rate(rating : number){
    this.currentRating=rating-1;
  }

}

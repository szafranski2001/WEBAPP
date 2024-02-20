import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-add-rating',
  templateUrl: './add-rating.component.html',
  styleUrl: './add-rating.component.css'
})
export class AddRatingComponent{

  @Input() Rating : {value : number, stars : Array<string>};

  DinamicUpdateStars(rating : number) {
    for(let i=0; i<5; i++)
      i < rating ? this.Rating.stars[i]='starFull' : this.Rating.stars[i]='starEmpty';
  }

  Rate(value : number){
    this.Rating.value=value;
  }

}

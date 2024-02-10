import { Component, Input, OnInit } from '@angular/core';
import { genere, videogame } from '../model/Videogame';
import { VideogameDataService } from '../services/videogame-data.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-videogame-detail',
  templateUrl: './videogame-detail.component.html',
  styleUrl: './videogame-detail.component.css'
})
export class VideogameDetailComponent implements OnInit {
  
  videogame: videogame;
  videogameImageURL = '/assets/images/videogames/';

  constructor(private videogameData: VideogameDataService, private route : ActivatedRoute) {}

  ngOnInit(): void {
    this.videogame=this.videogameData.getData(parseInt(this.route.snapshot.paramMap.get('id')!));
    this.videogameImageURL= this.videogameImageURL+this.videogame.id+".png";
  }

  getGenereColor(){
    switch (this.videogame.genere){
      case genere.FPS:
        return 'var(--fps-color)';
      case genere.MOBA:
        return 'var(--moba-color)';
      case genere.Sandbox:
        return 'var(--sandbox-color)';
      case genere.MMORPG:
        return 'var(--mmorpg-color)';
      default:
        return 'var(--systemGray)';
    }
  }

}

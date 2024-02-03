import { Component, Input, OnInit } from '@angular/core';
import { videogame,genere } from '../model/Videogame';
import { VideogameDataService } from '../services/videogame-data.service';

@Component({
  selector: 'app-videogame-detail',
  templateUrl: './videogame-detail.component.html',
  styleUrl: './videogame-detail.component.css'
})
export class VideogameDetailComponent implements OnInit {
  @Input() videogameId:number;
  
  videogame: videogame;

  constructor(private videogameData: VideogameDataService) {}
  ngOnInit(): void {
    this.videogame=this.videogameData.getData(this.videogameId);
  }


}

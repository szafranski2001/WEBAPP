import { ChangeDetectorRef,NgZone, Component,ElementRef,OnInit, ViewChild, OnChanges, SimpleChanges } from '@angular/core';
import { genere, videogame } from '../model/Videogame';
import { VideogameDataService } from '../../services/videogame-data.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-videogame-detail',
  templateUrl: './videogame-detail.component.html',
  styleUrl: './videogame-detail.component.css'
})
export class VideogameDetailComponent implements OnInit{
  
  videogame: videogame;
  videogameImageURL = '/assets/images/videogames/';
  Genre=Object.values(genere);

  constructor(private videogameData: VideogameDataService, private route : ActivatedRoute,private cdr: ChangeDetectorRef,private ngZone : NgZone) {}

  ngOnInit(): void {
    this.videogame=this.videogameData.getData(parseInt(this.route.snapshot.paramMap.get('id')!));
    this.videogameImageURL= this.videogameImageURL+this.videogame.id+".png";
  }

  getEditMode(){
    return this.videogameData.isEditMode();
  }

  getGenereColor(){
    return 'var(--'+this.videogame.genere.toLowerCase()+'-color)';
  }

  ToTheTopPressed(){
    window.scrollTo({top:0,behavior:'smooth'});
  }

  HandleUpdateData(){
    this.videogameData.UpdateData();
  }

}

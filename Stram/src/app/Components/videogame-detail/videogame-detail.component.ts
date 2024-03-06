import { Component,OnInit } from '@angular/core';
import { genere, videogame } from '../model/Videogame';
import { VideogameDataService } from '../../services/videogame-data.service';
import { ActivatedRoute } from '@angular/router';
import { GeneralTasksService } from '../../services/general-tasks.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-videogame-detail',
  templateUrl: './videogame-detail.component.html',
  styleUrl: './videogame-detail.component.css'
})
export class VideogameDetailComponent implements OnInit{
  
  videogame: videogame;
  videogameImageURL = '/assets/images/videogames/';
  Genre=Object.values(genere);

  constructor(private videogameData: VideogameDataService, private route : ActivatedRoute, private generalTasks : GeneralTasksService) {}

  ngOnInit(): void {
    let id=parseInt(this.route.snapshot.paramMap.get('id')!);
    //this.videogameData.GetVideogameImage(id).subscribe( (response) => );
    this.videogameData.GetVideogameDetails(id).subscribe({
    next: (response) => {
      this.videogame=response;
      this.videogameData.SetSelectedVideogame(response);
    },
    error: () => {
      alert("Errore durante il caricamento del gioco");
    }

    }); //da cambiare non appena riesco a prendere i dati dal backend
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
    let dataElements=document.getElementsByClassName("data-element");
    //Si formattano i dati per validare gli input dell'utente
    
    let titolo=String((dataElements[0] as HTMLElement).innerText);
    this.videogame.titolo=this.generalTasks.formatData(titolo,50);

    let casaP=String((dataElements[1] as HTMLElement).innerText);
    this.videogame.casaP=this.generalTasks.formatData(casaP,64);

    let durata=Number((dataElements[2] as HTMLElement).innerText);
    (Number.isNaN(durata) || durata > 9999 || durata <= 0 ) ? this.videogame.durata = 0 : this.videogame.durata=durata;

    let anno=Number((dataElements[3] as HTMLElement).innerText);
    (Number.isNaN(anno) || anno > 9999 || anno <= 1950 ) ? this.videogame.anno= 9999 : this.videogame.anno=anno;

    let descrizione=String((dataElements[4] as HTMLElement).innerText);
    this.videogame.descrizione=this.generalTasks.formatData(descrizione,3000);
  }

  UserLogged(){
    //prendere il return del UserLogged dal service di login
    return true;
  }

}

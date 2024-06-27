import { Component,OnInit } from '@angular/core';
import { genere, videogame } from '../../model/Videogame';
import { VideogameDataService } from '../../services/videogame-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { GeneralTasksService } from '../../services/general-tasks.service';
import { NotFoundError } from '../../model/Message';

@Component({
  selector: 'app-videogame-detail',
  templateUrl: './videogame-detail.component.html',
  styleUrl: './videogame-detail.component.css'
})
export class VideogameDetailComponent implements OnInit{
  
  videogame: videogame;
  Genre=Object.values(genere);
  User = localStorage.getItem("user");

  constructor(private videogameData: VideogameDataService, private route : ActivatedRoute, private generalTasks : GeneralTasksService, private router : Router) {}

  ngOnInit(): void {
    let id=parseInt(this.route.snapshot.paramMap.get('id')!);

    this.videogameData.GetVideogameDetails(id).subscribe({
    next: (response) => {
      this.videogame=response;
      this.videogameData.SetSelectedVideogame(response);
    },
    error: () => {
      alert(NotFoundError);
      this.router.navigate(["/"]); 
    }

    });
  }
  
  getEditMode(){
    return this.videogameData.isEditMode();
  }

  getGenereColor(){
    return 'var(--'+this.videogame.genere.toLowerCase()+'-color)';
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
}

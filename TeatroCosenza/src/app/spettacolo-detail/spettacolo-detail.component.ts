import { Component, OnInit } from '@angular/core';
import { spettacolo } from '../spettacolo';
import { SpettacoloDataService } from '../spettacolo-data.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-spettacolo-detail',
  templateUrl: './spettacolo-detail.component.html',
  styleUrl: './spettacolo-detail.component.css'
})
export class SpettacoloDetailComponent implements OnInit{

  spettacolo !: spettacolo;

  constructor(
    private spettacoloDetailService: SpettacoloDataService,
    private route: ActivatedRoute
    ){}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    // Chiama il servizio per ottenere lo spettacolo con l'ID specificato
    this.spettacoloDetailService.getSpettacolo(id)
      .subscribe(spettacolo => {
        this.spettacolo = spettacolo;
      });
  } 
}

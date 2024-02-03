import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-videogame',
  templateUrl: './videogame.component.html',
  styleUrl: './videogame.component.css'
})
export class VideogameComponent implements OnInit{
  id:number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.id=parseInt(this.route.snapshot.paramMap.get('id')!);
  }
}

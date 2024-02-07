import { Component } from '@angular/core';
import {SingleGameInfo} from "../model/SingleGameInfo";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  topGames: SingleGameInfo[] = [
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
  ];

  games: SingleGameInfo[] = [
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4 },
  ];
}


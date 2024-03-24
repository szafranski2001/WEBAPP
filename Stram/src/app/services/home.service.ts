import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {SingleGameInfo} from "../model/SingleGameInfo";
import {ResultStatusOnly} from "../model/ResultStatusOnly";

@Injectable({
  providedIn: 'root'
})
export class HomeService {


  constructor(private http: HttpClient) { }


  topGames: SingleGameInfo[] = [
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: false },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: true },
  ];

  games: SingleGameInfo[] = [
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 1, isFavourite: false  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: false  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 5, isFavourite: true  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: false  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: true  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: false  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 5, isFavourite: false  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 0, isFavourite: true  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 1, isFavourite: false  },
    { id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: true  },
  ];

  isLogged = true;
  backend: string = "http://localhost:8080/"

  changeFavoriteFlag(game: SingleGameInfo ) {

    if (this.isLogged) {
      game.isFavourite = !game.isFavourite;

      //update the db
      const cmd: string = "/edit-favourite"

      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
      };

      const params = {
        id: game.id,
        isFavourite: !game.isFavourite
      };

      console.log(params);

      let result: ResultStatusOnly;

      return this.http.post<ResultStatusOnly>(this.backend + cmd, params, {...httpOptions});

    } else {

      //todo show popup component or redirect to login
      alert("To perform this action, is required to have done the login")
      return undefined;
    }
  }
}

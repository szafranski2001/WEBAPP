import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {SingleGameInfo} from "../model/SingleGameInfo";
import {ResultStatusOnly} from "../model/ResultStatusOnly";

@Injectable({
  providedIn: 'root'
})
export class HomeService {


  constructor(private http: HttpClient) { }


  topGame: SingleGameInfo = { rank: 1, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true };

  firstSlider: SingleGameInfo[] = [
    { rank: 1, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true },
    { rank: 2, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/1.png', rate: 1, isFavourite: false  },
    { rank: 3, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/3.png', rate: 4, isFavourite: true  },
    { rank: 4, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: false  },
    { rank: 5, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/1.png', rate: 5, isFavourite: true  },
    { rank: 6, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/3.png', rate: 3, isFavourite: false  },
    { rank: 7, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: true  },
    { rank: 8, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: false  },
    { rank: 9, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/1.png', rate: 4, isFavourite: true  },
    { rank: 10, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/3.png', rate: 5, isFavourite: false  },
  ];
  secondSlider: SingleGameInfo[] = [
    { rank: 1, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true },
    { rank: 2, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/1.png', rate: 1, isFavourite: false  },
    { rank: 3, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/3.png', rate: 4, isFavourite: true  },
    { rank: 4, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: false  },
    { rank: 5, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/1.png', rate: 5, isFavourite: true  },
    { rank: 6, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/3.png', rate: 3, isFavourite: false  },
    { rank: 7, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: true  },
    { rank: 8, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/1.png', rate: 2, isFavourite: false  },
    { rank: 9, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/3.png', rate: 4, isFavourite: true  },
    { rank: 10, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 5, isFavourite: false  },
  ];

  isLogged = true;
  backend: string = "http://localhost:8080/"


  public getTopGame() { return this.topGame }
  public getFirstSlider() { return this.firstSlider}
  public getSecondSlider() { return this.secondSlider}

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

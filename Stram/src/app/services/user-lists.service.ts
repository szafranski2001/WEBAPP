import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {SingleGameInfo} from '../model/SingleGameInfo';

@Injectable({
  providedIn: 'root'
})
export class UserListsService {

  private BackEndURL="http://localhost:8080/api";

  User = localStorage.getItem("user");

  constructor(private http : HttpClient) { }

  favoriteSlider: SingleGameInfo[] = [
    { rank: 1, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true },
    { rank: 2, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 1, isFavourite: false  },
    { rank: 3, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true  },
    { rank: 4, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: false  },
    { rank: 5, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 5, isFavourite: true  },
    { rank: 6, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: false  },
    { rank: 7, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: true  },
    { rank: 8, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: false  },
    { rank: 9, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true  },
    { rank: 10, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 5, isFavourite: false  },
  ];

  wishSlider: SingleGameInfo[] = [
    { rank: 1, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true },
    { rank: 2, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 1, isFavourite: false  },
    { rank: 3, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true  },
    { rank: 4, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: false  },
    { rank: 5, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 5, isFavourite: true  },
    { rank: 6, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 3, isFavourite: false  },
    { rank: 7, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: true  },
    { rank: 8, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 2, isFavourite: false  },
    { rank: 9, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 4, isFavourite: true  },
    { rank: 10, id: 1, name: 'Minecraft', imgUrl: 'assets/images/videogames/0.png', rate: 5, isFavourite: false  },
  ];

  public getFavoriteSlider() { return this.favoriteSlider}
  public getWishSlider() { return this.wishSlider}

  getUserFavoriteList(){
    return this.http.get<SingleGameInfo[]>(this.BackEndURL+"/GetVideogameInPreferredList/");
  }

  getUserWishList(){
    return this.http.get<SingleGameInfo[]>(this.BackEndURL+"/GetVideogameInWishList/");
  }

  isVideogameInFavoriteList(videogameId : number){
    //verifica che il videogioco si trovi nella lista dei favoriti dell'utente corrente
    return this.http.post<boolean>(this.BackEndURL+"/GetVideogameInPreferredList/"+videogameId,this.User);
  }

  isVideogameInWishList(videogameId : number){
    //verifica che il videogioco si trovi nella lista dei desideri dell'utente corrente
    return this.http.post<boolean>(this.BackEndURL+"/GetVideogameInWishList/"+videogameId,this.User);
  }

  addVideoGameToFavoriteList(videogameId : number){
    return this.http.post(this.BackEndURL+"/AddVideogameInPreferredList/"+videogameId,this.User);
  }

  addVideoGameToWishList(videogameId : number){
    return this.http.post(this.BackEndURL+"/AddVideogameInWishList/"+videogameId,this.User);
  }

  removeVideogameFromFavoriteList(videogameId : number){
    const options = { body : this.User }
    return this.http.delete(this.BackEndURL+"/RemoveVideogameInPreferredList/"+videogameId,options);
  }

  removeVideogameFromWishList(videogameId : number){
    const options = { body : this.User }
    return this.http.delete(this.BackEndURL+"/RemoveVideogameInWishList/"+videogameId,options);
  }

}

import {Component, OnInit} from '@angular/core';
import {UserListsService} from '../../services/user-lists.service';
import {SingleGameInfo} from '../../model/SingleGameInfo';

@Component({
  selector: 'app-videogame-lists',
  templateUrl: './videogame-lists.component.html',
  styleUrl: './videogame-lists.component.css'
})

export class VideogameListsComponent implements OnInit {
  user = localStorage.getItem("user");
  favoriteSlider : SingleGameInfo[];
  wishSlider : SingleGameInfo[];
  favorite = "Lista dei preferiti"
  wish = "Lista dei desideri"


  constructor(private listsService: UserListsService) {}

  ngOnInit(): void {
    this.loadLists();
  }


  loadLists(): void{
    // @ts-ignore
    this.listsService.getUserFavoriteList(this.user).subscribe(response =>{
      this.favoriteSlider = response;
    },
      error => {
        console.error('Error fetching preferred games', error);
      }
    );
    // @ts-ignore
    this.listsService.getUserWishList(this.user).subscribe(response =>{
      this.wishSlider = response;
    },
      error => {
        console.error('Error fetching wished games', error);
      }
    );
  }
}

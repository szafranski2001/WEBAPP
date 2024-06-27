import {Component, OnInit} from '@angular/core';
import {UserListsService} from '../../services/user-lists.service';
import {SingleGameInfo} from '../../model/SingleGameInfo';
import {Observable} from "rxjs";

@Component({
  selector: 'app-videogame-lists',
  templateUrl: './videogame-lists.component.html',
  styleUrl: './videogame-lists.component.css'
})

export class VideogameListsComponent implements OnInit {
  favoriteSlider = this.userListsService.getFavoriteSlider();
  wishSlider = this.userListsService.getWishSlider();
  favorite = "Lista dei preferiti"
  wish = "Lista dei desideri"



  constructor(private userListsService: UserListsService) {}

  ngOnInit(): void {
//    this.loadLists();
  }
/*
  loadLists(): void{
    this.userListsService.getUserFavoriteList().subscribe(response =>{
      this.favoriteList = response;
    });
    this.userListsService.getUserWishList().subscribe(response =>{
      this.wishList = response;
    });
  }
*/
}

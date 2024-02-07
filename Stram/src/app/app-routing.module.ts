import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { GuidelinesComponent } from './guidelines/guidelines.component';
import { VideogameDetailComponent } from './videogame-detail/videogame-detail.component';
import { VideogameListsComponent} from "./videogame-lists/videogame-lists.component";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'profile', component:ProfileComponent},
  {path: 'login', component: LoginComponent},
  {path: 'guidelines', component:GuidelinesComponent},
  {path: 'videogame/:id', component:VideogameDetailComponent},
  {path: 'videogame-lists', component: VideogameListsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

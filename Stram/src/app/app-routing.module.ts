import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './Components/profile/profile.component';
import { GuidelinesComponent } from './Components/guidelines/guidelines.component';
import { VideogameDetailComponent } from './Components/videogame-detail/videogame-detail.component';
import { VideogameListsComponent} from "./Components/videogame-lists/videogame-lists.component";
import {HomeComponent} from "./Components/home/home.component";
import {LoginComponent} from "./Components/login/login.component";
import { NotfoundComponent } from './Components/notfound/notfound.component';
import {SignupComponent} from "./Components/signup/signup.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'profile', component:ProfileComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'guidelines', component:GuidelinesComponent},
  {path: 'videogame/:id', component:VideogameDetailComponent},
  {path: 'videogame-lists', component: VideogameListsComponent},
  {path: '404', component: NotfoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

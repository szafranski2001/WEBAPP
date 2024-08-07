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
import {AddGameComponent} from "./Components/add-game/add-game.component";
import { AdminReportsComponent } from './Components/admin-reports/admin-reports.component';
import {adminGuard, authGuard, NoAuthGuard} from './services/auth.guard';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'addGame', component: AddGameComponent, canActivate:[authGuard,adminGuard]},
  {path: 'profile', component:ProfileComponent,canActivate:[authGuard]},
  {path: 'signup', component: SignupComponent, canActivate:[NoAuthGuard]},
  {path: 'login', component: LoginComponent, canActivate:[NoAuthGuard]},
  {path: 'guidelines', component:GuidelinesComponent},
  {path: 'videogame/:id', component:VideogameDetailComponent},
  {path: 'videogame-lists', component: VideogameListsComponent,canActivate:[authGuard]},
  {path: 'reports-handling', component: AdminReportsComponent,canActivate:[authGuard,adminGuard]},
  {path: '404', component: NotfoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

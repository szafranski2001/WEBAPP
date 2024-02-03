import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { GuidelinesComponent } from './guidelines/guidelines.component';
import { VideogameComponent } from './videogame/videogame.component';

const routes: Routes = [
  {path:'profile', component:ProfileComponent},
  {path:'guidelines', component:GuidelinesComponent},
  {path:'videogame/:id', component:VideogameComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { GuidelinesComponent } from './guidelines/guidelines.component';
import { VideogameDetailComponent } from './videogame-detail/videogame-detail.component';

const routes: Routes = [
  {path:'profile', component:ProfileComponent},
  {path:'guidelines', component:GuidelinesComponent},
  {path:'videogame/:id', component:VideogameDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { GuidelinesComponent } from './guidelines/guidelines.component';
import { HeaderComponent } from './header/header.component';
import { ProfileComponent } from './profile/profile.component';
import { VideogameComponent } from './videogames/videogame.component';
import { VideogameDetailComponent } from './videogame-detail/videogame-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    GuidelinesComponent,
    HeaderComponent,
    ProfileComponent,
    VideogameComponent,
    VideogameDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { GuidelinesComponent } from './guidelines/guidelines.component';
import { HeaderComponent } from './header/header.component';
import { ProfileComponent } from './profile/profile.component';
import { VideogameDetailComponent } from './videogame-detail/videogame-detail.component';
import { VideogameReviewsComponent } from './videogame-review/videogame-review.component';
import { VideogameToolbarComponent } from './videogame-toolbar/videogame-toolbar.component';
import { VideogameListsComponent } from './videogame-lists/videogame-lists.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    GuidelinesComponent,
    HeaderComponent,
    ProfileComponent,
    VideogameDetailComponent,
    VideogameReviewsComponent,
    VideogameToolbarComponent,
    VideogameListsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { FooterComponent } from './Components/footer/footer.component';
import { GuidelinesComponent } from './Components/guidelines/guidelines.component';
import { HeaderComponent } from './Components/header/header.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { VideogameDetailComponent } from './Components/videogame-detail/videogame-detail.component';
import { VideogameReviewsComponent } from './Components/videogame-review/videogame-review.component';
import { VideogameToolbarComponent } from './Components/videogame-toolbar/videogame-toolbar.component';
import { VideogameListsComponent } from './Components/videogame-lists/videogame-lists.component';
import { RatingComponent } from './Components/rating/rating.component';
import { HomeComponent } from './Components/home/home.component';
import { LoginComponent } from './Components/login/login.component';
import { NotfoundComponent } from './Components/notfound/notfound.component';
import { ReviewToolbarComponent } from './Components/review-toolbar/review-toolbar.component';
import { AddRatingComponent } from './Components/add-rating/add-rating.component';
import { AddReviewComponent } from './Components/add-review/add-review.component';

import { MatIconModule } from '@angular/material/icon';
import { MatIconRegistry } from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { SignupComponent } from './Components/signup/signup.component';
import { AddGameComponent } from './Components/add-game/add-game.component';
import { CardComponent } from './Components/card/card.component';
import {NgOptimizedImage} from "@angular/common";
import { SliderComponent } from './Components/slider/slider.component';

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
    VideogameListsComponent,
    RatingComponent,
    HomeComponent,
    LoginComponent,
    NotfoundComponent,
    ReviewToolbarComponent,
    AddRatingComponent,
    AddReviewComponent,
    SignupComponent,
    AddGameComponent,
    CardComponent,
    SliderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatIconModule,
    FormsModule,
    MatProgressSpinnerModule,
    HttpClientModule,
    NgOptimizedImage
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private iconRegistry: MatIconRegistry) {
    iconRegistry.setDefaultFontSetClass('material-symbols-outlined');
  }
}

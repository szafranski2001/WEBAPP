import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { GuidelinesComponent } from './guidelines/guidelines.component';
import { HeaderComponent } from './header/header.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ProfileComponent } from './profile/profile.component';
import { SpettacoloDetailComponent } from './spettacolo-detail/spettacolo-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    GuidelinesComponent,
    HeaderComponent,
    NavBarComponent,
    ProfileComponent,
    SpettacoloDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

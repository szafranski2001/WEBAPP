import { Component } from "@angular/core";
import { MatIconRegistry } from "@angular/material/icon";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Stram';

  constructor(private matRegistryIcon : MatIconRegistry, private DomSanitizer : DomSanitizer){
    this.matRegistryIcon.addSvgIcon(
      'starEmpty',
      this.DomSanitizer.bypassSecurityTrustResourceUrl('assets/images/rating/starEmpty.svg')
      )

    this.matRegistryIcon.addSvgIcon(
      'starFull',
      this.DomSanitizer.bypassSecurityTrustResourceUrl("../assets/images/rating/starFull.svg")
    )
  }
}

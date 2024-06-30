import { Component, ElementRef, HostListener, ViewChild, OnInit, AfterViewInit, QueryList, ViewChildren } from '@angular/core';
import { SingleGameInfo } from "../../model/SingleGameInfo";
import { ResultStatusOnly } from "../../model/ResultStatusOnly";
import { HomeService } from "../../services/home.service";
import { FileUploadModule } from 'primeng/fileupload';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  ngOnInit() {
  }
  constructor(protected home: HomeService) {}

  isLogged = true;
  topGame = this.home.getTopGame();
  firstSlider = this.home.getFirstSlider();
  secondSlider = this.home.getSecondSlider();
  firstTitle: String = "Top Games"
  secondTitle: String = "Adventures"


}

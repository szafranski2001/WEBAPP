import { Component } from '@angular/core';

@Component({
  selector: 'app-to-the-top-button',
  templateUrl: './to-the-top-button.component.html',
  styleUrl: './to-the-top-button.component.css'
})
export class ToTheTopButtonComponent {


  ToTheTopPressed(){
    window.scrollTo({top:0,behavior:'smooth'});
  }
  
}

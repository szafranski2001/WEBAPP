import {Component, OnInit} from '@angular/core';
import { User } from '../../model/User';
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  user : User;

  ngOnInit(): void {
  }


}

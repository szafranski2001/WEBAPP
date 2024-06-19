import {Component, OnInit} from '@angular/core';
import {User, UserCredentials} from '../../model/User';
import {NgForm} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  user : UserCredentials;
  constructor( private service :LoginService) {

  }
  ngOnInit(): void {

  }
  onSubmit(form: NgForm) {
    const username = form.value['username'];
    const password = form.value['password'];

    this.user = { username: username, password: password };

    console.log(username, password);
    this.service.doLogin(this.user).subscribe(response => {
      // Se il login ha successo, reindirizza l'utente
      this.router.navigate(['/profile']); // Modifica il percorso secondo le tue esigenze
    }, error => {
      // Gestisci l'errore di login qui, ad esempio mostrando un messaggio di errore
      console.error('Login failed', error);
    });

    form.reset();
  }

}

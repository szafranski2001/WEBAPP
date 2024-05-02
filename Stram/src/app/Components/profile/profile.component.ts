import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {UserCredentials} from "../../model/User";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  user: UserCredentials;

  constructor(private http: HttpClient) { }

  onSubmit(form: NgForm) {
    const nome = form.value['changeNameBar'];
    const cognome = form.value['changeSurnameBar'];
    const email = form.value['changeEmailBar'];
    const password = form.value['changePasswordBar'];
    const confirmPassword = form.value['confirmPasswordBar'];

    // Costruisco un oggetto con i dati da inviare al backend
    const userData= {
      nome: nome,
      cognome: cognome,
      email: email,
      password: password,
      confirmPassword: confirmPassword,
      username: this.user.username
    };

    // Invio i dati al backend
    this.http.post<any>('/api/profile/update', JSON.stringify(userData)).subscribe(
      response => {
        console.log('Dati aggiornati con successo:', response);
      },
      error => {
        console.error('Si è verificato un errore', error);
      }
    );
  }
}

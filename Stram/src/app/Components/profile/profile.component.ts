import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {UserCredentials} from "../../model/User";
import { GeneralTasksService } from '../../services/general-tasks.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user= localStorage.getItem("user");

  private BackEndUrl = "http://localhost:8080"

  userImage : number;

  constructor(private http: HttpClient, private generalTasks : GeneralTasksService, private router : Router) { }


  ngOnInit(): void {
    this.userImage=this.generalTasks.getRandomValue(6);
  }


  onSubmit(form: NgForm) {
    let nome = form.value['changeNameBar'];
    let cognome = form.value['changeSurnameBar'];
    const email = form.value['changeEmailBar'];
    const password = form.value['changePasswordBar'];
    const confirmPassword = form.value['confirmPasswordBar'];

    // Costruisco un oggetto con i dati da inviare al backend
    let userData= {
      nome: nome,
      cognome: cognome,
      email: email,
      password: password,
      confirmPassword: confirmPassword,
      username: this.user
    };

    if(this.user != null){
      this.http.post<any>(this.BackEndUrl+'/api/profile/update', JSON.stringify(userData)).subscribe(
        response => {
          alert('Dati aggiornati con successo');
          this.router.navigate(["/"])
        },
        error => {
          alert("Si è verificato un errore durante l' aggiornamento \n(Password non corrisponde o Email non valida)");
        }
      );
    }
    // Invio i dati al backend
  }

  isSomethingNotEmpty(form : NgForm){
    return form.value['changeNameBar'] != "" || form.value['changeSurnameBar'] != "" || form.value['changeEmailBar'] != "" || (form.value['changePasswordBar'] != "" &&  form.value['confirmPasswordBar']);
  }
  

}

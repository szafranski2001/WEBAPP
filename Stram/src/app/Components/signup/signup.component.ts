import {Component, OnInit} from '@angular/core';
import {NgForm} from "@angular/forms";
import {analyticsDisabled} from "@angular/cli/src/utilities/environment-options";
import {User, UserCredentials} from "../../model/User";
import {SignupService} from "../../services/signup.service";

import {LoginService} from "../../services/login.service";


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent  implements OnInit{

  protected readonly onsubmit = onsubmit;
  user : User;

  constructor(private service: SignupService, private serviceLogin:LoginService) {

  }
  ngOnInit(): void {
  }

  onSubmit(form: NgForm)
  {

    const name = form.value.name
    const surname = form.value.surname
    const username= form.value.username
    const password = form.value.password
    const email = form.value.email
    const question = form.value.question
    const answer = form.value.answer
    this.user = {cognome: surname, domanda: question, email: email, nome: name, password: password, risposta: answer, tipologia: 0, username: username}
    //controllare input utente se valido
    this.service.doSignUp(this.user).subscribe(response=>{
      if (response)
      {
        let userC :  UserCredentials={password: password, username: username}
        this.serviceLogin.doLogin(userC)
      }
    })

    form.reset()
  }
}

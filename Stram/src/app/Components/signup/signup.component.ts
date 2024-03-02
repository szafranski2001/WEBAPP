import {Component, OnInit} from '@angular/core';
import {NgForm} from "@angular/forms";
import {analyticsDisabled} from "@angular/cli/src/utilities/environment-options";


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent  implements OnInit{

  protected readonly onsubmit = onsubmit;
  constructor() {
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
    console.log(name,surname,username,password,email,question,answer)
    form.reset()
  }
}

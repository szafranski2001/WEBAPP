import {Component, NgIterable, OnInit} from '@angular/core';
import {genere, videogame} from "../../model/Videogame";
import {core} from "@angular/compiler";
import {dateTimestampProvider} from "rxjs/internal/scheduler/dateTimestampProvider";
import {Observable, Subject, tap} from "rxjs";
import {AddGameService} from "../../services/add-game.service";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {FormBuilder, FormGroup, FormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrl: './add-game.component.css'
})
export class AddGameComponent implements OnInit {
  gameForm: FormGroup;
  categories: string[] = ['Azione', 'Avventura', 'Puzzle', 'Sport'];
  selectedFile: File | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient, protected addGameService: AddGameService) {
    this.gameForm = this.fb.group({
      title: ['', Validators.required],
      duration: ['', [Validators.required, Validators.min(1)]],
      category: ['', Validators.required],
    });
  }


  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }
  }

  onSubmit() {
    if (this.gameForm.invalid) {
      return;
    }

    const formData = new FormData();
    formData.append('title', this.gameForm.get('title')?.value);
    formData.append('duration', this.gameForm.get('duration')?.value);
    formData.append('category', this.gameForm.get('category')?.value);
    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    this.http.post('http://localhost:8080/addGame', formData).subscribe(response => {
      console.log('Risposta del server', response);
    }, (error: any) => {
      console.error('Errore durante l\'invio', error);
    });
  }

  ngOnInit() {
    this.requestOptionsToTheServer();

  }


  newVideogame = {} as videogame;

  genereOptions: string[] = [];
  annoOptions: number[] = [];
  caseProduttriciOptions: string[] = [];








  private requestOptionsToTheServer() {

    this.addGameService.getOptionsForAddNewVideoGame().subscribe(
      {
        next: (data) => {

          let fromYear: number = parseInt(data.fromYear[0]);
          let currentYear: number = new Date().getFullYear();

          for (let i = fromYear; i <= currentYear; i++) {
            this.annoOptions.push(i);
          }

          this.annoOptions.reverse();
          this.genereOptions = data.genres;
          this.caseProduttriciOptions = data.caseProduttrici;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error);
        }
      }
    );
  }




}

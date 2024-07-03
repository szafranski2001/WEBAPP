import {Component, OnInit} from '@angular/core';
import {genere} from "../../model/Videogame";

import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, FormsModule, Validators} from '@angular/forms';
import { NgxImageCompressService } from 'ngx-image-compress';
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrl: './add-game.component.css'
})
export class AddGameComponent implements OnInit {

  gameForm: FormGroup;
  generi = Object.values(genere);
  verticalImg: String;
  horizontalImg: String;

  vEstenzione: string;
  hEstensione: string;



  constructor(private fb: FormBuilder, private http: HttpClient, private imageCompress: NgxImageCompressService, protected route: Router) {
    this.gameForm = new FormGroup({
      title: new FormControl('',
        [ Validators.required,
                        Validators.minLength(2)]),
      descrizione: new FormControl('',
        [ Validators.required]),
      genere: new FormControl('',
        [ Validators.required]),
      duration: new FormControl('',
        [ Validators.required]),
      anno: new FormControl('',
            [Validators.required,
                          Validators.min(1950),
                          Validators.max(2050)]),
      valutazione: new FormControl('',
            [Validators.required,
                           Validators.min(0),
                           Validators.max(5)]),
      trailer: new FormControl('',
            [Validators.required]),
      casa: new FormControl('',
            [Validators.required]),
      verticalposter: new FormControl('',
            [ Validators.required]),
      horizontalposter: new FormControl('',
            [ Validators.required])
    });
  }

  validateNumberInput(event: Event) {
    const input = event.target as HTMLInputElement;
    input.value = input.value.replace(/[^0-9]/g, '');
    this.gameForm.get(input.id)?.setValue(input.value); // Update the form control value
  }

  preventNonNumericalInput(event: KeyboardEvent) {
    if (!/[0-9]/.test(event.key)) {
      event.preventDefault();
    }
  }

  onFileChange(event: any) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length) {
      const file = input.files[0];
      this.gameForm.patchValue({
        verticalposter: file
      });
    }

    const files = event.target.files;
    if (files.length > 0) {
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();
        reader.onload = (e: any) => {
          const image = e.target.result;

          this.imageCompress
            .compressFile(image, -1, 50, 50) // 50% ratio, 50% quality
            .then((compressedImage: any) => {
              this.verticalImg = compressedImage;
              console.log("Base64 " )
            })
            .catch((error: any) => {
              console.log("image compress " + error);
            });
        };
        reader.readAsDataURL(file);
      }
    }

  }

  onFileChange2(event: any) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length) {
      const file = input.files[0];
      this.gameForm.patchValue({
        horizontalposter: file
      });
    }

    const files = event.target.files;
    if (files.length > 0) {
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();
        reader.onload = (e: any) => {
          const image = e.target.result;

          this.imageCompress
            .compressFile(image, -1, 50, 50) // 50% ratio, 50% quality
            .then((compressedImage: any) => {
              this.horizontalImg = compressedImage;
              console.log("Base64 ")
            })
            .catch((error: any) => {
              console.log("image compress " + error);
            });
        };
        reader.readAsDataURL(file);
      }
    }
  }

  onSubmit(e: any) {
    e.preventDefault();
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    const formData = new FormData();
    formData.append('title', this.gameForm.value.title);
    formData.append('descrizione', this.gameForm.value.descrizione);
    formData.append('genere', this.gameForm.value.genere);
    formData.append('duration', this.gameForm.value.duration);
    formData.append('anno', this.gameForm.value.anno == 2050 ? "9999" : this.gameForm.value.anno);
    formData.append('valutazione', "0");
    formData.append('trailer', this.gameForm.value.trailer);
    formData.append('casa', this.gameForm.value.casa);

    console.log(formData.get("anno"));


    if (this.verticalImg) {
      formData.append('verticalposter', this.verticalImg.toString());
    } else {
      console.log("vertical photo not present")
      alert("Compila tutti i campi");
      return;
    }

    if (this.horizontalImg) {
      formData.append('horizontalposter', this.horizontalImg.toString());
    } else {
      console.log("horizontal photo not present")
      alert("Compila tutti i campi");
      return;
    }


    this.http.post('http://localhost:8080/addGame', formData, {...headers}).subscribe(response => {
      console.log('Risposta del server', response);
      alert("Videogioco aggiunto!")
      this.route.navigate(["/"]);
    }, (error: any) => {
      console.error('Errore durante l\'invio', error);
      alert("Errore caricamento videogioco!")
    });
  }

  ngOnInit() {

  }



}

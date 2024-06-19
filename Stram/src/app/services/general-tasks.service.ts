import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GeneralTasksService {

  constructor() { }

  getRandomValue(value : number){
    return Math.floor(Math.random() * value);
  }

  formatData( value : string, maxlength : number){
    let string=value.trim();
    return string.length > maxlength ? value.slice(0,maxlength) : value;
  }
  
}

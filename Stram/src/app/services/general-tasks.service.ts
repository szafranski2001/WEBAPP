import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GeneralTasksService {

  constructor() { }

  getRandomValue(value : number){
    return Math.floor(Math.random() * value);
  }
}

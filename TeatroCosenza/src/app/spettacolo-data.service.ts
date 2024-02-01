import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { spettacolo } from './spettacolo';

@Injectable({
  providedIn: 'root'
})
export class SpettacoloDataService {

  constructor(private http: HttpClient) {}

  getSpettacolo(id: number): Observable<spettacolo> {
    return this.http.get<spettacolo>(`/api/spettacoli/${id}`);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GiudizioSegnalazione, reviewInfo } from '../model/ReviewInfo';

@Injectable({
  providedIn: 'root'
})
export class AdminDataService {

  BackEndURL = "http://localhost:8080/api"

  constructor(private http : HttpClient) {}

  getReportsData(){
    return this.http.get<reviewInfo[]>(this.BackEndURL+"/GetAllReports");
  }

  setStatusInReportsList(){
    return this.http.put(this.BackEndURL+"/UpdateReports",null);
  }

  ReportsJudgment(reportData : reviewInfo,judgment : GiudizioSegnalazione){
    let report : reviewInfo = { mittente : reportData.mittente, destinatario:reportData.destinatario, idVideogioco: reportData.idVideogioco };

    return judgment == GiudizioSegnalazione.Corretta ? this.http.put(this.BackEndURL+"/BlockUserReported",report) : this.http.put(this.BackEndURL+"/RemoveReportFromReview",report);
  }
}

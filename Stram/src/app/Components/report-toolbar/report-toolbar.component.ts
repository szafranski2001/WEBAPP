import { Component, EventEmitter, Input, Output } from '@angular/core';
import { GiudizioSegnalazione, reviewInfo } from '../../model/ReviewInfo';
import { AdminDataService } from '../../services/admin-data.service';
import { ConfirmJudgmentReport, SuccessfulJudgmentReport } from '../../model/Message';

@Component({
  selector: 'app-report-toolbar',
  templateUrl: './report-toolbar.component.html',
  styleUrl: './report-toolbar.component.css'
})
export class ReportToolbarComponent {
  
  @Input() report : reviewInfo;

  @Output() updateReport = new EventEmitter<void>();

  constructor(private adminService : AdminDataService){}

  RemoveReportFromUser(){
    if(confirm(ConfirmJudgmentReport)){
      this.adminService.ReportsJudgment(this.report,GiudizioSegnalazione.Corretta).subscribe({
        next : () =>{
          //aggiungere sparizione report
          alert(SuccessfulJudgmentReport);
        },
        error : (error) => {
          alert(error.error);
        }
      });
    }
  }

  BlockUserFromPlatform(){
    if(confirm(ConfirmJudgmentReport)){
      this.adminService.ReportsJudgment(this.report,GiudizioSegnalazione.Errata).subscribe({
        next : () => {
          //aggiungere sparizione report
          alert(SuccessfulJudgmentReport);
        },
        error : (error) => {
          alert(error.error);
        }
      });
    }
  }
}

import { Component, EventEmitter, Input, Output } from '@angular/core';
import { GiudizioSegnalazione, reviewInfo } from '../../model/ReviewInfo';
import { AdminDataService } from '../../services/admin-data.service';
import { ConfirmJudgmentReport, SuccessfulJudgmentReport } from '../../model/Message';
import { elementAt } from 'rxjs';

@Component({
  selector: 'app-report-toolbar',
  templateUrl: './report-toolbar.component.html',
  styleUrl: './report-toolbar.component.css'
})
export class ReportToolbarComponent {
  
  @Input() report : reviewInfo;
  @Input() reportID : number;

  @Output() updateReport = new EventEmitter<{ reportId : number, r : reviewInfo}>();

  constructor(private adminService : AdminDataService){}

  RemoveReportFromUser(){
    if(confirm(ConfirmJudgmentReport)){
      this.adminService.ReportsJudgment(this.report,GiudizioSegnalazione.Errata).subscribe({
        next : () =>{
          alert(SuccessfulJudgmentReport);
          this.updateReport.emit({reportId : this.reportID, r : this.report});
        },
        error : (error) => {
          alert(error.error);
        }
      });
    }
  }

  BlockUserFromPlatform(){
    if(confirm(ConfirmJudgmentReport)){
      this.adminService.ReportsJudgment(this.report,GiudizioSegnalazione.Corretta).subscribe({
        next : () => {
          alert(SuccessfulJudgmentReport);
          this.updateReport.emit({reportId : this.reportID, r : this.report});
        },
        error : (error) => {
          alert(error.error);
        }
      });
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { StatoSegnalazione, reviewInfo } from '../../model/ReviewInfo';
import { AdminDataService } from '../../services/admin-data.service';
import { Router } from '@angular/router';
import { NotFoundError } from '../../model/Message';

@Component({
  selector: 'app-admin-reports',
  templateUrl: './admin-reports.component.html',
  styleUrl: './admin-reports.component.css'
})
export class AdminReportsComponent implements OnInit {

  reportList : reviewInfo[];

  constructor(private adminService : AdminDataService, private router : Router) {}

  ngOnInit(): void {
    this.adminService.getReportsData().subscribe({
      next : (response) => {
        this.reportList=response;
        if(this.reportList.find( report => report.stato == StatoSegnalazione.NuovaSegnalazione))
          this.adminService.setStatusInReportsList().subscribe();
      },
      error : () => {
        alert(NotFoundError);
        this.router.navigate(["/"]);
      }
    });
  }

  GoToReportVideogame(report : reviewInfo){
    this.router.navigate(["/videogame/"+report.idVideogioco])
  }
  
  RemoveReportFromList(report : {reportId : number, r : reviewInfo}){
    let reportElement =document.getElementsByName('report-'+report.reportId);
    let reportIndex=this.reportList.indexOf(report.r);

    reportElement[0].remove();
    if(reportIndex != -1)
      this.reportList.splice(reportIndex,1);
  }
}

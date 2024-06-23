package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.ErrorMessage.ReportMessageDB;
import it.unical.studenti.strambackend.persistence.Model.InLavorazioneSegnalazione;
import it.unical.studenti.strambackend.persistence.Model.Segnalazioni;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdminReportsController {

    @GetMapping("/api/GetAllReports")
    public ResponseEntity<List<Segnalazioni>> GetAllReports(){
        try{
            List<Segnalazioni> Segnalazioni=DBManager.getInstance().segnalazioniDAO().findAll().stream().filter(s -> !s.getState().statoSegnalazione().equals("COMPLETATA")).toList();
            return new ResponseEntity<>(Segnalazioni, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/UpdateReports")
    public ResponseEntity<?> UpdateReports(){
        try{
            List<Segnalazioni> segnalazioni = DBManager.getInstance().segnalazioniDAO().findAll().stream().filter(s -> s.getState().statoSegnalazione().equals("NUOVA SEGNALAZIONE")).toList();
            for(Segnalazioni s : segnalazioni)
                DBManager.getInstance().segnalazioniDAO().updateSegnalazione(s);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/BlockUserReported")
    public ResponseEntity<?> BlockUserReported(@RequestBody Segnalazioni segnalazione){
        try{
            segnalazione.changeState(new InLavorazioneSegnalazione(segnalazione));
            DBManager.getInstance().segnalazioniDAO().updateSegnalazione(segnalazione);
            DBManager.getInstance().userDAO().delete(segnalazione.getDestinatario());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ReportMessageDB.ERROR_COMPLETING_REPORT,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/api/RemoveReportFromReview")
    public ResponseEntity<?> RemoveReportFromReview(@RequestBody Segnalazioni segnalazione){
        try{
            segnalazione.changeState(new InLavorazioneSegnalazione(segnalazione));
            DBManager.getInstance().segnalazioniDAO().updateSegnalazione(segnalazione);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ReportMessageDB.ERROR_COMPLETING_REPORT,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

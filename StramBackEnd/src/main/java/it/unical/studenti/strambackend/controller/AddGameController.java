package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.commands.CommandInvoker;
import it.unical.studenti.strambackend.commands.concreteCommands.addGame.GetOptionsForAddNewVideogame;
import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.Model.VideogiocoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;



@RestController
@CrossOrigin("http://localhost:4200")
public class AddGameController {

    public AddGameController() {}

    @PostMapping("/addGame") @ResponseBody
    public ResponseEntity<?> addGame(@ModelAttribute VideogiocoAPI v) {


        // Nome del file immagine da salvare
        int id = DBManager.getInstance().VideogiocoDAO().lastID()+1;
        String extensions = ".jpg";
        String fileVertical = id + extensions;
        String fileHorizontal = id + extensions;

        // Decodifica e salva l'immagine
        boolean decodeVertical = decodeBase64AndSaveImage(v.getVerticalposter(), fileVertical);
        boolean decodeHorizontal = decodeBase64AndSaveImage(v.getVerticalposter(), fileHorizontal);

        //check and save in database
        if (decodeVertical && decodeHorizontal) {
            System.out.println("Videogioco: " + v);

            try {
                 DBManager.getInstance().VideogiocoDAO().save(v.convert(id));
                System.out.println("VG Saved on DB");
            } catch (Exception e) { throw new RuntimeException(e); }

            return new ResponseEntity<>("VG e Immagini salvate", HttpStatus.CREATED);
        }


        return new ResponseEntity<>("Decode Vertical o Horizontal Failed", HttpStatus.INTERNAL_SERVER_ERROR);


    }


    private boolean decodeBase64AndSaveImage(String base64String, String fileName) {

        System.out.println("-------enter decode base 64");
        if (base64String == null || base64String.isEmpty()) {
            System.err.println("La stringa base64 fornita è null o vuota.");
            return false;
        }

        if (base64String.contains(",")) {
            base64String = base64String.split(",")[1];
        }

        base64String = base64String.replaceAll("\\s+", "");

        String folderPath = "src/main/resources/static/images/videogames";

        // Crea la cartella se non esiste
        File directory = new File(folderPath);
        if (!directory.exists()) {
            System.out.println("Directory not found " + folderPath);
            return false;
        }

        System.out.println("decodifica");
        // Decodifica la stringa base64 in un array di byte
        byte[] imageBytes = Base64.getDecoder().decode(base64String);

        // Definisci il percorso completo del file immagine
        String filePath = folderPath + File.separator + fileName;

        // Salva l'immagine nel file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageBytes);
            System.out.println("Immagine salvata con successo in " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio dell'immagine: " + e.getMessage());
        }
        return false;
    }




}

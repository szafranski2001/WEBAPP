package it.unical.studenti.strambackend.controller.servlet;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/videogames")
public class VideogamesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Videogioco> videogiochi;
        try {
            videogiochi = DBManager.getInstance().VideogiocoDAO().findAll();
            //Passare anche tutti i giochi nelle liste
        } catch (DatabaseException e) {
            videogiochi=null;
        }
        req.setAttribute("videogames_list",videogiochi);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/views/videogames.html");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

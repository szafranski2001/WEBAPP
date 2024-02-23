package it.unical.studenti.strambackend.controller.servlet;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/videogames")
public class VideogamesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Videogioco>videogiochi = DBManager.getInstance().VideogiocoDAO().findAll();

        req.setAttribute("videogames_list",videogiochi);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/views/videogames.html");
        dispatcher.forward(req,resp);
    }
}

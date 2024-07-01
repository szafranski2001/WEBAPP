package it.unical.studenti.strambackend.controller.servlet;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Lists;
import it.unical.studenti.strambackend.persistence.Model.TokenManager;
import it.unical.studenti.strambackend.persistence.Model.UserCredenziali;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/videogames")
public class VideogamesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Videogioco> videogiochi;
        String token= req.getParameter("token");
        String User = TokenManager.verificaToken(token);

        try {
            videogiochi = DBManager.getInstance().VideogiocoDAO().findAll();
        } catch (Exception e) {
            videogiochi=null;
        }
        if(DBManager.getInstance().userDAO().existsUser(User)) {
            req.setAttribute("token",token);
            req.setAttribute("user", User); 
            if(DBManager.getInstance().userDAO().getTypeUser(User) == 1) {
                req.setAttribute("admin", true);
            }
            else
                req.setAttribute("admin",false);
        }


        req.setAttribute("videogames_list",videogiochi);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/views/videogames.html");
        dispatcher.forward(req,resp);
    }
}

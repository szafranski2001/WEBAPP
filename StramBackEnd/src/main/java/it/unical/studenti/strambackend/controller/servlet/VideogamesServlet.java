package it.unical.studenti.strambackend.controller.servlet;

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
        // List<Videogioco>videogiochi = DBManager.getInstance().VideogiocoDAO().findAll();
        List<Videogioco> videogiochi=List.of(new Videogioco(0,"Tom clancy's Rainbow six siege: Operation Health afaga agaga gag agag ag agagad","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/0.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"MinecraftMinecraftMinecraftMinecraftMinecraftMinecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/0.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/0.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/0.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraftasagaghadhahahahdahshshahahanamnamnanmaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/0.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/5.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"),
                new Videogioco(0,"Minecraft","sandbox adventure videogame","sandbox",15,209,4,"images/videogames/3.png","https://www.youtube.com/watch?v=Vebznwclv6w"));

        req.setAttribute("videogames_list",videogiochi);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/views/videogames.html");
        dispatcher.forward(req,resp);
    }
}

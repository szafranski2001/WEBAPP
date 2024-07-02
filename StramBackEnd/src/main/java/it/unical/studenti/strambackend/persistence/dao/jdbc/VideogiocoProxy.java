
package it.unical.studenti.strambackend.persistence.dao.jdbc;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.DBSource;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.dao.VideogiocoDAO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class VideogiocoProxy implements VideogiocoDAO {

    private final VideogiocoDAOJDBC videogiocoDAOJDBC;

    private static final List<Videogioco> CachedVideogames = new CopyOnWriteArrayList<>();;

    public VideogiocoProxy(DBSource source) {
        this.videogiocoDAOJDBC = new VideogiocoDAOJDBC(source);
    }

    @Override
    public void save(Videogioco videogioco) throws Exception {
        if(!CachedVideogames.isEmpty()){
            CachedVideogames.add(videogioco);
        }
        videogiocoDAOJDBC.save(videogioco);
    }

    @Override
    public Videogioco findByPrimaryKey(int idVideogioco) throws Exception {
        if(!CachedVideogames.isEmpty()){
            return CachedVideogames.stream().filter( videogioco -> videogioco.getId() == idVideogioco).findFirst().get();
        }
        else{
            return videogiocoDAOJDBC.findByPrimaryKey(idVideogioco);
        }

    }

    @Override
    public List<Videogioco> findAll() throws Exception {
        if(CachedVideogames.isEmpty()){
            CachedVideogames.addAll(videogiocoDAOJDBC.findAll());
        }
        return CachedVideogames;
    }

    @Override
    public void delete(int videogiocoId) throws Exception {
        if(!CachedVideogames.isEmpty()){
            Videogioco videogame = CachedVideogames.stream().filter(videogioco -> videogioco.getId() == videogiocoId).findFirst().get();
            CachedVideogames.remove(videogame);
        }
        videogiocoDAOJDBC.delete(videogiocoId);
    }

    @Override
    public List<Videogioco> findByName(String videogiocoName) throws Exception {
        if(!CachedVideogames.isEmpty()){
            return CachedVideogames.stream().filter( videogioco -> videogioco.getTitolo().equals(videogiocoName)).toList();
        }
        else{
            return videogiocoDAOJDBC.findByName(videogiocoName);
        }
    }

    @Override
    public boolean existsVideogioco(Videogioco videogioco) {
        if(!CachedVideogames.isEmpty()){
            return CachedVideogames.stream().anyMatch(videogioco1 -> videogioco1.getId() == videogioco.getId());
        }
        else{
            return videogiocoDAOJDBC.existsVideogioco(videogioco);
        }
    }

    @Override
    public void updateVideogioco(Videogioco videogioco, int videogiocoId) throws Exception {
        if(!CachedVideogames.isEmpty()){
            Videogioco videogame = CachedVideogames.stream().filter(vd -> vd.getId() == videogiocoId).findFirst().get();
            CachedVideogames.remove(videogame);
            CachedVideogames.add(videogioco);
        }
        videogiocoDAOJDBC.updateVideogioco(videogioco,videogiocoId);
    }
    @Override
    public String findTitoloById(int videogioco) {
        if(!CachedVideogames.isEmpty()){
            return CachedVideogames.stream().filter( vd -> vd.getId() == videogioco).findFirst().get().getTitolo();
        }
        return videogiocoDAOJDBC.findTitoloById(videogioco);
    }

    @Override
    public List<Videogioco> top10(){
        if(!CachedVideogames.isEmpty()){
            return CachedVideogames.stream().sorted(Comparator.comparingInt(Videogioco::getValutazione).reversed()).limit(10).toList();
        }
        return videogiocoDAOJDBC.top10();
    }

    @Override
    public List<Videogioco> get10() {
        if(!CachedVideogames.isEmpty()){
            return CachedVideogames.stream().collect(Collectors.groupingBy(Videogioco::getGenere)).values().stream()
                    .flatMap(games -> games.stream().limit(10)).toList();
        }
        return videogiocoDAOJDBC.get10();
    }

    @Override
    public Videogioco getRandomVideogame() {
        if(!CachedVideogames.isEmpty()){
            Random random= new Random();
            int randomId = random.nextInt(CachedVideogames.size());
            return CachedVideogames.stream().filter(videogioco -> videogioco.getId() == randomId).findFirst().get();
        }
        return videogiocoDAOJDBC.getRandomVideogame();
    }

    @Override
    public List<Videogioco> risultati(String input) {
        return videogiocoDAOJDBC.risultati(input);
    }

    @Override
    public int lastID() {
        if(!CachedVideogames.isEmpty()){
            return CachedVideogames.stream().max(Comparator.comparingInt(Videogioco::getId)).get().getId();
        }
        return videogiocoDAOJDBC.lastID();
    }

    public static void UpdateRatingAfterAddingReview(int idVideogioco) throws Exception{
        if(!CachedVideogames.isEmpty()){
            int valutazione = DBManager.getInstance().recensioneDAO().mediaVoti(idVideogioco);
            for (Videogioco videogioco : CachedVideogames)
                if (videogioco.getId() == idVideogioco) {
                    videogioco.setValutazione(valutazione);
                    break;
                }
        }
    }

}

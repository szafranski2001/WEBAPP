package it.unical.studenti.strambackend.persistence.Model;


public class VideogiocoAPI {

    private String title;
    private String descrizione;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getValutazione() {
        return valutazione;
    }

    public void setValutazione(String valutazione) {
        this.valutazione = valutazione;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getVerticalposter() {
        return verticalposter;
    }

    public void setVerticalposter(String verticalposter) {
        this.verticalposter = verticalposter;
    }

    public String getHorizontalposter() {
        return horizontalposter;
    }

    public void setHorizontalposter(String horizontalposter) {
        this.horizontalposter = horizontalposter;
    }

    private String genere;
    private String duration;
    private String anno;
    private String valutazione;
    private String trailer;
    private String casa;
    private String verticalposter;
    private String horizontalposter;



    public VideogiocoAPI() { }
    public VideogiocoAPI(String title, String descrizione, String genere, String duration, String anno, String valutazione, String trailer, String casa, String verticalposter, String horizontalposter) {
        this.title = title;
        this.descrizione = descrizione;
        this.genere = genere;
        this.duration = duration;
        this.anno = anno;
        this.valutazione = valutazione;
        this.trailer = trailer;
        this.casa = casa;
        this.verticalposter = verticalposter;
        this.horizontalposter = horizontalposter;
    }

    @Override
    public String toString() {
        return "VideogiocoAPI{" +
                "title='" + title + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", genere='" + genere + '\'' +
                ", duration='" + duration + '\'' +
                ", anno='" + anno + '\'' +
                ", valutazione='" + valutazione + '\'' +
                ", trailer='" + trailer + '\'' +
                ", casa='" + casa + '\'' +
                ", verticalposter='" + verticalposter + '\'' +
                ", horizontalposter='" + horizontalposter + '\'' +
                '}';
    }


    public Videogioco convert(int id) {
        return new Videogioco(
                id,
                getTitle(),
                getDescrizione(),
                getGenere(),
                Integer.parseInt(getDuration()),
                Integer.parseInt(getAnno()),
                Integer.parseInt(getValutazione()),
                getTrailer(),
                getCasa()
        );
    }
}

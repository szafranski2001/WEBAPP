package it.unical.studenti.strambackend.persistence.Model;

public class SingleGameInfo {
    public int rank;
    public int id;
    public String name;
    public String imgUrl;
    public int rate;
    public boolean isFavourite;

    public SingleGameInfo(int rank, int id, String name, String imgUrl, int rate, boolean isFavourite) {
        this.rank = rank;
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.rate = rate;
        this.isFavourite = isFavourite;
    }
}

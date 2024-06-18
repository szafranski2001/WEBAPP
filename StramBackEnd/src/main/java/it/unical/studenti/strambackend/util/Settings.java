package it.unical.studenti.strambackend.util;

public class Settings {

    static public int MIN_VIDEOGAME_YEAR_OPTION = 1950;

    public enum params {
        USERTOKEN("userToken");


        //add-game



        public final String label;
        params(String label) { this.label = label; }
    }

}

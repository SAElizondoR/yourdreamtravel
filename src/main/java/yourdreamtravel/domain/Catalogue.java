package yourdreamtravel.domain;

import java.util.ArrayList;

public class Catalogue {
    private CatalogueId id;
    private ArrayList<Lieu> destinations;
    private ArrayList<Vol> vols;
    private ArrayList<Hotel> hotels;
    private ArrayList<LoueurVoiture> loueurs;

    public void addDestination(String nom) {
        // destinations.add(new Lieu(nom));
    }

    public void addVol() {
        /* à implementer */
    }
}

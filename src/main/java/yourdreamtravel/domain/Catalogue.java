package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {
    private CatalogueId id;
    private ArrayList<Lieu> destinations;
    private ArrayList<Vol> vols;
    private ArrayList<Hotel> hotels;
    private ArrayList<LoueurVoiture> loueurs;

    public List<String> getDestinationNames() {
        List<String> names = new ArrayList<>();
        for (Lieu lieu: destinations) {
            names.add(lieu.getNom());
        }
        return names;
    }

    public void addDestination(String nom) {
        // destinations.add(new Lieu(nom));
    }

    public void addVol() {
        /* à implementer */
    }
}

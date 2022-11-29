package yourdreamtravel.domain;

import java.util.ArrayList;

public class Catalogue {
    private final CatalogueId id;
    private final ArrayList<Lieu> destinations;
    private final ArrayList<Vol> vols;
    private final ArrayList<Hotel> hotels;
    private final ArrayList<LoueurVoiture> loueurs;

    public void addDestination(String nom) {
        destinations.add(new Destination(new DestinationId(), nom));
    }

    public void addVol() {
        /* à implementer */
    }
}

package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {
    private CatalogueId id;
    private List<Lieu> destinations;
    private List<Vol> vols;
    private List<Hotel> hotels;
    private List<LoueurVoiture> loueurs;

    public Catalogue(List<Lieu> destinations) {
        id = new CatalogueId();
        this.destinations = destinations;
    }

    public List<String> getDestinationNames() {
        List<String> names = new ArrayList<>();
        for (Lieu lieu: destinations) {
            names.add(lieu.getNom());
        }
        return names;
    }

    public void addDestination(String nom) {
        destinations.add(new Lieu(new LieuId(), nom));
    }

    public CatalogueId getId() {
        return id;
    }

    public void addVol() {
        /* à implementer */
    }

    public List<Vol> getVols() {
        return vols;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public List<LoueurVoiture> getLoueurs() {
        return loueurs;
    }
}

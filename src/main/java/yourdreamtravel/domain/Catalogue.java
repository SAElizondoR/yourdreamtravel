package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {
    private final CatalogueId id;
    private final List<Lieu> destinations;
    private final List<Vol> vols;
    private final List<Hotel> hotels;
    private final List<LoueurVoiture> loueurs;

    public Catalogue(List<Lieu> destinations, List<Vol> vols,
        List<Hotel> hotels, List<LoueurVoiture> loueurs) {
        id = new CatalogueId();
        this.destinations = destinations;
        this.vols = vols;
        this.hotels = hotels;
        this.loueurs =  loueurs;
    }

    public List<String> getDestinationNames() {
        List<String> names = new ArrayList<>();
        for (Lieu lieu: destinations) {
            names.add(lieu.getNom());
        }
        return names;
    }

    public void addDestination(String nom) {
        destinations.add(new Lieu(nom));
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

    public List<Lieu> getDestinations() {
        return new ArrayList<>();
    }
}

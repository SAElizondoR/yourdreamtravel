package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalogue {
    private final CatalogueId id;
    private final List<Lieu> destinations;
    private final List<Vol> vols;
    private final List<Hotel> hotels;
    private final List<LoueurVoiture> loueurs;

    public Catalogue(CatalogueId id, List<Lieu> destinations, List<Vol> vols,
        List<Hotel> hotels, List<LoueurVoiture> loueurs) {
        this.id = id;
        this.destinations = destinations;
        this.vols = vols;
        this.hotels = hotels;
        this.loueurs =  loueurs;
    }

    public List<Vol> calculerItineraire(Lieu depart, Lieu destination) {
        List<Vol> volsPossibles = new ArrayList<>();
        for (Vol vol: vols)
            if (vol.getDepart().equals(depart)) {
                Lieu destPossible = vol.getDestination();
                if (destPossible.equals(destination)) 
                    return Arrays.asList(vol);
                volsPossibles.add(vol);
            }
                
        for (Vol volPossible: volsPossibles) {
            Lieu destinationPossible = volPossible.getDestination();
            for (Vol vol: vols)
                if (vol.getDepart().equals(destinationPossible) &&
                    vol.getDestination().equals(destination))
                    return Arrays.asList(volPossible, vol);
        }
        return new ArrayList<>();
    }

    public CatalogueId getId() {
        return id;
    }

    public List<Lieu> getDestinations() {
        return destinations;
    }

    public List<String> getDestinationNames() {
        List<String> names = new ArrayList<>();
        for (Lieu lieu: destinations) {
            names.add(lieu.getNom());
        }
        return names;
    }

    public Lieu getDestinationByName(String name) {
        for (Lieu lieu: destinations)
            if (lieu.getNom().equals(name))
                return lieu;
        return null;
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

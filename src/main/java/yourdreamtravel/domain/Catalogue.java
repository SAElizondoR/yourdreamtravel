package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Lieu> getDestinationMap() {
        Map<String, Lieu> map = new LinkedHashMap<>();
        for (Lieu lieu: destinations)
            map.put(lieu.getNom(), lieu);
        
        return map;
    }

    public Map<String, Vol> calculerItineraire(Lieu depart, Lieu destination) {
        Map<String, Vol> itineraire = new LinkedHashMap<>();
        List<Vol> volsPossibles = new ArrayList<>();
        for (Vol vol: vols)
            if (vol.getDepart().equals(depart)) {
                Lieu destPossible = vol.getDestination();
                if (destPossible.equals(destination)) {
                    itineraire.put(String.join(" - ", depart.getNom(),
                        destination.getNom()), vol);
                    return itineraire;
                }
                volsPossibles.add(vol);
            }
                
        for (Vol volPossible: volsPossibles) {
            Lieu destinationPossible = volPossible.getDestination();
            for (Vol vol: vols)
                if (vol.getDepart().equals(destinationPossible) &&
                    vol.getDestination().equals(destination)) {
                    itineraire.put(String.join(" - ", depart.getNom(),
                        destinationPossible.getNom()), volPossible);
                    itineraire.put(String.join(" - ",
                        destinationPossible.getNom(),
                        destination.getNom()), vol);
                    return itineraire;
                }
        }
        return itineraire;
    }

    public Map<String, Hotel> getHotelMap(Lieu lieu) {
        Map<String, Hotel> map = new LinkedHashMap<>();
        for (Hotel hotel: hotels)
            if (hotel.getLieu().equals(lieu))
                map.put(hotel.getNom(), hotel);
        
        return map;
    }

    public Map<String, LoueurVoiture> getLoueurMap(Lieu lieu) {
        Map<String, LoueurVoiture> map = new LinkedHashMap<>();
        for (LoueurVoiture loueur: loueurs)
            if (loueur.getLieu().equals(lieu))
                map.put(loueur.getAdresse(), loueur);
        return map;
    }

    public CatalogueId getId() {
        return id;
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

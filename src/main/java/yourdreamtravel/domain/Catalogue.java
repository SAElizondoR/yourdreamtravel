package yourdreamtravel.domain;

public class Catalogue {
    private final CatalogueId id;
    private final ArrayList<Destination> destinations;
    private final ArrayList<Hotel> hotels;
    private final ArrayList<LoueurVoiture> loueurs;
    private final ArrayList<Vol> vols;

    public addDestination(String nom) {
        destinations.add(new Destination(new DestinationId(), nom));
    }
}

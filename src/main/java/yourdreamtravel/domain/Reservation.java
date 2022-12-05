package yourdreamtravel.domain;

import java.util.List;

public class Reservation {
    private ReservationId id;
    private Client client;
    private List<Vol> itineraire;
    private List<Service> services;

    public Reservation(Client client, List<Vol> itineraire) {
        id = new ReservationId();
        this.client = client;
        this.itineraire = itineraire;
    }

    public ReservationId getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Vol> getItineraire() {
        return itineraire;
    }

    public Lieu getDepart() {
        return itineraire.get(0).getDepart();
    }

    public Lieu getDestination() {
        return itineraire.get(itineraire.size() - 1).getDestination();
    }

    public List<Service> getServices() {
        return services;
    }
}

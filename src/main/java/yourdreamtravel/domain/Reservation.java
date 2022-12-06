package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Reservation {
    private ReservationId id;
    private Client client;
    private List<PlaceVol> placeVols;
    private Calendar date;
    private List<Service> services;
    private boolean reduced;

    public Reservation(Client client, List<Vol> itineraire, Calendar date,
        Integer classe) {
        id = new ReservationId();
        this.client = client;
        this.date = date;

        placeVols = new ArrayList<>();
        for (Vol vol: itineraire) {
            placeVols.add(new PlaceVol(vol, classe));
        }

        services = new ArrayList<>();
    }

    public Vol getVolDepart() {
        return placeVols.get(0).getVol();
    }

    public Lieu getDepart() {
        return getVolDepart().getDepart();
    }

    public Lieu getDestination() {
        return placeVols.get(placeVols.size() - 1).getVol().getDestination();
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void reducePrix() {
        reduced = true;
    }

    public Integer computerTotal() {
        Integer total = 0;
        for (PlaceVol place: placeVols) {
            Integer volPrix = place.getVol().getPrix();
            if (place.getClasse() == 1)
                volPrix = (int) (1.3 * volPrix);
            total += volPrix;
        }

        if (reduced)
            total = (int) (0.8 * total);
        
        for (Service service: services)
            total += service.getPrix();
        return total;
    }

    public ReservationId getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Service> getServices() {
        return services;
    }

    public Calendar getDate() {
        return date;
    }
}

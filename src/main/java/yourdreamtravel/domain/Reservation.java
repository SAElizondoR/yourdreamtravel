package yourdreamtravel.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Reservation {
    private ReservationId id;
    private Client client;
    private List<Vol> itineraire;
    private Calendar date;
    private List<Service> services;

    public Reservation(Client client, List<Vol> itineraire) {
        id = new ReservationId();
        this.client = client;
        this.itineraire = itineraire;
        services = new ArrayList<>();
    }

    public Map<String, Calendar> getDatesPossibles() {
        Map<String, Calendar> map = new LinkedHashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (Calendar datePossible: itineraire.get(0).getDates())
            map.put(formatter.format(datePossible.getTime()), datePossible);
        
        return map;
    }

    public Lieu getDepart() {
        return itineraire.get(0).getDepart();
    }

    public Lieu getDestination() {
        return itineraire.get(itineraire.size() - 1).getDestination();
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void setDate(Calendar date) {
        this.date = date;
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

    public List<Service> getServices() {
        return services;
    }

    public Calendar getDate() {
        return date;
    }
}

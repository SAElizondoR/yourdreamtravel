package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Agence {
    private final AgenceId id;
    private final List<Client> clients;
    private final List<Reservation> reservations;
    private final Catalogue catalogue;
    private final Map<Lieu, Integer> ticketsReduction;

    public Agence(Catalogue catalogue, Map<Lieu, Integer> ticketsReduction) {
        id = new AgenceId();
        clients = new ArrayList<>();
        reservations = new ArrayList<>();
        this.catalogue = catalogue;
        this.ticketsReduction = ticketsReduction;
    }

    public void addClient(String nom) {
        clients.add(new Client(new ClientId(), nom));
    }

    public List<Client> getClients() {
        return clients;
    }

    public Map<String, Client> getClientsMap() {
        Map<String, Client> map = new LinkedHashMap<>();
        for (Client client: clients)
            map.put(client.getNom(), client);
        
        return map;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void addReservation(Reservation reservation) {
        Lieu destination = reservation.getDestination();
        Integer numberTicketsReduction
            = ticketsReduction.get(destination);
        if (numberTicketsReduction > 0) {
            ticketsReduction.put(destination, numberTicketsReduction - 1);
            reservation.reducePrix();
        }
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public AgenceId getId() {
        return id;
    }

    public Map<Lieu, Integer> getTicketsReduction() {
        return ticketsReduction;
    }
}

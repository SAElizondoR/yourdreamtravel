package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Agence {
    private final AgenceId id;
    private final ArrayList<Client> clients;
    private final ArrayList<Reservation> reservations;
    private final Catalogue catalogue;

    public Agence(Catalogue catalogue) {
        id = new AgenceId();
        clients = new ArrayList<>();
        reservations = new ArrayList<>();
        this.catalogue = catalogue;
    }

    public void addClient(String nom) {
        clients.add(new Client(new ClientId(), nom));
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

    public AgenceId getId() {
        return id;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

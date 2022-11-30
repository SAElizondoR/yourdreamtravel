package yourdreamtravel.domain;

import java.util.ArrayList;
import java.util.List;

public class Agence {
    private final AgenceId id;
    private final ArrayList<Client> clients;
    private final ArrayList<Reservation> reservations;
    private final Catalogue catalogue;

    public Agence() {
        id = new AgenceId();
        clients = new ArrayList<>();
        reservations = new ArrayList<>();
        catalogue = new Catalogue();
    }

    public void addClient(String nom) {
        clients.add(new Client(new ClientId(), nom));
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<String> getClientNames() {
        List<String> names = new ArrayList<>();
        for (Client client: clients) {
            names.add(client.getNom());
        }
        return names;
    }
}

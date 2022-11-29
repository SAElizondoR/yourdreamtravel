package yourdreamtravel.domain;

import java.util.ArrayList;

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
}

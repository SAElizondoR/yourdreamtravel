package yourdreamtravel.domain;

public class Client {
    private final ClientId id;
    private final String nom;

    public Client(ClientId id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}

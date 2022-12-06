package yourdreamtravel.domain;

public class Lieu {
    private final LieuId id;
    private final String nom;

    public Lieu(String nom) {
        id = new LieuId();
        this.nom = nom;
    }

    public LieuId getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Lieu))
            return false;
        Lieu other = (Lieu)obj;
        return nom.equals(other.nom);
    }

    public int hashCode() {
        return id.hashCode();
    }
}

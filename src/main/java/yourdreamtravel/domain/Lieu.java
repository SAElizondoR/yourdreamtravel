package yourdreamtravel.domain;

public class Lieu {
    private LieuId id;
    private String nom;

    public Lieu(LieuId id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public LieuId getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}

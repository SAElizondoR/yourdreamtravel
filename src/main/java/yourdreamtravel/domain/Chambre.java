package yourdreamtravel.domain;


public class Chambre {
    private final Integer taille;
    private final Integer prix;

    public Chambre(Integer taille, Integer prix) {
        this.taille = taille;
        this.prix = prix;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getPrix() {
        return prix;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Chambre))
            return false;
        Chambre other = (Chambre)obj;
        return taille.equals(other.taille) && prix.equals(other.prix);
    }

    public int hashCode() {
        return hashCode();
    }
}

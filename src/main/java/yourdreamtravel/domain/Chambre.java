package yourdreamtravel.domain;

import java.math.BigDecimal;

public class Chambre {
    private final Integer taille;
    private final BigDecimal prix;

    public Chambre(Integer taille, BigDecimal prix) {
        this.taille = taille;
        this.prix = prix;
    }

    public Integer getTaille() {
        return taille;
    }

    public BigDecimal getPrix() {
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

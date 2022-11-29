package yourdreamtravel.domain;

import java.math.BigDecimal;

public class Voiture {
    private final String marque;
    private final String model;
    private final BigDecimal prix;

    public Voiture(String marque, String model, BigDecimal prix) {
        this.marque = marque;
        this.model = model;
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Voiture))
            return false;
        Voiture other = (Voiture)obj;
        return marque.equals(other.marque) && model.equals(other.model);
    }

    public int hashCode() {
        return 37 * marque.hashCode() + model.hashCode();
    }
}

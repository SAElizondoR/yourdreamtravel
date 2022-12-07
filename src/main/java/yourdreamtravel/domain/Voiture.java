package yourdreamtravel.domain;

public class Voiture {
    private final VoitureId id;
    private final String marque;
    private final String model;
    private final Integer prix;

    public Voiture(String marque, String model, Integer prix) {
        id = new VoitureId();
        this.marque = marque;
        this.model = model;
        this.prix = prix;
    }

    public VoitureId getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public String getModel() {
        return model;
    }

    public Integer getPrix() {
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

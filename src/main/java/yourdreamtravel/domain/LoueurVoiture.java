package yourdreamtravel.domain;

import java.util.List;

public class LoueurVoiture {
    private LoueurVoitureId id;
    private Lieu lieu;
    private String adresse;
    private List<Voiture> voitures;
    
    public LoueurVoiture(Lieu lieu, String adresse, List<Voiture> voitures) {
        id = new LoueurVoitureId();
        this.lieu = lieu;
        this.adresse = adresse;
        this.voitures = voitures;
    }

    public LoueurVoitureId getId() {
        return id;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Voiture> getVoitures() {
        return voitures;
    }
}

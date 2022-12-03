package yourdreamtravel.domain;

import java.util.List;

public class LoueurVoiture {
    private LoueurVoitureId id;
    private String adresse;
    private List<Voiture> voitures;
    
    public LoueurVoiture(LoueurVoitureId id, String adresse, List<Voiture> voitures) {
        this.id = id;
        this.adresse = adresse;
        this.voitures = voitures;
    }

    public LoueurVoitureId getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Voiture> getVoitures() {
        return voitures;
    }
}

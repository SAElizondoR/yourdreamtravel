package yourdreamtravel.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Voiture> getVoitureMap() {
        Map<String, Voiture> map = new LinkedHashMap<>();
        for (Voiture voiture: voitures) {
            String text = "Marque: " + voiture.getMarque()
                + ", Modele: " + voiture.getModel()
                + ", Prix: " + voiture.getPrix();
            map.put(text, voiture);
        }
        return map;
    }

    public List<Voiture> getVoitures() {
        return voitures;
    }
}

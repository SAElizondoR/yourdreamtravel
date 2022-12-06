package yourdreamtravel.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    private HotelId id;
    private String nom;
    private Lieu lieu;
    private List<Chambre> chambres;

    public Hotel(String nom, Lieu lieu, List<Chambre> chambres) {
        id = new HotelId();
        this.nom = nom;
        this.lieu = lieu;
        this.chambres = chambres;
    }

    public HotelId getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public Map<String, Chambre> getChambreMap() {
        Map<String, Chambre> map = new LinkedHashMap<>();
        for (Chambre chambre: chambres) {
            String text = "Taille: " + chambre.getTaille() 
                + ", Prix: " + chambre.getPrix();
            map.put(text, chambre);
        }
        return map;
            
    }

    public List<Chambre> getChambres() {
        return chambres;
    }
}

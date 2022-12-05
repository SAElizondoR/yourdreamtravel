package yourdreamtravel.domain;

import java.util.List;

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

    public List<Chambre> getChambres() {
        return chambres;
    }
}

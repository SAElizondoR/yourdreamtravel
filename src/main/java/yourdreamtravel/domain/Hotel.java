package yourdreamtravel.domain;

import java.math.BigDecimal;
import java.util.List;

public class Hotel {
    private HotelId id;
    private String nom;
    private BigDecimal prix;
    private List<Chambre> chambres;

    public Hotel(HotelId id, String nom, BigDecimal prix, List<Chambre> chambres) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.chambres = chambres;
    }

    public HotelId getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }
}

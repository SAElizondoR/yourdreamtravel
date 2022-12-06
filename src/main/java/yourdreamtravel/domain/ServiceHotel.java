package yourdreamtravel.domain;

import java.util.Calendar;

public class ServiceHotel extends Service {
    private final Hotel hotel;
    private final Chambre chambre;
    private final boolean prestationsLuxe;

    public ServiceHotel(Calendar dateEntree, Calendar dateSortie, Lieu lieu,
        Hotel hotel, Chambre chambre, boolean prestationsLuxe) {
        super(dateEntree, dateSortie, lieu);
        this.hotel = hotel;
        this.chambre = chambre;
        this.prestationsLuxe = prestationsLuxe;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public Integer getPrix() {
        Integer prix = (int) (super.getNombreDeJours() * chambre.getPrix());
        if (prestationsLuxe)
            prix = (int) (1.2 * prix);
        return prix;
    }
}

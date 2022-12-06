package yourdreamtravel.domain;

import java.util.Calendar;

public class ServiceHotel extends Service {
    private final Hotel hotel;
    private final Chambre chambre;

    public ServiceHotel(Calendar dateEntree, Calendar dateSortie,
        Hotel hotel, Chambre chambre) {
        super(dateEntree, dateSortie);
        this.hotel = hotel;
        this.chambre = chambre;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Chambre getChambre() {
        return chambre;
    }
}

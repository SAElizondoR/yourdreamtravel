package yourdreamtravel.domain;


public class ServiceHotel {
    private final ServiceHotelId id;
    private final Hotel hotel;
    private final Chambre chambre;

    public ServiceHotel(Hotel hotel, Chambre chambre) {
        id = new ServiceHotelId();
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

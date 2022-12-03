package yourdreamtravel.domain;

public class ReservationService {
    private ReservationId id;
    private Service service;

    public ReservationService(ReservationId id, Service service) {
        this.id = id;
        this.service = service;
    }

    public ReservationId getId() {
        return id;
    }

    public Service getService() {
        return service;
    }
}

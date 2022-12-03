package yourdreamtravel.domain;

import java.util.List;

public class Reservation {
    private ReservationId id;
    private List<Vol> vols;

    public Reservation(ReservationId id, List<Vol> vols) {
        this.id = id;
        this.vols = vols;
    }

    public ReservationId getId() {
        return id;
    }

    public List<Vol> getVols() {
        return vols;
    }
}

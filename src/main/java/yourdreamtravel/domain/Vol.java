package yourdreamtravel.domain;

import java.util.Calendar;
import java.util.List;

public class Vol {
    private final VolId id;
    private final Lieu depart;
    private final Lieu destination;
    private final List<Calendar> dates;

    public Vol(Lieu depart, Lieu destination, List<Calendar> dates) {
        id = new VolId();
        this.depart = depart;
        this.destination = destination;
        this.dates = dates;
    }

    public VolId getId() {
        return id;
    }

    public Lieu getDepart() {
        return depart;
    }

    public Lieu getDestination() {
        return destination;
    }

    public List<Calendar> getDates() {
        return dates;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Vol))
            return false;
        Vol other = (Vol)obj;
        return id.equals(other.id) && depart.equals(other.depart) &&
            destination.equals(other.destination) && dates.equals(other.dates);
    }

    public int hashCode() {
        return id.hashCode();
    }
}

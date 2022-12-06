package yourdreamtravel.domain;

import java.util.Calendar;
import java.util.List;

public class Vol {
    private final VolId id;
    private final Lieu depart;
    private final Lieu destination;
    private final Integer duree;
    private final List<Calendar> dates;
    private final Integer prix;

    public Vol(Lieu depart, Lieu destination, Integer duree,
        List<Calendar> dates, Integer prix) {
        id = new VolId();
        this.depart = depart;
        this.destination = destination;
        this.duree = duree;
        this.dates = dates;
        this.prix = prix;
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

    public Integer getDuree() {
        return duree;
    }

    public List<Calendar> getDates() {
        return dates;
    }

    public Integer getPrix() {
        return prix;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Vol))
            return false;
        Vol other = (Vol)obj;
        return id.equals(other.id) && depart.equals(other.depart) &&
            destination.equals(other.destination) &&
            duree.equals(other.duree) && dates.equals(other.dates);
    }

    public int hashCode() {
        return id.hashCode();
    }
}

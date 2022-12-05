package yourdreamtravel.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Vol {
    private final VolId id;
    private final Lieu depart;
    private final Lieu destination;
    private final Date date;
    private final Integer classe;

    public Vol(VolId id, Lieu depart, Lieu destination, Date date,
        Integer classe) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.classe = classe;
    }

    public Vol(VolId volId, Lieu lieu, Lieu lieu2, List<Calendar> lesDates) {
        this.id = volId;
        this.depart = lieu;
        this.destination = lieu2;
        this.date = new Date();
        this.classe = lesDates.size();
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

    public Date getDate() {
        return date;
    }

    public Integer getClasse() {
        return classe;
    }
}

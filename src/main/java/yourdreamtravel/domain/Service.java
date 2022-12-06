package yourdreamtravel.domain;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public abstract class Service {
    private final ServiceId id;
    private final Calendar dateDebut;
    private final Calendar dateFin;
    private final Lieu lieu;

    protected Service(Calendar dateDebut, Calendar dateFin, Lieu lieu) {
        id = new ServiceId();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
    }

    public ServiceId getId() {
        return id;
    }

    public Calendar getDateDebut() {
        return dateDebut;
    }

    public Calendar getDateFin() {
        return dateFin;
    }

    public Long getNombreDeJours() {
        return TimeUnit.MILLISECONDS.toDays(Math.abs(
            dateFin.getTime().getTime() - dateDebut.getTime().getTime()));
    }

    public Lieu getLieu() {
        return lieu;
    }

    abstract Integer getPrix();
}

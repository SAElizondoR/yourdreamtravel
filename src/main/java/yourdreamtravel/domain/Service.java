package yourdreamtravel.domain;

import java.util.Calendar;

public abstract class Service {
    private final ServiceId id;
    private final Calendar date;

    public Service(Calendar date) {
        id = new ServiceId();
        this.date = date;
    }

    public ServiceId getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }
}

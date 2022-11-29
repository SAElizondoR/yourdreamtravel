package yourdreamtravel.domain;

import java.util.Date;

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
}

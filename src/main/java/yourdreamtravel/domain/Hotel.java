package yourdreamtravel.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Hotel {
    private final HotelId id;
    private final String nom;
    private final BigDecimal prix;
    private final ArrayList<Chambre> chambres;
}

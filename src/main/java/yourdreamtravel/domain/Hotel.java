package yourdreamtravel.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Hotel {
    private HotelId id;
    private String nom;
    private BigDecimal prix;
    private ArrayList<Chambre> chambres;
}

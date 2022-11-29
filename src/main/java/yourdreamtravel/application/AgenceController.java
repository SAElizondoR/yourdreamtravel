package yourdreamtravel.application;

import yourdreamtravel.domain.Agence;

public class AgenceController {
    private final Agence agence;

    public AgenceController() {
        agence = new Agence();
    }

    public Agence getAgence() {
        return agence;
    }
}

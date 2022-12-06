package yourdreamtravel.domain;

import java.util.Calendar;

public class ServiceLoueur extends Service {
    private LoueurVoiture loueur;
    private Voiture voiture;

    public ServiceLoueur(Calendar dateDebut, Calendar dateFin, LoueurVoiture loueur, Voiture voiture) {
        super(dateDebut, dateFin);
        this.loueur = loueur;
        this.voiture = voiture;
    }

    public LoueurVoiture getLoueur() {
        return loueur;
    }

    public Voiture getVoiture() {
        return voiture;
    }
}

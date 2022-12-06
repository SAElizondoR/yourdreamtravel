package yourdreamtravel.domain;

public class ServiceLoueur {
    private ServiceLoueurId id;
    private LoueurVoiture loueur;
    private Voiture voiture;

    public ServiceLoueur(LoueurVoiture loueur, Voiture voiture) {
        id = new ServiceLoueurId();
        this.loueur = loueur;
        this.voiture = voiture;
    }

    public ServiceLoueurId getId() {
        return id;
    }

    public LoueurVoiture getLoueur() {
        return loueur;
    }

    public Voiture getVoiture() {
        return voiture;
    }
}

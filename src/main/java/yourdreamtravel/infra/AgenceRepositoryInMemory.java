package yourdreamtravel.infra;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.AgenceRepository;
import yourdreamtravel.domain.Catalogue;
import yourdreamtravel.domain.CatalogueId;
import yourdreamtravel.domain.Chambre;
import yourdreamtravel.domain.Hotel;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.LoueurVoiture;
import yourdreamtravel.domain.Voiture;
import yourdreamtravel.domain.Vol;

public class AgenceRepositoryInMemory implements AgenceRepository {
    Set<Agence> memory;

    public AgenceRepositoryInMemory() {
        memory = new HashSet<>();
    }

    @Override
    public void save(Agence agence) throws IOException {
        memory.add(agence);
    }

    public Agence retrieveAgenceData() {
        List<Lieu> destinations = retrieveDestinationData();
        List<Lieu> hotelDestinations = retrieveHotelDestinationData();
        List<Vol> vols = retrieveVolData(destinations);
        List<Hotel> hotels = retrieveHotelData(hotelDestinations);
        List<LoueurVoiture> loueurs = retrieveLoueurData(hotelDestinations);
        
        Catalogue catalogue = new Catalogue(new CatalogueId(), destinations,
            vols, hotels, loueurs, hotelDestinations);
        return new Agence(catalogue);
    }

    private List<Lieu> retrieveDestinationData() {
        return Arrays.asList(
            new Lieu("Paris"),
            new Lieu("Bordeaux"),
            new Lieu("Camberra"),
            new Lieu("Tokio"),
            new Lieu("Delhi"));
    }

    private List<Lieu> retrieveHotelDestinationData() {
        return Arrays.asList(
            new Lieu("Paris"),
            new Lieu("Bordeaux"),
            new Lieu("Camberra"),
            new Lieu("Tokio"),
            new Lieu("Delhi"),
            new Lieu("Sydney"),
            new Lieu("Calcuta"));
    }

    private List<Vol> retrieveVolData(List<Lieu> destinations) {
        List<Calendar> lesDates = Arrays.asList(
            new GregorianCalendar(2023, 1, 2, 15, 0),
            new GregorianCalendar(2023, 1, 3, 8, 22),
            new GregorianCalendar(2022, 1, 5, 12, 0),
            new GregorianCalendar(2022, 1, 5, 13, 58)
        );

        return Arrays.asList(
            new Vol(destinations.get(0), destinations.get(1), 1, lesDates),
            new Vol(destinations.get(0), destinations.get(2), 24, lesDates),
            new Vol(destinations.get(0), destinations.get(4), 8, lesDates),
            new Vol(destinations.get(1), destinations.get(0), 1, lesDates),
            new Vol(destinations.get(1), destinations.get(3), 16, lesDates),
            new Vol(destinations.get(1), destinations.get(4), 10, lesDates),
            new Vol(destinations.get(2), destinations.get(0), 26, lesDates),
            new Vol(destinations.get(2), destinations.get(3), 12, lesDates),
            new Vol(destinations.get(2), destinations.get(4), 15, lesDates),
            new Vol(destinations.get(3), destinations.get(0), 14, lesDates),
            new Vol(destinations.get(3), destinations.get(1), 18, lesDates),
            new Vol(destinations.get(3), destinations.get(2), 12, lesDates),
            new Vol(destinations.get(4), destinations.get(0), 9, lesDates),
            new Vol(destinations.get(4), destinations.get(1), 11, lesDates)
        );
    }

    private List<Hotel> retrieveHotelData(List<Lieu> lieux) {
        List<Chambre> lesChambres = Arrays.asList(
            new Chambre(12, 50),
            new Chambre(14, 70),
            new Chambre(14, 70),
            new Chambre(14, 70),
            new Chambre(18, 90),
            new Chambre(18, 90),
            new Chambre(20, 140)
        );

        return Arrays.asList(
            new Hotel("Continental", lieux.get(0), lesChambres),
            new Hotel("Britannique", lieux.get(0), lesChambres),
            new Hotel("Reinassence", lieux.get(1), lesChambres),
            new Hotel("Apparthotel", lieux.get(1), lesChambres),
            new Hotel("Crowne Plaza", lieux.get(2), lesChambres),
            new Hotel("Novotel", lieux.get(2), lesChambres),
            new Hotel("Keihan", lieux.get(3), lesChambres),
            new Hotel("Manga", lieux.get(3), lesChambres),
            new Hotel("Suryaa", lieux.get(4), lesChambres),
            new Hotel("Surajkund", lieux.get(4), lesChambres),
            new Hotel("Australia hotel", lieux.get(5), lesChambres),
            new Hotel("Uttar hotel", lieux.get(6), lesChambres)
        );
    }
    
    private List<LoueurVoiture> retrieveLoueurData(List<Lieu> lieux) {
        List<Voiture> lesVoitures = Arrays.asList(
            new Voiture("Mitsubishi", "Outlander", 50),
            new Voiture("Hyundai", "Grand I10", 70),
            new Voiture("Ford", "Durango", 100),
            new Voiture("Chevrolet", "4x4", 120)
        );
        return Arrays.asList(
            new LoueurVoiture(lieux.get(0), "3 Bvd. De Gaulle", lesVoitures),
            new LoueurVoiture(lieux.get(0), "5 Italie", lesVoitures),
            new LoueurVoiture(lieux.get(1), "10 Libération", lesVoitures),
            new LoueurVoiture(lieux.get(1), "11 Marne", lesVoitures),
            new LoueurVoiture(lieux.get(2), "11 Washington", lesVoitures),
            new LoueurVoiture(lieux.get(3), "80 Kuzumi", lesVoitures),
            new LoueurVoiture(lieux.get(4), "1 Bhagabagdita", lesVoitures),
            new LoueurVoiture(lieux.get(5), "3 Tomson", lesVoitures),
            new LoueurVoiture(lieux.get(6), "4 Pradesh", lesVoitures)
        );
    }
}

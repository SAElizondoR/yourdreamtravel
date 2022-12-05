package yourdreamtravel.infra;

import java.io.IOException;
import java.util.ArrayList;
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
import yourdreamtravel.domain.Lieu;
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
        List<Vol> vols = retrieveVolData(destinations);
        
        Catalogue catalogue = new Catalogue(new CatalogueId(), destinations,
            vols, new ArrayList<>(), new ArrayList<>());
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

    private List<Vol> retrieveVolData(List<Lieu> destinations) {
        List<Calendar> lesDates = Arrays.asList(
            new GregorianCalendar(2023, 1, 2, 15, 0),
            new GregorianCalendar(2023, 1, 3, 8, 22),
            new GregorianCalendar(2022, 1, 5, 12, 0),
            new GregorianCalendar(2022, 1, 5, 13, 58)
        );
        return Arrays.asList(
            new Vol(destinations.get(0), destinations.get(1), lesDates),
            new Vol(destinations.get(0), destinations.get(2), lesDates),
            new Vol(destinations.get(0), destinations.get(4), lesDates),
            new Vol(destinations.get(1), destinations.get(0), lesDates),
            new Vol(destinations.get(1), destinations.get(3), lesDates),
            new Vol(destinations.get(1), destinations.get(4), lesDates),
            new Vol(destinations.get(2), destinations.get(0), lesDates),
            new Vol(destinations.get(2), destinations.get(3), lesDates),
            new Vol(destinations.get(2), destinations.get(4), lesDates),
            new Vol(destinations.get(3), destinations.get(0), lesDates),
            new Vol(destinations.get(3), destinations.get(1), lesDates),
            new Vol(destinations.get(3), destinations.get(2), lesDates),
            new Vol(destinations.get(4), destinations.get(0), lesDates),
            new Vol(destinations.get(4), destinations.get(1), lesDates)
        );
    }
}

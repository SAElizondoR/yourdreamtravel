package yourdreamtravel.infra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.AgenceRepository;
import yourdreamtravel.domain.Catalogue;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.Vol;
import yourdreamtravel.domain.VolId;

public class AgenceRepositoryInMemory implements AgenceRepository {
    Set<Agence> memory;

    public AgenceRepositoryInMemory() {
        memory = new HashSet<>();
    }

    @Override
    public void save(Agence agence) throws IOException {
        memory.add(agence);
    }

    public Agence initialiserAgence() {
        ArrayList<Lieu> destinations = new ArrayList<>();
        destinations.add(new Lieu("Paris"));
        destinations.add(new Lieu("Bordeaux"));
        destinations.add(new Lieu("Camberra"));
        destinations.add(new Lieu("Tokio"));
        destinations.add(new Lieu("Delhi"));
        
        Catalogue catalogue = new Catalogue(destinations, new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>());
        return new Agence(catalogue);
    }

    public List<Vol> tousLesVols(Agence agence){
        Calendar cal = Calendar.getInstance();
        ArrayList<Vol> vols = new ArrayList<>();
        ArrayList<Calendar> lesDates = new ArrayList<>();
        cal.set(2022, 12, 22, 12, 0);
        lesDates.add(cal);
        cal.set(2022, 12, 22, 13, 58);
        lesDates.add(cal);
        cal.set(2023, 1, 2, 15, 0);
        lesDates.add(cal);
        cal.set(2023, 1, 3, 8, 22);
        lesDates.add(cal);
        ArrayList<Lieu> dest;
        dest = (ArrayList<Lieu>) agence.getCatalogue().getDestinations();
        vols.add(new Vol(new VolId(), dest.get(0), dest.get(1), lesDates));
        vols.add(new Vol(new VolId(), dest.get(0), dest.get(2), lesDates));
        vols.add(new Vol(new VolId(), dest.get(0), dest.get(3), lesDates));
        vols.add(new Vol(new VolId(), dest.get(0), dest.get(4), lesDates));
        vols.add(new Vol(new VolId(), dest.get(1), dest.get(0), lesDates));
        vols.add(new Vol(new VolId(), dest.get(1), dest.get(3), lesDates));
        vols.add(new Vol(new VolId(), dest.get(1), dest.get(4), lesDates));
        vols.add(new Vol(new VolId(), dest.get(2), dest.get(0), lesDates));
        vols.add(new Vol(new VolId(), dest.get(2), dest.get(3), lesDates));
        vols.add(new Vol(new VolId(), dest.get(3), dest.get(1), lesDates));
        vols.add(new Vol(new VolId(), dest.get(3), dest.get(2), lesDates));
        vols.add(new Vol(new VolId(), dest.get(4), dest.get(0), lesDates));
        vols.add(new Vol(new VolId(), dest.get(4), dest.get(1), lesDates));
        vols.add(new Vol(new VolId(), dest.get(4), dest.get(2), lesDates));
        return vols;
    }

}

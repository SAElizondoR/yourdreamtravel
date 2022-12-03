package yourdreamtravel.infra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.AgenceRepository;
import yourdreamtravel.domain.Catalogue;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.LieuId;

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
        destinations.add(new Lieu(new LieuId(), "Paris"));
        destinations.add(new Lieu(new LieuId(), "Bordeaux"));
        destinations.add(new Lieu(new LieuId(), "Camberra"));
        destinations.add(new Lieu(new LieuId(), "Tokio"));
        destinations.add(new Lieu(new LieuId(), "Delhi"));
        
        Catalogue catalogue = new Catalogue(destinations);
        return new Agence(catalogue);
    }
}

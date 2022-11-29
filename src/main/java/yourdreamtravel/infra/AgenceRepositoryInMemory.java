package yourdreamtravel.infra;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.AgenceRepository;

public class AgenceRepositoryInMemory implements AgenceRepository {
    Set<Agence> memory;

    public AgenceRepositoryInMemory() {
        memory = new HashSet<>();
    }

    @Override
    public void save(Agence agence) throws IOException {
        memory.add(agence);
    }
}

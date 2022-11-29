package yourdreamtravel.domain;

import java.io.IOException;

public interface AgenceRepository {
    public void save(Agence agence) throws IOException;
}

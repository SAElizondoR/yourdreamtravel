package yourdreamtravel.application;

import java.util.List;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.Client;
import yourdreamtravel.infra.AgenceRepositoryInMemory;

public class AgenceService {
    private final Agence agence;
    private Client clientActif;
    private AgenceRepositoryInMemory repository;

    public AgenceService() {
        repository = new AgenceRepositoryInMemory();
        agence = repository.initialiserAgence();
    }

    public void addClient(String nom) {
        agence.addClient(nom);
    }

    public List<String> getClientNames() {
        return agence.getClientNames();
    }

    public void setClientActifByIndex(int clientIndex) {
        clientActif = agence.getClients().get(clientIndex);
    }

    public List<String> getDestinationNames() {
        return agence.getCatalogue().getDestinationNames();
    }
}

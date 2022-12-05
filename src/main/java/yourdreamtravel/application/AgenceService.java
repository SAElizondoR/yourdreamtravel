package yourdreamtravel.application;

import java.util.ArrayList;
import java.util.List;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.Client;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.Vol;
import yourdreamtravel.infra.AgenceRepositoryInMemory;

public class AgenceService {
    private final Agence agence;
    private Client clientActif;
    private AgenceRepositoryInMemory repository;

    public AgenceService() {
        repository = new AgenceRepositoryInMemory();
        agence = repository.retrieveAgenceData();
    }

    public void addClient(String nom) {
        agence.addClient(nom);
    }

    public void setClientActifByIndex(int clientIndex) {
        clientActif = agence.getClients().get(clientIndex);
    }

    public List<String> proposerItineraire(String departName,
        String destinationName) {
        Lieu depart = agence.getCatalogue().getDestinationByName(departName);
        Lieu destination = agence.getCatalogue().getDestinationByName(
            destinationName);
        List<Vol> itineraire = agence.getCatalogue().calculerItineraire(depart, destination);
        List<String> itineraireList = new ArrayList<>();
        itineraireList.add(itineraire.get(0).getDepart().getNom());
        for (Vol vol: itineraire)
            itineraireList.add(vol.getDestination().getNom());
        return itineraireList;
    }

    public List<String> getClientNames() {
        return agence.getClientNames();
    }

    public Client getClientActif() {
        return clientActif;
    }

    public List<String> getDestinationNames() {
        return agence.getCatalogue().getDestinationNames();
    }

    public List<String> getDestinationNamesExcept(int destinationIndex) {
        List<String> destinationNames = getDestinationNames();
        destinationNames.remove(destinationIndex);
        return destinationNames;
    }
}

package yourdreamtravel.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.Client;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.Reservation;
import yourdreamtravel.domain.Vol;
import yourdreamtravel.infra.AgenceRepositoryInMemory;

public class AgenceService {
    private final Agence agence;
    private AgenceRepositoryInMemory repository;
    private Client clientActif;
    private Reservation reservationActif;

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

    // public void setDate

    public List<String> proposerItineraire(String departName,
        String destinationName) {
        Lieu depart = agence.getCatalogue().getDestinationByName(departName);
        Lieu destination = agence.getCatalogue().getDestinationByName(
            destinationName);
        List<Vol> itineraire = agence.getCatalogue().calculerItineraire(depart, destination);
        reservationActif = new Reservation(clientActif, itineraire);
        
        List<String> itineraireString = new ArrayList<>();
        itineraireString.add(reservationActif.getDepart().getNom());
        for (Vol vol: itineraire)
            itineraireString.add(vol.getDestination().getNom());
        
        return itineraireString;
    }

    public List<String> proposerDates() {
        List<String> dateStrings = new ArrayList<>();
        for (Calendar date: reservationActif.getItineraire().get(0).getDates()) {
            dateStrings.add(date.toString());
        }
        return dateStrings;
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
}

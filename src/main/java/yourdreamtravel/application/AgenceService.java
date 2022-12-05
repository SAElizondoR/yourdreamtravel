package yourdreamtravel.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.Client;
import yourdreamtravel.domain.Hotel;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.LoueurVoiture;
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

    public Map<String, Client> getClientsMap() {
        return agence.getClientsMap();
    }

    public void setClientActif(Client client) {
        clientActif = client;
    }

    public Map<String, Lieu> getDestinationMap() {
        return agence.getCatalogue().getDestinationMap();
    }

    public List<String> proposerItineraire(Lieu depart, Lieu destination) {
        Map<String, Vol> itineraireMap = agence.getCatalogue()
            .calculerItineraire(depart, destination);
        reservationActif = new Reservation(clientActif, new ArrayList<>(itineraireMap.values()));
        return new ArrayList<>(itineraireMap.keySet());
    }

    public Map<String, Calendar> getDatesPossibles() {
        return reservationActif.getDatesPossibles();
    }

    public void setDateReservation(Calendar date) {
        reservationActif.setDate(date);
    }

    public Map<String, Hotel> getHotelMap() {
        return agence.getCatalogue().getHotelMap(
            reservationActif.getDestination());
    }

    public Map<String, LoueurVoiture> getLoueurMap() {
        return agence.getCatalogue().getLoueurMap(
            reservationActif.getDestination());
    }

    public Client getClientActif() {
        return clientActif;
    }
}

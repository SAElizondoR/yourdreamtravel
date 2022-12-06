package yourdreamtravel.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.Chambre;
import yourdreamtravel.domain.Client;
import yourdreamtravel.domain.Hotel;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.LoueurVoiture;
import yourdreamtravel.domain.Reservation;
import yourdreamtravel.domain.Vol;
import yourdreamtravel.domain.Service;
import yourdreamtravel.domain.ServiceHotel;
import yourdreamtravel.domain.ServiceLoueur;
import yourdreamtravel.domain.Voiture;
import yourdreamtravel.infra.AgenceRepositoryInMemory;

public class AgenceService {
    private final Agence agence;
    private AgenceRepositoryInMemory repository;
    private Client clientActif;
    private Reservation reservationActif;
    private Lieu destinationActif;

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

    public Map<String, Lieu> getHotelDestinationMap() {
        return agence.getCatalogue().getHotelDestinationMap();
    }

    public List<String> proposerItineraire(Lieu depart, Lieu destination) {
        Map<String, Vol> itineraireMap = agence.getCatalogue()
            .calculerItineraire(depart, destination);
        reservationActif = new Reservation(clientActif, new ArrayList<>(itineraireMap.values()));
        destinationActif = destination;
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
            destinationActif);
    }

    public Map<String, LoueurVoiture> getLoueurMap() {
        return agence.getCatalogue().getLoueurMap(
            destinationActif);
    }

    public Map<String, Chambre> getChambreMap(Hotel hotel) {
        return hotel.getChambreMap();
    }

    public void ajouterServiceHotel(Calendar dateEntree, Calendar dateSortie,
        Hotel hotel, Chambre chambre) {
        Service serviceHotel = new ServiceHotel(dateEntree,
            dateSortie, destinationActif, hotel, chambre);
        reservationActif.addService(serviceHotel);
    }

    public Map<String, Voiture> getVoitureMap(LoueurVoiture loueur) {
        return loueur.getVoitureMap();
    }

    public void ajouterServiceVoiture(Calendar dateDebut, Calendar dateFin,
    LoueurVoiture loueur, Voiture voiture) {
        Service loueurVoiture = new ServiceLoueur(dateDebut, dateFin,
            destinationActif, loueur, voiture);
           reservationActif.addService(loueurVoiture);
    }

    public Lieu getDestinationActif() {
        return destinationActif;
    }

    public void setDestinationActif(Lieu destinationActif) {
        this.destinationActif = destinationActif;
    }

    public Client getClientActif() {
        return clientActif;
    }
}

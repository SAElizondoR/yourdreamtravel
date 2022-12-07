package yourdreamtravel.infra;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import yourdreamtravel.domain.Agence;
import yourdreamtravel.domain.AgenceRepository;
import yourdreamtravel.domain.Catalogue;
import yourdreamtravel.domain.Chambre;
import yourdreamtravel.domain.Client;
import yourdreamtravel.domain.Hotel;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.LoueurVoiture;
import yourdreamtravel.domain.PlaceVol;
import yourdreamtravel.domain.Reservation;
import yourdreamtravel.domain.Service;
import yourdreamtravel.domain.ServiceHotel;
import yourdreamtravel.domain.ServiceLoueur;
import yourdreamtravel.domain.Voiture;
import yourdreamtravel.domain.Vol;

public class AgenceRepositoryInJSON implements AgenceRepository {

    @Override
    public void save(Agence agence) throws IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        Catalogue catalogue = agence.getCatalogue();
        
        for (Lieu lieu: catalogue.getDestinations()) {
            JsonObject lieuObject = Json.createObjectBuilder()
            .add("id", lieu.getId().getValue())
            .add("nom", lieu.getNom())
            .build();
            arrayBuilder.add(lieuObject);
        }
        objectBuilder.add("destinations", arrayBuilder.build());

        arrayBuilder = Json.createArrayBuilder();
        for (Lieu lieu: catalogue.getDestinations()) {
            JsonObject lieuObject = Json.createObjectBuilder()
            .add("id", lieu.getId().getValue())
            .add("nom", lieu.getNom())
            .build();
            arrayBuilder.add(lieuObject);
        }
        objectBuilder.add("hotelDestinations", arrayBuilder.build());

        arrayBuilder = Json.createArrayBuilder();
        for (Vol vol: catalogue.getVols()) {
            JsonArrayBuilder dateBuilder = Json.createArrayBuilder();
            for (Calendar date: vol.getDates()) {
                dateBuilder.add(date.getTime().toString());
            }

            JsonObject volObject = Json.createObjectBuilder()
            .add("id", vol.getId().getValue())
            .add("depart", vol.getDepart().getId().getValue())
            .add("destination", vol.getDestination().getId().getValue())
            .add("dates", dateBuilder.build())
            .add("prix", vol.getPrix())
            .build();
            arrayBuilder.add(volObject);
        }
        objectBuilder.add("vols", arrayBuilder.build());

        arrayBuilder = Json.createArrayBuilder();
        for (Hotel hotel: catalogue.getHotels()) {
            JsonArrayBuilder chambreBuilder = Json.createArrayBuilder();
            for (Chambre chambre: hotel.getChambres()) {
                JsonObject chambreObject = Json.createObjectBuilder()
                .add("taille", chambre.getTaille())
                .add("prix", chambre.getPrix())
                .build();
                chambreBuilder.add(chambreObject);
            }

            JsonObject hotelObject = Json.createObjectBuilder()
            .add("id", hotel.getId().getValue())
            .add("nom", hotel.getNom())
            .add("lieu", hotel.getLieu().getId().getValue())
            .add("chambres", chambreBuilder.build())
            .build();
            arrayBuilder.add(hotelObject);
        }
        objectBuilder.add("hotels", arrayBuilder.build());

        arrayBuilder = Json.createArrayBuilder();
        for (LoueurVoiture loueur: catalogue.getLoueurs()) {
            JsonArrayBuilder voitureBuilder = Json.createArrayBuilder();
            for (Voiture voiture: loueur.getVoitures()) {
                JsonObject voitureObject = Json.createObjectBuilder()
                .add("marque", voiture.getMarque())
                .add("model", voiture.getModel())
                .add("prix", voiture.getPrix())
                .build();
                voitureBuilder.add(voitureObject);
            }

            JsonObject loueurObject = Json.createObjectBuilder()
            .add("id", loueur.getId().getValue())
            .add("lieu", loueur.getLieu().getId().getValue())
            .add("adresse", loueur.getAdresse())
            .add("voitures", voitureBuilder.build())
            .build();
            arrayBuilder.add(loueurObject);
        }
        objectBuilder.add("loueurs", arrayBuilder.build());

        arrayBuilder = Json.createArrayBuilder();
        for (Client client: agence.getClients()) {
            JsonObject clientObject = Json.createObjectBuilder()
            .add("id", client.getId().getValue())
            .add("nom", client.getNom())
            .build();
            arrayBuilder.add(clientObject);
        }
        objectBuilder.add("clients", arrayBuilder.build());

        arrayBuilder = Json.createArrayBuilder();

        for (Reservation reservation: agence.getReservations()) {
            JsonArrayBuilder placeVolBuilder = Json.createArrayBuilder();
            for (PlaceVol placeVol: reservation.getPlaceVols()) {
                JsonObject placeVolObject = Json.createObjectBuilder()
                .add("id", placeVol.getId().getValue())
                .add("vol", placeVol.getVol().getId().getValue())
                .add("place", placeVol.getPlace())
                .add("classe", placeVol.getClasse())
                .build();
                placeVolBuilder.add(placeVolObject);
            }

            JsonArrayBuilder serviceBuilder = Json.createArrayBuilder();
            for (Service service: reservation.getServices()) {
                JsonObjectBuilder serviceObjectBuilder = Json.createObjectBuilder()
                .add("id", service.getId().getValue())
                .add("dateDebut", service.getDateDebut().toString())
                .add("dateFin", service.getDateFin().toString())
                .add("lieu", service.getLieu().getId().getValue());

                if (service instanceof ServiceHotel) {
                    serviceObjectBuilder.add("type", "hotel")
                    .add("hotel", ((ServiceHotel) service).getHotel().getId().getValue())
                    .add("chambre", ((ServiceHotel) service).getChambre().getId().getValue())
                    .add("luxe", ((ServiceHotel) service).getPrestations());
                }
                else {
                    serviceObjectBuilder.add("type", "loueur")
                    .add("loueur", ((ServiceLoueur) service).getLoueur().getId().getValue())
                    .add("voiture", ((ServiceLoueur) service).getVoiture().getId().getValue());
                }
                serviceBuilder.add(serviceObjectBuilder.build());
            }


            JsonObject reservationObject = Json.createObjectBuilder()
            .add("id", reservation.getId().getValue())
            .add("client", reservation.getClient().getId().getValue())
            .add("placeVols", placeVolBuilder.build())
            .add("date", reservation.getDate().toString())
            .add("services", serviceBuilder.build())
            .add("reduced", reservation.isReduced())
            .build();
            arrayBuilder.add(reservationObject);
        }
        objectBuilder.add("reservations", arrayBuilder);

        arrayBuilder = Json.createArrayBuilder();
        for (var ticket: agence.getTicketsReduction().entrySet()) {
            JsonObject ticketObject = Json.createObjectBuilder()
            .add("lieu", ticket.getKey().getId().getValue())
            .add("tickets", ticket.getValue())
            .build();
            arrayBuilder.add(ticketObject);
        }
        objectBuilder.add("ticketsReduction", arrayBuilder);

        try (BufferedWriter buff
            = new BufferedWriter(new FileWriter("agence.txt"))) {
            buff.write(objectBuilder.build().toString());
            buff.flush();
        }
    }
}
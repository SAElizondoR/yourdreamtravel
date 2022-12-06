package yourdreamtravel.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import yourdreamtravel.application.AgenceService;
import yourdreamtravel.domain.Client;
import yourdreamtravel.domain.Hotel;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.LoueurVoiture;

public class TextUI {
    private final AgenceService agenceService;
    private final BufferedReader buff;

    public TextUI() {
        buff = new BufferedReader(new InputStreamReader(System.in));
        agenceService = new AgenceService();
    }

    public void run() {
        int choix = 0;
        do {
            System.console().printf("\nSÉLECTIONNER UNE OPTION\n");
            choix = choisirOption(Arrays.asList("Créer client", "Selectionner un client",
                "Quitter le programme"));
            switch (choix) {
                case 1:
                    creerClient();
                    break;
                case 2:
                    selectionnerClient();
                    break;
                default:
            }
        } while (choix != 3);
    }

    private void creerClient() {
        System.console().printf("CRÉER CLIENT\n");
        System.console().printf("Entrez le nom: ");
        String nom = lireChaine();
        agenceService.addClient(nom);
        System.console().printf("Client ajouté!\n");
    }

    private void selectionnerClient() {
        Map<String, Client> clients = agenceService.getClientsMap();
        System.console().printf("SÉLECTIONNER CLIENT\n");
        List<String> clientNames = new ArrayList<>(clients.keySet());
        int index = choisirOption(clientNames);
        agenceService.setClientActif(clients.get(clientNames.get(index - 1)));
        clientMenu();
    }

    private void clientMenu() {
        System.console().printf("MENU DU CLIENT\n");
        int choix = choisirOption(Arrays.asList("Faire une réservation de voyage",
            "Quitter le menu du client"));
        if (choix == 1)
            faireReservationVoyage();
    }

    private void faireReservationVoyage() {
        System.console().printf("FAIRE UNE RÉSERVATION DE VOYAGE\n");

        Map<String, Lieu> destinations = agenceService.getDestinationMap();
        System.console().printf("Sélectionnez le lieu de départ:\n");
        List<String> destinationNames = new ArrayList<>(destinations.keySet());
        int index = choisirOption(destinationNames);
        Lieu depart = destinations.get(destinationNames.get(index - 1));
        destinationNames.remove(index - 1);

        System.console().printf("Sélectionnez la destination:\n");
        index = choisirOption(destinationNames);
        Lieu destination = destinations.get(destinationNames.get(index - 1));
        System.console().printf("Itinéraire proposé:\n%s\n", String.join("\n",
            agenceService.proposerItineraire(depart, destination)));

        index = choisirOption(Arrays.asList("Accepter", "Réfuser"));
        if (index != 1)
            return;
        
        Map<String, Calendar> dates = agenceService.getDatesPossibles();
        System.console().printf("Sélectionnez la date de départ:\n");
        List<String> dateStrings = new ArrayList<>(dates.keySet());
        index = choisirOption(dateStrings);
        agenceService.setDateReservation(dates.get(dateStrings.get(index)));

        System.console().printf("Sélecionnez le type de service:\n");
        index = choisirOption(Arrays.asList("Service simple", "Service haute gamme", "Sans service"));
        switch (index) {
            case 1:
                menuServiceSimple();
                break;
            case 2:
                menuServiceHauteGamme();
                break;
            default:
        }

    }

    private void menuServiceSimple() {
        faireReservationHotel();
        locationDeVoiture();
    }

    private void menuServiceHauteGamme() {
        /* a implémenter */
    }

    private void faireReservationHotel() {
        Map<String, Hotel> hotels = agenceService.getHotelMap();
        System.console().printf("FAIRE UNE RÉSERVATION D'HOTEL\n");
        List<String> hotelNames = new ArrayList<>(hotels.keySet());
        int index = choisirOption(hotelNames);
    }

    private void locationDeVoiture() {
        Map<String, LoueurVoiture> loueurs = agenceService.getLoueurMap();
        System.console().printf("LOCATION DE VOITURE\n");
        List<String> loueurNames = new ArrayList<>(loueurs.keySet());
        int index = choisirOption(loueurNames);
    }

    private int choisirOption(List<String> options) {
        int compteur = 1;
        for (String option: options)
            System.console().printf("%d) %s\n", compteur++, option);
        System.console().printf("Votre choix: ");
        try {
            int choix = Integer.parseInt(lireChaine());
            System.console().printf("\n");
            return choix;
        } catch (NumberFormatException e) {
            System.console().printf("Entrez un numéro.\n");
        }
        return 0;
    }

    private String lireChaine() {
        try {
            return buff.readLine();
        } catch (IOException e) {
            System.console().printf("Impossible de lire l'entré.\n");
        }
        return null;
    }
}

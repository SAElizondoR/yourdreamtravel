package yourdreamtravel.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import yourdreamtravel.application.AgenceService;
import yourdreamtravel.domain.Chambre;
import yourdreamtravel.domain.Client;
import yourdreamtravel.domain.Hotel;
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.LoueurVoiture;
import yourdreamtravel.domain.Voiture;

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
            System.out.printf("\nSÉLECTIONNER UNE OPTION\n");
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
        System.out.printf("CRÉER CLIENT\n");
        System.out.printf("Entrez le nom: ");
        String nom = lireChaine();
        agenceService.addClient(nom);
        System.out.printf("Client ajouté!\n");
    }

    private void selectionnerClient() {
        Map<String, Client> clients = agenceService.getClientsMap();
        System.out.printf("SÉLECTIONNER CLIENT\n");
        List<String> clientNames = new ArrayList<>(clients.keySet());
        int index = choisirOption(clientNames);
        agenceService.setClientActif(clients.get(clientNames.get(index - 1)));
        clientMenu();
    }

    private void clientMenu() {
        System.out.printf("MENU DU CLIENT\n");
        int choix = choisirOption(Arrays.asList("Faire une réservation de voyage",
            "Quitter le menu du client"));
        if (choix == 1)
            faireReservationVoyage();
    }

    private void faireReservationVoyage() {
        System.out.printf("FAIRE UNE RÉSERVATION DE VOYAGE\n");

        Map<String, Lieu> destinations = agenceService.getDestinationMap();
        System.out.printf("Sélectionnez le lieu de départ:\n");
        List<String> destinationNames = new ArrayList<>(destinations.keySet());
        int index = choisirOption(destinationNames);
        Lieu depart = destinations.get(destinationNames.get(index - 1));
        destinationNames.remove(index - 1);

        System.out.printf("Sélectionnez la destination:\n");
        index = choisirOption(destinationNames);
        Lieu destination = destinations.get(destinationNames.get(index - 1));
        System.out.printf("Itinéraire proposé:\n%s\n", String.join("\n",
            agenceService.proposerItineraire(depart, destination)));

        index = choisirOption(Arrays.asList("Accepter", "Réfuser"));
        if (index != 1)
            return;

        Map<String, Calendar> dates = agenceService.getDatesPossibles();
        System.out.printf("Sélectionnez la date de départ:\n");
        List<String> dateStrings = new ArrayList<>(dates.keySet());
        index = choisirOption(dateStrings);
        agenceService.setDateReservation(dates.get(dateStrings.get(index - 1)));

        System.out.printf("Sélecionnez le type de service:\n");
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
        System.out.printf("Vous voulez louer une voiture ?\n");
        int index = choisirOption(Arrays.asList("Oui", "Non"));
        if (index == 1)
            locationDeVoiture();
    }

    private void menuServiceHauteGamme() {
        faireReservationHotel();
        locationDeVoiture();

        Map<String, Lieu> destinations = agenceService.getHotelDestinationMap();
        destinations.remove(agenceService.getDestinationActif());
        System.out.printf("Sélectionnez la deuxieme destination:\n");
        List<String> destinationNames = new ArrayList<>(destinations.keySet());
        int index = choisirOption(destinationNames);
        Lieu deuxiemeDestination = destinations.get(destinationNames.get(index - 1));
        
    }

    private void faireReservationHotel() {
        Map<String, Hotel> hotels = agenceService.getHotelMap();
        System.out.printf("FAIRE UNE RÉSERVATION D'HOTEL\n");
        List<String> hotelNames = new ArrayList<>(hotels.keySet());
        int index = choisirOption(hotelNames);
        Hotel hotel = hotels.get(hotelNames.get(index - 1));
        Map<String, Chambre> chambres = agenceService.getChambreMap(hotel);
        System.out.printf("Sélectionnez une chambre:\n");
        List<String> chambreNames = new ArrayList<>(chambres.keySet());
        index = choisirOption(chambreNames);
        Chambre chambre = chambres.get(chambreNames.get(index - 1));
        System.out.printf("Date d'entrée (jj/mm/aaaa): ");
        Calendar dateEntree = lireDate();
        System.out.printf("\nDate de sortie (jj/mm/aaaa): ");
        Calendar dateSortie = lireDate();
        agenceService.ajouterServiceHotel(dateEntree, dateSortie,
            hotel, chambre);
    }

    private void locationDeVoiture() {
        Map<String, LoueurVoiture> loueurs = agenceService.getLoueurMap();
        System.out.printf("LOCATION DE VOITURE\n");
        List<String> loueurNames = new ArrayList<>(loueurs.keySet());
        int index = choisirOption(loueurNames);
        LoueurVoiture loueur = loueurs.get(loueurNames.get(index - 1));
        Map<String, Voiture> voitures = agenceService.getVoitureMap(loueur);
        System.out.printf("Sélectionnez une voiture:\n");
        List<String> voitureNames = new ArrayList<>(voitures.keySet());
        index = choisirOption(voitureNames);
        Voiture voiture = voitures.get(voitureNames.get(index - 1));
        System.out.printf("Date début (jj/mm/aaaa): ");
        Calendar dateDebut = lireDate();
        System.out.printf("\nDate fin (jj/mm/aaaa): ");
        Calendar dateFin = lireDate();
        System.out.printf("\n");
        agenceService.ajouterServiceVoiture(dateDebut, dateFin,
            loueur, voiture);
    }

    private int choisirOption(List<String> options) {
        int compteur = 1;
        for (String option: options)
            System.out.printf("%d) %s\n", compteur++, option);
        System.out.printf("Votre choix: ");
        try {
            int choix = Integer.parseInt(lireChaine());
            System.out.printf("\n");
            return choix;
        } catch (NumberFormatException e) {
            System.out.printf("Entrez un numéro.\n");
        }
        return 0;
    }

    private Calendar lireDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String input = lireChaine();
        return GregorianCalendar.from(
            LocalDate.parse(input, formatter).atStartOfDay(ZoneOffset.UTC)
        );
    }

    private String lireChaine() {
        try {
            return buff.readLine();
        } catch (IOException e) {
            System.out.printf("Impossible de lire l'entré.\n");
        }
        return null;
    }
}

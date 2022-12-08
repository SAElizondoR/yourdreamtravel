package yourdreamtravel.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
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
import yourdreamtravel.domain.Vol;

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
            choix = choisirOption(Arrays.asList("Créer client",
                "Selectionner un client", "Sauver l'information",
                "Quitter le programme"));
            switch (choix) {
                case 1:
                    creerClient();
                    break;
                case 2:
                    selectionnerClient();
                    break;
                case 3:
                    sauverInformation();
                    break;
                default:
            }
        } while (choix != 4);
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
        if (clients.size() == 0) {
            System.out.printf("Il n'y a pas de clients!\n");
            return;
        }
        List<String> clientNames = new ArrayList<>(clients.keySet());
        int index = 1;
        System.out.printf("SÉLECTIONNER CLIENT\n");
        index = choisirOption(clientNames);
        
        agenceService.setClientActif(clients.get(clientNames.get(index - 1)));
        clientMenu();
    }

    private void clientMenu() {
        System.out.printf("MENU DU CLIENT\n");
        int choix = choisirOption(Arrays.asList(
            "Faire une réservation de voyage", "Quitter le menu du client"));
        if (choix == 1)
            faireReservationVoyage();
    }

    private void faireReservationVoyage() {
        System.out.printf("FAIRE UNE RÉSERVATION DE VOYAGE\n");

        Map<String, Lieu> destinations = agenceService.getDestinationMap();
        System.out.printf("Sélectionnez le lieu de départ:\n");
        List<String> destinationNames
            = new ArrayList<>(destinations.keySet());
        int index = choisirOption(destinationNames);
        Lieu depart = destinations.get(destinationNames.get(index - 1));
        destinationNames.remove(index - 1);

        System.out.printf("Sélectionnez la destination:\n");
        index = choisirOption(destinationNames);
        Lieu destination = destinations.get(destinationNames.get(index - 1));
        Map<String, Vol> itineraire = agenceService.proposerItineraire(depart, destination);
        List<String> itineraireString = new ArrayList<>(itineraire.keySet());
        System.out.printf("Itinéraire proposé:\n%s\n", String.join("\n",
            itineraireString));
        List<Vol> itineraireVols = new ArrayList<>(itineraire.values());

        index = choisirOption(Arrays.asList("Accepter", "Réfuser"));
        if (index != 1)
            return;

        Map<String, Calendar> dateOptions
            = agenceService.getDatesPossibles(itineraireVols.get(0));
        System.out.printf("Sélectionnez la date de départ:\n");
        List<String> dateStrings = new ArrayList<>(dateOptions.keySet());
        index = choisirOption(dateStrings);
        Calendar date = dateOptions.get(dateStrings.get(index - 1));

        System.out.printf("Sélectionnez la classe:\n");
        index = choisirOption(Arrays.asList("Premier classe",
            "Deuxieme classe"));
        Integer classe = index;

        agenceService.creerReservation(itineraireVols, date, classe);

        System.out.printf("Sélecionnez le type de service:\n");
        index = choisirOption(Arrays.asList("Service simple",
            "Service haute gamme", "Sans service"));
        switch (index) {
            case 1:
                menuServiceSimple();
                break;
            case 2:
                menuServiceHauteGamme();
                break;
            default:
        }
        agenceService.addReservationActif();

        System.out.printf("Total à payer: %d euros\n", agenceService.computerTotal());
        choisirOption(Arrays.asList("Ok"));

        System.out.printf("Reservation créé !\n");
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

        Map<String, Lieu> destinations
            = agenceService.getHotelDestinationMap();
        destinations.remove(agenceService.getDestinationActif().getNom());
        System.out.printf("Sélectionnez la deuxieme destination:\n");
        List<String> destinationNames
            = new ArrayList<>(destinations.keySet());
        int index = choisirOption(destinationNames);
        Lieu deuxiemeDestination
            = destinations.get(destinationNames.get(index - 1));
        agenceService.setDestinationActif(deuxiemeDestination);

        faireReservationHotel();
        locationDeVoiture();
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
        System.out.printf("Voulez-vous des prestations luxueuses ?\n");
        index = choisirOption(Arrays.asList("Oui", "Non"));
        boolean prestationsLuxe = index == 1;
        agenceService.ajouterServiceHotel(dateEntree, dateSortie,
            hotel, chambre, prestationsLuxe);
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

    private void sauverInformation() {
        try {
            agenceService.saveRepository();
            System.out.println("L'information a été sauve! (agence.txt)");
        } catch (IOException e) {
            System.out.println("Impossible de sauver\n");
        }
    }

    private int choisirOption(List<String> options) {
        int compteur = 1;
        for (String option: options)
            System.out.printf("%d) %s\n", compteur++, option);
        int choix = 0;
        do {
            System.out.printf("Votre choix: ");
            try {
                choix = Integer.parseInt(lireChaine());
                System.out.printf("\n");
            } catch (NumberFormatException e) {
                System.out.printf("Entrez un numéro. ");
                choix = 0;
            }
            if (choix < 1 || choix > options.size())
                System.out.printf("Option invalide.\n");
            else
                break;
        } while (true);
        
        return choix;
    }

    private Calendar lireDate() {
        DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("d/M/yyyy");
        formatter.withResolverStyle(ResolverStyle.LENIENT);
        do {
            String input = lireChaine();
            try {
                return GregorianCalendar.from(
                    LocalDate.parse(input, formatter).atStartOfDay(ZoneOffset.UTC)
                );
            } catch (Exception e) {
                System.out.printf("Le format n'est pas valide. Entrez une date valide (dd/MM/yyyy): ");
            }
        } while (true);
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

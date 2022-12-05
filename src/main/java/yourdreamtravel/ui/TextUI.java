package yourdreamtravel.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import yourdreamtravel.application.AgenceService;

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
        System.console().printf("SÉLECTIONNER CLIENT\n");
        int clientIndex = choisirOption(agenceService.getClientNames());
        agenceService.setClientActifByIndex(clientIndex - 1);
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
        System.console().printf("Sélectionnez le lieu de départ:\n");
        List<String> destinationNames = agenceService.getDestinationNames();
        int index = choisirOption(destinationNames);
        String departName = destinationNames.get(index - 1);
        destinationNames.remove(index - 1);
        System.console().printf("Sélectionnez la destination:\n");
        index = choisirOption(destinationNames);
        String destinationName = destinationNames.get(index - 1);
        System.console().printf("Itinéraire proposé: %s\n", String.join(" - ",
            agenceService.proposerItineraire(departName, destinationName)));
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

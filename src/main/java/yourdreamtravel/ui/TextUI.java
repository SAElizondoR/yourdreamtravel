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
            System.out.println("\nSÉLECTIONNER UNE OPTION");
            choix = choisirOption(Arrays.asList("Créer client", "Selectionner un client",
                "Quitter le programme"));
            System.out.println();
            switch (choix) {
                case 1:
                    creerClient();
                    break;
                case 2:
                    selectionnerClient();
                    break;
                default:
            }
            System.out.println();
        } while (choix != 3);
    }

    private void creerClient() {
        System.out.println("CRÉER CLIENT");
        System.out.print("Entrez le nom: ");
        String nom = lireChaine();
        agenceService.addClient(nom);
        System.out.println("Client ajouté!");
    }

    private void selectionnerClient() {
        System.out.println("SÉLECTIONNER CLIENT");
        int clientIndex = choisirOption(agenceService.getClientNames());
        agenceService.setClientActifByIndex(clientIndex - 1);
        System.out.println();
        clientMenu();
    }

    private void clientMenu() {
        System.out.println("MENU DU CLIENT");
        int choix = choisirOption(Arrays.asList("Faire une réservation de voyage",
            "Quitter le menu du client"));
        System.out.println();
        if (choix == 1)
            faireReservationVoyage();
    }

    private void faireReservationVoyage() {
        System.out.println("FAIRE UNE RÉSERVATION DE VOYAGE");
        System.out.println("\nSélectionnez une destination pour voyager:");
        choisirOption(agenceService.getDestinationNames());
    }

    private int choisirOption(List<String> options) {
        int compteur = 1;
        for (String option: options)
            System.out.printf("%d) %s%n", compteur++, option);
        System.out.print("Votre choix: ");
        try {
            return Integer.parseInt(lireChaine());
        } catch (NumberFormatException e) {
            System.err.println("Entrez un numéro.\n");
        }
        return 0;
    }

    private String lireChaine() {
        try {
            return buff.readLine();
        } catch (IOException e) {
            System.err.println("Impossible de lire l'entré.\n");
        }
        return null;
    }
}

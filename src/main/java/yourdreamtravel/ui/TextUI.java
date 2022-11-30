package yourdreamtravel.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import yourdreamtravel.application.AgenceController;

public class TextUI {
    private final AgenceController agenceController;
    private final BufferedReader buff;

    public TextUI() {
        buff = new BufferedReader(new InputStreamReader(System.in));
        agenceController = new AgenceController();
    }

    public void run() {
        int choix = 0;
        do {
            System.out.println("\n1) Créer client\n2) Selectionner client\n3) Quitter le programme\nVotre choix: ");
            try {
                choix = Integer.parseInt(lireChaine());
            } catch (NumberFormatException e) {
                System.err.println("Entrez un numéro.\n");
            }
            switch (choix) {
                case 1:
                    break;
                case 2:
                    break;
                default:
            }
        } while (choix != 3);
    }

    private void creerClient() {
        System.out.println("\nEntrez le nom: ");
        String nom = lireChaine();
        agenceController.getAgence().addClient(nom);
        System.out.println("Client ajouté!");
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

package yourdreamtravel;

import yourdreamtravel.ui.DisplayUI;
import yourdreamtravel.ui.TextUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * App
 */
public final class App extends Application{


    @Override
    public void start(final Stage primaryStage) throws FileNotFoundException {
        DisplayUI displayUI = new DisplayUI();
        displayUI.run(primaryStage);

    }

    private static int choisirOption(List<String> options) {
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

    private static String lireChaine() {
        BufferedReader buff;
        buff = new BufferedReader(new InputStreamReader(System.in));
        try {
            return buff.readLine();
        } catch (IOException e) {
            System.out.printf("Impossible de lire l'entré.\n");
        }
        return null;
    }

    public static void main(String[] args) {
        int choix = 0;
            System.out.printf("\nSÉLECTIONNER UNE OPTION\n");
            choix = choisirOption(Arrays.asList("mode graphic",
                "mode terminal", "Quitter le programme"));
            switch (choix) {
                case 1:
                    launch(args);
                    break;
                case 2:
                    TextUI textUI = new TextUI();
                    textUI.run();
                    break;
                default:
            }
    }
}

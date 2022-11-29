package yourdreamtravel.ui;

public class TextUI {
    private final BufferedReader buff = new BufferedReader(
        new InputStreamReader(System.in));

    run() {
        do {
            System.out.println("1) Créer client\n
                2) Selectionner client\n
                3) Quitter le programme\n
                Votre choix: ");
            int choix = Integer.parseInt(buff.readLine());
            switch (choix) {
                case 1:
                    break;
                case 2:
                    break;
            }
        } while (choix != 3);
    }
}

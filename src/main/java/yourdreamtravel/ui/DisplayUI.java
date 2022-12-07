package yourdreamtravel.ui;

import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yourdreamtravel.application.AgenceService;
import yourdreamtravel.domain.*;

import java.io.*;
import java.util.*;

public class DisplayUI {
    private final AgenceService agenceService;



    public DisplayUI() {
        agenceService = new AgenceService();
    }

    private void creerClient() {
        VBox root = new VBox();
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);
        Label label = new Label("Quel est votre nom ? ");
        TextField name = new TextField("");
        Scene secondScene = new Scene(root, 400, 400);
        Stage newWindow = new Stage();

        Button buttonValide = new Button();
        buttonValide.setText("valider");
        buttonValide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                agenceService.addClient(name.textProperty().getValue());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(name.textProperty().getValue()+" est un client");
                alert.show();
                newWindow.close();
            }
        });
        root.getChildren().addAll(label, name, buttonValide);
        newWindow.setTitle("Creer un client");
        newWindow.setScene(secondScene);
        // Set position of second window, related to primary window.
        newWindow.setX(newWindow.getX() + 100);
        newWindow.setY(newWindow.getY() + 100);

        newWindow.show();
    }

    private void selectionClient() throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/java/yourdreamtravel/fond.jpg");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        Stage newWindow = new Stage();
        try {
            Pane root = new Pane();
            Scene scene = new Scene(root,400,400);
            Map<String, Client> clients = agenceService.getClientsMap();
            List<String> clientNames = new ArrayList<>(clients.keySet());
            ListView<String> listView = new ListView<String>();
            ObservableList<String> list = FXCollections.observableArrayList();
            listView.setItems(list);
            for(int i=0; i<clientNames.size(); i++) {
                list.add(clients.get(clientNames.get(i)).getNom());
            }
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            listView.setOnMouseClicked(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();
                    for(String s : selectedItems){
                        for(int i =0; i<clients.size(); i++){
                            if(clients.get(clientNames.get(i)).getNom().equals(s)){
                                agenceService.setClientActif(clients.get(clientNames.get(i)));
                                root.getChildren().clear();
                                root.getChildren().add(imageView);
                                faireReservationVoyage(root);
                            }
                        }
                    }
                }
            });
            root.getChildren().add(listView);
            newWindow.setScene(scene);
            newWindow.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void lieuDeDepart(Pane root, List<String> destinationNames, Map<String, Lieu> destinations){
        ComboBox<String> myComboBox1 = new ComboBox<String>();
        Label label1 = new Label("Lieu de depart : ");
        label1.setLayoutX(0);
        label1.setLayoutY(0);
        label1.setStyle("-fx-font-size: 18");
        myComboBox1.setLayoutX(130);
        myComboBox1.setLayoutY(2);

        for(int i=0; i<destinationNames.size(); i++) {
            myComboBox1.getItems().add(destinations.get(destinationNames.get(i)).getNom());
        }
        myComboBox1.setEditable(true);
        myComboBox1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<destinationNames.size(); i++){
                    if(destinations.get(destinationNames.get(i)).getNom().equals(myComboBox1.getValue())){
                        Lieu depart = destinations.get(destinationNames.get(i));
                        //destinationNames.remove(i);
                        lieuDestination(root, destinationNames, destinations, depart);
                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox1, label1);
    }

    private void lieuDestination(Pane root, List<String> destinationNames, Map<String, Lieu> destinations, Lieu depart) {
        ComboBox<String> myComboBox2 = new ComboBox<String>();
        Label label2 = new Label("Lieu d'arriver : ");
        label2.setLayoutX(0);
        label2.setLayoutY(50);
        label2.setStyle("-fx-font-size: 18");
        myComboBox2.setLayoutX(130);
        myComboBox2.setLayoutY(50);

        for(int i=0; i<destinationNames.size(); i++) {
            myComboBox2.getItems().add(destinations.get(destinationNames.get(i)).getNom());
        }
        myComboBox2.setEditable(true);
        myComboBox2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<destinationNames.size(); i++){
                    if(destinations.get(destinationNames.get(i)).getNom().equals(myComboBox2.getValue())){
                        Lieu destination = destinations.get(destinationNames.get(i));
                        if(depart.equals(destination)){
                            Alert alert=new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("impossible");
                            alert.show();
                            System.out.println("impossible");
                        }
                        else{
                            Map<String, Vol> itineraire = agenceService.proposerItineraire(depart, destination);
                            List<String> itineraireString = new ArrayList<>(itineraire.keySet());
                            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Itinéraire proposé: " +String.join("\n", itineraireString));
                            alert.show();
                            System.out.printf("Itinéraire proposé:\n%s\n", String.join("\n",
                                itineraireString));
                            List<Vol> itineraireVols = new ArrayList<>(itineraire.values());
                            Map<String, Calendar> dateOptions = agenceService.getDatesPossibles(itineraireVols.get(0));
                            System.out.printf("Sélectionnez la date de départ:\n");
                            List<String> dateStrings = new ArrayList<>(dateOptions.keySet());
                            choisirDateDeDepart(root, dateStrings, dateOptions, itineraireVols);

                        }

                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox2, label2);
    }

    private void choisirDateDeDepart(Pane root, List<String> dateStrings, Map<String, Calendar> dateOptions, List<Vol> itineraireVols){
        ComboBox<String> myComboBox3 = new ComboBox<String>();
        Label label3 = new Label("date de depart: ");
        label3.setLayoutX(0);
        label3.setLayoutY(100);
        label3.setStyle("-fx-font-size: 18");
        myComboBox3.setLayoutX(130);
        myComboBox3.setLayoutY(100);

        for(int i=0; i<dateStrings.size(); i++) {
            myComboBox3.getItems().add(dateOptions.get(dateStrings.get(i)).getTime().toString());
        }
        myComboBox3.setEditable(true);
        myComboBox3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<dateStrings.size(); i++){
                    if(dateOptions.get(dateStrings.get(i)).getTime().toString().equals(myComboBox3.getValue())){
                        Calendar date = dateOptions.get(dateStrings.get(i));
                        ChosirClasse(root, itineraireVols,date);
                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox3, label3);
    }


    private void ChosirClasse(Pane root, List<Vol> itineraireVols, Calendar date){
        ComboBox<String> myComboBox4 = new ComboBox<String>();
        Label label4 = new Label("Classe ");
        label4.setLayoutX(0);
        label4.setLayoutY(150);
        label4.setStyle("-fx-font-size: 18");
        myComboBox4.setLayoutX(130);
        myComboBox4.setLayoutY(150);
        myComboBox4.getItems().add("Premiere classe");
        myComboBox4.getItems().add("Deuxieme classe");
        myComboBox4.setEditable(true);
        myComboBox4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<2; i++) {
                    switch (myComboBox4.getValue()) {
                        case "Premiere classe":
                            agenceService.creerReservation(itineraireVols, date, 1);
                            ChosirService(root);
                            break;
                        case "Deuxieme classe":
                            agenceService.creerReservation(itineraireVols, date, 2);
                            ChosirService(root);
                            break;
                        default:
                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox4, label4);
    }


    private void ChosirService(Pane root){
        ComboBox<String> myComboBox5 = new ComboBox<String>();
        Label label5 = new Label("Service ");
        label5.setLayoutX(0);
        label5.setLayoutY(200);
        label5.setStyle("-fx-font-size: 18");
        myComboBox5.setLayoutY(200);
        myComboBox5.setLayoutX(130);
        myComboBox5.getItems().add("Service simple");
        myComboBox5.getItems().add("Service haute gamme");
        myComboBox5.setEditable(true);
        myComboBox5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<2; i++) {
                    switch (myComboBox5.getValue()) {
                        case "Service simple":
                            menuServiceSimple(root);
                            break;
                        case "Service haute gamme":
                            //menuServiceHauteGamme();
                            break;
                        default:
                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox5, label5);
    }

    private void menuServiceSimple(Pane root) {
        faireReservationHotel(root);
    }

    private void faireReservationHotel(Pane root) {
        ComboBox<String> myComboBox6 = new ComboBox<String>();
        Label label6 = new Label("Hotel : ");
        label6.setLayoutX(0);
        label6.setLayoutY(250);
        label6.setStyle("-fx-font-size: 18");
        myComboBox6.setLayoutX(130);
        myComboBox6.setLayoutY(250);

        Map<String, Hotel> hotels = agenceService.getHotelMap();
        System.out.printf("FAIRE UNE RÉSERVATION D'HOTEL\n");
        List<String> hotelNames = new ArrayList<>(hotels.keySet());

        for(int i=0; i<hotelNames.size(); i++) {
            myComboBox6.getItems().add(hotels.get(hotelNames.get(i)).getNom());
        }
        myComboBox6.setEditable(true);
        myComboBox6.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<hotelNames.size(); i++){
                    if(hotels.get(hotelNames.get(i)).getNom().equals(myComboBox6.getValue())){
                        Hotel hotel = hotels.get(hotelNames.get(i));
                        FaireReservationChambre(root, hotel);
                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox6, label6);

    }

    private void FaireReservationChambre(Pane root, Hotel hotel){
        ComboBox<String> myComboBox7 = new ComboBox<String>();
        Label label7 = new Label("Chambre : ");
        label7.setStyle("-fx-font-size: 18");
        label7.setLayoutX(0);
        label7.setLayoutY(300);
        myComboBox7.setLayoutX(130);
        myComboBox7.setLayoutY(300);

        Map<String, Chambre> chambres = agenceService.getChambreMap(hotel);
        System.out.printf("Sélectionnez une chambre:\n");
        List<String> chambreNames = new ArrayList<>(chambres.keySet());

        for(int i=0; i<chambreNames.size(); i++) {
            myComboBox7.getItems().add("taille: " + chambres.get(chambreNames.get(i)).getTaille().toString() + " Prix: " + chambres.get(chambreNames.get(i)).getPrix().toString());
        }
        myComboBox7.setEditable(true);
        myComboBox7.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<chambreNames.size(); i++){
                    if(("taille: " + chambres.get(chambreNames.get(i)).getTaille().toString() + " Prix: " + chambres.get(chambreNames.get(i)).getPrix().toString()).equals(myComboBox7.getValue())){
                        Chambre chambre = chambres.get(chambreNames.get(i));
                        System.out.println("taille: " + chambres.get(chambreNames.get(i)).getTaille().toString() + " Prix: " + chambres.get(chambreNames.get(i)).getPrix().toString());
                        //FaireReservationChambre(root, hotel);
                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox7, label7);
    }


    private void faireReservationVoyage(Pane root) {
        Map<String, Lieu> destinations = agenceService.getDestinationMap();
        List<String> destinationNames = new ArrayList<>(destinations.keySet());
        lieuDeDepart(root, destinationNames, destinations);
    }





    public void run(Stage primaryStage) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/java/yourdreamtravel/fond.jpg");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        Button buttonCreerClient = new Button();
        Button buttonSelectClient = new Button();
        buttonCreerClient.setText("Creer un client");
        buttonSelectClient.setText("Selectionner un client");

        Group root = new Group();


        buttonCreerClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                creerClient();
            }
        });

        buttonSelectClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    selectionClient();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        buttonCreerClient.setLayoutX(150);
        buttonCreerClient.setLayoutY(100);
        buttonSelectClient.setLayoutX(325);
        buttonSelectClient.setLayoutY(100);
        buttonCreerClient.setStyle("-fx-font: bold italic 10pt \"Arial\";\n" +"    -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        buttonSelectClient.setStyle("-fx-font: bold italic 10pt \"Arial\";\n" +"    -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");


        root.getChildren().addAll(imageView);
        root.getChildren().addAll(buttonCreerClient, buttonSelectClient);
        //root.getChildren().add(buttonSelectClient);

        Scene scene = new Scene(root, 600, 400);


        primaryStage.setTitle("YOUR DREAM TRAVEL");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }



}

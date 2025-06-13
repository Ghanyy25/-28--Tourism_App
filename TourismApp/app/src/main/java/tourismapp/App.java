package tourismapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    private String Destination = "";
    private Wisata wisata;
    private Hotel hotel;
    private Planning planning; 

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.wisata = new Wisata(this, primaryStage);
        this.hotel = new Hotel(this, primaryStage);
        this.planning = new Planning(this, primaryStage);
        showMainMenu();
    }

    public void showMainMenu() {
        Label titleLabel = new Label("TOURISM APP");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.DARKBLUE);

        Label subtitleLabel = new Label("Pilih Fitur yang Ingin Digunakan");
        subtitleLabel.setFont(Font.font("Arial", 16));
        subtitleLabel.setTextFill(Color.GRAY);

        Button tourismBtn = UIHelp.createMenuButton("🏝 WISATA", "Destinasi Wisata");
        Button hotelBtn = UIHelp.createMenuButton("🏨 HOTEL", "Cari Hotel Sekitar Destinasi");
        Button planningBtn = UIHelp.createMenuButton("📋 PLANNING", "Perencanaan Trip & Budget");
        Button exitBtn = UIHelp.createMenuButton("🚪 KELUAR", "Tutup Aplikasi");

        tourismBtn.setOnAction(e -> wisata.show());

        hotelBtn.setOnAction(e -> {
            if (Destination.isEmpty()) {
                UIHelp.showAlert("Informasi", "Silakan pilih destinasi wisata dahulu!", Alert.AlertType.WARNING);
            } else {
                hotel.show();
            }
        });

        planningBtn.setOnAction(e -> {
            if (Destination.isEmpty()) {
                UIHelp.showAlert("Informasi", "Silakan pilih destinasi wisata dahulu!", Alert.AlertType.WARNING);
            } else {
                planning.show();
            }
        });

        exitBtn.setOnAction(e -> primaryStage.close());

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(20);
        buttonGrid.setVgap(20);
        buttonGrid.setPadding(new Insets(30));

        buttonGrid.add(tourismBtn, 0, 0);
        buttonGrid.add(hotelBtn, 1, 0);
        buttonGrid.add(planningBtn, 0, 1);
        buttonGrid.add(exitBtn, 1, 1);

        Label destinationInfo = new Label();
        if (!Destination.isEmpty()) {
            destinationInfo.setText("📍 Destinasi Terpilih: " + Destination);
            destinationInfo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            destinationInfo.setTextFill(Color.DARKGREEN);
        }

        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(20);
        mainLayout.setPadding(new Insets(40));

        if (!Destination.isEmpty()) {
            mainLayout.getChildren().addAll(titleLabel, subtitleLabel, destinationInfo, buttonGrid);
        } else {
            mainLayout.getChildren().addAll(titleLabel, subtitleLabel, buttonGrid);
        }

        mainLayout.setStyle("-fx-background: linear-gradient(to bottom, #e3f2fd, #bbdefb);");

        Scene scene = new Scene(mainLayout, 800, 550);
        primaryStage.setTitle("Tourism App - Menu Utama");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getSelectedDestination() {
        return Destination;
    }

    public void setSelectedDestination(String destination) {
        this.Destination = destination;
    }

    public boolean hasSelectedDestination() {
        return !Destination.isEmpty();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
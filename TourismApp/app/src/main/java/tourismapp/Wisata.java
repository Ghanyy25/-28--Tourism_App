package tourismapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class Wisata {
    
    private App mainApp;
    private Stage primaryStage;
    private Map<String, String> destinationDetails;
    private Map<String, List<String>> destinationImages;
    private ScrollPane thumbnailScrollPane;
    private GridPane thumbnailContainer;
    
    public Wisata(App mainApp, Stage primaryStage) {
        this.mainApp = mainApp;
        this.primaryStage = primaryStage;
        DestinationData();
        DestinationImages();
    }
    
    private void DestinationData() {
        destinationDetails = new HashMap<>();
        
        destinationDetails.put("Pantai Kuta", 
            "🏖 PANTAI KUTA, BALI\n\n" +
            "📍 Lokasi: Badung, Bali\n" +
            "⭐ Rating: 4.5/5\n" +
            "🌅 Terkenal dengan sunset yang menakjubkan\n" +
            "🏄‍♂ Aktivitas: Surfing, Beach Volleyball, Spa\n" +
            "🍽 Kuliner: Seafood segar, Nasi Ayam Kedewatan\n" +
            "💰 Budget: Rp 300.000 - 1.500.000/hari\n\n" +
            "Pantai yang sangat populer di Bali dengan pasir putih halus dan ombak yang cocok untuk surfing pemula.");
            
        destinationDetails.put("Candi Borobudur", 
            "🛕 CANDI BOROBUDUR, YOGYAKARTA\n\n" +
            "📍 Lokasi: Magelang, Jawa Tengah\n" +
            "⭐ Rating: 4.8/5\n" +
            "🏛 Candi Buddha terbesar di dunia\n" +
            "🌅 Aktivitas: Sunrise tour, Fotografi, Cultural tour\n" +
            "🍽 Kuliner: Gudeg, Bakpia, Wedang Ronde\n" +
            "💰 Budget: Rp 200.000 - 800.000/hari\n\n" +
            "Warisan dunia UNESCO dengan arsitektur yang menakjubkan dan pemandangan sunrise yang memukau.");
            
        destinationDetails.put("Gunung Bromo", 
            "🌋 GUNUNG BROMO, JAWA TIMUR\n\n" +
            "📍 Lokasi: Tengger, Malang, Jawa Timur\n" +
            "⭐ Rating: 4.7/5\n" +
            "🌄 Pemandangan kaldera dan sunrise spektakuler\n" +
            "🚙 Aktivitas: Jeep tour, Hiking, Horseback riding\n" +
            "🍽 Kuliner: Rawon, Pecel, Tahu Tempe Penyet\n" +
            "💰 Budget: Rp 400.000 - 1.200.000/hari\n\n" +
            "Gunung berapi aktif dengan lanskap seperti planet Mars dan sunrise yang tak terlupakan.");
            
        destinationDetails.put("Danau Toba", 
            "🏞 DANAU TOBA, SUMATERA UTARA\n\n" +
            "📍 Lokasi: Sumatera Utara\n" +
            "⭐ Rating: 4.6/5\n" +
            "🌊 Danau vulkanik terbesar di Indonesia\n" +
            "🛶 Aktivitas: Boat tour, Pulau Samosir, Budaya Batak\n" +
            "🍽 Kuliner: Ikan Mas Arsik, Saksang, Babi Panggang\n" +
            "💰 Budget: Rp 250.000 - 900.000/hari\n\n" +
            "Danau kaldera raksasa dengan Pulau Samosir di tengahnya, kaya akan budaya Batak.");
    }
    
    private void DestinationImages() {
        destinationImages = new HashMap<>();
        
        destinationImages.put("Pantai Kuta", Arrays.asList(
            "/Kuta1.jpg",      
            "/Kuta2.jpg",
            "/Kuta3.jpg",
            "/Kuta4.jpg",      
            "/Kuta5.jpg"
        ));
        
        destinationImages.put("Candi Borobudur", Arrays.asList(
            "/Borobudur1.jpg",      
            "/Borobudur2.jpg",
            "/Borobudur3.jpg",
            "/Borobudur4.jpg",      
            "/Borobudur5.jpg",
            "/Borobudur6.jpg",
            "/Borobudur7.jpg"
        ));
        
        destinationImages.put("Gunung Bromo", Arrays.asList(
            "/Bromo1.jpg",      
            "/Bromo2.jpg",
            "/Bromo3.jpg",
            "/Bromo4.jpg",      
            "/Bromo5.jpg"
        ));
        
        destinationImages.put("Danau Toba", Arrays.asList(
            "/Toba1.jpg",      
            "/Toba2.jpg",
            "/Toba3.jpg",
            "/Toba4.jpg",      
            "/Toba5.jpg"
        ));
    }
    
    public void show() {
        String[] destinations = {"Pantai Kuta", "Candi Borobudur", "Gunung Bromo", "Danau Toba"};

        ListView<String> listdestinasi = new ListView<>();
        listdestinasi.getItems().addAll(destinations);

        TextArea detailArea = new TextArea();
        detailArea.setEditable(false);
        detailArea.setPrefHeight(200);

        thumbnailContainer = new GridPane();
        thumbnailContainer.setHgap(8);
        thumbnailContainer.setVgap(8);
        thumbnailContainer.setPadding(new Insets(5, 8, 5, 8));
        thumbnailScrollPane = new ScrollPane(thumbnailContainer);
        thumbnailScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        thumbnailScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        thumbnailScrollPane.setPrefHeight(160);
        thumbnailScrollPane.setMaxHeight(160);
        thumbnailScrollPane.setStyle(
            "-fx-background-color: white; " +
            "-fx-border-color: #cccccc; " +
            "-fx-border-width: 1px; " +
            "-fx-border-radius: 4px; " +
            "-fx-background-radius: 4px;"
        );
        
        Label photoLabel = new Label("📸 Galeri Foto ");
        photoLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: #666666;");

        Button selectDestinationBtn = new Button("Pilih Destinasi Ini");
        selectDestinationBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        
        Button backBtn = new Button("⬅ Kembali ke Menu");
        backBtn.setOnAction(e -> mainApp.showMainMenu());

        listdestinasi.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String details = getDestinationDetails(newVal);
                detailArea.setText(details);
                loadThumbnailImages(newVal);
            }
        });

        selectDestinationBtn.setOnAction(e -> {
            String selected = listdestinasi.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mainApp.setSelectedDestination(selected);
                UIHelp.showAlert("Sukses", "Destinasi " + selected + " berhasil dipilih!\nSekarang Anda bisa mengakses semua fitur.", Alert.AlertType.INFORMATION);
                mainApp.showMainMenu();
            } else {
                UIHelp.showAlert("Peringatan", "Pilih destinasi terlebih dahulu!", Alert.AlertType.WARNING);
            }
        });

        VBox leftPane = new VBox(new Label("Destinasi Wisata"), listdestinasi, selectDestinationBtn, backBtn);
        leftPane.setSpacing(10);
        
        VBox photoSection = new VBox(photoLabel, thumbnailScrollPane);
        photoSection.setSpacing(5);
        photoSection.setPadding(new Insets(10, 10, 10, 10));
        
        VBox rightPane = new VBox(new Label("Detail Destinasi"), detailArea, photoSection);
        rightPane.setSpacing(10);

        HBox root = new HBox(leftPane, rightPane);
        root.setSpacing(15);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 800, 550);
        primaryStage.setTitle("Aplikasi Pariwisata");
        primaryStage.setScene(scene);
    }
    
    private String getDestinationDetails(String destination) {
        return destinationDetails.getOrDefault(destination, "Detail destinasi tidak tersedia.");
    }
    
    private void loadThumbnailImages(String destination) {
        List<String> imageList = destinationImages.get(destination);
        thumbnailContainer.getChildren().clear();
        thumbnailContainer.setVgap(10);
        thumbnailContainer.setHgap(10);
        thumbnailContainer.setPadding(new Insets(5));

        if (imageList != null && !imageList.isEmpty()) {
            for (int i = 0; i < imageList.size(); i++) {
                String imagePath = imageList.get(i);
                ImageView thumbnail = createThumbnail(imagePath, i + 1);

                final String finalImagePath = imagePath;
                thumbnail.setOnMouseClicked(e -> showImage(finalImagePath, destination));

                int row = i / 3;
                int col = i % 3;
                thumbnailContainer.add(thumbnail, col, row);
            }
        } else {
            Label noImageLabel = new Label("📷 Tidak ada foto tersedia");
            noImageLabel.setStyle("-fx-text-fill: #999999; -fx-font-style: italic;");
            thumbnailContainer.getChildren().add(noImageLabel);
        }
    }
    
    private ImageView createThumbnail(String imagePath, int imageNumber) {
        ImageView thumbnail = new ImageView();
        thumbnail.setFitWidth(140);
        thumbnail.setFitHeight(140);
        thumbnail.setPreserveRatio(false);
        thumbnail.setSmooth(true);
        
        thumbnail.setStyle(
            "-fx-border-color: #dddddd; " +
            "-fx-border-width: 1px; " +
            "-fx-border-radius: 3px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 2, 0, 0, 1);"
        );
        
        thumbnail.setOnMouseEntered(e -> {
            thumbnail.setStyle(
                "-fx-border-color: #2196F3; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 3px; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(33,150,243,0.3), 4, 0, 0, 1); " +
                "-fx-cursor: hand;"
            );
        });
        
        thumbnail.setOnMouseExited(e -> {
            thumbnail.setStyle(
                "-fx-border-color: #dddddd; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 3px; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 2, 0, 0, 1);"
            );
        });
        
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            if (image.isError()) {
                image = new Image("file:" + imagePath);
            }
            if (image.isError()) {
                createPlaceholderThumbnail(thumbnail, imageNumber);
            } else {
                thumbnail.setImage(image);
            }
        } catch (Exception e) {
            createPlaceholderThumbnail(thumbnail, imageNumber);
        }
        
        return thumbnail;
    }
    
    private void createPlaceholderThumbnail(ImageView thumbnail, int imageNumber) {
        thumbnail.setImage(null);
        System.out.println("Image " + imageNumber + " not found");
    }
    
    private void showImage(String imagePath, String destinationName) {
        Stage imageStage = new Stage();
        imageStage.setTitle("📸 " + destinationName + " - Foto Detail");
        imageStage.initModality(Modality.APPLICATION_MODAL);
        imageStage.initOwner(primaryStage);
        
        ImageView enlargedImage = new ImageView();
        enlargedImage.setFitWidth(600);
        enlargedImage.setFitHeight(450);
        enlargedImage.setPreserveRatio(true);
        enlargedImage.setSmooth(true);
        
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            if (image.isError()) {
                image = new Image("file:" + imagePath);
            }
            if (!image.isError()) {
                enlargedImage.setImage(image);
            }
        } catch (Exception e) {
            System.out.println("Failed to load enlarged image: " + imagePath);
        }
        
        Button closeBtn = new Button("✖ Tutup");
        closeBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        closeBtn.setOnAction(e -> imageStage.close());
        
        VBox imageContainer = new VBox(enlargedImage, closeBtn);
        imageContainer.setSpacing(10);
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setPadding(new Insets(15));
        imageContainer.setStyle("-fx-background-color: #f5f5f5;");
        
        Scene imageScene = new Scene(imageContainer);
        imageStage.setScene(imageScene);
        imageStage.setResizable(false);
        imageStage.showAndWait();
    }
}
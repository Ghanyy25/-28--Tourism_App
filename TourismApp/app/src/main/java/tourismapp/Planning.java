package tourismapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Planning {

    private App app;
    private Stage primaryStage;

    public Planning(App app, Stage primaryStage) {
        this.app = app;
        this.primaryStage = primaryStage;
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        String destination = app.getSelectedDestination();
        String itinerary = generateItinerary(destination);

        // ================= ITINERARY TEXT =================
        TextArea itineraryArea = new TextArea();
        itineraryArea.setText(itinerary);
        itineraryArea.setWrapText(true);
        itineraryArea.setEditable(false);
        itineraryArea.setPrefHeight(300);
        itineraryArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 14;");
        VBox.setVgrow(itineraryArea, Priority.ALWAYS);

        ScrollPane itineraryScroll = new ScrollPane(itineraryArea);
        itineraryScroll.setFitToWidth(true);
        itineraryScroll.setPrefHeight(350);

        // ================= IMAGE GALLERY =================
        HBox imageGallery = new HBox(10);
        imageGallery.setAlignment(Pos.CENTER);
        imageGallery.setPadding(new Insets(10));

        List<String> imagePaths = getImagePaths(destination);
        for (String path : imagePaths) {
            try {
                if (getClass().getResourceAsStream(path) != null) {
                    Image image = new Image(getClass().getResourceAsStream(path));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(200);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    imageView.setStyle("-fx-effect: dropshadow(three-pass-box, gray, 5, 0, 0, 2);");

                    // ✅ Event untuk memperbesar gambar saat diklik
                    imageView.setOnMouseClicked(event -> {
                        Stage popupStage = new Stage();
                        popupStage.setTitle("📸 Gambar Besar");

                        ImageView largeImageView = new ImageView(image);
                        largeImageView.setPreserveRatio(true);
                        largeImageView.setFitWidth(800);
                        largeImageView.setSmooth(true);

                        StackPane popupRoot = new StackPane(largeImageView);
                        popupRoot.setPadding(new Insets(10));
                        Scene popupScene = new Scene(popupRoot);
                        popupStage.setScene(popupScene);
                        popupStage.show();
                    });

                    imageGallery.getChildren().add(imageView);
                } else {
                    System.err.println("⚠ Gambar tidak ditemukan: " + path);
                }
            } catch (Exception ex) {
                System.err.println("❌ Gagal memuat gambar " + path + ": " + ex.getMessage());
            }
        }

        ScrollPane imageScroll = new ScrollPane(imageGallery);
        imageScroll.setFitToHeight(true);
        imageScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        imageScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        imageScroll.setPrefHeight(220);

        // ================= BACK BUTTON =================
        Button backButton = UIHelp.createMenuButton("🔙 KEMBALI", "Kembali ke Menu Utama");
        backButton.setOnAction(e -> app.showMainMenu());

        HBox buttonBox = new HBox(backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));

        // Layout utama
        VBox centerBox = new VBox(15, itineraryScroll, imageScroll);
        root.setCenter(centerBox);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 800, 700);
        primaryStage.setTitle("Tourism App - Planning");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<String> getImagePaths(String destination) {
        Map<String, List<String>> imageMap = new HashMap<>();
        imageMap.put("Pantai Kuta", Arrays.asList("/pkuta1.jpg", "/pkuta2.jpg", "/pkuta3.jpg", "/pkuta4.jpg", "/pkuta5.jpg"));
        imageMap.put("Candi Borobudur", Arrays.asList("/pborobudur1.jpg", "/pborobudur2.jpg", "/pborobudur3.jpg", "/pborobudur4.jpg", "/pborobudur5.jpg"));
        imageMap.put("Gunung Bromo", Arrays.asList("/pbromo1.jpg", "/pbromo2.jpg", "/pbromo3.jpg", "/pbromo4.jpg", "/pbromo5.jpg"));
        imageMap.put("Danau Toba", Arrays.asList("/ptoba1.jpg", "/ptoba2.jpg", "/ptoba3.jpg", "/ptoba4.jpg", "/ptoba5.jpg"));

        return imageMap.getOrDefault(destination, Arrays.asList());
    }

    private String generateItinerary(String destination) {
        String baseHeader = "📋 SUGGESTED 3-DAY ITINERARY\n📍 Destination: " + destination + "\n\n═══════════════════════════════════════════════\n\n";
        switch (destination.toLowerCase()) {
            case "pantai kuta":
                return baseHeader +
                    "📅 HARI 1 - Beach Arrival\n" +
                    "09:00 - Tiba di Bandara Ngurah Rai\n" +
                    "11:00 - Check-in hotel dekat Pantai Kuta\n" +
                    "13:00 - Lunch seafood tepi pantai\n" +
                    "15:00 - Main surfing di Kuta\n" +
                    "18:00 - Sunset & foto-foto\n" +
                    "20:00 - Dinner & Night Market\n\n" +
                    "📅 HARI 2 - Budaya & Belanja\n" +
                    "08:00 - Sarapan di hotel\n" +
                    "09:00 - Kunjungan ke Pura Uluwatu\n" +
                    "12:00 - Lunch khas Bali\n" +
                    "14:00 - Belanja di Beachwalk Mall\n" +
                    "17:00 - Tari Kecak di pantai\n" +
                    "20:00 - Dinner romantis\n\n" +
                    "📅 HARI 3 - Relaksasi\n" +
                    "09:00 - Spa tradisional Bali\n" +
                    "11:00 - Coffeeshop lokal\n" +
                    "13:00 - Makan siang & check-out\n" +
                    "15:00 - Ke bandara\n\n" + tipsAndEmergency();

            case "candi borobudur":
                return baseHeader +
                    "📅 HARI 1 - Kedatangan & Eksplorasi\n" +
                    "09:00 - Tiba di Yogyakarta\n" +
                    "11:00 - Perjalanan ke Magelang\n" +
                    "13:00 - Check-in hotel dekat Candi Borobudur\n" +
                    "15:00 - Sunset view di Punthuk Setumbu\n" +
                    "18:00 - Dinner lokal\n\n" +
                    "📅 HARI 2 - Candi & Sejarah\n" +
                    "04:30 - Sunrise tour Candi Borobudur\n" +
                    "08:00 - Sarapan tradisional\n" +
                    "10:00 - Kunjungi Candi Mendut\n" +
                    "12:00 - Museum Borobudur\n" +
                    "15:00 - Workshop batik\n" +
                    "19:00 - Cultural performance\n\n" +
                    "📅 HARI 3 - Santai & Belanja\n" +
                    "09:00 - Pasar tradisional\n" +
                    "11:00 - Makan siang\n" +
                    "13:00 - Perjalanan kembali ke Jogja\n" +
                    "15:00 - Transfer ke bandara\n\n" + tipsAndEmergency();

            case "gunung bromo":
                return baseHeader +
                    "📅 HARI 1 - Perjalanan ke Bromo\n" +
                    "10:00 - Tiba di Surabaya\n" +
                    "12:00 - Perjalanan ke Probolinggo\n" +
                    "15:00 - Check-in penginapan\n" +
                    "17:00 - Makan malam & istirahat\n\n" +
                    "📅 HARI 2 - Sunrise & Petualangan\n" +
                    "03:00 - Jeep tour ke Penanjakan\n" +
                    "05:00 - Sunrise di Bromo\n" +
                    "07:00 - Lautan Pasir & Kawah Bromo\n" +
                    "10:00 - Sarapan & bersantai\n" +
                    "13:00 - Bukit Teletubbies\n" +
                    "17:00 - Dinner lokal\n\n" +
                    "📅 HARI 3 - Pulang\n" +
                    "09:00 - Sarapan\n" +
                    "11:00 - Perjalanan ke Surabaya\n" +
                    "14:00 - Oleh-oleh khas\n" +
                    "16:00 - Ke bandara\n\n" + tipsAndEmergency();

            case "danau toba":
            default:
                return baseHeader +
                    "📅 HARI 1 - Arrival & Lake Tour\n" +
                    "09:00 - Tiba di Medan\n" +
                    "11:00 - Perjalanan ke Danau Toba (3 jam)\n" +
                    "14:00 - Lunch di Parapat\n" +
                    "15:00 - Boat tour ke Pulau Samosir\n" +
                    "17:00 - Check-in hotel\n" +
                    "19:00 - Dinner ikan mas arsik\n\n" +
                    "📅 HARI 2 - Budaya & Alam\n" +
                    "08:00 - Sarapan\n" +
                    "09:00 - Museum Batak\n" +
                    "11:00 - Desa tradisional Batak\n" +
                    "13:00 - Lunch lokal\n" +
                    "15:00 - Air terjun Sipiso-piso\n" +
                    "18:00 - Tari Tor-tor\n" +
                    "20:00 - Dinner budaya\n\n" +
                    "📅 HARI 3 - Relaksasi & Pulang\n" +
                    "08:00 - Sarapan\n" +
                    "10:00 - Memancing di danau\n" +
                    "12:00 - Lunch & check-out\n" +
                    "14:00 - Boat ke Parapat\n" +
                    "15:00 - Perjalanan ke Medan\n" +
                    "18:00 - Transfer ke bandara\n\n" + tipsAndEmergency();
        }
    }

    private String tipsAndEmergency() {
        return
            "💡 TIPS PERJALANAN:\n" +
            "─────────────────\n" +
            "• Bawa pakaian sesuai cuaca destinasi\n" +
            "• Jangan lupa obat-obatan pribadi\n" +
            "• Siapkan uang tunai untuk pembayaran lokal\n" +
            "• Download offline map untuk navigasi\n" +
            "• Hormati budaya lokal\n" +
            "• Jaga kebersihan lingkungan\n\n" +
            "📱 KONTAK DARURAT:\n" +
            "─────────────────\n" +
            "• Polisi: 110\n" +
            "• Ambulans: 118\n" +
            "• Pemadam Kebakaran: 113\n" +
            "• Tourist Police: 021-5703002";
    }
}
package tourismapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

public class Hotel {
    private App mainApp;
    private Stage primaryStage;
    private Map<String, List<HotelInfo>> hotelData;
    private GridPane thumbnailContainer;

    public Hotel(App mainApp, Stage primaryStage) {
        this.mainApp = mainApp;
        this.primaryStage = primaryStage;
        initializeHotelData();
    }

    private void initializeHotelData() {
        hotelData = new HashMap<>();

        hotelData.put("Pantai Kuta", Arrays.asList(
            new HotelInfo("THE 1O1 Bali Fontana Seminyak", "Jl. Dewi Sri no. 68, Legian, Kuta 80361 Indonesia", "Rp 550.000/malam", "⭐ 4.5",
                Arrays.asList("/hotel_kuta1_1.jpg", "/hotel_kuta1_2.jpg", "/hotel_kuta1_3.jpg"),
                Arrays.asList("🛏️ Kamar Nyaman", "📶 Wi-Fi Gratis", "🏊 Kolam Renang", "🍽️ Restoran")),
            new HotelInfo("Amnaya Resort Kuta", "Gg. Puspa Ayu No. 99 Jalan Kartika Plaza, Kuta 80361 Indonesia", "Rp 1.200.000/malam", "⭐ 4.8",
                Arrays.asList("/hotel_kuta2_1.jpg", "/hotel_kuta2_2.jpg", "/hotel_kuta2_3.jpg"),
                Arrays.asList("🛏️ Kamar Premium", "💆 Spa", "📶 Wi-Fi", "🍽️ Restoran", "🏋️ Gym", "🏊 Kolam Renang")),
            new HotelInfo("Discovery Kartika Plaza Hotel", "Jl. Kartika Plaza, Kuta 80361 Indonesia", "Rp 1.300.000/malam", "⭐ 4.5",
                Arrays.asList("/hotel_kuta3_1.jpg", "/hotel_kuta3_2.jpg", "/hotel_kuta3_3.jpg"),
                Arrays.asList("🌊 Akses Pantai", "🛏️ Suite", "🍽️ Fine Dining", "📶 Wi-Fi", "🏊 Kolam Renang")),
            new HotelInfo("Bali Mandira Beach Resort & Spa", "Jl. Padma No. 2, Legian 80361 Indonesia", "Rp 850.000/malam", "⭐ 4.4",
                Arrays.asList("/hotel_kuta4_1.jpg", "/hotel_kuta4_2.jpg", "/hotel_kuta4_3.jpg"),
                Arrays.asList("🌅 View Laut", "💆 Spa", "🏊 Kolam Anak & Dewasa", "🍹 Bar Tepi Kolam")),
            new HotelInfo("Rama Residence Padma", "Jl. Padma, Legian 80631 Indonesia", "Rp 1.200.000/malam", "⭐ 4.8",
                Arrays.asList("/hotel_kuta5_1.jpg", "/hotel_kuta5_2.jpg", "/hotel_kuta5_3.jpg"),
                Arrays.asList("🛏️ Apartemen Mewah", "🍳 Dapur Lengkap", "🧺 Laundry", "📶 Wi-Fi", "💪 Fitness Center"))
        ));

        hotelData.put("Candi Borobudur", Arrays.asList(
            new HotelInfo("Plataran Borobudur Resort & Spa", "Jl. Dusun Tanjungan, Borobudur, Magelang 56553 Indonesia", "Rp 4.600.000/malam", "⭐ 4.8",
                Arrays.asList("/hotel_borobudur1_1.jpg", "/hotel_borobudur1_2.jpg", "/hotel_borobudur1_3.jpg"),
                Arrays.asList("🛏️ Villa Mewah", "🌄 View Candi Borobudur", "💆 Spa Tradisional", "🍷 Wine Lounge", "🏊 Infinity Pool")),
            new HotelInfo("Villa Borobudur Resort", "Jl. Pete Dusun Sawah, Majaksingi, Borobudur, Magelang 56553 Indonesia", "Rp 1.600.000/malam", "⭐ 4.7",
                Arrays.asList("/hotel_borobudur2_1.jpg", "/hotel_borobudur2_2.jpg", "/hotel_borobudur2_3.jpg"),
                Arrays.asList("🏯 Arsitektur Jawa", "🛏️ Kamar Artistik", "🍽️ Restoran", "📶 Wi-Fi", "💆 Spa")),
            new HotelInfo("Hotel Le Temple", "Jalan Sawah Borobudur, Borobudur, Magelang 56553 Indonesia", "Rp 4.900.000/malam", "⭐ 4.9",
                Arrays.asList("/hotel_borobudur3_1.jpg", "/hotel_borobudur3_2.jpg", "/hotel_borobudur3_3.jpg"),
                Arrays.asList("🛏️ Suite Premium", "🌅 Sunrise View", "🍾 Sarapan Eksklusif", "💆 Layanan Pijat", "🚗 Shuttle Gratis")),
            new HotelInfo("Manohara Borobudur Hotel", "Jl. Badrawati Komplek Taman Wisata Candi Borobudur, Borobudur, Magelang 56553 Indonesia", "Rp 2.700.000/malam", "⭐ 4.1",
                Arrays.asList("/hotel_borobudur4_1.jpg", "/hotel_borobudur4_2.jpg", "/hotel_borobudur4_3.jpg"),
                Arrays.asList("🎫 Tiket Masuk Candi", "📷 Tour Guide", "🍽️ Restoran", "🛏️ Kamar Nyaman")),
            new HotelInfo("Amanjiwo", "Ds. Majaksingi, Borobudur, Magelang 56553 Indonesia", "Rp 20.000.000/malam", "⭐ 4.8",
                Arrays.asList("/hotel_borobudur5_1.jpg", "/hotel_borobudur5_2.jpg", "/hotel_borobudur5_3.jpg"),
                Arrays.asList("🏛️ Arsitektur Mewah", "🛏️ Suite Privat", "🍷 Fine Dining", "🛕 Akses Eksklusif Candi", "🛩️ Antar-jemput Helikopter"))
        ));

        hotelData.put("Gunung Bromo", Arrays.asList(
            new HotelInfo("Manis Ae Cabin & Resto Bromo", "Jl. Raya Bromo Dusun I, Jetak, Sukapura 67254 Indonesia", "Rp 250.000/malam", "⭐ 4.8",
                Arrays.asList("/hotel_bromo1_1.jpg", "/hotel_bromo1_2.jpg", "/hotel_bromo1_3.jpg"),
                Arrays.asList("🔥 Api Unggun", "🏕️ Kabin Kayu", "🍽️ Makanan Tradisional")),
            new HotelInfo("Lava View Lodge Hotel", "Cemorolawang, Ngadisari 67254 Indonesia", "Rp 870.000/malam", "⭐ 4.0",
                Arrays.asList("/hotel_bromo2_1.jpg", "/hotel_bromo2_2.jpg", "/hotel_bromo2_3.jpg"),
                Arrays.asList("🌋 View Gunung Bromo", "🛏️ Kamar Luas", "🍳 Sarapan", "📶 Wi-Fi")),
            new HotelInfo("Bromo Terrace Hotel & Resto", "Jl. Raya Bromo, Sapikerep, Sukapura 67254 Indonesia", "Rp 1.200.000/malam", "⭐ 3.9",
                Arrays.asList("/hotel_bromo3_1.jpg", "/hotel_bromo3_2.jpg", "/hotel_bromo3_3.jpg"),
                Arrays.asList("🌲 Pemandangan Alam", "🍽️ Resto Modern", "🛏️ Kamar Nyaman", "📶 Wi-Fi")),
            new HotelInfo("Lereng Bromo Hotel", "Jl. Bromo Ledoksari, Pasuruan, Tosari 67177 Indonesia", "Rp 1.400.000/malam", "⭐ 4.2",
                Arrays.asList("/hotel_bromo4_1.jpg", "/hotel_bromo4_2.jpg", "/hotel_bromo4_3.jpg"),
                Arrays.asList("🌄 Balkon Gunung", "🛏️ Kamar Keluarga", "🔥 Perapian", "🍽️ Restoran")),
            new HotelInfo("Bromo Backpacker Tosari", "Dusun Ledoksari, Tosari, Pasuruan 67177 Indonesia", "Rp 220.000/malam", "⭐ 4.3",
                Arrays.asList("/hotel_bromo5_1.jpg", "/hotel_bromo5_2.jpg", "/hotel_bromo5_3.jpg"),
                Arrays.asList("🛌 Dormitory Bersih", "📶 Wi-Fi", "☕ Area Santai", "🧳 Penyimpanan Barang"))
        ));

        hotelData.put("Danau Toba", Arrays.asList(
            new HotelInfo("Taman Simalem Resort", "Jl. Sidikalang Km. 9, Merek 22173 Indonesia", "Rp 1.300.000/malam", "⭐ 4.5",
                Arrays.asList("/hotel_toba1_1.jpg", "/hotel_toba1_2.jpg", "/hotel_toba1_3.jpg"),
                Arrays.asList("🌲 Hutan Pinus", "🚴 Aktivitas Outdoor", "🍽️ Resto Panorama", "🛏️ Kamar Nyaman")),
            new HotelInfo("Mas Cottages", "Tuk Tuk Pinda Raya Tuk Tuk Pindaraya, Samosir 22395 Indonesia", "Rp 350.000/malam", "⭐ 4.0",
                Arrays.asList("/hotel_toba2_1.jpg", "/hotel_toba2_2.jpg", "/hotel_toba2_3.jpg"),
                Arrays.asList("🏝️ Tepi Danau", "🛶 Sewa Kano", "🍳 Sarapan Lokal")),
            new HotelInfo("Reggae Guest House", "Jl. Lkr. Tuktuk, Tuktuk Siadang, Samosir 22395 Indonesia", "Rp 250.000/malam", "⭐ 4.1",
                Arrays.asList("/hotel_toba3_1.jpg", "/hotel_toba3_2.jpg", "/hotel_toba3_3.jpg"),
                Arrays.asList("🎶 Musik Live", "🍛 Resto Santai", "📶 Wi-Fi Gratis")),
            new HotelInfo("Laster Jony's Guesthouse Tuktuk Samosir RedPartner", "Jl. Lkr. Tuktuk, Tuktuk Siadong, Samosir 22395 Indonesia", "Rp 130.000/malam", "⭐ 4.4",
                Arrays.asList("/hotel_toba4_1.jpg", "/hotel_toba4_2.jpg", "/hotel_toba4_3.jpg"),
                Arrays.asList("🛏️ Kamar Budget", "🍽️ Dapur Bersama", "🚲 Sewa Sepeda")),
            new HotelInfo("Villa Durian", "Jl. Lkr. Tuktuk Ambarita, Samosir 22395 Indonesia", "Rp 400.000/malam", "⭐ 4.4",
                Arrays.asList("/hotel_toba5_1.jpg", "/hotel_toba5_2.jpg", "/hotel_toba5_3.jpg"),
                Arrays.asList("🌺 Taman Tropis", "🛏️ Kamar Bersih", "🍽️ Resto Tradisional"))
        ));
    }


    public void show() {
        String destination = mainApp.getSelectedDestination();
        List<HotelInfo> hotels = hotelData.getOrDefault(destination, new ArrayList<>());

        ListView<String> hotelListView = new ListView<>();
        for (HotelInfo h : hotels) hotelListView.getItems().add(h.getName());

        TextArea detailArea = new TextArea();
        detailArea.setEditable(false);
        detailArea.setPrefHeight(250);

        thumbnailContainer = new GridPane();
        thumbnailContainer.setHgap(10);
        thumbnailContainer.setVgap(10);
        thumbnailContainer.setPadding(new Insets(10));

        ScrollPane imageScroll = new ScrollPane(thumbnailContainer);
        imageScroll.setPrefHeight(150);
        imageScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Label photoLabel = new Label("📸 Galeri Foto (Klik untuk memperbesar)");
        photoLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: #666666;");

        VBox rightPane = new VBox(new Label("Detail Hotel"), detailArea, photoLabel, imageScroll);
        rightPane.setSpacing(10);
        rightPane.setPadding(new Insets(10));

        hotelListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            HotelInfo selected = hotels.stream().filter(h -> h.getName().equals(newVal)).findFirst().orElse(null);
            if (selected != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("🏨 *").append(selected.getName()).append("*\n");
                sb.append("📍 ").append(selected.getAddress()).append("\n");
                sb.append("💰 ").append(selected.getPrice()).append("\n");
                sb.append("🌟 ").append(selected.getRating()).append("\n\n");
                sb.append("🛎️ Fasilitas:\n");
                for (String f : selected.getFacilities()) {
                    sb.append("   • ").append(f).append("\n");
                }
                detailArea.setText(sb.toString());
                loadHotelImages(selected.getImagePaths(), selected.getName());
            }
        });

        Button backBtn = new Button("⬅️ Kembali ke Menu");
        backBtn.setOnAction(e -> mainApp.showMainMenu());

        Button bookBtn = new Button("🛏️ Booking Hotel Ini");
        bookBtn.setOnAction(e -> UIHelp.showAlert("Booking", "Hotel berhasil dibooking!", Alert.AlertType.INFORMATION));
        HBox buttonBar = new HBox(backBtn, bookBtn);
        buttonBar.setSpacing(10);

        VBox leftPane = new VBox(new Label("Daftar Hotel"), hotelListView, buttonBar);
        leftPane.setSpacing(10);
        leftPane.setPadding(new Insets(10));

        HBox root = new HBox(leftPane, rightPane);
        root.setSpacing(15);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 800, 550);
        primaryStage.setTitle("Rekomendasi Hotel - " + destination);
        primaryStage.setScene(scene);
    }

    private void loadHotelImages(List<String> imagePaths, String hotelName) {
        thumbnailContainer.getChildren().clear();

        for (int i = 0; i < imagePaths.size(); i++) {
            String path = imagePaths.get(i);
            ImageView thumbnail = new ImageView();
            thumbnail.setFitWidth(140);
            thumbnail.setFitHeight(140);
            thumbnail.setPreserveRatio(false);
            thumbnail.setSmooth(true);

            try {
                Image image = new Image(getClass().getResourceAsStream(path));
                if (!image.isError()) {
                    thumbnail.setImage(image);
                    final String imgPath = path;
                    thumbnail.setOnMouseClicked(e -> showEnlargedImage(imgPath, hotelName));
                    thumbnailContainer.add(thumbnail, i % 3, i / 3);
                }
            } catch (Exception e) {
                System.out.println("Gagal load image: " + path);
            }
        }
    }

    private void showEnlargedImage(String imagePath, String hotelName) {
        Stage imageStage = new Stage();
        imageStage.setTitle("📸 " + hotelName + " - Foto Detail");
        imageStage.initModality(Modality.APPLICATION_MODAL);
        imageStage.initOwner(primaryStage);

        ImageView enlarged = new ImageView();
        enlarged.setFitWidth(600);
        enlarged.setFitHeight(450);
        enlarged.setPreserveRatio(true);

        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            if (!image.isError()) enlarged.setImage(image);
        } catch (Exception e) {
            System.out.println("Failed to load enlarged image: " + imagePath);
        }

        Button closeBtn = new Button("✖ Tutup");
        closeBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        closeBtn.setOnAction(e -> imageStage.close());

        VBox layout = new VBox(enlarged, closeBtn);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout);
        imageStage.setScene(scene);
        imageStage.setResizable(false);
        imageStage.showAndWait();
    }

    static class HotelInfo {
        private String name, address, price, rating;
        private List<String> imagePaths;
        private List<String> facilities;

        public HotelInfo(String name, String address, String price, String rating, List<String> imagePaths, List<String> facilities) {
            this.name = name;
            this.address = address;
            this.price = price;
            this.rating = rating;
            this.imagePaths = imagePaths;
            this.facilities = facilities;
        }

        public String getName() { return name; }
        public String getAddress() { return address; }
        public String getPrice() { return price; }
        public String getRating() { return rating; }
        public List<String> getImagePaths() { return imagePaths; }
        public List<String> getFacilities() { return facilities; }
    }
}


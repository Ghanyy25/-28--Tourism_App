package tourismapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate; 
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Planning {

    private App app;
    private Stage primaryStage;

    private Map<String, DestinationBudget> destinationBudgets;

    private int totalDays = 1;
    private int totalPeople = 1;
    private double totalBudget = 0.0;

    public Planning(App app, Stage primaryStage) {
        this.app = app;
        this.primaryStage = primaryStage;
        initializeBudgetData();
    }

    private void initializeBudgetData() {
        destinationBudgets = new HashMap<>();

        destinationBudgets.put("Pantai Kuta", new DestinationBudget(
                300000, 800000,
                100000, 300000,
                50000, 200000,
                150000, 500000
        ));

        destinationBudgets.put("Candi Borobudur", new DestinationBudget(
                200000, 600000,
                75000, 200000,
                40000, 150000,
                100000, 300000
        ));

        destinationBudgets.put("Gunung Bromo", new DestinationBudget(
                250000, 700000,
                80000, 250000,
                100000, 300000,
                150000, 400000
        ));

        destinationBudgets.put("Danau Toba", new DestinationBudget(
                200000, 500000,
                70000, 180000,
                60000, 200000,
                120000, 350000
        ));
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        String destination = app.getSelectedDestination();
        
        Label titleLabel = new Label("📋 PERENCANAAN PERJALANAN");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.DARKBLUE);

        Label destinationLabel = new Label("📍 Destinasi: " + destination);
        destinationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        destinationLabel.setTextFill(Color.DARKGREEN);

        VBox topSection = new VBox(10);
        topSection.setAlignment(Pos.TOP_LEFT);
        topSection.getChildren().addAll(titleLabel, destinationLabel);
        root.setTop(topSection);

        TextArea itineraryArea = new TextArea();
        itineraryArea.setText(generateItinerary(destination));
        itineraryArea.setWrapText(true);
        itineraryArea.setEditable(false);
        itineraryArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 14;");

        ScrollPane itineraryScroll = new ScrollPane(itineraryArea);
        itineraryScroll.setFitToWidth(true);
        itineraryScroll.setFitToHeight(true);

        Tab itineraryTab = new Tab("📋 Itinerary");
        itineraryTab.setContent(itineraryScroll);
        itineraryTab.setClosable(false);

        Tab budgetTab = new Tab("💰 Budget Planning");
        budgetTab.setContent(createBudgetPlanningPane(destination));
        budgetTab.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(budgetTab, itineraryTab);
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        Button backButton = new Button("⬅️ Kembali ke Menu");
        backButton.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold;");
        backButton.setOnAction(e -> app.showMainMenu());

        HBox buttonBox = new HBox(backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(15, 0, 0, 0));

        root.setCenter(tabPane);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Tourism App - Planning");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createBudgetPlanningPane(String destination) {
        VBox budgetPane = new VBox();
        budgetPane.setSpacing(15);
        budgetPane.setPadding(new Insets(20));

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(15);
        inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.CENTER_LEFT);

        Label startDateLabel = new Label("Tanggal Mulai:");
        DatePicker startDatePicker = new DatePicker(LocalDate.now().plusDays(7));

        Label endDateLabel = new Label("Tanggal Selesai:");
        DatePicker endDatePicker = new DatePicker(LocalDate.now().plusDays(10));

        Label peopleLabel = new Label("Jumlah Orang:");
        Spinner<Integer> peopleSpinner = new Spinner<>(1, 20, 2);

        Label budgetTypeLabel = new Label("Tipe Budget:");
        ComboBox<String> budgetCombo = new ComboBox<>();
        budgetCombo.getItems().addAll("Budget Travel", "Standard", "Luxury");
        budgetCombo.setValue("Standard");

        inputGrid.add(startDateLabel, 0, 0);
        inputGrid.add(startDatePicker, 1, 0);
        inputGrid.add(endDateLabel, 2, 0);
        inputGrid.add(endDatePicker, 3, 0);
        inputGrid.add(peopleLabel, 0, 1);
        inputGrid.add(peopleSpinner, 1, 1);
        inputGrid.add(budgetTypeLabel, 2, 1);
        inputGrid.add(budgetCombo, 3, 1);

        Button calculateBtn = new Button("🧮 Hitung Budget");
        calculateBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

        TextArea budgetBreakdown = new TextArea();
        budgetBreakdown.setEditable(false);
        budgetBreakdown.setPrefHeight(300);
        budgetBreakdown.setWrapText(true);
        budgetBreakdown.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12px;");

        calculateBtn.setOnAction(e -> {
            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();

            if (start != null && end != null && !end.isBefore(start)) {
                totalDays = (int) ChronoUnit.DAYS.between(start, end) + 1;
                totalPeople = peopleSpinner.getValue();
                String budgetType = budgetCombo.getValue();

                String breakdown = calculateBudgetBreakdown(destination, totalDays, totalPeople, budgetType);
                budgetBreakdown.setText(breakdown);
            } else {
                UIHelp.showAlert("Error", "Tanggal tidak valid! Pastikan tanggal selesai tidak lebih awal dari tanggal mulai.", Alert.AlertType.ERROR);
            }
        });

        budgetPane.getChildren().addAll(
                new Label("💰 BUDGET CALCULATOR"),
                new Separator(),
                inputGrid,
                calculateBtn,
                new Label("📊 Budget Breakdown:"),
                budgetBreakdown
        );

        return budgetPane;
    }

    private String calculateBudgetBreakdown(String destination, int days, int people, String budgetType) {
        DestinationBudget budget = destinationBudgets.get(destination);
        if (budget == null) return "Budget data tidak tersedia untuk destinasi ini.";

        double multiplier = 1.0;
        switch (budgetType) {
            case "Standard":
                multiplier = 1.5;
                break;
            case "Luxury":
                multiplier = 2.0;
                break;
            case "Budget Travel":
            default:
                multiplier = 1.0;
                break;
        }

        double accommodationCost = budget.accommodationMin * multiplier * days;
        double foodCost = budget.foodMin * multiplier * days * people;
        double transportCost = budget.transportMin * multiplier * people;
        double activitiesCost = budget.activitiesMin * multiplier * people;

        totalBudget = accommodationCost + foodCost + transportCost + activitiesCost;

        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════\n");
        sb.append("         BUDGET BREAKDOWN - ").append(destination.toUpperCase()).append("\n");
        sb.append("═══════════════════════════════════════════════\n\n");
        sb.append(String.format("📅 Durasi         : %d hari\n", days));
        sb.append(String.format("👥 Jumlah Orang   : %d orang\n", people));
        sb.append(String.format("💎 Tipe Budget    : %s\n\n", budgetType));
        sb.append("─────────── RINCIAN BIAYA ───────────\n\n");
        sb.append(String.format("🏨 Akomodasi      : Rp %,.0f\n", accommodationCost));
        sb.append(String.format("    (%,.0f/hari x %d hari)\n\n", budget.accommodationMin * multiplier, days));
        sb.append(String.format("🍽️ Makanan        : Rp %,.0f\n", foodCost));
        sb.append(String.format("    (%,.0f/hari/orang x %d hari x %d orang)\n\n", budget.foodMin * multiplier, days, people));
        sb.append(String.format("🚗 Transportasi   : Rp %,.0f\n", transportCost));
        sb.append(String.format("    (%,.0f/orang x %d orang)\n\n", budget.transportMin * multiplier, people));
        sb.append(String.format("🎯 Aktivitas      : Rp %,.0f\n", activitiesCost));
        sb.append(String.format("    (%,.0f/orang x %d orang)\n\n", budget.activitiesMin * multiplier, people));
        sb.append("═══════════════════════════════════════════════\n");
        sb.append(String.format("💰 TOTAL BUDGET   : Rp %,.0f\n", totalBudget));
        sb.append(String.format("👤 Per Orang      : Rp %,.0f\n", totalBudget / people));
        sb.append("═══════════════════════════════════════════════\n\n");
        sb.append("💡 Tips:\n");
        sb.append("• Budget ini adalah estimasi, harga bisa berubah sewaktu-waktu.\n");
        sb.append("• Siapkan dana darurat sekitar 10-20% dari total budget.\n");
        sb.append("• Cek promo hotel dan tiket pesawat untuk hemat biaya lebih lanjut.\n");

        return sb.toString();
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

    private static class DestinationBudget {
        double accommodationMin, accommodationMax;
        double foodMin, foodMax;
        double transportMin, transportMax;
        double activitiesMin, activitiesMax;

        public DestinationBudget(double accMin, double accMax, double foodMin, double foodMax,
                                 double transMin, double transMax, double actMin, double actMax) {
            this.accommodationMin = accMin;
            this.accommodationMax = accMax;
            this.foodMin = foodMin;
            this.foodMax = foodMax;
            this.transportMin = transMin;
            this.transportMax = transMax;
            this.activitiesMin = actMin;
            this.activitiesMax = actMax;
        }
    }
}
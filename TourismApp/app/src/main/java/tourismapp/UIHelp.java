package tourismapp;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UIHelp {
    
    public static Button createMenuButton(String text, String description) {
        Button button = new Button();
        
        Label mainLabel = new Label(text);
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        mainLabel.setTextFill(Color.WHITE);
        
        Label descLabel = new Label(description);
        descLabel.setFont(Font.font("Arial", 12));
        descLabel.setTextFill(Color.LIGHTGRAY);
        
        VBox buttonContent = new VBox();
        buttonContent.setAlignment(Pos.CENTER);
        buttonContent.setSpacing(5);
        buttonContent.getChildren().addAll(mainLabel, descLabel);
        
        button.setGraphic(buttonContent);
        
        button.setPrefSize(180, 120);
        button.setStyle(
            "-fx-background-color: linear-gradient(to bottom, #2196f3, #1976d2); " +
            "-fx-background-radius: 15; " +
            "-fx-border-radius: 15; " +
            "-fx-border-color: #1565c0; " +
            "-fx-border-width: 2; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 2, 2);"
        );
        
        button.setOnMouseEntered(e -> {
            button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #42a5f5, #2196f3); " +
                "-fx-background-radius: 15; " +
                "-fx-border-radius: 15; " +
                "-fx-border-color: #1565c0; " +
                "-fx-border-width: 2; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 8, 0, 3, 3); " +
                "-fx-scale-x: 1.05; " +
                "-fx-scale-y: 1.05;"
            );
        });
        
        button.setOnMouseExited(e -> {
            button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #2196f3, #1976d2); " +
                "-fx-background-radius: 15; " +
                "-fx-border-radius: 15; " +
                "-fx-border-color: #1565c0; " +
                "-fx-border-width: 2; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 2, 2); " +
                "-fx-scale-x: 1.0; " +
                "-fx-scale-y: 1.0;"
            );
        });
        
        return button;
    }
    
    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void showComingSoon(String featureName) {
        showAlert("Coming Soon", 
            "Fitur " + featureName + " akan segera hadir!\n" +
            "Saat ini hanya tersedia fitur Wisata untuk memilih destinasi.\n\n" +
            "Fitur lain sedang dalam tahap pengembangan.", 
            Alert.AlertType.INFORMATION);
    }
}
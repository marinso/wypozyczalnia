package pl.wypozyczalnia.samochodow.utils;

import javafx.scene.control.Alert;

public class Alerts {

    public static void warrningAlert(String title,String header) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(null);
        alert.showAndWait();
    }
}


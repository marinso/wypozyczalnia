package pl.wypozyczalnia.samochodow.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FxmlUtils {

    public static Pane fxmlLoader(String fxmlpath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlpath));
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}

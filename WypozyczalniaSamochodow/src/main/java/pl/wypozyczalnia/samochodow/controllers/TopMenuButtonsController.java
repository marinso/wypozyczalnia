package pl.wypozyczalnia.samochodow.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {

    @FXML
    private ToggleGroup buttonsGroup;

    private MainController mainController;

    public void openWypozyczeniaOnAction() { mainController.setCenter("/fxml/WypozyczeniaTableView.fxml");}

    public void openZwrotyOnAction() { mainController.setCenter("/fxml/Zwroty.fxml");}

    public void openKlienciOnAction() { mainController.setCenter("/fxml/DodajKlienta.fxml");}

    public void openPracownicyOnAction() {
        mainController.setCenter("/fxml/DodajPracownika.fxml");
    }

    public void openSamochodyOnAction() {
        mainController.setCenter("/fxml/DodajSamochody.fxml");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void wypozyczSamochodOnAction() { this.mainController.setCenter("/fxml/WypozyczSamochod.fxml");}
}

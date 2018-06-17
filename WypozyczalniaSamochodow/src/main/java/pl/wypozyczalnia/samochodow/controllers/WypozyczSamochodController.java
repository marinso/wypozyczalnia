package pl.wypozyczalnia.samochodow.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pl.wypozyczalnia.samochodow.modelFx.*;

import java.time.LocalDate;

public class WypozyczSamochodController {

    @FXML
    public Label idWyp;
    public Label dataWyp;
    public ComboBox<SamochodFx> comboSamochod;
    public ComboBox<KlientFx> comboKlient;
    public ComboBox<PracownicyFx> comboPracownik;
    public Button buttonZatwierdź;

    private WypozyczSamochodModel wypozyczSamochodModel;

    @FXML
    public void initialize(){
        wypozyczSamochodModel = new WypozyczSamochodModel();
        this.wypozyczSamochodModel.init();
        bindings();
    }


    private void bindings() {
        comboKlient.setItems(this.wypozyczSamochodModel.getKlienciFxObservableList());
        comboPracownik.setItems(this.wypozyczSamochodModel.getPracownicyFxObservableList());
        comboSamochod.setItems(this.wypozyczSamochodModel.getSamochodyFxObservableList());
        this.wypozyczSamochodModel.getWypozyczeniaObjectProperty().samochodyFxProperty().bind(comboSamochod.valueProperty());
        this.wypozyczSamochodModel.getWypozyczeniaObjectProperty().pracownicyFxProperty().bind(comboPracownik.valueProperty());
        this.wypozyczSamochodModel.getWypozyczeniaObjectProperty().klienciFxProperty().bind(comboKlient.valueProperty());
        dataWyp.textProperty().set(String.valueOf(LocalDate.now()));
        buttonZatwierdź.disableProperty().bind(comboPracownik.valueProperty().isNull()
                .or(comboKlient.valueProperty().isNull()
                        .or(comboSamochod.valueProperty().isNull())));
    }

    public void zatwierdźKlienta() {
        this.wypozyczSamochodModel.saveInDataBase();
        comboSamochod.setValue(null);
        comboPracownik.setValue(null);
        comboKlient.setValue(null);
    }
}

package pl.wypozyczalnia.samochodow.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.wypozyczalnia.samochodow.modelFx.*;

import java.time.LocalDate;

public class WypozyczeniaTableViewController {

    public TableView                                     WypozyczeniaTableView;
    public TableColumn<WypozyczSamochodFX, Number>       ID_COLUMN;
    public TableColumn<WypozyczSamochodFX, KlientFx>     ID_KLIENT_COLUMN;
    public TableColumn<WypozyczSamochodFX, SamochodFx>   SAMOCHOD_COLUMN;
    public TableColumn<WypozyczSamochodFX, PracownicyFx> PRACOWNIK_COLUMN;
    public TableColumn<WypozyczSamochodFX, LocalDate>    DATA_COLUMN;
    public TableColumn<WypozyczSamochodFX, Boolean>      CZY_ZWRCONY_COLUMN;
    public ComboBox                                      comboKlient;


    private WypozyczSamochodViewModel wypozyczSamochodViewModel;

    public void initialize() {
        wypozyczSamochodViewModel = new WypozyczSamochodViewModel();
        wypozyczSamochodViewModel.init();

        this.WypozyczeniaTableView.setItems(this.wypozyczSamochodViewModel.getWypozyczSamochodFXObservableList());
        this.ID_COLUMN.setCellValueFactory(celldata -> celldata.getValue().idProperty());
        this.ID_KLIENT_COLUMN.setCellValueFactory(celldata -> celldata.getValue().klienciFxProperty());
        this.SAMOCHOD_COLUMN.setCellValueFactory(celldata -> celldata.getValue().samochodyFxProperty());
        this.PRACOWNIK_COLUMN.setCellValueFactory(celldata -> celldata.getValue().pracownicyFxProperty());
        this.DATA_COLUMN.setCellValueFactory(celldata -> celldata.getValue().data_wypProperty());
        this.CZY_ZWRCONY_COLUMN.setCellValueFactory(celldata -> celldata.getValue().czy_zwrconyProperty());


        comboKlient.setItems(this.wypozyczSamochodViewModel.getKlientFxObservableList());
        this.wypozyczSamochodViewModel.klientFxObjectPropertyProperty().bind(this.comboKlient.valueProperty());
    }

    public void comboKlientOnAction() {
        this.wypozyczSamochodViewModel.filterWypozyczeniaList();
    }

    public void clearKlient() {
        comboKlient.setValue(null);
    }

    public void pokazNiezwrocone() {
        this.wypozyczSamochodViewModel.Czy_zwrocony();
    }

    public void pokazWszystkie() {
        initialize();
    }
}

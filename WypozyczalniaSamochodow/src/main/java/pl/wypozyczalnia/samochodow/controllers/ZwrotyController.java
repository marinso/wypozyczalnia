package pl.wypozyczalnia.samochodow.controllers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.wypozyczalnia.samochodow.modelFx.*;

import java.time.LocalDate;
import java.time.Period;

public class ZwrotyController {

    public TableView                           zwrotyView;
    public TableColumn<ZwrotyFx, Number>          idColumn;
    public TableColumn<ZwrotyFx, PracownicyFx> praconwikColumn;
    public TableColumn<ZwrotyFx, SamochodFx>   samochodColumn;
    public TableColumn<ZwrotyFx, LocalDate>          dataWypColumn;
    public TableColumn<ZwrotyFx, LocalDate>          dataZwrotColumn;
    public TableColumn<ZwrotyFx, KlientFx>          klientColumn;
    public TableColumn<ZwrotyFx, String>          kosztColumn;

    private ZwrotyModel zwrotyModel;
    public ComboBox<WypozyczSamochodFX>     comboWypozyczenie;
    public Label                             kosztZwrotu;
    String koszt2;

    public void initialize() {
        zwrotyModel = new ZwrotyModel();
        zwrotyModel.init();
        initBinds();
        bindView();

    }

    private void bindView() {
        this.zwrotyView.setItems(this.zwrotyModel.getZwrotyFxObservableList());

        this.idColumn.setCellValueFactory(celldata-> celldata.getValue().idProperty());
        this.praconwikColumn.setCellValueFactory(celldata-> celldata.getValue().getWypozyczeniaFx().pracownicyFxProperty());
        this.samochodColumn.setCellValueFactory(celldata-> celldata.getValue().getWypozyczeniaFx().samochodyFxProperty());
        this.klientColumn.setCellValueFactory(celldata-> celldata.getValue().getWypozyczeniaFx().klienciFxProperty());
        this.dataWypColumn.setCellValueFactory(celldata->celldata.getValue().getWypozyczeniaFx().data_wypProperty());
        this.dataZwrotColumn.setCellValueFactory(celldata->celldata.getValue().dateProperty());
        this.kosztColumn.setCellValueFactory(celldata->celldata.getValue().koszt_wypProperty());
    }

    private void initBinds() {
        comboWypozyczenie.setItems(zwrotyModel.getWypozyczSamochodFXObservableList());
        this.zwrotyModel.getZwrotyFxObjectProperty().dateProperty().setValue(LocalDate.now());
        this.zwrotyModel.getZwrotyFxObjectProperty().wypozyczeniaFxProperty().bind(comboWypozyczenie.valueProperty());
    }

    public void selectWyp() {
        String cena_podstawowa = String.valueOf((comboWypozyczenie.getSelectionModel().getSelectedItem().getSamochodyFx().getCena_za_dzien()));

        LocalDate dzien_wyp = comboWypozyczenie.getSelectionModel().getSelectedItem().getData_wyp();
        LocalDate now = LocalDate.now();

        Period dni =  Period.between(dzien_wyp,now);
        int ile_dni = dni.getDays();
        System.out.println(ile_dni);

        double cena = Double.parseDouble(cena_podstawowa);
        double koszt = ile_dni * cena;
        koszt2 = String.valueOf(koszt);

        kosztZwrotu.setText(String.valueOf(koszt) + " zł");

        System.out.println(this.zwrotyModel.getZwrotyFxObjectProperty().getWypozyczeniaFx().getId());
        this.zwrotyModel.getZwrotyFxObjectProperty().setKoszt_wyp(koszt2);
    }

    public void zatwierdzZwrot() {
        try {
            zwrotyModel.saveToDataBase();
            kosztZwrotu.setText(null);
            comboWypozyczenie.valueProperty().isNull();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

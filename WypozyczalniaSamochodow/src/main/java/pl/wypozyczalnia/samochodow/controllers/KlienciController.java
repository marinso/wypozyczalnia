package pl.wypozyczalnia.samochodow.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.wypozyczalnia.samochodow.modelFx.KlientFx;
import pl.wypozyczalnia.samochodow.modelFx.KlientModel;

public class KlienciController {


    public ComboBox klienciCombobox;
    public Button usunButton;
    public TextField imieTextField;
    public TextField nazwiskoTextField;
    public TextField nrTelTextField;
    public Button dodajKlienta;
    public TableColumn<KlientFx, Number> IDTable;
    public TableColumn<KlientFx, String> imieTableColumn;
    public TableColumn<KlientFx, String> nazwiskoTableColumn;
    public TableColumn<KlientFx, String> nrTelTableColumn;
    public TableView tableViewKlienci;

     KlientModel klientModel;

    @FXML
    public void initialize() {
        this.klientModel = new KlientModel();
        this.klientModel.objectPropertyKlienciProperty().get().nameProperty().bind(imieTextField.textProperty());
        this.klientModel.objectPropertyKlienciProperty().get().surnameProperty().bind(nazwiskoTextField.textProperty());
        this.klientModel.objectPropertyKlienciProperty().get().nrtelProperty().bind(nrTelTextField.textProperty());

        klientModel.init();
        klienciCombobox.setItems(this.klientModel.getObservableListKlienci());

        this.tableViewKlienci.setItems(this.klientModel.getObservableListKlienci());
        this.IDTable.setCellValueFactory(celldata->celldata.getValue().idProperty());
        this.imieTableColumn.setCellValueFactory(celldata->celldata.getValue().nameProperty());
        this.nazwiskoTableColumn.setCellValueFactory(celldata->celldata.getValue().surnameProperty());
        this.nrTelTableColumn.setCellValueFactory(celldata->celldata.getValue().nrtelProperty());

        this.tableViewKlienci.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            this.klientModel.setObjectPropertyEditKlienci((KlientFx) newValue);
        }));

        this.imieTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.nazwiskoTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.nrTelTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        bindings();
    }

    public void bindings(){
        this.dodajKlienta.disableProperty().bind(imieTextField.textProperty().isEmpty().or(nazwiskoTextField.textProperty().isEmpty().or(nrTelTextField.textProperty().isEmpty())));
        this.usunButton.disableProperty().bind(this.klientModel.objectPropertyKlienciProperty().isNull());
    }

    public void boxOnAction() {
        this.klientModel.setObjectPropertyKlienci((KlientFx) klienciCombobox.getSelectionModel().getSelectedItem());
    }

    public void usunOnAction() {
        this.klientModel.deleteById();
    }

    public void dodajOnAction() {
        initialize();
        klientModel.saveKlienciInDataBase();
        imieTextField.clear();
        nazwiskoTextField.clear();
        nrTelTextField.clear();
    }

    public void imieOnEdit(TableColumn.CellEditEvent<KlientFx,String> klientFxStringCellEditEvent) {
        this.klientModel.getObjectPropertyEditKlienci().setName(klientFxStringCellEditEvent.getNewValue());
        this.klientModel.saveKlienciEditInDataBase();
    }

    public void nazwiskoOnEdit(TableColumn.CellEditEvent<KlientFx,String> klientFxStringCellEditEvent) {
        this.klientModel.getObjectPropertyEditKlienci().setSurname(klientFxStringCellEditEvent.getNewValue());
        this.klientModel.saveKlienciEditInDataBase();
    }

    public void telOnEdit(TableColumn.CellEditEvent<KlientFx,String> klientFxStringCellEditEvent) {
        this.klientModel.getObjectPropertyEditKlienci().setNrtel(klientFxStringCellEditEvent.getNewValue());
        this.klientModel.saveKlienciEditInDataBase();
    }
}

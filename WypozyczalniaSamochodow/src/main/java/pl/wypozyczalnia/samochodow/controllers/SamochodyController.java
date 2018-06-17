package pl.wypozyczalnia.samochodow.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BooleanStringConverter;
import pl.wypozyczalnia.samochodow.modelFx.SamochodFx;
import pl.wypozyczalnia.samochodow.modelFx.SamochodModel;

public class SamochodyController {

    @FXML
    public ComboBox<SamochodFx> textFieldcomboBox;
    @FXML
    public TextField modelTextField;
    @FXML
    public TextField cenaTextField;
    @FXML
    public Button usunButton;
    @FXML
    public Button dodajButton;

    @FXML
    private TableView tableViewSamochody;
    @FXML
    private TableColumn<SamochodFx, String> modelTableColumn;
    @FXML
    private TableColumn<SamochodFx, String> cenaTableColumn;
    @FXML
    private TableColumn<SamochodFx, Boolean> dostepnoscTableColumn;
    @FXML
    private TableColumn<SamochodFx, Number> IDTable;

    SamochodModel samochodModel;


    @FXML
    public void initialize(){
        this.samochodModel = new SamochodModel();

        this.samochodModel.init();
        textFieldcomboBox.setItems(this.samochodModel.getObservableListSamochody());

        this.tableViewSamochody.setItems(this.samochodModel.getObservableListSamochody());
        this.modelTableColumn.setCellValueFactory(celldata-> celldata.getValue().modelProperty());
        this.cenaTableColumn.setCellValueFactory(celldata-> celldata.getValue().cena_za_dzienProperty());
        this.dostepnoscTableColumn.setCellValueFactory(celldata-> celldata.getValue().czy_dostepnyProperty());
        this.IDTable.setCellValueFactory(celldata-> celldata.getValue().idProperty());

        this.tableViewSamochody.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            this.samochodModel.setObjectPropertyEditSamochody((SamochodFx) newValue);
        }));

        this.modelTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.cenaTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.dostepnoscTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));

        initBindings();
    }

    public void usunOnAction(){
        this.samochodModel.deleteById();
    }

    public void dodajOnAction(){
        this.samochodModel.objectPropertySamochodyProperty().get().modelProperty().bind(modelTextField.textProperty());
        this.samochodModel.objectPropertySamochodyProperty().get().cena_za_dzienProperty().bind(cenaTextField.textProperty());

        samochodModel.saveSamochodyInDataBase();
        modelTextField.clear();
        cenaTextField.clear();
    }

    public void boxOnAction(){
        this.samochodModel.setObjectPropertySamochody(textFieldcomboBox.getSelectionModel().getSelectedItem());
    }

    private void initBindings(){
        this.usunButton.disableProperty().bind(this.samochodModel.objectPropertySamochodyProperty().isNull());
        this.dodajButton.disableProperty().bind(cenaTextField.textProperty().isEmpty().or(modelTextField.textProperty().isEmpty()));
    }


    public void modelOnEdit(TableColumn.CellEditEvent<SamochodFx,String> samochodFxStringCellEditEvent) {
        this.samochodModel.getObjectPropertyEditSamochody().setModel(samochodFxStringCellEditEvent.getNewValue());
        this.samochodModel.saveSamochodyEditInDataBase();
    }

    public void cenaOnEdit(TableColumn.CellEditEvent<SamochodFx,String> samochodFxNumberCellEditEvent) {
        this.samochodModel.getObjectPropertyEditSamochody().setCena_za_dzien(String.valueOf(samochodFxNumberCellEditEvent.getNewValue()));
        this.samochodModel.saveSamochodyEditInDataBase();
    }

    public void DostepnoscOnEdit(TableColumn.CellEditEvent<SamochodFx,Boolean> samochodFxBooleanCellEditEvent) {
        this.samochodModel.getObjectPropertyEditSamochody().setCzy_dostepny(samochodFxBooleanCellEditEvent.getNewValue());
        this.samochodModel.saveSamochodyEditInDataBase();
    }
}

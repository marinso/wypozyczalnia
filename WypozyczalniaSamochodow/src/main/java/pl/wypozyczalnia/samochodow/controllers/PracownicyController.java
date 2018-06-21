package pl.wypozyczalnia.samochodow.controllers;


import com.j256.ormlite.stmt.query.In;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import pl.wypozyczalnia.samochodow.database.models.Pracownicy;
import pl.wypozyczalnia.samochodow.modelFx.PracownicyFx;
import pl.wypozyczalnia.samochodow.modelFx.PracownicyModel;
import pl.wypozyczalnia.samochodow.utils.Alerts;
import pl.wypozyczalnia.samochodow.utils.Check;


public class PracownicyController {

    @FXML
    public ComboBox<PracownicyFx> usunPracownikaBox;
    @FXML
    public Button usunPracownikaButton;
    @FXML
    public TextField textFieldName;
    @FXML
    public TextField textFieldSurname;
    @FXML
    public TextField textFieldSalary;
    @FXML
    public TextField texFieldNumber;
    @FXML
    public Button addPracownik;

    @FXML
    private TableView pracownicyTableView;
    @FXML
    private TableColumn<PracownicyFx, String> imieTableColumn;
    @FXML
    private TableColumn<PracownicyFx, String> nazwiskoTableColumn;
    @FXML
    private TableColumn<PracownicyFx, String> pensjaTableColumn;
    @FXML
    private TableColumn<PracownicyFx, String> nrTelTableColumn;
    @FXML
    private TableColumn<PracownicyFx, Number> IDCOLIMN;

    private PracownicyModel pracownicyModel;

    @FXML
    public void initialize(){
        this.pracownicyModel = new PracownicyModel();
        this.pracownicyModel.init();
        this.usunPracownikaBox.setItems(this.pracownicyModel.getObservableListPracownicy());

        this.pracownicyTableView.setItems(this.pracownicyModel.getObservableListPracownicy());
        this.imieTableColumn.setCellValueFactory(celldata-> celldata.getValue().nameProperty());
        this.nazwiskoTableColumn.setCellValueFactory(celldata-> celldata.getValue().surnameProperty());
        this.pensjaTableColumn.setCellValueFactory(celldata-> celldata.getValue().pensjaProperty());
        this.nrTelTableColumn.setCellValueFactory(celldata-> celldata.getValue().numerTelProperty());
        this.IDCOLIMN.setCellValueFactory(celldata->celldata.getValue().idProperty());

        this.imieTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.nazwiskoTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.pensjaTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.nrTelTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

       this.pracownicyTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           this.pracownicyModel.setObjectPropertyPracownicyEdit((PracownicyFx) newValue);
       });

        initBindings();
    }

    private void initBindings() {
        this.addPracownik.disableProperty().bind(textFieldName.textProperty().isEmpty().or(textFieldSurname.textProperty().isEmpty()).or(texFieldNumber.textProperty().isEmpty().or(textFieldSalary.textProperty().isEmpty())));
        this.usunPracownikaButton.disableProperty().bind(this.pracownicyModel.objectPropertyPracownicyProperty().isNull());
    }

    public void usunPracownikaOnAction() {
        this.pracownicyModel.deleteById();

    }

    public void dodajPracownikaOnAction() {
        if((Check.sameLitery(textFieldName.getText())
                && Check.sameLitery(textFieldSurname.getText())
                && Check.sameCyfry(textFieldSalary.getText())
                && Check.numerTel(texFieldNumber.getText())
        ) == true) {
            System.out.println(Check.sameLitery(textFieldName.getText()));
            this.pracownicyModel.objectPropertyPracownicyProperty().get().nameProperty().bind(textFieldName.textProperty());
            this.pracownicyModel.objectPropertyPracownicyProperty().get().surnameProperty().bind(textFieldSurname.textProperty());
            this.pracownicyModel.objectPropertyPracownicyProperty().get().pensjaProperty().bind(textFieldSalary.textProperty());
            this.pracownicyModel.objectPropertyPracownicyProperty().get().numerTelProperty().bind(texFieldNumber.textProperty());

            pracownicyModel.savePracownicyInDataBase();
            clear();
        } else {
            Alerts.warrningAlert("BŁĄD", "Wprowadzono niewłaściwe dane");
            clear();
        }


    }

    private void clear() {
        texFieldNumber.clear();
        textFieldName.clear();
        textFieldSalary.clear();
        textFieldSurname.clear();
    }

    public void boxOnAction() {
        this.pracownicyModel.setObjectPropertyPracownicy(this.usunPracownikaBox.getSelectionModel().getSelectedItem());
    }

    public void editImie(TableColumn.CellEditEvent<PracownicyFx,String> pracownicyFxStringCellEditEvent) {
        this.pracownicyModel.getObjectPropertyPracownicyEdit().setName(pracownicyFxStringCellEditEvent.getNewValue());
        this.pracownicyModel.savePracownicyEditInDataBase();
    }

    public void editNaziwsko(TableColumn.CellEditEvent<PracownicyFx,String> pracownicyFxStringCellEditEvent) {
        this.pracownicyModel.getObjectPropertyPracownicyEdit().setSurname(pracownicyFxStringCellEditEvent.getNewValue());
        this.pracownicyModel.savePracownicyEditInDataBase();
    }

    public void editPensja(TableColumn.CellEditEvent<PracownicyFx,String> pracownicyFxNumberCellEditEvent) {
         this.pracownicyModel.getObjectPropertyPracownicyEdit().setPensja(pracownicyFxNumberCellEditEvent.getNewValue());
         this.pracownicyModel.savePracownicyEditInDataBase();
    }

    public void editNrTele(TableColumn.CellEditEvent<PracownicyFx,String> pracownicyFxNumberCellEditEvent) {
        this.pracownicyModel.getObjectPropertyPracownicyEdit().setNumerTel(pracownicyFxNumberCellEditEvent.getNewValue());
        this.pracownicyModel.savePracownicyEditInDataBase();
    }

}

package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.*;

public class SamochodFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty model = new SimpleStringProperty();
    private StringProperty cena_za_dzien = new SimpleStringProperty();
    private BooleanProperty czy_dostepny = new SimpleBooleanProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public double getCena_za_dzien() {
        return Double.parseDouble(cena_za_dzien.get());
    }

    public StringProperty cena_za_dzienProperty() {
        return cena_za_dzien;
    }

    public void setCena_za_dzien(String cena_za_dzien) {
        this.cena_za_dzien.set(cena_za_dzien);
    }

    public boolean isCzy_dostepny() {
        return czy_dostepny.get();
    }

    public BooleanProperty czy_dostepnyProperty() {
        return czy_dostepny;
    }

    public void setCzy_dostepny(boolean czy_dostepny) {
        this.czy_dostepny.set(czy_dostepny);
    }

    @Override
    public String toString() {
        return model.getValue() + " ID: " + id.getValue() ;
    }
}

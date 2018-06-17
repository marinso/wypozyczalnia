package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.*;

public class PracownicyFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty numerTel = new SimpleStringProperty();
    private StringProperty pensja = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getNumerTel() {
        return numerTel.get();
    }

    public StringProperty numerTelProperty() {
        return numerTel;
    }



    public void setNumerTel(String numerTel) {
        this.numerTel.set(numerTel);
    }

    public String getPensja() {
        return pensja.get();
    }

    public StringProperty pensjaProperty() {
        return pensja;
    }

    public void setPensja(String pensja) {
        this.pensja.set(pensja);
    }

    @Override
    public String toString() {
        return name.getValue() + " " + surname.getValue();
    }
}

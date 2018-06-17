package pl.wypozyczalnia.samochodow.database.models;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRACOWNICY")

public class Pracownicy implements BaseModel{
    public Pracownicy(){}

    @DatabaseField(generatedId = true)
    private int id_pracownika;

    @DatabaseField(columnName = "IMIE_PRACOWNIKA", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "NAZWISKO_PRACOWNIKA", canBeNull = false)
    private String surname;

    @DatabaseField(columnName = "NUMERTELE_PRACOWNIKA")
    private String numerTel;

    @DatabaseField(columnName = "PENSJA", canBeNull = false)
    private String pensja;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Wypozyczenia> wypozyczenias;

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getPensja() {
        return pensja;
    }

    public void setPensja(String pensja) {
        this.pensja = pensja;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumerTel() {
        return numerTel;
    }

    public void setNumerTel(String numerTel) {
        this.numerTel = numerTel;
    }
}

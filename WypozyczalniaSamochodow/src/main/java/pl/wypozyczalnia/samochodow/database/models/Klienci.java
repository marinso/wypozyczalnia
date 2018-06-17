package pl.wypozyczalnia.samochodow.database.models;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "KLIENCI")

public class Klienci implements BaseModel{
    public Klienci(){}

    @DatabaseField(generatedId = true)
    private int id_klienta;

    @DatabaseField(columnName = "IMIE_KLIENTA", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "NAZWISKO_KLIENTA", canBeNull = false)
    private String surname;

    @DatabaseField(columnName = "NUMERTELE_KLIENTA")
    private String numerTel;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Wypozyczenia> wypozyczenias;

    public int getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
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

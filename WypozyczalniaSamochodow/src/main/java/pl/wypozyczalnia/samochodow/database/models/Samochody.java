package pl.wypozyczalnia.samochodow.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "SAMOCHODY")
public class Samochody implements BaseModel {

    public Samochody() { }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "MODEL", canBeNull = false)
    private String model;

    @DatabaseField(columnName = "CENA_ZA_DZIEN", canBeNull = false)
    private String cena_za_dzien;

    @DatabaseField(columnName = "CZY_DOSTEPNY",canBeNull = false)
    private boolean czy_dostepny;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Wypozyczenia> wypozyczeniass;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCena_za_dzien() {
        return cena_za_dzien;
    }

    public void setCena_za_dzien(String cena_za_dzien) {
        this.cena_za_dzien = cena_za_dzien;
    }

    public boolean isCzy_dostepny() {
        return czy_dostepny;
    }

    public void setCzy_dostepny(boolean czy_dostepny) {
        this.czy_dostepny = czy_dostepny;
    }


}

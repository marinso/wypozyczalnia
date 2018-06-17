package pl.wypozyczalnia.samochodow.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "WYPOZYCZENIA")
public class Wypozyczenia implements BaseModel {

    public Wypozyczenia(){}

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "DATA_WYPOZYCZENIA", canBeNull = false, dataType = DataType.DATE_STRING, format = "yyyy-MM-DD")
    private Date data_wypozyczenia;

    @DatabaseField(columnName = "KLIENT_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Klienci klienci;

    @DatabaseField(columnName = "PRACOWNICY_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Pracownicy pracownicy;

    @DatabaseField(columnName = "SAMOCHODY_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Samochody samochody;

    @DatabaseField(columnName = "CZY_ZWROCONY")
    private Boolean czy_zwrocony;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Zwroty> zwroties;

    public Boolean getCzy_zwrocony() {
        return czy_zwrocony;
    }

    public void setCzy_zwrocony(Boolean czy_zwrocony) {
        this.czy_zwrocony = czy_zwrocony;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public void setData_wypozyczenia(Date data_wypozyczenia) {
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public Klienci getKlienci() {
        return klienci;
    }

    public void setKlienci(Klienci klienci) {
        this.klienci = klienci;
    }

    public Pracownicy getPracownicy() {
        return pracownicy;
    }

    public void setPracownicy(Pracownicy pracownicy) {
        this.pracownicy = pracownicy;
    }

    public Samochody getSamochody() {
        return samochody;
    }

    public void setSamochody(Samochody samochody) {
        this.samochody = samochody;
    }

}

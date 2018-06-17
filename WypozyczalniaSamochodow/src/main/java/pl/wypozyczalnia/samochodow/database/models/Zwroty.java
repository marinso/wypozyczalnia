package pl.wypozyczalnia.samochodow.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "ZWROTY")
public class Zwroty implements BaseModel {
    public Zwroty(){}

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "DATA_ZWROTU", canBeNull = false)
    private Date data_zwrotu;

    @DatabaseField(columnName = "KOSZT_WYPOZYCZENIA")
    private Double koszt_wypozyczenia;

    @DatabaseField(columnName = "WYPOZYCZENIA_ID",foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Wypozyczenia wypozyczenia;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_zwrotu() {
        return data_zwrotu;
    }

    public void setData_zwrotu(Date data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    public Double getKoszt_wypozyczenia() {
        return koszt_wypozyczenia;
    }

    public void setKoszt_wypozyczenia(Double koszt_wypozyczenia) {
        this.koszt_wypozyczenia = koszt_wypozyczenia;
    }

    public Wypozyczenia getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(Wypozyczenia wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }
}

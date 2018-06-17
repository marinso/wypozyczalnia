package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class ZwrotyFx {

    private IntegerProperty                    id             = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate>          date           = new SimpleObjectProperty<>();
    private StringProperty                     koszt_wyp      = new SimpleStringProperty();
    private ObjectProperty<WypozyczSamochodFX> wypozyczeniaFx = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getKoszt_wyp() {
        return koszt_wyp.get();
    }

    public StringProperty koszt_wypProperty() {
        return koszt_wyp;
    }

    public void setKoszt_wyp(String koszt_wyp) {
        this.koszt_wyp.set(koszt_wyp);
    }

    public WypozyczSamochodFX getWypozyczeniaFx() {
        return wypozyczeniaFx.get();
    }

    public ObjectProperty<WypozyczSamochodFX> wypozyczeniaFxProperty() {
        return wypozyczeniaFx;
    }

    public void setWypozyczeniaFx(WypozyczSamochodFX wypozyczeniaFx) {
        this.wypozyczeniaFx.set(wypozyczeniaFx);
    }
}

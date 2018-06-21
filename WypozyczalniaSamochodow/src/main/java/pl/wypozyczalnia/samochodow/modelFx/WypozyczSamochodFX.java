package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class WypozyczSamochodFX {

    private IntegerProperty              id           = new SimpleIntegerProperty();
    private ObjectProperty<KlientFx>     klienciFx    = new SimpleObjectProperty<>();
    private ObjectProperty<SamochodFx>   samochodyFx  = new SimpleObjectProperty<>();
    private ObjectProperty<PracownicyFx> pracownicyFx = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate>    data_wyp     = new SimpleObjectProperty<>(LocalDate.now());
    private BooleanProperty czy_zwrcony = new SimpleBooleanProperty();

    public boolean isCzy_zwrcony() {
        return czy_zwrcony.get();
    }

    public BooleanProperty czy_zwrconyProperty() {
        return czy_zwrcony;
    }

    public void setCzy_zwrcony(boolean czy_zwrcony) {
        this.czy_zwrcony.set(czy_zwrcony);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public KlientFx getKlienciFx() {
        return klienciFx.get();
    }

    public ObjectProperty<KlientFx> klienciFxProperty() {
        return klienciFx;
    }

    public void setKlienciFx(KlientFx klienciFx) {
        this.klienciFx.set(klienciFx);
    }

    public SamochodFx getSamochodyFx() {
        return samochodyFx.get();
    }

    public ObjectProperty<SamochodFx> samochodyFxProperty() {
        return samochodyFx;
    }

    public void setSamochodyFx(SamochodFx samochodyFx) {
        this.samochodyFx.set(samochodyFx);
    }

    public PracownicyFx getPracownicyFx() {
        return pracownicyFx.get();
    }

    public ObjectProperty<PracownicyFx> pracownicyFxProperty() {
        return pracownicyFx;
    }

    public void setPracownicyFx(PracownicyFx pracownicyFx) {
        this.pracownicyFx.set(pracownicyFx);
    }

    public LocalDate getData_wyp() {
        return data_wyp.get();
    }

    public ObjectProperty<LocalDate> data_wypProperty() {
        return data_wyp;
    }

    public void setData_wyp(LocalDate data_wyp) {
        this.data_wyp.set(data_wyp);
    }

    @Override
    public String toString() {
        return klienciFx.get().getSurname() + " " + klienciFx.get().getName() + " " + samochodyFxProperty().get().getModel();
    }
}

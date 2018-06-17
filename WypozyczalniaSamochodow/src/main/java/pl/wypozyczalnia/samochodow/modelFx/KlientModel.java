package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.wypozyczalnia.samochodow.database.dao.KlienciDao;
import pl.wypozyczalnia.samochodow.database.dao.SamochodyDao;
import pl.wypozyczalnia.samochodow.database.dbutils.DbManager;
import pl.wypozyczalnia.samochodow.database.models.Klienci;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterKlient;

import java.util.List;

public class KlientModel {

    private ObservableList<KlientFx> observableListKlienci = FXCollections.observableArrayList();
    private ObjectProperty<KlientFx> objectPropertyKlienci = new SimpleObjectProperty<>(new KlientFx());
    private ObjectProperty<KlientFx> objectPropertyEditKlienci = new SimpleObjectProperty<>();

    public void init() {
        observableListKlienci.clear();
        KlienciDao klienciDao = new KlienciDao();
        List<Klienci> klienci = klienciDao.queryForAll(Klienci.class);
        klienci.forEach(e->{
            KlientFx KlientFx = new KlientFx();
            KlientFx.setId(e.getId_klienta());
            KlientFx.setName(e.getName());
            KlientFx.setSurname(e.getSurname());
            KlientFx.setNrtel(e.getNumerTel());

            observableListKlienci.add(KlientFx);
        });
        DbManager.closeConnectionSource();
    }

    public void saveKlienciInDataBase() {
        KlienciDao klienciDao = new KlienciDao();
        Klienci klienci = ConverterKlient.klienciFxToKlienci(this.getObjectPropertyKlienci());
        klienciDao.creatOrUpdate(klienci);
        init();
        DbManager.closeConnectionSource();
    }

    public void saveKlienciEditInDataBase() {
        KlienciDao klienciDao = new KlienciDao();
        Klienci klienci = ConverterKlient.klienciFxToKlienci(this.getObjectPropertyEditKlienci());
        klienciDao.creatOrUpdate(klienci);
        init();
        DbManager.closeConnectionSource();
    }

    public void deleteById(){
        SamochodyDao samochodyDao = new SamochodyDao();
        samochodyDao.deleteById(Klienci.class,objectPropertyKlienci.getValue().getId());
        init();
        DbManager.closeConnectionSource();
    }

    public ObservableList<KlientFx> getObservableListKlienci() {
        return observableListKlienci;
    }

    public void setObservableListKlienci(ObservableList<KlientFx> observableListKlienci) {
        this.observableListKlienci = observableListKlienci;
    }

    public KlientFx getObjectPropertyKlienci() {
        return objectPropertyKlienci.get();
    }

    public ObjectProperty<KlientFx> objectPropertyKlienciProperty() {
        return objectPropertyKlienci;
    }

    public void setObjectPropertyKlienci(KlientFx objectPropertyKlienci) {
        this.objectPropertyKlienci.set(objectPropertyKlienci);
    }

    public KlientFx getObjectPropertyEditKlienci() {
        return objectPropertyEditKlienci.get();
    }

    public ObjectProperty<KlientFx> objectPropertyEditKlienciProperty() {
        return objectPropertyEditKlienci;
    }

    public void setObjectPropertyEditKlienci(KlientFx objectPropertyEditKlienci) {
        this.objectPropertyEditKlienci.set(objectPropertyEditKlienci);
    }
}

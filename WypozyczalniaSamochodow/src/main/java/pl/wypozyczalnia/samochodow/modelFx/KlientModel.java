package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.wypozyczalnia.samochodow.database.dao.KlienciDao;
import pl.wypozyczalnia.samochodow.database.dao.SamochodyDao;
import pl.wypozyczalnia.samochodow.database.dao.WypozyczeniaDao;
import pl.wypozyczalnia.samochodow.database.dbutils.DbManager;
import pl.wypozyczalnia.samochodow.database.models.Klienci;
import pl.wypozyczalnia.samochodow.database.models.Wypozyczenia;
import pl.wypozyczalnia.samochodow.utils.Alerts;
import pl.wypozyczalnia.samochodow.utils.Utils;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterKlient;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterWypozyczenia;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class KlientModel {

    private ObservableList<KlientFx> observableListKlienci     = FXCollections.observableArrayList();
    private ObjectProperty<KlientFx> objectPropertyKlienci     = new SimpleObjectProperty<>(new KlientFx());
    private ObjectProperty<KlientFx> objectPropertyEditKlienci = new SimpleObjectProperty<>();

    private List<WypozyczSamochodFX>           wypozyczeniaFxList         = new ArrayList<>();

    public void init() {
        observableListKlienci.clear();
        KlienciDao klienciDao = new KlienciDao();
        List<Klienci> klienci = klienciDao.queryForAll(Klienci.class);
        klienci.forEach(e -> {
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

    public void deleteById() {
        WypozyczeniaDao wypozyczeniaDao = new WypozyczeniaDao();
        wypozyczeniaFxList.clear();
        List<Wypozyczenia> wypozyczenia = wypozyczeniaDao.queryForAll(Wypozyczenia.class);
        wypozyczenia.forEach(e -> {
            wypozyczeniaFxList.add(ConverterWypozyczenia.convertToWypozyczeniaFx(e));
        });

        if(filterPredicate(predicateKlient())) {
            KlienciDao klienciDao = new KlienciDao();
            klienciDao.deleteById(Klienci.class, objectPropertyKlienci.getValue().getId());
            init();
            DbManager.closeConnectionSource();
        } else {
            Alerts.warrningAlert("BŁĄD", "samochod jest użyty w bazie");
        }
    }

    private Predicate<WypozyczSamochodFX> predicateKlient() {
        return wypozyczSamochodFX -> wypozyczSamochodFX.getKlienciFx().getId() == getObjectPropertyKlienci().getId();
    }

    private boolean filterPredicate(Predicate<WypozyczSamochodFX> predicate) {
        List<WypozyczSamochodFX> list = wypozyczeniaFxList.stream().filter(predicate).collect(Collectors.toList());
        if(list.isEmpty()){
            return true;
        }
        return false;
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
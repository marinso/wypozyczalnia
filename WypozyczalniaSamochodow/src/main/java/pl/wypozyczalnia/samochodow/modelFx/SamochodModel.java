package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.wypozyczalnia.samochodow.database.dao.PracownicyDao;
import pl.wypozyczalnia.samochodow.database.dao.SamochodyDao;
import pl.wypozyczalnia.samochodow.database.dbutils.DbManager;
import pl.wypozyczalnia.samochodow.database.models.Samochody;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterSamochod;

import java.util.List;

public class SamochodModel {

    private ObservableList<SamochodFx> observableListSamochody = FXCollections.observableArrayList();
    private ObjectProperty<SamochodFx> objectPropertySamochody = new SimpleObjectProperty<>(new SamochodFx());
    private ObjectProperty<SamochodFx> objectPropertyEditSamochody = new SimpleObjectProperty<>(new SamochodFx());

    public void init() {
        SamochodyDao samochodyDao = new SamochodyDao();
        List<Samochody> samochody = samochodyDao.queryForAll(Samochody.class);
        observableListSamochody.clear();
        samochody.forEach(e->{
            SamochodFx samochodFx = new SamochodFx();
            samochodFx.setId(e.getId());
            samochodFx.setModel(e.getModel());
            samochodFx.setCena_za_dzien(e.getCena_za_dzien());
            samochodFx.setCzy_dostepny(e.isCzy_dostepny());

            observableListSamochody.add(samochodFx);
        });
        DbManager.closeConnectionSource();
    }

    public void saveSamochodyEditInDataBase() {
        SamochodyDao samochodyDao = new SamochodyDao();
        Samochody samochody = ConverterSamochod.convertSamochodyFxToSamochody(this.getObjectPropertyEditSamochody());
        samochodyDao.creatOrUpdate(samochody);
        init();
        DbManager.closeConnectionSource();
    }

    public void saveSamochodyInDataBase() {
        SamochodyDao samochodyDao = new SamochodyDao();
        Samochody samochody = ConverterSamochod.convertSamochodyFxToSamochody(this.getObjectPropertySamochody());
        samochody.setCzy_dostepny(true);
        samochodyDao.creatOrUpdate(samochody);
        init();
        DbManager.closeConnectionSource();
    }

    public void deleteById(){
        SamochodyDao samochodyDao = new SamochodyDao();
        samochodyDao.deleteById(Samochody.class,objectPropertySamochody.getValue().getId());
        init();
        DbManager.closeConnectionSource();
    }

    public ObservableList<SamochodFx> getObservableListSamochody() {
        return observableListSamochody;
    }

    public void setObservableListSamochody(ObservableList<SamochodFx> observableListSamochody) {
        this.observableListSamochody = observableListSamochody;
    }

    public SamochodFx getObjectPropertySamochody() {
        return objectPropertySamochody.get();
    }

    public ObjectProperty<SamochodFx> objectPropertySamochodyProperty() {
        return objectPropertySamochody;
    }

    public void setObjectPropertySamochody(SamochodFx objectPropertySamochody) {
        this.objectPropertySamochody.set(objectPropertySamochody);
    }

    public SamochodFx getObjectPropertyEditSamochody() {
        return objectPropertyEditSamochody.get();
    }

    public ObjectProperty<SamochodFx> objectPropertyEditSamochodyProperty() {
        return objectPropertyEditSamochody;
    }

    public void setObjectPropertyEditSamochody(SamochodFx objectPropertyEditSamochody) {
        this.objectPropertyEditSamochody.set(objectPropertyEditSamochody);
    }
}

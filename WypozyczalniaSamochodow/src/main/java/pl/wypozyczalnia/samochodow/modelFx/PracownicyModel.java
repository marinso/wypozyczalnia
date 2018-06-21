package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.wypozyczalnia.samochodow.database.dao.PracownicyDao;
import pl.wypozyczalnia.samochodow.database.dao.WypozyczeniaDao;
import pl.wypozyczalnia.samochodow.database.dbutils.DbManager;
import pl.wypozyczalnia.samochodow.database.models.Pracownicy;
import pl.wypozyczalnia.samochodow.database.models.Wypozyczenia;
import pl.wypozyczalnia.samochodow.utils.Alerts;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterPracownik;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterWypozyczenia;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PracownicyModel {

    private ObservableList<PracownicyFx> observableListPracownicy = FXCollections.observableArrayList();
    private ObjectProperty<PracownicyFx> objectPropertyPracownicy = new SimpleObjectProperty<>(new PracownicyFx());
    private ObjectProperty<PracownicyFx> objectPropertyPracownicyEdit = new SimpleObjectProperty<>();

    private List<WypozyczSamochodFX>           wypozyczeniaFxList         = new ArrayList<>();


    public void init(){
        PracownicyDao pracownicyDao = new PracownicyDao();
        List<Pracownicy> pracownicy = pracownicyDao.queryForAll(Pracownicy.class);
        observableListPracownicy.clear();
        pracownicy.forEach(e->{
            PracownicyFx pracownicyFx = new PracownicyFx();
            pracownicyFx.setId(e.getId_pracownika());
            pracownicyFx.setName(e.getName());
            pracownicyFx.setSurname(e.getSurname());
            pracownicyFx.setNumerTel(e.getNumerTel());
            pracownicyFx.setPensja(e.getPensja());
            this.observableListPracownicy.add(pracownicyFx);
        });
        DbManager.closeConnectionSource();
    }

    public void savePracownicyEditInDataBase() {
        PracownicyDao pracownicyDao = new PracownicyDao();
        Pracownicy pracownicy = ConverterPracownik.pracownicyFxToPracownicy(this.getObjectPropertyPracownicyEdit());
        pracownicyDao.creatOrUpdate(pracownicy);
        this.init();
        DbManager.closeConnectionSource();
    }

    public void savePracownicyInDataBase() {
        PracownicyDao pracownicyDao = new PracownicyDao();
        Pracownicy pracownicy = ConverterPracownik.pracownicyFxToPracownicy(this.getObjectPropertyPracownicy());
        pracownicyDao.creatOrUpdate(pracownicy);
        this.init();
        DbManager.closeConnectionSource();
    }


    public void deleteById(){
        WypozyczeniaDao wypozyczeniaDao = new WypozyczeniaDao();
        wypozyczeniaFxList.clear();
        List<Wypozyczenia> wypozyczenia = wypozyczeniaDao.queryForAll(Wypozyczenia.class);
        wypozyczenia.forEach(e -> {
            wypozyczeniaFxList.add(ConverterWypozyczenia.convertToWypozyczeniaFx(e));
        });

        if(filterPredicate(predicatePracownik())) {
            PracownicyDao pracownicyDao = new PracownicyDao();
            pracownicyDao.deleteById(Pracownicy.class, objectPropertyPracownicy.getValue().getId());
            init();
            DbManager.closeConnectionSource();
        }else{
            Alerts.warrningAlert("BŁĄD", "pracownik jest użyty w bazie");
        }
    }

    private Predicate<WypozyczSamochodFX> predicatePracownik() {
        return wypozyczSamochodFX -> wypozyczSamochodFX.getKlienciFx().getId() == getObjectPropertyPracownicy().getId();
    }

    private boolean filterPredicate(Predicate<WypozyczSamochodFX> predicate) {
        List<WypozyczSamochodFX> list = wypozyczeniaFxList.stream().filter(predicate).collect(Collectors.toList());
        if(list.isEmpty()){
            return true;
        }
        return false;
    }

    public ObservableList<PracownicyFx> getObservableListPracownicy() {
        return observableListPracownicy;
    }

    public void setObservableListPracownicy(ObservableList<PracownicyFx> observableListPracownicy) {
        this.observableListPracownicy = observableListPracownicy;
    }

    public PracownicyFx getObjectPropertyPracownicy() {
        return objectPropertyPracownicy.get();
    }

    public ObjectProperty<PracownicyFx> objectPropertyPracownicyProperty() {
        return objectPropertyPracownicy;
    }

    public void setObjectPropertyPracownicy(PracownicyFx objectPropertyPracownicy) {
        this.objectPropertyPracownicy.set(objectPropertyPracownicy);
    }

    public PracownicyFx getObjectPropertyPracownicyEdit() {
        return objectPropertyPracownicyEdit.get();
    }

    public ObjectProperty<PracownicyFx> objectPropertyPracownicyEditProperty() {
        return objectPropertyPracownicyEdit;
    }

    public void setObjectPropertyPracownicyEdit(PracownicyFx objectPropertyPracownicyEdit) {
        this.objectPropertyPracownicyEdit.set(objectPropertyPracownicyEdit);
    }
}

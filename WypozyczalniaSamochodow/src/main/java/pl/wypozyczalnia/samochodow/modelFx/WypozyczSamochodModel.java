package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import pl.wypozyczalnia.samochodow.database.dao.KlienciDao;
import pl.wypozyczalnia.samochodow.database.dao.PracownicyDao;
import pl.wypozyczalnia.samochodow.database.dao.SamochodyDao;
import pl.wypozyczalnia.samochodow.database.dao.WypozyczeniaDao;
import pl.wypozyczalnia.samochodow.database.dbutils.DbManager;
import pl.wypozyczalnia.samochodow.database.models.*;
import pl.wypozyczalnia.samochodow.utils.Utils;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterKlient;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterPracownik;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterSamochod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WypozyczSamochodModel {

    private ObjectProperty<WypozyczSamochodFX> wypozyczeniaObjectProperty = new SimpleObjectProperty<>(new WypozyczSamochodFX());
    private ObservableList<PracownicyFx> pracownicyFxObservableList = FXCollections.observableArrayList();
    private ObservableList<KlientFx> klienciFxObservableList = FXCollections.observableArrayList();
    private ObservableList<SamochodFx> samochodyFxObservableList = FXCollections.observableArrayList();

    public void init(){
        initPracownicyList();
        initKlienciList();
        initSamochodyList();
    }

    private void initSamochodyList() {
        SamochodyDao samochodyDao = new SamochodyDao();
        List<Samochody> samochodies = samochodyDao.queryForAll(Samochody.class);
        samochodyFxObservableList.clear();
        samochodies.forEach(e->{
            SamochodFx samochodFx = ConverterSamochod.SamochodToSamochodFx(e);
            if(samochodFx.isCzy_dostepny() == true) {
                this.samochodyFxObservableList.add(samochodFx);
            }
        });
        DbManager.closeConnectionSource();
    }

    private void initKlienciList() {
        KlienciDao klienciDao = new KlienciDao();
        List<Klienci> klienciList = klienciDao.queryForAll(Klienci.class);
        klienciFxObservableList.clear();
        klienciList.forEach(e->{
            KlientFx klientFx = ConverterKlient.convertToKlienciFx(e);
            this.klienciFxObservableList.add(klientFx);
        });
        DbManager.closeConnectionSource();
    }

    private void initPracownicyList() {
       PracownicyDao pracownicyDao = new PracownicyDao();
        List<Pracownicy> pracownicyList = pracownicyDao.queryForAll(Pracownicy.class);
        pracownicyFxObservableList.clear();
        pracownicyList.forEach(e->{
            PracownicyFx pracownicyFx = ConverterPracownik.ConvertToPracownicyFx(e);
            this.pracownicyFxObservableList.add(pracownicyFx);
        });
        DbManager.closeConnectionSource();
    }

    public void saveInDataBase() {
        PracownicyDao pracownicyDao = new PracownicyDao();
        Pracownicy pracownicy = pracownicyDao.findById(Pracownicy.class, getWypozyczeniaObjectProperty().getPracownicyFx().getId());

        SamochodyDao samochodyDao = new SamochodyDao();
        Samochody samochody = samochodyDao.findById(Samochody.class,getWypozyczeniaObjectProperty().getSamochodyFx().getId());
        samochody.setCzy_dostepny(false);
        samochodyDao.creatOrUpdate(samochody);


        KlienciDao klienciDao = new KlienciDao();
        Klienci klienci = klienciDao.findById(Klienci.class, getWypozyczeniaObjectProperty().getKlienciFx().getId());

        Wypozyczenia wypozyczenia = new Wypozyczenia();
        wypozyczenia.setCzy_zwrocony(false);
        wypozyczenia.setPracownicy(pracownicy);
        wypozyczenia.setSamochody(samochody);
        wypozyczenia.setKlienci(klienci);
        wypozyczenia.setData_wypozyczenia(Utils.convertToDate(LocalDate.now()));

        WypozyczeniaDao wypozyczeniaDao = new WypozyczeniaDao();
        wypozyczeniaDao.creatOrUpdate(wypozyczenia);

        DbManager.closeConnectionSource();
    }

    public WypozyczSamochodFX getWypozyczeniaObjectProperty() {
        return wypozyczeniaObjectProperty.get();
    }

    public ObjectProperty<WypozyczSamochodFX> wypozyczeniaObjectPropertyProperty() {
        return wypozyczeniaObjectProperty;
    }

    public void setWypozyczeniaObjectProperty(WypozyczSamochodFX wypozyczeniaObjectProperty) {
        this.wypozyczeniaObjectProperty.set(wypozyczeniaObjectProperty);
    }

    public ObservableList<PracownicyFx> getPracownicyFxObservableList() {
        return pracownicyFxObservableList;
    }

    public void setPracownicyFxObservableList(ObservableList<PracownicyFx> pracownicyFxObservableList) {
        this.pracownicyFxObservableList = pracownicyFxObservableList;
    }

    public ObservableList<KlientFx> getKlienciFxObservableList() {
        return klienciFxObservableList;
    }

    public void setKlienciFxObservableList(ObservableList<KlientFx> klienciFxObservableList) {
        this.klienciFxObservableList = klienciFxObservableList;
    }

    public ObservableList<SamochodFx> getSamochodyFxObservableList() {
        return samochodyFxObservableList;
    }

    public void setSamochodyFxObservableList(ObservableList<SamochodFx> samochodyFxObservableList) {
        this.samochodyFxObservableList = samochodyFxObservableList;
    }
}

package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.wypozyczalnia.samochodow.database.dao.KlienciDao;
import pl.wypozyczalnia.samochodow.database.dao.WypozyczeniaDao;
import pl.wypozyczalnia.samochodow.database.models.Klienci;
import pl.wypozyczalnia.samochodow.database.models.Wypozyczenia;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterKlient;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterWypozyczenia;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WypozyczSamochodViewModel {

    private ObservableList<WypozyczSamochodFX> wypozyczSamochodFXObservableList = FXCollections.observableArrayList();

    private ObservableList<KlientFx> klientFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<KlientFx> klientFxObjectProperty = new SimpleObjectProperty<>();

    private List<WypozyczSamochodFX> wypozyczeniaFxList = new ArrayList<>();


    public void init(){
        WypozyczeniaDao wypozyczeniaDao = new WypozyczeniaDao();
        List<Wypozyczenia> wypozyczenia = wypozyczeniaDao.queryForAll(Wypozyczenia.class);
        wypozyczenia.forEach(e->{
            this.wypozyczeniaFxList.add(ConverterWypozyczenia.convertToWypozyczeniaFx(e));
        });
        this.wypozyczSamochodFXObservableList.setAll(wypozyczeniaFxList);
        initKlientFx();
    }

    public void filterWypozyczeniaList(){
        if(getKlientFxObjectProperty() != null){
            filterPredicate(predicateKlient());
        } else
            this.wypozyczSamochodFXObservableList.setAll(wypozyczeniaFxList);
    }

    private void initKlientFx() {
        KlienciDao klienciDao = new KlienciDao();
        List<Klienci> klienci = klienciDao.queryForAll(Klienci.class);
        klienci.forEach(e -> {
            this.klientFxObservableList.add(ConverterKlient.convertToKlienciFx(e));
         });
    }

    private Predicate<WypozyczSamochodFX> predicateKlient(){
        return wypozyczSamochodFX -> wypozyczSamochodFX.getKlienciFx().getId() == getKlientFxObjectProperty().getId();
    }

    private void filterPredicate(Predicate<WypozyczSamochodFX> predicate){
        List<WypozyczSamochodFX> list = wypozyczeniaFxList.stream().filter(predicate).collect(Collectors.toList());
        this.wypozyczSamochodFXObservableList.setAll(list);
    }

    public ObservableList<WypozyczSamochodFX> getWypozyczSamochodFXObservableList() {
        return wypozyczSamochodFXObservableList;
    }

    public void setWypozyczSamochodFXObservableList(ObservableList<WypozyczSamochodFX> wypozyczSamochodFXObservableList) {
        this.wypozyczSamochodFXObservableList = wypozyczSamochodFXObservableList;
    }

    public ObservableList<KlientFx> getKlientFxObservableList() {
        return klientFxObservableList;
    }

    public void setKlientFxObservableList(ObservableList<KlientFx> klientFxObservableList) {
        this.klientFxObservableList = klientFxObservableList;
    }

    public KlientFx getKlientFxObjectProperty() {
        return klientFxObjectProperty.get();
    }

    public ObjectProperty<KlientFx> klientFxObjectPropertyProperty() {
        return klientFxObjectProperty;
    }

    public void setKlientFxObjectProperty(KlientFx klientFxObjectProperty) {
        this.klientFxObjectProperty.set(klientFxObjectProperty);
    }
}


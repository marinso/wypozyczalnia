package pl.wypozyczalnia.samochodow.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.wypozyczalnia.samochodow.database.dao.WypozyczeniaDao;
import pl.wypozyczalnia.samochodow.database.dao.ZwrotyDao;
import pl.wypozyczalnia.samochodow.database.dbutils.DbManager;
import pl.wypozyczalnia.samochodow.database.models.Wypozyczenia;
import pl.wypozyczalnia.samochodow.database.models.Zwroty;
import pl.wypozyczalnia.samochodow.utils.Utils;
import pl.wypozyczalnia.samochodow.utils.converters.ConverterWypozyczenia;

import java.util.List;

public class ZwrotyModel {

    private ObjectProperty<ZwrotyFx> zwrotyFxObjectProperty = new SimpleObjectProperty<>(new ZwrotyFx());
    private ObservableList<WypozyczSamochodFX> wypozyczSamochodFXObservableList = FXCollections.observableArrayList(new WypozyczSamochodFX());

    private ObservableList<ZwrotyFx> zwrotyFxObservableList = FXCollections.observableArrayList();

    public void init(){
        ZwrotyDao zwrotyDao = new ZwrotyDao();
        List<Zwroty> zwroties = zwrotyDao.queryForAll(Zwroty.class);
        zwrotyFxObservableList.clear();
        zwroties.forEach(e->{
           ZwrotyFx zwrotyFx = new ZwrotyFx();
           zwrotyFx.setId(e.getId());
           zwrotyFx.setDate(Utils.convertToLocalDate(e.getData_zwrotu()));
           zwrotyFx.setWypozyczeniaFx(ConverterWypozyczenia.convertToWypozyczeniaFx(e.getWypozyczenia()));
           zwrotyFx.setKoszt_wyp(String.valueOf(e.getKoszt_wypozyczenia()));
           zwrotyFxObservableList.add(zwrotyFx);
        });

        zwrotyFxObservableList.forEach(e-> {
            System.out.println(e.getKoszt_wyp());
            System.out.println(e.getDate());
        });
        initWypozyczenia();
    }

    private void initWypozyczenia() {
        WypozyczeniaDao wypozyczeniaDao = new WypozyczeniaDao();
        List<Wypozyczenia> wypozyczenia = wypozyczeniaDao.queryForAll(Wypozyczenia.class);
        wypozyczSamochodFXObservableList.clear();
        wypozyczenia.forEach(e-> {
            WypozyczSamochodFX wypozyczeniaFx = ConverterWypozyczenia.convertToWypozyczeniaFx(e);
            if(wypozyczeniaFx.isCzy_zwrcony()== false)
             this.wypozyczSamochodFXObservableList.add(wypozyczeniaFx);
        });
        DbManager.closeConnectionSource();
    }

    public void saveToDataBase() {
        WypozyczeniaDao wypozyczeniaDao = new WypozyczeniaDao();
        Wypozyczenia wypozyczenia = wypozyczeniaDao.findById(Wypozyczenia.class,getZwrotyFxObjectProperty().getWypozyczeniaFx().getId());
        wypozyczenia.setCzy_zwrocony(true);
        wypozyczeniaDao.creatOrUpdate(wypozyczenia);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(wypozyczenia.getCzy_zwrocony());
        System.out.println();
        System.out.println();


        Zwroty zwroty = new Zwroty();
        zwroty.setData_zwrotu(Utils.convertToDate(zwrotyFxObjectProperty.get().getDate()));
        zwroty.setKoszt_wypozyczenia(Double.valueOf(zwrotyFxObjectProperty.get().getKoszt_wyp()));
        zwroty.setWypozyczenia(wypozyczenia);



        ZwrotyDao zwrotyDao = new ZwrotyDao();
        zwrotyDao.creatOrUpdate(zwroty);

        //wypozyczeniaDao.deleteById(Wypozyczenia.class,getZwrotyFxObjectProperty().getWypozyczeniaFx().getId());
        init();
        DbManager.closeConnectionSource();
    }


    public ZwrotyFx getZwrotyFxObjectProperty() {
        return zwrotyFxObjectProperty.get();
    }

    public ObjectProperty<ZwrotyFx> zwrotyFxObjectPropertyProperty() {
        return zwrotyFxObjectProperty;
    }

    public void setZwrotyFxObjectProperty(ZwrotyFx zwrotyFxObjectProperty) {
        this.zwrotyFxObjectProperty.set(zwrotyFxObjectProperty);
    }

    public ObservableList<WypozyczSamochodFX> getWypozyczSamochodFXObservableList() {
        return wypozyczSamochodFXObservableList;
    }

    public void setWypozyczSamochodFXObservableList(ObservableList<WypozyczSamochodFX> wypozyczSamochodFXObservableList) {
        this.wypozyczSamochodFXObservableList = wypozyczSamochodFXObservableList;
    }

    public ObservableList<ZwrotyFx> getZwrotyFxObservableList() {
        return zwrotyFxObservableList;
    }

    public void setZwrotyFxObservableList(ObservableList<ZwrotyFx> zwrotyFxObservableList) {
        this.zwrotyFxObservableList = zwrotyFxObservableList;
    }
}

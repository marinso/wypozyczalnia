package pl.wypozyczalnia.samochodow.utils.converters;

import pl.wypozyczalnia.samochodow.database.models.Samochody;
import pl.wypozyczalnia.samochodow.modelFx.SamochodFx;

public class ConverterSamochod {

    public static Samochody convertSamochodyFxToSamochody(SamochodFx samochodFx){
        Samochody samochody = new Samochody();
        samochody.setModel(samochodFx.getModel());
        samochody.setCena_za_dzien(String.valueOf(samochodFx.getCena_za_dzien()));
        samochody.setCzy_dostepny(samochodFx.isCzy_dostepny());
        samochody.setId(samochodFx.getId());
        return samochody;
    }

    public static SamochodFx SamochodToSamochodFx(Samochody samochody){
        SamochodFx samochodFx = new SamochodFx();
        samochodFx.setModel(samochody.getModel());
        samochodFx.setCena_za_dzien(String.valueOf(samochody.getCena_za_dzien()));
        samochodFx.setCzy_dostepny(samochody.isCzy_dostepny());
        samochodFx.setId(samochody.getId());
        return samochodFx;
    }
}

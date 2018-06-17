package pl.wypozyczalnia.samochodow.utils.converters;


import pl.wypozyczalnia.samochodow.database.models.Wypozyczenia;
import pl.wypozyczalnia.samochodow.modelFx.WypozyczSamochodFX;
import pl.wypozyczalnia.samochodow.utils.Utils;

public class ConverterWypozyczenia {

    public static Wypozyczenia wypozyczeniaFxToWypozyczenia(WypozyczSamochodFX wypozyczSamochodFX){
        Wypozyczenia wypozyczenia = new Wypozyczenia();
        wypozyczenia.setId(wypozyczSamochodFX.getId());
        wypozyczenia.setKlienci(ConverterKlient.klienciFxToKlienci(wypozyczSamochodFX.getKlienciFx()));
        wypozyczenia.setSamochody(ConverterSamochod.convertSamochodyFxToSamochody(wypozyczSamochodFX.getSamochodyFx()));
        wypozyczenia.setPracownicy(ConverterPracownik.pracownicyFxToPracownicy(wypozyczSamochodFX.getPracownicyFx()));
        wypozyczenia.setData_wypozyczenia(Utils.convertToDate(wypozyczSamochodFX.getData_wyp()));
        return wypozyczenia;
    }

    public static WypozyczSamochodFX convertToWypozyczeniaFx(Wypozyczenia wypozyczenia){
        WypozyczSamochodFX wypozyczSamochodFX = new WypozyczSamochodFX();
        wypozyczSamochodFX.setId(wypozyczenia.getId());
        wypozyczSamochodFX.setKlienciFx(ConverterKlient.convertToKlienciFx(wypozyczenia.getKlienci()));
        wypozyczSamochodFX.setSamochodyFx(ConverterSamochod.SamochodToSamochodFx(wypozyczenia.getSamochody()));
        wypozyczSamochodFX.setPracownicyFx(ConverterPracownik.ConvertToPracownicyFx(wypozyczenia.getPracownicy()));
        wypozyczSamochodFX.setData_wyp(Utils.convertToLocalDate(wypozyczenia.getData_wypozyczenia()));
        wypozyczSamochodFX.setCzy_zwrcony(wypozyczenia.getCzy_zwrocony());
        return wypozyczSamochodFX;
    }

}

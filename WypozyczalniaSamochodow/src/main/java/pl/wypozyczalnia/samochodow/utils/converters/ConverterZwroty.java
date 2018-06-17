package pl.wypozyczalnia.samochodow.utils.converters;

import pl.wypozyczalnia.samochodow.database.models.Zwroty;
import pl.wypozyczalnia.samochodow.modelFx.ZwrotyFx;
import pl.wypozyczalnia.samochodow.utils.Utils;

public class ConverterZwroty {

    public static ZwrotyFx convertToZwrotyFx (Zwroty zwroty){
        ZwrotyFx zwrotyFx = new ZwrotyFx();
        zwrotyFx.setKoszt_wyp(String.valueOf(zwroty.getKoszt_wypozyczenia()));
        zwrotyFx.setDate(Utils.convertToLocalDate(zwroty.getData_zwrotu()));
        zwrotyFx.setId(zwroty.getId());
        zwrotyFx.setWypozyczeniaFx(ConverterWypozyczenia.convertToWypozyczeniaFx(zwroty.getWypozyczenia()));

        return zwrotyFx;
    }

}

package pl.wypozyczalnia.samochodow.utils.converters;

import pl.wypozyczalnia.samochodow.database.models.Klienci;
import pl.wypozyczalnia.samochodow.modelFx.KlientFx;

public class ConverterKlient {

    public static Klienci klienciFxToKlienci (KlientFx klientFx){
        Klienci klienci = new Klienci();
        klienci.setName(klientFx.getName());
        klienci.setSurname(klientFx.getSurname());
        klienci.setNumerTel(klientFx.getNrtel());
        klienci.setId_klienta(klientFx.getId());
        return klienci;
    }
    public static KlientFx convertToKlienciFx (Klienci klienci){
        KlientFx klienciFx = new KlientFx();
        klienciFx.setName(klienci.getName());
        klienciFx.setSurname(klienci.getSurname());
        klienciFx.setNrtel(klienci.getNumerTel());
        klienciFx.setId(klienci.getId_klienta());
        return klienciFx;
    }

}

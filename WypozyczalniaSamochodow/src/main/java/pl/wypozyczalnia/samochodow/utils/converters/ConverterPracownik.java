package pl.wypozyczalnia.samochodow.utils.converters;

import pl.wypozyczalnia.samochodow.database.models.Pracownicy;
import pl.wypozyczalnia.samochodow.modelFx.PracownicyFx;

public class ConverterPracownik {

    public static Pracownicy pracownicyFxToPracownicy(PracownicyFx pracownicyFx){
        Pracownicy pracownicy = new Pracownicy();
        pracownicy.setName(pracownicyFx.getName());
        pracownicy.setSurname(pracownicyFx.getSurname());
        pracownicy.setNumerTel(pracownicyFx.getNumerTel());
        pracownicy.setPensja(pracownicyFx.getPensja());
        pracownicy.setId_pracownika(pracownicyFx.getId());
        return pracownicy;
    }

    public static PracownicyFx ConvertToPracownicyFx(Pracownicy pracownicy){
        PracownicyFx pracownicyFx = new PracownicyFx();
        pracownicyFx.setName(pracownicy.getName());
        pracownicyFx.setSurname(pracownicy.getSurname());
        pracownicyFx.setNumerTel(pracownicy.getNumerTel());
        pracownicyFx.setPensja(pracownicy.getPensja());
        pracownicyFx.setId(pracownicy.getId_pracownika());
        return pracownicyFx;
    }
}

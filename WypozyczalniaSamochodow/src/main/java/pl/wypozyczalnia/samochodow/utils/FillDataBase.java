package pl.wypozyczalnia.samochodow.utils;

import pl.wypozyczalnia.samochodow.database.dao.KlienciDao;
import pl.wypozyczalnia.samochodow.database.dao.PracownicyDao;
import pl.wypozyczalnia.samochodow.database.dao.SamochodyDao;
import pl.wypozyczalnia.samochodow.database.dbutils.DbManager;
import pl.wypozyczalnia.samochodow.database.models.Klienci;
import pl.wypozyczalnia.samochodow.database.models.Pracownicy;
import pl.wypozyczalnia.samochodow.database.models.Samochody;

public class FillDataBase {

    public void fillDataBase() {
        Pracownicy pracownicy = new Pracownicy();
        pracownicy.setName("Pawel");
        pracownicy.setSurname("Adamski");
        pracownicy.setNumerTel("634-634-634");
        pracownicy.setPensja("3.200");

        Pracownicy pracownicy2 = new Pracownicy();
        pracownicy2.setName("Piotr");
        pracownicy2.setSurname("Fill");
        pracownicy2.setNumerTel("324-523-634");
        pracownicy2.setPensja("3.600");


        Pracownicy pracownicy3 = new Pracownicy();
        pracownicy3.setName("Mateusz");
        pracownicy3.setSurname("Jezior");
        pracownicy3.setNumerTel("888-523-432");
        pracownicy3.setPensja("2.200");


        Pracownicy pracownicy4 = new Pracownicy();
        pracownicy4.setName("Mieczysław");
        pracownicy4.setSurname("Syrocki");
        pracownicy4.setNumerTel("669-444-634");
        pracownicy4.setPensja("5.200");

        try{
            PracownicyDao pracownicyDao = new PracownicyDao();
            pracownicyDao.creatOrUpdate(pracownicy);
            pracownicyDao.creatOrUpdate(pracownicy2);
            pracownicyDao.creatOrUpdate(pracownicy3);
            pracownicyDao.creatOrUpdate(pracownicy4);
            DbManager.closeConnectionSource();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        Samochody samochody = new Samochody();
        samochody.setCzy_dostepny(true);
        samochody.setCena_za_dzien("500");
        samochody.setModel("BMW E90");


        Samochody samochody2 = new Samochody();
        samochody2.setCzy_dostepny(true);
        samochody2.setCena_za_dzien("700");
        samochody2.setModel("Audi A5");


        Samochody samochody3 = new Samochody();
        samochody3.setCzy_dostepny(true);
        samochody3.setCena_za_dzien("50");
        samochody3.setModel("Maluch");


        Samochody samochody4 = new Samochody();
        samochody4.setCzy_dostepny(true);
        samochody4.setCena_za_dzien("5000");
        samochody4.setModel("Porsche 911");

        try{
            SamochodyDao samochodyDao = new SamochodyDao();
            samochodyDao.creatOrUpdate(samochody);
            samochodyDao.creatOrUpdate(samochody2);
            samochodyDao.creatOrUpdate(samochody3);
            samochodyDao.creatOrUpdate(samochody4);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Klienci klienci = new Klienci();
        klienci.setName("Pawel");
        klienci.setSurname("Rybinski");
        klienci.setNumerTel("234-532-532");


        Klienci klienci2 = new Klienci();
        klienci2.setName("Szymon");
        klienci2.setSurname("Stock");
        klienci2.setNumerTel("734-322-532");


        Klienci klienci3 = new Klienci();
        klienci3.setName("Marcin");
        klienci3.setSurname("Kowalski");
        klienci3.setNumerTel("734-423-532");

        KlienciDao klienciDao = new KlienciDao();
        klienciDao.creatOrUpdate(klienci);
        klienciDao.creatOrUpdate(klienci2);
        klienciDao.creatOrUpdate(klienci3);

    }


}

package pl.wypozyczalnia.samochodow.utils;

public class Check {

    public static boolean sameLitery(String napis){
       boolean wynik =  napis.matches("[A-Z][a-z]+");
       return wynik;
    }

    public static boolean sameCyfry(String napis){
        boolean wynik = napis.matches("[0-9.]+");
        return wynik;
    }

    public static boolean numerTel(String napis){
        boolean wynik = napis.matches("[1-9]{3}[-][0-9]{3}[-][0-9]{3}");
        return wynik;
    }

}

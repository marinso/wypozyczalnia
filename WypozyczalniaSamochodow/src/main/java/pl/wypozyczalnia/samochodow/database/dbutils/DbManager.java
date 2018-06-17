package pl.wypozyczalnia.samochodow.database.dbutils;


import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.wypozyczalnia.samochodow.database.models.*;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./wypozyczalniaDB";
    private static final String USER = "admin";
    private static final String PASS = "admin";

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        createConnectionSource();
        dropTable();
        createTable();
        closeConnectionSource();
    }

    private static void createTable() {
        try{
            TableUtils.createTableIfNotExists(connectionSource, Klienci.class);
            TableUtils.createTableIfNotExists(connectionSource, Pracownicy.class);
            TableUtils.createTableIfNotExists(connectionSource, Zwroty.class);
            TableUtils.createTableIfNotExists(connectionSource, Samochody.class);
            TableUtils.createTableIfNotExists(connectionSource, Wypozyczenia.class);
           // TableUtils.createTableIfNotExists(connectionSource, WypozyczeniaSamochody.class);
        }catch(SQLException e){
            LOGGER.warn(e.getMessage());
        }
    }

    public static void closeConnectionSource() {
        if(connectionSource!=null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Klienci.class,true);
            TableUtils.dropTable(connectionSource, Zwroty.class,true);
            TableUtils.dropTable(connectionSource, Wypozyczenia.class,true);
            TableUtils.dropTable(connectionSource, Pracownicy.class,true);
            TableUtils.dropTable(connectionSource, Samochody.class,true);
           // TableUtils.dropTable(connectionSource, WypozyczeniaSamochody.class,true);
        }catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
    }

    private static ConnectionSource createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD,USER,PASS);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    public static ConnectionSource getConnectionSource() {
        if(connectionSource == null){
            return createConnectionSource();
        }
        return connectionSource;
    }


}

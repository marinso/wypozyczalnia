package pl.wypozyczalnia.samochodow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.wypozyczalnia.samochodow.database.dbutils.DbManager;
import pl.wypozyczalnia.samochodow.utils.FillDataBase;
import pl.wypozyczalnia.samochodow.utils.FxmlUtils;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Pane borderPane = FxmlUtils.fxmlLoader("/fxml/BorderPaneMain.fxml");
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wypożyczalnia Samochodów");
        primaryStage.show();

        FillDataBase fillDataBase = new FillDataBase();

        DbManager.initDatabase();

        fillDataBase.fillDataBase();
    }
}

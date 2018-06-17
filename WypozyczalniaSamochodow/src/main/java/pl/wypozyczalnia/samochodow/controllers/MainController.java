package pl.wypozyczalnia.samochodow.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import pl.wypozyczalnia.samochodow.utils.FxmlUtils;

public class MainController {

    @FXML
    BorderPane borderPane;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    public void initialize(){
        topMenuButtonsController.setMainController(this);
    }

    public void setCenter(String fxmlpath){
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlpath));
    }
}

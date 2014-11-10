package com.bench.mac.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {

    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @FXML
    private Button helloButton;

    @FXML
    private MenuBar menuBar;

    public void sayHello(ActionEvent actionEvent) {
        logger.info("Hello world!");
    }
}


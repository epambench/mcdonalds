package com.bench.mac.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;

public class Controller {

    @FXML
    private Button helloButton;

    @FXML
    private MenuBar menuBar;

    public void sayHello(ActionEvent actionEvent) {
        System.out.println("Hello world!");
    }
}


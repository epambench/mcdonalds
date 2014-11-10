package com.bench.mac.ui;

import com.bench.mac.config.guice.MacModule;
import com.bench.mac.api.jmx.MacConfiguratorMXBean;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Thread.currentThread().getClass().getResource("/ui/fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MacModule());
        injector.getInstance(MacConfiguratorMXBean.class);
        launch(args);
    }
}

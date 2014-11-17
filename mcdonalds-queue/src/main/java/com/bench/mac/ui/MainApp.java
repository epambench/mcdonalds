package com.bench.mac.ui;

import com.bench.mac.api.jmx.MacConfiguratorMXBean;
import com.bench.mac.config.guice.MacModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainApp extends Application {
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        Logger staticLogger = LoggerFactory.getLogger(MainApp.class);
        staticLogger.debug("Configuring Guice...");
        Injector injector = Guice.createInjector(new MacModule());
        injector.getInstance(MacConfiguratorMXBean.class);
        staticLogger.debug("Launching UI...");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        logger.debug("Opening Main FXML layout...");
        Parent root = FXMLLoader.load(Thread.currentThread().getClass().getResource("/ui/fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
        logger.debug("App initialized, UI started");
    }
}

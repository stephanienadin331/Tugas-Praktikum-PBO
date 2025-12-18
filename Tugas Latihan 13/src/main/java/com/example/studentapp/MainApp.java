package com.example.studentapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Student Manager (Factory + MVC)");
        stage.setScene(scene);
        stage.setWidth(760);
        stage.setHeight(540);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
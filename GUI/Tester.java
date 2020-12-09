package com.project;


import javafx.application.Application;
import javafx.stage.Stage;



public class Tester extends Application {
    public static void main(String[] args){
        launch(args);
        Menu n = new Menu();
        n.start();


    }

    @Override
    public void start(Stage stage) throws Exception {
        Scenes s = new Scenes(stage);
        stage.setScene(s.getBaseScene());
        stage.setTitle("Property Management");
        stage.show();
    }
}


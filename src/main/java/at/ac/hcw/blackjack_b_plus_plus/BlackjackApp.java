package at.ac.hcw.blackjack_b_plus_plus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BlackjackApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(BlackjackApp.class.getResource("menu-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

        stage.setTitle("Blackjack B++");

        stage.setResizable(true);

        stage.setMinWidth(1024);
        stage.setMinHeight(768);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
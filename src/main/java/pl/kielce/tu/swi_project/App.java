package pl.kielce.tu.swi_project;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/main-window.fxml"));
        stage.setTitle("Statystyki policyjne");
        stage.setScene(new Scene(root));
        stage.show();
    }
}

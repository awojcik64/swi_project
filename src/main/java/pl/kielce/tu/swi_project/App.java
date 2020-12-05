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
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css/tables.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

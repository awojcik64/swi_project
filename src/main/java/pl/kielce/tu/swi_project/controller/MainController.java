package pl.kielce.tu.swi_project.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import pl.kielce.tu.swi_project.chernoff.ChernoffBuilderDirector;
import pl.kielce.tu.swi_project.chernoff.ChernoffFaceBuilderImpl;
import pl.kielce.tu.swi_project.chernoff.resources.FaceBundle;
import pl.kielce.tu.swi_project.model.Point;
import pl.kielce.tu.swi_project.model.VoivodeshipData;
import pl.kielce.tu.swi_project.statistics.ColumnStats;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    @FXML
    private Canvas mapCanvas;

    @FXML
    private TextField dataFileBar;

    @FXML
    private Button readDataButton;

    private List<VoivodeshipData> data = new ArrayList<>();

    private ChernoffBuilderDirector builderDirector;

    private Map<Integer, Point> voivodeshipMapPosition = new HashMap<>();

    public MainController() {
        voivodeshipMapPosition.put(1, new Point(110, 329)); //Dolnośląskie
        voivodeshipMapPosition.put(2, new Point(230, 165));//Kujawsko-pomorskie
        voivodeshipMapPosition.put(3, new Point(445, 320));//Lubelskie
        voivodeshipMapPosition.put(4, new Point(50, 250));//Lubuskie
        voivodeshipMapPosition.put(5, new Point(270, 285));//Lodzkie
        voivodeshipMapPosition.put(6, new Point(320, 455));//Malopolskie
        voivodeshipMapPosition.put(7, new Point(350, 200));//Mazowieckie
        voivodeshipMapPosition.put(8, new Point(190, 390));//Opolskie
        voivodeshipMapPosition.put(9, new Point(415, 440));//Podkarpackie
        voivodeshipMapPosition.put(10, new Point(450, 140));//Podlaskie
        voivodeshipMapPosition.put(11, new Point(200, 60));//Pomorskie
        voivodeshipMapPosition.put(12, new Point(250, 400));//Slaskie
        voivodeshipMapPosition.put(13, new Point(350, 370));//Swietokrzyskie
        voivodeshipMapPosition.put(14, new Point(340, 90));//Warminsko-mazurskie
        voivodeshipMapPosition.put(15, new Point(160, 245));//Wielkopolskie
        voivodeshipMapPosition.put(16, new Point(80, 115));//Zachodnio-pomorskie
        voivodeshipMapPosition.put(17, new Point(0, 0));//KSP Warszawa
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.builderDirector = new ChernoffBuilderDirector(new ChernoffFaceBuilderImpl(new FaceBundle()));
        Image mapImage = new Image(getClass().getResourceAsStream("../img/poland2.png"));
        GraphicsContext context = mapCanvas.getGraphicsContext2D();
        context.drawImage(mapImage, 0.0, 0.0, mapCanvas.getWidth(), mapCanvas.getHeight());

        readDataButton.setOnAction(e -> {
            try {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Wybierz plik z danymi");
                chooser.getExtensionFilters()
                        .addAll(
                                new FileChooser.ExtensionFilter("Pliki CSV", "*.csv"),
                                new FileChooser.ExtensionFilter("Plik tekstowy", "*.txt")
                        );
                chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                File dataFile = chooser.showOpenDialog(((Node)e.getTarget()).getScene().getWindow());
                if(dataFile != null) {
                    CSVReader reader = new CSVReaderBuilder(new FileReader(dataFile))
                            .withSkipLines(1)
                            .build();
                    String[] row;
                    while((row = reader.readNext())!= null) {
                        data.add(new VoivodeshipData(Integer.parseInt(row[0]),
                                        row[1],
                                        Integer.parseInt(row[2]),
                                        Integer.parseInt(row[3]),
                                        Integer.parseInt(row[4]),
                                        Integer.parseInt(row[5]),
                                        Integer.parseInt(row[6])
                                )
                        );
                    }
                    Map<String, ColumnStats> columnStatsMap = new HashMap<>();
                    columnStatsMap.put("accidents", new ColumnStats(data, VoivodeshipData::getAccidentsCount));
                    columnStatsMap.put("victims", new ColumnStats(data, VoivodeshipData::getVictims));
                    columnStatsMap.put("injured", new ColumnStats(data, VoivodeshipData::getInjured));
                    columnStatsMap.put("after_alcohol", new ColumnStats(data, VoivodeshipData::getAfterAlcoholUsage));
                    columnStatsMap.put("drunk", new ColumnStats(data, VoivodeshipData::getDrunkDrivers));
                    builderDirector.acceptRuleSet(columnStatsMap);
                    data.forEach(datum -> {
                        Image face = builderDirector.make(datum);
                        mapCanvas.getGraphicsContext2D().drawImage(face, voivodeshipMapPosition.get(datum.getId()).getX()-face.getWidth()/2, voivodeshipMapPosition.get(datum.getId()).getY()-face.getHeight()/2);
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



    }
}

package pl.kielce.tu.swi_project.chernoff.resources;

import javafx.scene.image.Image;
import lombok.Data;
import pl.kielce.tu.swi_project.controller.MainController;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
public class FaceBundle {

    public Image head;

    private List<FaceElementRule> ears;

    private List<FaceElementRule> mouths;

    private List<FaceElementRule> eyes;

    private List<FaceElementRule> eyeBrows;

    private List<FaceElementRule> noses;

    public FaceBundle() {

        head = new Image(MainController.class.getResourceAsStream("../img/chernoff/head/head1.png"));
        ears = new ArrayList<>();
        ears.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/ears/ears1.png"))));
        ears.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/ears/ears2.png"))));
        ears.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/ears/ears3.png"))));
        mouths = new ArrayList<>();
        mouths.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/mouth/mouth1.png"))));
        mouths.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/mouth/mouth2.png"))));
        mouths.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/mouth/mouth3.png"))));
        eyes = new ArrayList<>();
        eyes.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/eyes/eyes1.png"))));
        eyes.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/eyes/eyes2.png"))));
        eyes.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/eyes/eyes3.png"))));
        eyeBrows = new ArrayList<>();
        eyeBrows.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/eyebrow/eyebrows1.png"))));
        eyeBrows.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/eyebrow/eyebrows2.png"))));
        eyeBrows.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/eyebrow/eyebrows3.png"))));
        noses = new ArrayList<>();
        noses.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/nose/nose1.png"))));
        noses.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/nose/nose2.png"))));
        noses.add(new FaceElementRule(0, 0, new Image(MainController.class.getResourceAsStream("../img/chernoff/nose/nose3.png"))));
    }
}

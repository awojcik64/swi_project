package pl.kielce.tu.swi_project.chernoff.resources;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaceElementRule {
    private int min;
    private int max;
    private Image image;
}

package pl.kielce.tu.swi_project.chernoff.resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaceElementRule {
    private int min;
    private int max;
    private ImageView image;

    public FaceElementRule(int min, int max, Image image) {
        this.min = min;
        this.max = max;
        this.image = new ImageView(image);
    }
}

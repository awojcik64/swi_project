package pl.kielce.tu.swi_project.model;

import javafx.scene.shape.Rectangle;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoundsIdBundle {
    private Integer id;

    private Rectangle rectangle;
}

package pl.kielce.tu.swi_project.chernoff;

import javafx.scene.canvas.Canvas;
import pl.kielce.tu.swi_project.statistics.ColumnStats;

import java.util.Map;

public interface ChernoffFaceBuilder {
    void drawHead(Canvas image, int value);

    void drawHair(Canvas image, int value);

    void drawMouth(Canvas image, int value);

    void drawNose(Canvas image, int value);

    void drawEyes(Canvas image, int value);

    void drawEyebrows(Canvas image, int value);

    void drawEars(Canvas image, int value);

    void drawLabel(Canvas image, String label);

    void acceptRules(Map<String, ColumnStats> rulesMap);
}

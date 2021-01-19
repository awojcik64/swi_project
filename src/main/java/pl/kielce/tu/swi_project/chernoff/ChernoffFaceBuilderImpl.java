package pl.kielce.tu.swi_project.chernoff;


import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import pl.kielce.tu.swi_project.chernoff.resources.FaceBundle;
import pl.kielce.tu.swi_project.chernoff.resources.FaceElementRule;
import pl.kielce.tu.swi_project.statistics.ColumnStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class ChernoffFaceBuilderImpl implements ChernoffFaceBuilder {

    final private FaceBundle bundle;

    public ChernoffFaceBuilderImpl(FaceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void drawHead(Canvas image, int value) {
        image.getGraphicsContext2D().drawImage(bundle.getHead(), 0, 0);
    }

    @Override
    public void drawHair(Canvas image, int value) {
        drawImage(image, value, FaceBundle::getHair,19, 0);
    }

    @Override
    public void drawMouth(Canvas image, int value) {
        drawImage(image, value, FaceBundle::getMouths, 22, 45);
    }

    @Override
    public void drawNose(Canvas image, int value) {
        drawImage(image, value, FaceBundle::getNoses, 34, 26);
    }

    @Override
    public void drawEyes(Canvas image, int value) {
        drawImage(image, value, FaceBundle::getEyes, 22, 19);
    }

    @Override
    public void drawEyebrows(Canvas image, int value) {
        drawImage(image, value, FaceBundle::getEyeBrows, 22, 14);
    }

    @Override
    public void drawEars(Canvas image, int value) {
        drawImage(image, value, FaceBundle::getEars, 0, 20);
    }

    @Override
    public void drawLabel(Canvas image, String label) {
        image.getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);
        image.getGraphicsContext2D().setTextBaseline(VPos.CENTER);
        image.getGraphicsContext2D().strokeText(label, 38, 68, 70);
    }

    @Override
    public void acceptRules(Map<String, ColumnStats> rulesMap) {
        setRules(FaceBundle::getEars, rulesMap, "after_alcohol");
        setRules(FaceBundle::getNoses, rulesMap, "drunk");
        setRules(FaceBundle::getMouths, rulesMap, "accidents");
        setRules(FaceBundle::getEyes, rulesMap, "injured");
        setRules(FaceBundle::getEyeBrows, rulesMap, "victims");
        setRules(FaceBundle::getHair, rulesMap, "victims");
    }

    private void drawImage(Canvas image, int value, Function<FaceBundle, List<FaceElementRule>> function, int x, int y) {
        for (FaceElementRule rule:
                function.apply(bundle)) {
            if(value >= rule.getMin() && value <= rule.getMax()) {
                image.getGraphicsContext2D().drawImage(rule.getImage().getImage(), x, y);
            }
        }
    }

    private void setRules(Function<FaceBundle, List<FaceElementRule>> function,Map<String, ColumnStats> rulesMap, String columnName) {
        function.apply(bundle).get(0).setMin(rulesMap.get(columnName).getMin());
        function.apply(bundle).get(0).setMax(rulesMap.get(columnName).getQuantile33());
        function.apply(bundle).get(1).setMin(rulesMap.get(columnName).getQuantile33()+1);
        function.apply(bundle).get(1).setMax(rulesMap.get(columnName).getQuantile66());
        function.apply(bundle).get(2).setMin(rulesMap.get(columnName).getQuantile66()+1);
        function.apply(bundle).get(2).setMax(rulesMap.get(columnName).getMax());
    }
}

package pl.kielce.tu.swi_project.chernoff;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import pl.kielce.tu.swi_project.misc.Toolbox;
import pl.kielce.tu.swi_project.model.VoivodeshipData;
import pl.kielce.tu.swi_project.statistics.ColumnStats;


import javax.tools.Tool;
import java.awt.image.BufferedImage;
import java.util.Map;


public class ChernoffBuilderDirector {
    private ChernoffFaceBuilder builder;

    public ChernoffBuilderDirector(ChernoffFaceBuilder builder) {
        this.builder = builder;
    }

    public void acceptRuleSet(Map<String, ColumnStats> rulesMap) {
        builder.acceptRules(rulesMap);
    }

    public Image make(VoivodeshipData stats) {
        WritableImage image = new WritableImage(76, 78);
        Canvas canvas = new Canvas(76, 78);
        builder.drawEars(canvas, stats.getAfterAlcoholUsage());
        builder.drawHead(canvas, 0);
        builder.drawHair(canvas, stats.getVictims());
        builder.drawMouth(canvas, stats.getAccidentsCount());
        builder.drawEyes(canvas, stats.getInjured());
        builder.drawNose(canvas, stats.getDrunkDrivers());
        builder.drawEyebrows(canvas, stats.getVictims());
        builder.drawLabel(canvas, stats.getName());
        canvas.snapshot(null, image);
        image = (WritableImage) Toolbox.makeTransparent(image);
        return image;
    }
}

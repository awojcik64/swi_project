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

    public Image makeTest(){
//        BufferedImage img = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
//        Graphics graphics = img.getGraphics();
//        graphics.setColor(Color.BLACK);
//
//        graphics.drawOval(5, 5, 25, 25);
//        WritableImage image = SwingFXUtils.toFXImage(img, null);
//        return new ImageView(image).getImage();
        WritableImage image = new WritableImage(50, 50);
        Canvas canvas = new Canvas(50, 50);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.BLACK);
        context.setStroke(Color.BLACK);
        context.setLineWidth(1);
        context.strokeOval(1, 1, 48, 48);
        //context.fillRect(0, 0, 50, 50)
//        for(int i=0; i<50; i++) {
//            for(int j=0; j<50; j++) {
//                context.getPixelWriter().setArgb(i, j, 0x000000FF);
//            }
//        }

        canvas.snapshot(null, image);

        return image;
    }

    public Image make(VoivodeshipData stats) {
        WritableImage image = new WritableImage(76, 78);
        Canvas canvas = new Canvas(76, 78);
        builder.drawEars(canvas, stats.getAfterAlcoholUsage());
        builder.drawHead(canvas, 0);
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

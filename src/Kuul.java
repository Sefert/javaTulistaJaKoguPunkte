import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

/**
 * Created by Sefert on 30.10.2016.
 */
public class Kuul extends Screen {
    Circle pullet = new Circle();
    int radius;
    double centerx, centery;

    public Kuul(double x, double y, int r) {
        this.centerx = x;
        this.centery = y;
        this.radius = r;
    }

    public void generate() {
        pullet.setRadius(radius);
        pullet.setCenterX(centerx);
        pullet.setCenterY(centery);
        pane.getChildren().addAll(pullet);
    }

    public void liigutaKuuli(int b) {
        pullet.setCenterY(pullet.getCenterY() + b);
    }

    public Bounds getBounds(){
        return pullet.getBoundsInLocal();
    }
    public void remove() {
        pane.getChildren().removeAll(pullet);
    }
}

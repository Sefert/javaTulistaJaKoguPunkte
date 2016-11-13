/**
 * Created by marko on 13/11/2016.
 */

import javafx.scene.Node;
import javafx.scene.shape.Circle;


public class Kera {
    Circle circle = new Circle();
    int radius;
    double centerx, centery;

    public Node kera(double x, double y, int r) {
        this.centerx = x;
        this.centery = y;
        this.radius = r;
        generate();
        return circle;
    }

    public void generate() {
        circle.setRadius(radius);
        circle.setCenterX(centerx);
        circle.setCenterY(centery);
    }

    public void liigutaKuuli(int b) {
        circle.setCenterY(circle.getCenterY() + b);
    }
}

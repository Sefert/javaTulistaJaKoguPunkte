/**
 * Created by marko on 13/11/2016.
 */

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Kera {
    Circle circle = new Circle();
    Circle circle2 = new Circle();
    int radius;
    double centerx, centery;
    Color color;
    Liigu liigu=new Liigu();

    public Node kera(double x, double y, int r,Color c,int stroke) {
        this.centerx = x;
        this.centery = y;
        this.radius = r;
        this.color = c;
        generate(stroke);
        return circle;
    }

    public void generate(int stroke) {
        circle.setRadius(radius);
        circle.setCenterX(centerx);
        circle.setCenterY(centery);
        if (stroke>0) {
            circle.setStroke(color);
            circle.setStrokeWidth(stroke);
            circle.setFill(Color.TRANSPARENT);
        } else
            circle.setFill(color);
    }

    public Node liigutaKuuli(double b) {
        circle.setCenterY(circle.getCenterY() + b);
        return circle;
    }
    public Node liiguXY(double x){
        circle.setCenterX(circle.getCenterX()+x);
        circle.setCenterY(liigu.diagonaal(circle.getCenterY())+x);
        return circle;
    }
    public Node negliiguXY(double x){
        circle.setCenterX(circle.getCenterX()-x);
        circle.setCenterY(liigu.diagonaal(circle.getCenterY())+x);
        return circle;
    }
}

/**
 * Created by marko on 13/11/2016.
 */

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Kera {
    Circle circle = new Circle();
    int radius;
    double centerx, centery, liiguy;
    Color color;
    Liigu liigu=new Liigu();

    public Node kera(double x, double y, int r,Color c) {
        this.centerx = x;
        this.centery = y;
        this.radius = r;
        this.color = c;
        generate();
        return circle;
    }

    public void generate() {
        circle.setRadius(radius);
        circle.setCenterX(centerx);
        circle.setCenterY(centery);
        circle.setFill(color);
    }

    public Node liigutaKuuli(int b) {
        circle.setCenterY(circle.getCenterY() + b);
        return circle;
    }
    public Node liiguXY(double x){
        circle.setCenterX(circle.getCenterX()+x);
        circle.setCenterY(liigu.diagonaal(circle.getCenterY())+x);
        return circle;
    }
    //public double[] liiguXY(double x){
    //    circle.setCenterX(circle.getCenterX()+x);
    //    circle.setCenterY(liigu.diagonaal(circle.getCenterY())+x);
    //   return new double[]{circle.getCenterX(),circle.getCenterY()};
    //}
    //public double[] liiguXY(double x){
      //  circle.setCenterX(circle.getCenterX()+x);
        //circle.setCenterY(liigu.parapool(circle.getCenterX()));
       //return new double[]{circle.getCenterX(),circle.getCenterY()};
    //}
}

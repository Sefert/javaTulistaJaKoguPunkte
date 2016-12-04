import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Created by Sefert on 31.10.2016.
 */
public class Kolmnurk {
    Polygon kolmnurk;

    public Node genKolmnurk(double tippx, double tippy, Color c){
        kolmnurk=new Polygon();
        kolmnurk.getPoints().addAll(
                tippx, tippy,
                tippx-30, tippy+50,
                tippx+30, tippy+50);
        kolmnurk.setFill(c);
        return kolmnurk;
    }

}

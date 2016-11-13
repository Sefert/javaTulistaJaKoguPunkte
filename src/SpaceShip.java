import javafx.scene.Node;
import javafx.scene.shape.Polygon;

/**
 * Created by Sefert on 31.10.2016.
 */
public class SpaceShip {
    Polygon laev;

    public Node genLaev(double tippx, double tippy){
        laev=new Polygon();
        laev.getPoints().addAll(
                tippx, tippy,
                tippx-30, tippy+50,
                tippx+30, tippy+50);
        return laev;
    }
}

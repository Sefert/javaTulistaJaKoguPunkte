import javafx.scene.shape.Polygon;

/**
 * Created by Sefert on 31.10.2016.
 */
public class SpaceShip extends Screen {
    Polygon laev = new Polygon();


    public void genLaev(double tippx,double tippy){
        pane.getChildren().add(laev);
        laev.getPoints().addAll(
                tippx, tippy,
                tippx-30, tippy+50,
                tippx+30, tippy+50);

    }
    public void remLaev(double tippx,double tippy){
        laev.getPoints().removeAll(
                tippx, tippy,
                tippx-30, tippy+50,
                tippx+30, tippy+50);
    }

}

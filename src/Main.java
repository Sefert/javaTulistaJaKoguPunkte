import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


/**
 * Created by Sefert on 20.10.2016.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        int x=800, y=800;

        Scene scene = new Scene(pane, x, y);
        primaryStage.setScene(scene);
        primaryStage.show();

        Polygon a=new Polygon();

        double tippx=400, tippy=500, vasakx=tippx+30, vasaky=tippy+50, paremx=tippx-30, paremy=tippy+50;
        pane.setOnMouseMoved(event -> {
            mouseMoved(event.getScreenX(),event.getSceneY());
        });
        //pane.setOnMouseMoved(event -> {
        //    double  tippx=event.getSceneX();
        //    double  tippy=event.getSceneY();
        //    double  vasakx=tippx+30, vasaky=tippy+50, paremx=tippx-30, paremy=tippy+50;
        //    double  oldvasakx=vasakx, oldvasaky=vasaky, oldparemx=paremx, oldparemy=paremy;
        //    a.getPoints().addAll(
        //            tippx, tippy,
        //            paremx, paremy,
        //            vasakx, vasaky);
        //    System.out.println(tippx+","+tippy);
        //});
        //Point p = MouseInfo.getPointerInfo().getLocation();
        //Circle r = new Circle(90);
        //a.setCenterX(100);
        //a.setCenterY(100);
        a.getPoints().addAll(
                tippx, tippy,
                paremx, paremy,
                vasakx, vasaky);
        pane.getChildren().add(a);
    }
    public double[] mouseMoved(double x,double y) {
        double[] kordinaat = new double[1];
        kordinaat[0]=x;
        kordinaat[1]=y;
        System.out.println(kordinaat[0]+","+kordinaat[1]);
        return kordinaat;
    }
    //public static final EventType<MouseEvent> MOUSE_MOVED

}

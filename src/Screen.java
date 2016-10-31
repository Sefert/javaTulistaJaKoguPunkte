import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Sefert on 30.10.2016.
 */
public class Screen {
    Pane pane = new Pane();
    Scene scene= new Scene(pane,800,800);
    Stage stage= new Stage();

    public Pane getPane() {
        return pane;
    }

    public void setStage(){
        stage.setScene(scene);
        stage.setTitle("Shooter");
        stage.show();
    }
}

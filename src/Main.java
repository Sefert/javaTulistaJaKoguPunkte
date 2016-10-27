import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Created by Sefert on 20.10.2016.
 */
public class Main extends Application {
    BorderPane borderPane;
    Scene scene;
    Pane pane;
    Stage stage;
    Circle kuul;
    double tippx=400, tippy=500;
    Timeline timeline;
    @Override
    public void start(Stage primaryStage) throws Exception {
        teeAken();
        loginBox();
    }

    public void loginBox(){
        VBox login=new VBox();
        login.setSpacing(5);
        login.setAlignment(Pos.CENTER);

        Label pealkiri = new Label("Kes sa oled selline?");

        TextField nameField = new TextField();
        nameField.setMaxWidth(200);

        Button sisesta = new Button("Sisesta");
        sisesta.setPrefSize(100,30);

        Button edetabel= new Button("Edetabel");
        edetabel.setPrefSize(100,30);

        login.getChildren().addAll(pealkiri,nameField,sisesta,edetabel);
        borderPane.setCenter(login);

        sisesta.setOnAction(event -> {
            String nimi=nameField.getText();
            stage.close();
            gameWindow();
            spaceShip();
            tulista();
        });

        edetabel.setOnAction(event -> {

        });
    }

    public void teeAken(){
        borderPane = new BorderPane();
        scene = new Scene(borderPane, 800, 800);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Lisa oma nimi highScore edetabeli jaoks!!!");
        stage.show();
    }
    public void gameWindow(){
        pane = new Pane();
        scene= new Scene(pane,800,800);
        stage= new Stage();
        stage.setScene(scene);
        stage.setTitle("Shooter");
        stage.show();
    }
    public void spaceShip(){
        Polygon laev = new Polygon();
        double vasakx=tippx+30, vasaky=tippy+50, paremx=tippx-30, paremy=tippy+50;
        laev.getPoints().addAll(
                tippx, tippy,
                paremx, paremy,
                vasakx, vasaky);
        pane.getChildren().add(laev);
        pane.setOnMouseMoved(event -> {
            laev.getPoints().removeAll(
                    tippx, tippy,
                    tippx-30, tippy+50,
                    tippx+30, tippy+50);
            tippx=event.getSceneX();
            tippy=event.getSceneY();
            laev.getPoints().addAll(
                    tippx, tippy,
                    tippx-30, tippy+50,
                    tippx+30, tippy+50);
        });
    }
    public  void tulista(){
        kuul = new Circle();
        kuul.setRadius(3);
        pane.setOnMousePressed(event -> {
            //System.out.println("KLICK");
            if (event.getButton() == MouseButton.PRIMARY) {
                pane.getChildren().remove(kuul);
                kuul.setCenterX(tippx);
                kuul.setCenterY(tippy);
                pane.getChildren().add(kuul);
                System.out.println("KLICK");
                //liigutaKuuli();
                timeline = new Timeline(new KeyFrame(
                        Duration.millis(30),
                        ae -> liigutaKuuli()));
                timeline.play();

            }
        });
    }
    public void liigutaKuuli(){
        System.out.println(tippy);

        if (tippx!=0){
            for(int i=0; i <= tippy ; i++) {
                System.out.println("Siin1");
                kuul.setCenterX(tippx);
                kuul.setCenterY(tippy - i);
            }
        } else {
            timeline.stop();
        }
    }
}

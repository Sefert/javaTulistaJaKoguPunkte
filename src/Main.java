import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
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
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


/**
 * Created by Sefert on 20.10.2016.
 */
public class Main extends Application {
    BorderPane borderPane;
    Scene scene;
    Pane pane;
    Stage stage;
    Circle kuulvastane;
    Circle kuul;
    double tippx=400, tippy=500;
    Timeline timeline;
    AnimationTimer animationTimer;
    Path path;
    PathTransition suund;
    Duration aeg;
    Random juhus;
    int score=0,b;
    boolean vastane=true, tulista;
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
            startGame();
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
    public void tulista(){
        kuul = new Circle();
        kuul.setRadius(3);
        pane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                System.out.println("KLICK");
                pane.getChildren().remove(kuul);
                kuul.setCenterX(tippx);
                kuul.setCenterY(tippy);
                pane.getChildren().add(kuul);
                tulista=true;
            }
        });
    }
    public void liigutaKuuli(int b){
        kuul.setCenterY(kuul.getCenterY()+b);
        if (tippy==0)
            tulista=false;
        collisionControll();
    }
    public int scoreCounter(int b){
        score += b;
        return score;
    }
    public void vastane(){
        vastane = false;
        kuulvastane =new Circle();
        kuulvastane.setRadius(40);
        juhus=new Random();
        kuulvastane.setCenterX(juhus.nextInt(500));
        kuulvastane.setCenterY(juhus.nextInt(500));
        pane.getChildren().add(kuulvastane);
    }
    private void startGame(){
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                //System.out.println("Siin2");
                if (vastane) {
                    vastane();
                }
                if (tulista) {
                    liigutaKuuli(-7);
                }
            }
        };
        animationTimer.start();
    }

    public void collisionControll(){
        if (kuulvastane.getBoundsInLocal().intersects(kuul.getBoundsInLocal())){
            pane.getChildren().removeAll(kuulvastane,kuul);
            int fullscore = scoreCounter(1);
            System.out.println(fullscore);
            vastane=true;
            tulista=false;
        }
    }
}

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by Sefert on 20.10.2016.
 */
public class Main extends Application {
    BorderPane borderPane;
    Scene scene;
    Stage stage;
    double tippx=400, tippy=500;
    AnimationTimer animationTimer;
    Random juhus;
    int score=0, clickcounter=0;
    boolean vastane=true, tulista;
    Kuul pullet, vpullet;
    Screen gamewindow;
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
            gamewindow= new Screen();
            gamewindow.setStage();
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
    public void spaceShip(){
        SpaceShip laev = new SpaceShip();
        laev.genLaev(tippx,tippy);
        gamewindow.getPane().setOnMouseMoved(event -> {
            laev.remLaev(tippx,tippy);
            tippx=event.getSceneX();
            System.out.println(tippx);
            tippy=event.getSceneY();
            laev.genLaev(tippx,tippy);
            System.out.println(tippy);
            System.out.println("SINNNNNNNNNNNNNNNN");
        });
    }
    public void tulista(){
        gamewindow.getPane().setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                pullet=new Kuul(tippx,tippy,3);
                clickcounter += 1;
                pullet.generate();
                System.out.println("KLICK");
                tulista=true;
            }
        });
    }
    public void liigutaKuuli(int b){
        pullet.liigutaKuuli(b);
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
        juhus=new Random();
        double a = juhus.nextInt(500);
        vpullet = new Kuul(a, a, 40);
        vpullet.generate();
    }
    private void startGame(){
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
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
        if (vpullet.getBounds().intersects(pullet.getBounds())){
            pullet.remove();
            vpullet.remove();
            int fullscore = scoreCounter(1);
            System.out.println(fullscore);
            vastane=true;
            tulista=false;
        }
    }
}

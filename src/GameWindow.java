import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created by marko on 13/11/2016.
 */
public class GameWindow {
    Scene scene;
    Stage stage;
    Pane pane;
    double tippx=400, tippy=500;
    int a=0, b=0, c=1;
    Node shape, ship, vshape;
    boolean vastane=true, tulista = false, alghetk =false, liiguvastane =false, lasevastane=false, kaimapanek=true;
    Kera circle, kuul;
    Random juhus;
    AnimationTimer animationTimer;
    int score=0, fullscore=0;
    long vAlghetkAeg, praegu, kaimapanekuAeg;
    ArrayList<Kera> valang= new ArrayList();
    ArrayList<Kera> vastased= new ArrayList();

    public GameWindow(){
        gameWindow();                                                       //mänguakna esile toomine
        spaceShip();                                                        //kosmoslaeva genereerimine ja kustutamine
        startGame();                                                        //animationtimeri käivitamine
        mousePressed();                                                     //kuuli genereerimine
    }
    public void gameWindow(){
        pane = new Pane();                                                  //pane väljatüüp
        pane.setStyle("-fx-background-color: transparent;");
        scene= new Scene(pane,800,800,Color.BLACK);                         //mõõtudega 800px X 800px
        stage= new Stage();
        //scene.setFill(Color.AQUA);
        //pane.setStyle("-fx-background: #FFFFFF;");
        stage.setScene(scene);
        stage.setTitle("Shooter - punktisumma on:  " + fullscore);
        stage.show();                                                       //kuva välja
    }
    public void spaceShip(){
        Kolmnurk laev = new Kolmnurk();                                     //uue Spaceship klassi defineerimine
        pane.setOnMouseMoved(event -> {                                     //hiire liigutuse peale sündmuse esile kutsumine
            pane.getChildren().removeAll(ship);                             //vana leava eemaldamine
            tippx = event.getSceneX();                                      //hiire x koordinaat
            tippy = event.getSceneY();                                      //hiire y koordinaat
            ship=laev.genKolmnurk(tippx, tippy, Color.WHITE);               //laeva seadistamine
            pane.getChildren().add(ship);                                   //laeva kuvamine
        });
    }
    public void genvastane(){
        juhus=new Random();
        for (int i=0;i<juhus.nextInt(400)+1;i++){
            circle= new Kera();
            vastased.add(i,circle);
        }
        vastane = false;                                                    //peale genereerimist ei või uuesti genereerida
    }
    public void valjastaVastane(int i){
        juhus=new Random();
        vshape = vastased.get(i).kera(juhus.nextInt(800)-400,-50,juhus.nextInt(60)+10,Color.RED);
        pane.getChildren().add(vshape);
        liiguvastane = true;

    }
    private void startGame(){
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                praegu=now;
                if (kaimapanek) {
                    kaimapanekuAeg = Math.round(now / 1_000_000_000);
                    kaimapanek=false;
                }
                if (vastane) {
                    genvastane();
                    lasevastane=true;
                }
                if (a != vastased.size()){
                    if (lasevastane){
                        valjastaVastane(a);
                        vAlghetkAeg=Math.round(now / 1_000_000_000);
                        lasevastane=false;
                    }
                    if (Math.round(now / 1_000_000_000)-vAlghetkAeg == 1){
                        a++;
                        lasevastane=true;
                    }
                } else {
                    a=0;
                    for (int i=0;i<vastased.size();i++) {
                        vshape = vastased.get(i).kera(circle.centerx,circle.centery,circle.radius,circle.color);
                        pane.getChildren().remove(vshape);
                    }
                    vastased.clear();
                    vastane=true;
                }
                if (tulista) {
                    if (alghetk){
                        tulista(b);
                        b++;
                        alghetk = false;
                    }
                }
                if (liiguvastane){
                    if (Math.round(now / 1_000_000_000)-kaimapanekuAeg==10) {
                        if (c<10) {
                            c++;
                            kaimapanekuAeg = Math.round(now / 1_000_000_000);
                        }
                    }
                    for (int i=0;i<vastased.size();i++){
                        vshape = vastased.get(i).liiguXY(c);
                        if (tulista)
                            for(int j=0;j<valang.size();j++) {
                                shape=valang.get(j).liigutaKuuli(-0.4);                    //kera liikumise esile kutsumine
                                pulletCollision(vshape,shape,i,j);                         //kokkupõrkekontroll
                                if (shape.intersects(0,-10,800,1)){
                                    valang.remove(j);
                                    b--;
                                }
                            }
                        try {
                            shipCollision(vshape);
                        }catch (NullPointerException e){
                            //System.out.println("error Nullpointer püütud");
                        }
                    }
                }
            }
        };
        animationTimer.start();
    }
    public void mousePressed(){
        pane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {             //premkliki defineerimine
                alghetk =true;
                tulista =true;
            }
        });
    }
    public void tulista(int b){
        kuul = new Kera();                                              //uue kera tüüpi elemendi defineerimine
        shape = kuul.kera(tippx,tippy,3,Color.ALICEBLUE);               //kuuli genereerimine
        valang.add(b,kuul);
        pane.getChildren().add(shape);                                  //kuuli kuvamine
    }
    public int scoreCounter(int b){
        score += b;                                                     //loendab punkte  võrra suurendades
        return score;                                                   //tagastab punktide väärtuse
    }
    public void pulletCollision(Node vshape, Node shape, int i, int j){
        if (vshape.getBoundsInLocal().intersects(shape.getBoundsInLocal())){
            vastased.remove(i);
            System.out.println(vastased.size());
            valang.remove(j);
            valang.removeIf(Objects::isNull);
            vastased.removeIf(Objects::isNull);
            pane.getChildren().removeAll(vshape,shape);
            fullscore = scoreCounter(100);
            stage.setTitle("Shooter - punktisumma on:  " + fullscore);
            b--;
            //a--;
        }
    }
    public void shipCollision(Node vshape){
            if (ship.getBoundsInLocal().intersects(vshape.getBoundsInLocal())) {
                pane.getChildren().removeAll(vshape, ship, shape);
                StackPane stack = new StackPane();
                stack.setStyle("-fx-background-color: transparent;");
                Label teade = new Label("Game Over");
                teade.setTextFill(Color.WHITE);
                teade.setFont(Font.font("Arial", 46));
                stack.getChildren().add(teade);
                scene.setRoot(stack);
                animationTimer.stop();
            }
        }
}

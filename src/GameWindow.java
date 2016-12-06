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
    int a=0, b=0;
    Node shape, ship, vshape;
    boolean vastane=true, tulista = false, alghetk =false, liiguvastane =false, lasevastane=false;
    Kera circle, kuul;
    Random juhus;
    AnimationTimer animationTimer;
    int score=0, fullscore=0;
    long vAlghetkAeg, praegu;
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
        for (int i=0;i<juhus.nextInt(200);i++){
            circle= new Kera();
            vastased.add(i,circle);
        }
        vastane = false;                                                    //peale genereerimist ei või uuesti genereerida
    }
    public void valjastaVastane(int i){
        vshape = vastased.get(i).kera(0,0,40,Color.RED);
        pane.getChildren().add(vshape);
        liiguvastane = true;
    }
    private void startGame(){
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                praegu=now;
                if (vastane) {
                    genvastane();
                    lasevastane=true;
                }
                //System.out.println(now);
                //System.out.println(now-vAlghetkAeg);
                //System.out.println(a);
                //System.out.println(vastased.size());
                //System.out.println(Math.round(now / 1_000_000_000));
                if (a != vastased.size()){
                    if (lasevastane){
                        valjastaVastane(a);
                        vAlghetkAeg=Math.round(now / 1_000_000_000);
                        //System.out.println(vAlghetkAeg);
                        lasevastane=false;

                    }
                    if (Math.round(now / 1_000_000_000)-vAlghetkAeg == 1){
                        a++;
                        lasevastane=true;
                        //System.out.println(a);
                    }
                } else {
                    //System.out.println(a);
                    a=0;
                    for (int i=0;i<vastased.size();i++) {
                        vshape = vastased.get(i).kera(0,0,40,Color.RED);
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
                    for (int i=0;i<vastased.size();i++){
                        vshape = vastased.get(i).liiguXY(1);
                        if (tulista)
                            for(int j=0;j<valang.size();j++) {
                                shape=valang.get(j).liigutaKuuli(-0.4);                    //kera liikumise esile kutsumine
                                pulletCollision(vshape,shape,i,j);                         //kokkupõrkekontroll
                                if (shape.intersects(0,-10,800,1)){
                                    //System.out.println("kohal");
                                    valang.remove(j);
                                    b--;
                                }
                            }
                        try {
                            shipCollision(vshape);
                        }catch (NullPointerException e){
                            //System.out.println("error Nullpointer püütud");
                        }

                        //if (x[0] >= 880 || x[1] >= 880) {
                        //    pane.getChildren().remove(vshape);
                        //}
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
                                                                        //väärtus, mis tõestab, et tegemist on kuuliga
    }
    public int scoreCounter(int b){
        score += b;                                                     //loendab punkte  võrra suurendades
        return score;                                                   //tagastab punktide väärtuse
    }
    public void pulletCollision(Node vshape, Node shape, int i, int j){
        if (vshape.getBoundsInLocal().intersects(shape.getBoundsInLocal())){
            vastased.remove(i);
            valang.remove(j);
            valang.removeIf(Objects::isNull);
            pane.getChildren().removeAll(vshape,shape);
            fullscore = scoreCounter(100);
            stage.setTitle("Shooter - punktisumma on:  " + fullscore);
            b--;
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

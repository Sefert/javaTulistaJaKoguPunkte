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
    Color laevavarv = Color.WHITE;
    Node shape, ship, vshape, vshape2, bar;
    boolean tulistamisealghetk =false, liiguvastane =false, lasevastane=true, kiiruseajamuut =true, barjaarialghetk=false, genbarjaar=false;
    Kera circle, kuul;
    Random juhus;
    AnimationTimer animationTimer;
    int score=0, fullscore=0;
    long vastaseAlgAeg, praegu, kiiruseAlgAeg, barjaariAlgAeg;
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
            ship=laev.genKolmnurk(tippx, tippy, laevavarv);               //laeva seadistamine
            pane.getChildren().add(ship);                                   //laeva kuvamine
            if (genbarjaar){
                Kera barjaar=new Kera();
                try {
                    pane.getChildren().removeAll(bar);
                } catch (NullPointerException e){

                }
                bar=barjaar.kera(tippx, tippy, 100, Color.GOLD,15);
                pane.getChildren().add(bar);
            }
        });

    }
    public void valjastaVastane(int i){
        circle= new Kera();
        juhus=new Random();
        vshape = circle.kera(juhus.nextInt(800)-400,-50,juhus.nextInt(60)+20,Color.RED,0);
        vastased.add(i,circle);
        pane.getChildren().add(vshape);
        liiguvastane = true;
    }
    private void startGame(){
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                praegu=now;
                if (kiiruseajamuut) {
                    kiiruseAlgAeg = Math.round(now / 1_000_000_000);
                    kiiruseajamuut =false;
                }
                if (barjaarialghetk) {
                    barjaariAlgAeg = Math.round(now / 1_000_000_000);
                    genbarjaar=true;
                    barjaarialghetk =false;
                }
                if (Math.round(now/1_000_000_000)-barjaariAlgAeg==10){
                    genbarjaar=false;
                    pane.getChildren().remove(bar);
                }

                if (score != 0 && score%3000==0) {
                    laevavarv=Color.BLUEVIOLET;
                }
                if (lasevastane){
                    valjastaVastane(a);
                    vastaseAlgAeg =Math.round(now / 1_000_000_000);
                    a++;
                    lasevastane=false;
                }
                if (Math.round(now / 1_000_000_000)- vastaseAlgAeg == 1){
                    lasevastane=true;
                }
                if (tulistamisealghetk){
                    tulista(b);
                    b++;
                    tulistamisealghetk = false;
                }
                if (liiguvastane){
                    if (Math.round(now / 1_000_000_000)- kiiruseAlgAeg ==30) {
                        if (c<10) {
                            c++;
                            kiiruseAlgAeg = Math.round(now / 1_000_000_000);
                        }
                    }
                    for (int i=0;i<vastased.size();i++){
                        vshape = vastased.get(i).liiguXY(c);
                        if (valang.size()>0)
                            for(int j=0;j<valang.size();j++) {
                                shape=valang.get(j).liigutaKuuli(-1);                    //kera liikumise esile kutsumine
                                pulletCollision(vshape,shape,i,j);                         //kokkupõrkekontroll
                                if (shape.intersects(0,-10,800,1)){
                                    valang.remove(j);
                                    b--;
                                }
                            }
                        if (vshape.intersects(0,810,3000,1)) {
                            pane.getChildren().remove(vshape);
                            a--;
                            vastased.remove(i);
                            System.out.println(vastased.size());
                        }
                        try {
                            shipCollision(vshape);
                            if (genbarjaar)
                                barjaarCollision(vshape,i);
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
                tulistamisealghetk =true;
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                if (laevavarv == Color.BLUEVIOLET) {
                    barjaarialghetk = true;
                    laevavarv = Color.WHITE;
                }
            }
        });
    }
    public void tulista(int b){
        kuul = new Kera();                                              //uue kera tüüpi elemendi defineerimine
        shape = kuul.kera(tippx,tippy,3,Color.ALICEBLUE,0);               //kuuli genereerimine
        valang.add(b,kuul);
        pane.getChildren().add(shape);                                  //kuuli kuvamine
    }
    public int scoreCounter(int b){
        score += b;                                                     //loendab punkte  võrra suurendades
        return score;                                                   //tagastab punktide väärtuse
    }
    public void pulletCollision(Node vshape, Node shape, int i, int j){
        if (vshape.getBoundsInLocal().intersects(shape.getBoundsInLocal())){
            if (vshape.getBoundsInLocal().getHeight()>70){
                Kera uuscircle=new Kera();
                double xmax = vshape.getBoundsInLocal().getMaxX();
                double xmin = vshape.getBoundsInLocal().getMinX();
                double ymax = vshape.getBoundsInLocal().getMaxY();
                double ymin = vshape.getBoundsInLocal().getMinY();
                int r = (int)vshape.getBoundsInLocal().getHeight();
                pane.getChildren().removeAll(vshape,shape);
                valang.remove(j);
                vastased.set(i,uuscircle);
                vshape2=uuscircle.kera((xmax-xmin)/2+xmin,(ymax-ymin)/2+ymin,r/2-20,Color.RED,0);
                pane.getChildren().add(vshape2);
                fullscore = scoreCounter(100);
                stage.setTitle("Shooter - punktisumma on:  " + fullscore);
                b--;
                System.out.println(vastased.size());
            } else {
                vastased.remove(i);
                System.out.println(vastased.size());
                valang.remove(j);
                valang.removeIf(Objects::isNull);
                vastased.removeIf(Objects::isNull);
                pane.getChildren().removeAll(vshape, shape);
                fullscore = scoreCounter(100);
                stage.setTitle("Shooter - punktisumma on:  " + fullscore);
                b--;
                a--;
            }
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
    public  void barjaarCollision(Node vshape, int i){
        if (bar.getBoundsInLocal().intersects(vshape.getBoundsInLocal())){
            pane.getChildren().remove(vshape);
            vastased.remove(i);
            fullscore = scoreCounter(50);
            stage.setTitle("Shooter - punktisumma on:  " + fullscore);
            a--;
        }
    }
}

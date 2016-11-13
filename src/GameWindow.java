import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Random;

/**
 * Created by marko on 13/11/2016.
 */
public class GameWindow {
    Scene scene;
    Stage stage;
    Pane pane;
    double tippx=400, tippy=500;
    Node shape, ship, vshape;
    boolean vastane=true, tulista=false;
    Kera circle, kuul;
    Random juhus;
    AnimationTimer animationTimer;
    int score=0, fullscore=0;

    public GameWindow(){
        gameWindow();                                                   //mänguakna esile toomine
        spaceShip();                                                    //kosmoslaeva genereerimine ja kustutamine
        startGame();                                                    //animationtimeri käivitamine
        tulista();                                                      //kuuli genereerimine
    }
    public void gameWindow(){
        pane = new Pane();                                              //pane väljatüüp
        scene= new Scene(pane,800,800);                                 //mõõtudega 800px X 800px
        stage= new Stage();
        stage.setScene(scene);
        stage.setTitle("Shooter - punktisumma on:  " + fullscore);
        stage.show();                                                   //kuva välja
    }
    public void spaceShip(){
        SpaceShip laev = new SpaceShip();                               //uue Spaceship klassi defineerimine

        pane.setOnMouseMoved(event -> {                                 //hiire liigutuse peale sündmuse esile kutsumine
            pane.getChildren().removeAll(ship);                         //vana leava eemaldamine
            tippx = event.getSceneX();                                  //hiire x koordinaat
            tippy = event.getSceneY();                                  //hiire y koordinaat
            ship=laev.genLaev(tippx, tippy);                            //laeva seadistamine
            pane.getChildren().add(ship);                               //laeva kuvamine
        });
    }
    public void vastane(){
        circle= new Kera();                                             //uue kerakujundi/vastase defineerimine
        vastane = false;                                                //peale genereerimist ei või uuesti genereerida
        juhus=new Random();                                             //juhuslik koordinaat
        vshape = circle.kera(juhus.nextInt(500),juhus.nextInt(500),40); //kera genereerimine
        pane.getChildren().add(vshape);                                 //kera kuvamine
    }
    private void startGame(){
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if (vastane) {
                    vastane();                                  //vastase genereerimine automaatselt, kui eelmine on hävitatud
                }
                if (tulista) {                                  //kui on tõene võib liigutada kuuli
                    kuul.liigutaKuuli(-7);                      //kera liikumise esile kutsumine
                    collisionControll();                        //kokkupõrkekontroll
                }
            }
        };
        animationTimer.start();
    }
    public void tulista(){
        pane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {     //premkliki defineerimine
                //System.out.println("KLICK");
                kuul = new Kera();                              //uue kera tüüpi elemendi defineerimine
                pane.getChildren().removeAll(shape);            //vana kera/kuuli eeemaldamine
                shape = kuul.kera(tippx,tippy,3);               //kuuli genereerimine
                pane.getChildren().add(shape);                  //kuuli kuvamine
                tulista=true;                                   //väärtus, mis tõestab, et tegemist on kuuliga
            }
        });
    }
    public int scoreCounter(int b){
        score += b;                                             //loendab punkte  võrra suurendades
        return score;                                           //tagastab punktide väärtuse
    }
    public void collisionControll(){
        if (vshape.getBoundsInLocal().intersects(shape.getBoundsInLocal())){
            pane.getChildren().removeAll(vshape,shape);
            fullscore = scoreCounter(1);
            stage.setTitle("Shooter - punktisumma on:  " + fullscore);
            vastane=true;
            tulista=false;
        }
    }
}

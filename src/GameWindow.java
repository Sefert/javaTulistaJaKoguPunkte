import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    double tippx=400, tippy=500,p=10,f=-1.5;
    int a=0, b=0, c=1;
    String name;
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
    ArrayList<Kera> vastased2= new ArrayList();

    public GameWindow(String nimi){
        gameWindow();                                                       //mänguakna esile toomine
        spaceShip();                                                        //kosmoslaeva genereerimine ja kustutamine
        startGame();                                                        //animationtimeri käivitamine
        mousePressed();                                                     //kuuli genereerimine
        name=nimi;
    }
    public void gameWindow(){
        pane = new Pane();                                                  //pane väljatüüp
        pane.setStyle("-fx-background-color: transparent;");
        scene= new Scene(pane,800,800,Color.BLACK);                         //mõõtudega 800px X 800px
        stage= new Stage();
        stage.setScene(scene);
        stage.setTitle("Shooter - punktisumma on:  " + fullscore);          //punktisumma kuvamine
        stage.show();                                                       //kuva välja
    }
    public void spaceShip(){                                                //barjääri ja laeva genereerimine hiire liigutamisel
        Kolmnurk laev = new Kolmnurk();                                     //uue Spaceship klassi defineerimine
        pane.setOnMouseMoved(event -> {                                     //hiire liigutuse peale sündmuse esile kutsumine
            pane.getChildren().removeAll(ship);                             //vana leava eemaldamine
            tippx = event.getSceneX();                                      //hiire x koordinaat
            tippy = event.getSceneY();                                      //hiire y koordinaat
            ship=laev.genKolmnurk(tippx, tippy, laevavarv);                 //laeva seadistamine
            pane.getChildren().add(ship);                                   //laeva kuvamine
            ship.setEffect(new Glow(0.3));
            if (genbarjaar){                                                //barjääri olemasolu kontroll
                Kera barjaar=new Kera();                                    //barjääri ringina defineerimine
                try {
                    pane.getChildren().removeAll(bar);                      //barjääri eemaldamine
                } catch (NullPointerException e){                           //püütakse kinni, kui pole barjääri, mida eemmaldada

                }
                bar=barjaar.kera(tippx, tippy, 100, Color.GOLD,15);         //barjäär(xkoord,ykoord,raadius,värvus,välisjoone paksus)
                bar.setEffect(new Glow(1));
                pane.getChildren().add(bar);                                //lisa barjäär
            }
        });

    }
    public void valjastaVastane(int i){                                     //vastase lisamine arraysse
        circle= new Kera();
        juhus=new Random();
        if (vastased.size()%3==0)
            vshape=circle.kera(juhus.nextInt(1100)-700,-50,juhus.nextInt(60)+20,Color.ORCHID,0);
        else
            vshape = circle.kera(juhus.nextInt(1100)-700,-50,juhus.nextInt(60)+20,Color.CORAL,0);
        vastased.add(i,circle);                                             //vastase lisamine arraysse(kohale i,vastane
        vshape.setEffect(new Glow(0.8));
        vshape.getStyleClass().add("kera");
        pane.getChildren().add(vshape);                                     //vastase lisamine ekraanile
        liiguvastane = true;                                                //vastase liigutamine muudetakse tõeseks
    }
    public void genvastane2(){
        juhus=new Random();
        for (int i=0;i<juhus.nextInt(20)+1;i++){
            circle= new Kera();
            vastased2.add(i,circle);
        }
    }
    public void valjastaVastane2(){
        juhus=new Random();
        for (int i=0;i<juhus.nextInt(20)+1;i++){
            vshape=circle.kera(juhus.nextInt(800)+500,-50,juhus.nextInt(60)+20,Color.ORCHID,0);
        }
        pane.getChildren().add(vshape);
        liiguvastane = true;
    }
    public void timeController(){                                           //erinevate liikumissammude muut
        c++;                                                                //vasatse liikumise sammu suurendamine
        System.out.println("Kiirus on "+ c);
        f-=0.5;                                                             //kuuli kiiruse muut
        System.out.println("Kuuli kiirus on "+ f);
    }
    private void startGame(){                                               //erinevate aegade registreerimine ja objektide liigutamine ajas
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                praegu=now;                                                 //globaalne ajahetk
                if (kiiruseajamuut) {
                    kiiruseAlgAeg = Math.round(now / 1_000_000_000);        //algaeg mängukiiruse muutmiseks
                    kiiruseajamuut =false;
                }
                if (barjaarialghetk) {
                    barjaariAlgAeg = Math.round(now / 1_000_000_000);       //barjääri alghetke salvestamine
                    genbarjaar=true;
                    barjaarialghetk =false;
                }
                if (Math.round(now/1_000_000_000)-barjaariAlgAeg==10){      //10 sekundi möödudes eemaldatakse barjäär
                    genbarjaar=false;
                    pane.getChildren().remove(bar);
                }

                if (score != 0 && score%5000==0) {                          //iga 5000 punkti tagant muudetakse laeva värvi
                    laevavarv=Color.BLUEVIOLET;
                }
                if (lasevastane){                                           //kui tõene väljastatakse vastane
                    valjastaVastane(a);                                     //vastane väljastatakse
                    vastaseAlgAeg =Math.round(now / 100_000_000);           //salvestatakse vastase algaeg
                    a++;                                                    //suurendatakse juba järgmist vastase salvestamise positsiooni arrays
                    lasevastane=false;                                      //vastase väljastamine lõpetatakse pidevalt jooksvas tsükklis
                }
                if (c<3) {                                                      //kui vastase samm on
                    if (Math.round(now / 100_000_000) - vastaseAlgAeg == p) {   //kontrollitakse, kas praeguse aja ja algusaja vahe on 1 sekund
                        lasevastane = true;                                     //tähendab, et iga 1 sekundi järel väljastatkse vastane
                    }
                } else if (c==3 || c==4){
                    if (Math.round(now / 100_000_000)- vastaseAlgAeg == 8 || Math.round(now / 100_000_000)- vastaseAlgAeg == 7){
                        lasevastane=true;
                    } else if (Math.round(now / 100_000_000) - vastaseAlgAeg == p) {
                        lasevastane = true;
                    }
                } else if (c>4){
                   if (Math.round(now / 100_000_000)- vastaseAlgAeg == 6 || Math.round(now / 100_000_000)- vastaseAlgAeg == 5){
                        lasevastane=true;
                    }else if (Math.round(now / 100_000_000) - vastaseAlgAeg == p) {
                        lasevastane = true;
                    }
                }
                if (tulistamisealghetk){
                    tulista(b);                                                         //kui tõene kutsutakse esile tulistamine arrays kohal b
                    b++;
                    tulistamisealghetk = false;
                }
                //if (liiguvastane){                                                      //kui vastane eksisteerib pildil
                    if (Math.round(now / 1_000_000_000)- kiiruseAlgAeg == 30) {         //iga 30 sekundi tagant muudetakse vastase sammu
                        if (c<6) {                                                      //vastase samm ei muutu üle 6 ühiku
                            timeController();                                           //globaalse sammumuutuse esile kutsumine
                            kiiruseAlgAeg = Math.round(now / 1_000_000_000);            //salvestatakse kiiruse muutmiseks vaja minev algaeg, mille suhtes kiirust muudetakse
                        }
                    }
                    for (int i=0;i<vastased.size();i++){                                //käiakse läbi kõik vastased arrays
                        vshape = vastased.get(i).liiguXY(c);                            //kutsutakse välja vastase liikumine vastava kujundi klassis
                        if (valang.size()>0)                                            //kui eksisteerib tulistamine
                            for(int j=0;j<valang.size();j++) {                          //käiakse läbi kõik kuulid arrays
                                shape=valang.get(j).liigutaKuuli(f);                    //kera liikumise esile kutsumine
                                pulletCollision(vshape,shape,i,j);                      //kuuli kokkupõrkekontroll
                                if (shape.intersects(0,-10,800,5)){                     //kuuli kontroll kujutletava piiriga allpool nähtavat ekraani
                                    valang.remove(j);                                   //sellisel juhul kuuli eemladamine
                                    b--;                                                //array suuruse vähendamine 1 võõra, mida kontrollitakse loobis
                                }
                            }
                        if (vshape.intersects(0,810,3000,5)) {                          //kui kuul põrkub nähtamatu elemntiga üleval ekraani ääres
                            pane.getChildren().remove(vshape);                          //eemaldatakse kuul
                            a--;
                            vastased.remove(i);
                            System.out.println(vastased.size());
                        }
                        try {
                            shipCollision(vshape);                                      //kosmoslaeva kokkupõrke kontroll vaastasega
                            if (genbarjaar)
                                barjaarCollision(vshape,i);                             //vastase kokkupõrge barjääriga
                        }catch (NullPointerException e){                                //püütakse kinni viga, kus laev ,vastane või barjäär on null
                            //System.out.println("error Nullpointer püütud");
                        }
                    }
                }
            //}
        };
        animationTimer.start();                                                         //käivitatakse ajaliselt jooksev süsteem
    }
    public void mousePressed(){
        pane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {                             //premkliki defineerimine
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
        kuul = new Kera();                                                              //uue kera tüüpi elemendi defineerimine
        shape = kuul.kera(tippx,tippy,3,Color.ALICEBLUE,0);                             //kuuli genereerimine
        valang.add(b,kuul);
        pane.getChildren().add(shape);                                                  //kuuli kuvamine
    }
    public int scoreCounter(int b){
        score += b;                                                                     //loendab punkte  võrra suurendades
        return score;                                                                   //tagastab punktide väärtuse
    }
    public void pulletCollision(Node vshape, Node shape, int i, int j){                 //kuuli kokkupõrge vastasega
        if (vshape.getBoundsInLocal().intersects(shape.getBoundsInLocal())){
            if (vshape.getBoundsInLocal().getHeight()>70){                              //kui vastase kõrgus on suurem kui 70
                Kera uuscircle=new Kera();                                              //tehakse uus vastane ja aesetatakse väiksemana järgnevalt fikseeritud positsioonile
                double xmax = vshape.getBoundsInLocal().getMaxX();
                double xmin = vshape.getBoundsInLocal().getMinX();
                double ymax = vshape.getBoundsInLocal().getMaxY();
                double ymin = vshape.getBoundsInLocal().getMinY();
                int r = (int)vshape.getBoundsInLocal().getHeight();
                Color c = circle.color;
                pane.getChildren().removeAll(vshape,shape);
                valang.remove(j);
                vastased.set(i,uuscircle);
                if (c==Color.ORCHID) {
                    vshape2 = uuscircle.kera((xmax - xmin) / 2 + xmin, (ymax - ymin) / 2 + ymin, r / 2 - 20, Color.ORCHID, 0);
                    vshape2.setEffect(new Glow(0.8));
                }
                else {
                    vshape2 = uuscircle.kera((xmax - xmin) / 2 + xmin, (ymax - ymin) / 2 + ymin, r / 2 - 20, Color.CORAL, 0);
                    vshape2.setEffect(new Glow(0.8));
                }
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
    public void shipCollision(Node vshape){                                     //laeva kokkupõrge vastasega
        if (ship.getBoundsInLocal().intersects(vshape.getBoundsInLocal())) {
            pane.getChildren().removeAll(vshape, ship, shape);
            stage.close();
            animationTimer.stop();
            SQLiteAndmed baas=new SQLiteAndmed();
            baas.uhenda();
            baas.lisaKasutaja(name, fullscore);
            Login login=new Login();
            login.gameOver();
        }
    }
    public  void barjaarCollision(Node vshape, int i){                          //barjääri kokkupõrge vastasega
        if (bar.getBoundsInLocal().intersects(vshape.getBoundsInLocal())){
            pane.getChildren().remove(vshape);
            vastased.remove(i);
            fullscore = scoreCounter(100);
            stage.setTitle("Shooter - punktisumma on:  " + fullscore);
            a--;
        }
    }
}

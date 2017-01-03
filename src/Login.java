import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by marko on 13/11/2016.
 */
public class Login {
    VBox login;
    BorderPane borderPane;
    Scene scene;
    Stage stage;
    Label pealkiri, markus;
    TextField nameField;
    Button sisesta, edetabel, mutebutton;
    Boolean mute;
    SQLiteAndmed baas=new SQLiteAndmed();
    TableView table;
    String ssound = "src/sound/Sci-Fi-Open_Looping.mp3";
    MediaPlayer mediaPlayer;
    File f = new File("src/button.css");
    String fileURI = f.toURI().toString();

    public Login(boolean mutesound){
        mute=mutesound;
        teeAken();
        loginBox();
    }

    public void teeAken(){
        if (mute) {
            mplayer();
        }
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: transparent;");
        borderPane.getStyleClass().add("borderPane");
        scene = new Scene(borderPane, 800, 800);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(fileURI);
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Lisa oma nimi highScore edetabeli jaoks!!!");
        stage.show();
    }
    public void loginBox(){
        login=new VBox();
        login.setSpacing(10);
        login.setPadding(new Insets(0, 0, 0, 400));
        login.setAlignment(Pos.CENTER_LEFT);

        pealkiri = new Label("Enter your Name");
        pealkiri.getStyleClass().add("kiri");

        nameField = new TextField();
        nameField.setMaxWidth(200);
        nameField.getStyleClass().add("lisa");

        sisesta = new Button("Sisesta");
        sisesta.setPrefSize(100,30);
        sisesta.setTextFill(Color.BLACK);
        sisesta.getStyleClass().add("lisa");

        edetabel= new Button("Edetabel");
        edetabel.setPrefSize(100,30);
        edetabel.getStyleClass().add("lisa");

        mutebutton = new Button("Mute");
        mutebutton.setPrefSize(100,30);
        mutebutton.getStyleClass().add("lisa");
        if (mute==false){
            mutebutton.setTextFill(Color.RED);
        }

        login.getChildren().addAll(pealkiri,nameField,sisesta,edetabel, mutebutton);
        borderPane.setCenter(login);

        sisesta.setOnAction(event -> {
            actionEvent();
        });

        edetabel.setOnAction(event -> {
            baas.persoonid.clear();
            baas.loeAndmed();
            login.getChildren().removeAll(table);
            vaataBaasi();
        });
        mutebutton.setOnAction(event -> {
            if (mute == true){
                mediaPlayer.stop();
                mutebutton.setTextFill(Color.RED);
                mute=false;
            } else {
                mplayer();
                mutebutton.setTextFill(Color.BLACK);
                mute=true;
            }
        });
    }
    public void actionEvent(){
        String nimi=nameField.getText();
        if (nimi.length()< 5 || nimi.isEmpty()) {
            login.getChildren().remove(markus);
            markus = new Label("Nime sisestamine on vajalik ja peab olema vähemalt 5 tähemärki!!!!");
            markus.setTextFill(Color.RED);
            login.getChildren().add(markus);

        }
        else {
            System.out.println(nimi);
            stage.close();
            if (mute)
                mediaPlayer.stop();
            new GameWindow(nimi,mute);
        }
    }
    public void vaataBaasi(){
        table = new TableView();
        table.setMaxWidth(400);
        table.getStyleClass().add("lisa");

        stage.setTitle("Parim punktisumma");
        stage.centerOnScreen();
        stage.setResizable(false);

        Label label = new Label("HighScore");
        label.setFont(Font.font("Arial", 20));
        table.setEditable(false);
        table.setId("my-table");

        TableColumn col1 = new TableColumn("Skoor");
        col1.setMinWidth(200);
        col1.getStyleClass().add("list");
        col1.setCellValueFactory(new PropertyValueFactory<>("Skoor"));

        TableColumn col2 = new TableColumn("Nimi");
        col2.setMinWidth(200);
        col2.getStyleClass().add("list");
        col2.setCellValueFactory(new PropertyValueFactory<>("Nimi"));

        table.setItems(baas.persoonid);
        table.getColumns().addAll(col1,col2);

        login.getChildren().addAll(table);
    }
    public void gameOver(){
        loginBox();
        scene.setCursor(Cursor.DEFAULT);
        Label teade = new Label("Game Over");
        Label soundlicence = new Label("Music (and/or Sound Effects) by Eric Matyas\n" +
                "www.soundimage.org");
        Label projektlicence = new Label("Projekt by Marko Mõznikov");
        projektlicence.setTextFill(Color.WHITE);
        soundlicence.setTextFill(Color.WHITE);
        teade.setTextFill(Color.WHITE);
        teade.setFont(Font.font("Arial", 50));
        login.getChildren().addAll(teade,projektlicence,soundlicence);
    }
    public void mplayer(){
        try {
            Media sound = new Media(new File(ssound).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }catch (Exception e){
            System.out.println("Linuks vist mussi niisama ei toeta");
        }
    }
}

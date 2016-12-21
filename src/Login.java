import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    Button sisesta, edetabel;
    SQLiteAndmed baas=new SQLiteAndmed();
    TableView table;
    File f = new File("src/button.css");
    String fileURI = f.toURI().toString();

    public Login(){
        teeAken();
        loginBox();
    }

    public void teeAken(){
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: transparent;");
        scene = new Scene(borderPane, 800, 800,Color.AZURE);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(fileURI);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Lisa oma nimi highScore edetabeli jaoks!!!");
        stage.show();
    }
    public void loginBox(){

        login=new VBox();
        login.setSpacing(5);
        login.setAlignment(Pos.CENTER);

        pealkiri = new Label("WHO are YOU?");
        pealkiri.setFont(Font.font("Arial", 26));
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

        login.getChildren().addAll(pealkiri,nameField,sisesta,edetabel);
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
            new GameWindow(nimi);
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
        Label teade = new Label("Game Over");
        pealkiri = new Label("PROOVI UUESTI!!!");
        pealkiri.setTextFill(Color.WHITE);
        scene.setFill(Color.BLACK);
        teade.setTextFill(Color.WHITE);
        teade.setFont(Font.font("Arial", 100));
        login.getChildren().add(teade);
    }
}

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by marko on 13/11/2016.
 */
public class Login {
    VBox login;
    BorderPane borderPane;
    Scene scene;
    Stage stage;
    Label pealkiri;
    TextField nameField;
    Button sisesta, edetabel;

    public Login(){
        teeAken();
        loginBox();
    }

    public void teeAken(){
        borderPane = new BorderPane();
        scene = new Scene(borderPane, 800, 800);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Lisa oma nimi highScore edetabeli jaoks!!!");
        stage.show();
    }
    public void loginBox(){
        login=new VBox();
        login.setSpacing(5);
        login.setAlignment(Pos.CENTER);

        pealkiri = new Label("Kes sa oled selline?");

        nameField = new TextField();
        nameField.setMaxWidth(200);

        sisesta = new Button("Sisesta");
        sisesta.setPrefSize(100,30);

        edetabel= new Button("Edetabel");
        edetabel.setPrefSize(100,30);

        login.getChildren().addAll(pealkiri,nameField,sisesta,edetabel);
        borderPane.setCenter(login);

        sisesta.setOnAction(event -> {
            actionEvent();
        });

        edetabel.setOnAction(event -> {

        });
    }
    public void actionEvent(){
        String nimi=nameField.getText();
        stage.close();
        new GameWindow();
    }
}

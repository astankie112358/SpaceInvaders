import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;


public class Menu {
    private Pane pane;
    private Scene scene;
    private int width, height;
    private SceneControler sceneControler;
    Menu(SceneControler sceneControler) {
        width = 800;
        height = 600;
        this.sceneControler = sceneControler;
        Text textField = new Text();
        textField.setText("Najeźdźcy z kosmosu");
        textField.setX(200);
        textField.setY(100);
        textField.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        textField.setStroke(Color.ORANGE);
        Button button=new Button("Start");
        button.setTranslateX(300);
        button.setTranslateY(200);
        button.setOnAction(event ->
        {
            Level level =new Level(this.sceneControler,1);
            sceneControler.getStage().setScene(level.getScene());
        });
        Button button1=new Button("Twórca");
        button1.setTranslateX(300);
        button1.setTranslateY(300);
        button1.setOnAction(event ->
        {
            Credits credits=new Credits(this.sceneControler);
            sceneControler.getStage().setScene(credits.getScene());
        });
        Button button2=new Button("Wyjscie");
        button2.setTranslateX(300);
        button2.setTranslateY(400);
        button2.setOnAction(event ->
        {
            sceneControler.getStage().close();
        });

        try {
            FileInputStream inputStream = new FileInputStream("bg1.png");
            Image background = new Image(inputStream);
            BackgroundImage backgroundImage = new BackgroundImage(background, null, null, null, null);
            pane = new Pane(textField);
            pane.setBackground(new Background(backgroundImage));
            pane.getChildren().addAll(button,button1,button2);

            scene = new Scene(pane, width, height);
        }catch (Exception ex){;}
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

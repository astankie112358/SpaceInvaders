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
import javafx.stage.Stage;

import java.io.FileInputStream;

public class NextLvl {
    private Pane pane;
    private Scene scene;
    private Stage stage;
    private int width, height;
    private SceneControler sceneControler;
    private int lvl;
    NextLvl(SceneControler sceneControler,int lvl)
    {
        width = 800;
        height = 600;
        this.sceneControler = sceneControler;
        Text textField = new Text();
        this.lvl=lvl;
        textField.setText("UDALO CI SIE\nPrzechodzisz na poziom "+this.lvl);
        textField.setX(200);
        textField.setY(100);

        textField.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        textField.setStroke(Color.ORANGE);
        Button button=new Button("Nastepny poziom");
        button.setTranslateX(300);
        button.setTranslateY(200);
        button.setOnAction(event ->
        {
            Level level =new Level(this.sceneControler,this.lvl);
            sceneControler.getStage().setScene(level.getScene());
        });
        Button button1=new Button("Powtórz poziom");
        button1.setTranslateX(300);
        button1.setTranslateY(400);
        button1.setOnAction(event ->
        {
            Level level =new Level(this.sceneControler,this.lvl-1);
            sceneControler.getStage().setScene(level.getScene());
        });

        Button button2=new Button("Wyjsście");
        button2.setTranslateX(300);
        button2.setTranslateY(600);
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
            pane.getChildren().addAll(button,button1);

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

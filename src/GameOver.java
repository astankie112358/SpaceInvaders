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

public class GameOver {
    private Pane pane;
    private Scene scene;
    private int width, height;
    private SceneControler sceneControler;
    private int lvl;






    public GameOver(SceneControler sceneControler,int lvl) {
        width=800;
        height=600;
        this.lvl=lvl;
        this.sceneControler=sceneControler;
        Button button=new Button("Sprobujesz jeszcze raz?");
        button.setTranslateX(300);
        button.setTranslateY(200);
        button.setOnAction(event ->
        {
            Level level =new Level(this.sceneControler,lvl);
            sceneControler.getStage().setScene(level.getScene());
        });
        Button button1=new Button("Wyjście");
        button1.setTranslateX(500);
        button1.setTranslateY(200);
        button1.setOnAction(event ->
        {
            this.sceneControler.getStage().close();
        });
        Text textField=new Text();
        textField.setText("Game over");
        textField.setX(200);
        textField.setY(100);
        textField.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        textField.setStroke(Color.RED);

        try {
            FileInputStream inputStream = new FileInputStream("bg1.png");
            Image background= new Image(inputStream);
            BackgroundImage backgroundImage=new BackgroundImage(background,null,null,null,null);
            pane=new Pane(textField);
            pane.setBackground(new Background(backgroundImage));

            pane.getChildren().addAll(button,button1);

            scene=new Scene(pane,width,height);
        }catch (Exception ex){;}




    }
    public Scene getScene() {
        return scene;
    }
    public SceneControler getSceneControler() {
        return sceneControler;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

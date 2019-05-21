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

public class EndGame {
        private Pane pane;
        private Scene scene;
        private Stage stage;
        private int width, height;
        private SceneControler sceneControler;

        EndGame(SceneControler sceneControler)
        {
            width = 800;
            height = 600;
            this.sceneControler = sceneControler;
            Text textField = new Text();
            textField.setText("UDALO CI SIE OBRONIĆ ZIEMIĘ\nDzięki za grę");
            textField.setX(50);
            textField.setY(100);
            textField.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
            textField.setStroke(Color.ORANGE);


            Button button1=new Button("Wyjście do menu");
            button1.setTranslateX(300);
            button1.setTranslateY(250);
            button1.setOnAction(event ->
            {
                Menu menu=new Menu(this.sceneControler);
                sceneControler.getStage().setScene(menu.getScene());
            });
            Button button=new Button("Wyjście");
            button.setTranslateX(300);
            button.setTranslateY(400);
            button.setOnAction(event ->
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

                scene=new Scene(pane,width,height);
            }catch (Exception ex){;}
        }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

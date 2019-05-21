import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;
    Scene menu,opcje;

    @Override
    public void start(Stage primaryStage) throws Exception {

        SceneControler sceneControler=new SceneControler();
        Menu menu =new Menu(sceneControler);



        sceneControler.getStage().setScene(menu.getScene());
        primaryStage=sceneControler.getStage();


        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
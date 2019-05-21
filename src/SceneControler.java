import javafx.stage.Stage;

public class SceneControler {
    private Stage stage;
    SceneControler()
    {
        this.stage=new Stage();


    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

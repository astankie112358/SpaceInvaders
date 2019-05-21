import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Enemy implements Ship{
    private ImageView enemy;
    boolean dead;
    private Level level;
    public int row;
    public int numberinrow;

    public void setDead(boolean dead) {
        this.dead = dead;
    }
    Enemy(Level level, int row, int numberinrow)
    {
        this.row=row;
        this.numberinrow=numberinrow;
        this.dead=false;
        this.level = level;
        try {
            FileInputStream fileInputStream = new FileInputStream("enemy1.png");
            Image player1 = new Image(fileInputStream);
            this.enemy = new ImageView(player1);
            enemy.setFitHeight(player1.getHeight());
            enemy.setFitWidth(player1.getWidth());
        } catch (Exception ex){;}
    }

    public ImageView getPlayer() {
        return enemy;
    }
}

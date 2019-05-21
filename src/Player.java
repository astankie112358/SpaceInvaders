

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player implements Ship {
    private ImageView player;
    boolean dead;
    private Level level;


    public void setDead(boolean dead) {
        this.dead = dead;
    }

    Player(Level level)
    {
        try {

            this.dead=false;
            this.level = level;
            FileInputStream fileInputStream = new FileInputStream("moj2.png");
            Image player1=new Image(fileInputStream);
            this.player=new ImageView(player1);
            player.setFitHeight(player1.getHeight());
            player.setFitWidth(player1.getWidth());


        }catch (Exception ex){;}

    }



    void moveLeft()
    {
        if(this.getPlayer().getTranslateX()+(this.getPlayer().getFitWidth())-5>(this.getPlayer().getFitWidth()))
        this.getPlayer().setTranslateX(this.getPlayer().getTranslateX()-5);
    }
    void moveRight()
    {
        if(this.getPlayer().getTranslateX()+(this.getPlayer().getFitWidth())+5<800)
        this.getPlayer().setTranslateX(this.getPlayer().getTranslateX()+5);
    }


    public ImageView getPlayer() {
        return player;
    }
}

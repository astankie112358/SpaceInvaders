import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Bullet extends Rectangle {
    private Level level;
    private Player player;
    public boolean dead;
    public Ship ship;
    //Color color;


Bullet(Level level, Ship ship, Color color)
{
    super(10,10,color);
    this.ship=ship;
    this.level = level;
    dead=false;
    if(this.ship.getClass().getName()=="Player") {
        this.setTranslateX(ship.getPlayer().getTranslateX() + ship.getPlayer().getFitWidth() / 2);
        this.setTranslateY(ship.getPlayer().getTranslateY());
    }
    else
    {
        this.setTranslateX(ship.getPlayer().getTranslateX() + ship.getPlayer().getFitWidth() / 2);
        this.setTranslateY(ship.getPlayer().getTranslateY()+ship.getPlayer().getFitHeight());
    }



}
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    void moveUp()
    {
          this.setTranslateY(this.getTranslateY()-5);
    }
    void moveDown()
    {
        this.setTranslateY(this.getTranslateY()+5);
    }
}

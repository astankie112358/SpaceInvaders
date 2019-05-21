import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Level {
    private int width, height;
    private Pane pane;
    private Scene scene;
    private Player player;
    private double t;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private SceneControler sceneControler;
    private int lvl;


    private AnimationTimer timer;
    Level(SceneControler sceneControler, int lvl)
    {
        t=0;
        width=800;
        height=600;
        pane=new Pane();
        Random random=new Random();
        int rand;
        this.lvl=lvl;
    this.sceneControler=sceneControler;
        try {
            bullets=new ArrayList<Bullet>();
            enemies=new ArrayList<>();
            this.timer=new AnimationTimer() {
                @Override
                public void handle(long now) {
                    update();
                }
            };

            FileInputStream inputStream=new FileInputStream("bg1.png");
            Image background= new Image(inputStream);
            BackgroundImage backgroundImage=new BackgroundImage(background,null,null,null,null);
            pane.setBackground(new Background(backgroundImage));
            player=new Player(this);
            player.getPlayer().setTranslateX(width/2);
            player.getPlayer().setTranslateY(height-player.getPlayer().getFitHeight());
            pane.getChildren().add(player.getPlayer());

            for(int i=0; i<12; i++) {
                rand=random.nextInt(100);
                if(rand<lvl*25) {
                    Enemy enemy = new Enemy(this, 2, i);
                    enemy.getPlayer().setTranslateX(20 + i * 64);
                    pane.getChildren().add(enemy.getPlayer());
                    enemies.add(enemy);
                }

           }
            for(int i=0; i<12; i++) {
                rand=random.nextInt(100);
                if(rand<lvl*25) {
                    Enemy enemy = new Enemy(this, 1, i);
                    enemy.getPlayer().setTranslateX(20 + i * 64);
                    enemy.getPlayer().setTranslateY(70);
                    pane.getChildren().add(enemy.getPlayer());
                    enemies.add(enemy);
                }

            }

        }catch (Exception ex){;}

        scene=new Scene(pane,width,height);
        scene.setOnKeyPressed(event ->
        {
            switch (event.getCode())
            {
                case A: this.player.moveLeft(); break;
                case D: this.player.moveRight(); break;
                case SPACE: this.shoot(player); break;
            }
        });
        timer.start();
    }

    public void shoot(Ship ship)
    {

            if(ship.getClass().getName()=="Player") {
                Bullet bullet = new Bullet(this, ship, Color.GREEN);
                this.bullets.add(bullet);
                this.getPane().getChildren().addAll(bullet);
            }
            else {
                Bullet bullet = new Bullet(this, ship, Color.RED);
                this.bullets.add(bullet);
                this.getPane().getChildren().addAll(bullet);
            }




    }

    public int getLvl() {
        return lvl;
    }

    public void update()
    {
        t += 0.016;
        Bullet bullet;
        Random random=new Random();
        for(int i=0;i<bullets.size();i++)
        {

            bullet=bullets.get(i);
            if(bullet.ship.getClass().getName()=="Player")
            {
                bullet.moveUp();
            }
            else
                bullet.moveDown();
            for(int j=0; j<enemies.size();j++)
            {
                bullet=bullets.get(i);
                if(bullet.ship.getClass().getName()=="Player") {
                    if (bullet.getBoundsInParent().intersects(enemies.get(j).getPlayer().getBoundsInParent())) {
                        enemies.get(j).dead = true;
                        bullet.dead = true;
                    }
                }
                else
                {
                    if (bullet.getBoundsInParent().intersects(player.getPlayer().getBoundsInParent())) {
                        player.dead = true;
                        bullet.dead = true;
                    }
                }

            }
            if(bullets.get(i).dead==true) {
                this.pane.getChildren().remove(bullets.get(i));
                bullets.remove(i);
                }
        }

        for(int i=0;i<enemies.size();i++)
        {
            if(enemies.get(i).dead==true)
            {
                this.pane.getChildren().remove(enemies.get(i).getPlayer());
                enemies.remove(i);
            }
        }
       for(int i=0;i<enemies.size();i++)
        {
            Enemy enemy;
            boolean exist=false;
            int rand;
            enemy=enemies.get(i);
            rand=random.nextInt(1000);
            if(enemy.row>1)
            {
                for(int j=0;j<enemies.size();j++)
                {
                    if(enemies.get(j).row==1)
                    {
                        if(enemies.get(j).numberinrow==enemy.numberinrow)
                        exist=true;
                    }
                }
                if(exist==false)
                {
                    if(rand>995)
                    shoot(enemy);
                }

            }
            else
            {
                if(rand>995)

                    shoot(enemy);
            }


        }
       if(player.dead==true)
       {
           player.dead=false;
           GameOver gameOver=new GameOver(this.sceneControler,this.lvl);
           this.sceneControler.getStage().setScene(gameOver.getScene());
           this.timer.stop();

       }
       if(enemies.size()==0)
       {
           if(this.lvl<4) {
               NextLvl nextLvl = new NextLvl(this.sceneControler, lvl + 1);
               this.sceneControler.getStage().setScene(nextLvl.getScene());
               this.timer.stop();
           }
           else
           {
               EndGame endGame=new EndGame(this.sceneControler);
               this.sceneControler.getStage().setScene(endGame.getScene());
               this.timer.stop();
           }
       }

        if (t > 2) {
            t = 0;
        }

    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pane getPane() {
        return pane;
    }

    public Scene getScene() {
        return scene;
    }
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }


    public void tick() {//allows velocity to work with evry tick.

        x+= velX;
        y+= velY;

        // if(y <= 0 || y >= Game.HEIGHT-64) velY *= -1;   works not as smoothly as clamp method...
        // if(x <= 0 || x >= Game.WIDTH-32) velX *= -1;

        x = Game.clamp(x, 0, Game.WIDTH -32);
        y = Game.clamp(y, 0, Game.HEIGHT -62);

        collision();
    
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.BasicEnemy){ //temp object is now basic enemy
                if(getBounds().intersects(tempObject.getBounds())){
                    //when player collides with particle;
                    HUD.HEALTH -=1;
                }
            }
        }
    }

    public void render(Graphics g) {

       g.setColor(Color.ORANGE);
       g.fillRect(x, y, 32, 32);
        
    }
    
}

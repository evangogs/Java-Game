import java.awt.Color;
import java.awt.Graphics;

public class HUD {

    public static int HEALTH = 100;
    
    public void tick(){
        
        HEALTH = Game.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(10, 10, 100, 16);
        g.setColor(Color.GREEN);
        g.fillRect(10, 10, HEALTH, 16);
        g.setColor(Color.ORANGE);
        g.drawRect(9, 9, 102, 18);
    
    }
}

/*game objects are players, enemy's everything in the game
 * needs and x variable and a y variable.
 */
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y; // allows you to set x and y varibale when you extend to game object in other file.
    protected ID id;
    protected int velX;
    protected int velY;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y =y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setID(ID id){
        this.id = id;
    }

    public ID getID(){
        return id;
    }

    public void setvelX(int velX){
        this.velX = velX;
    }

    public void setvelY(int velY){
        this.velY = velY;
    }

    public int getVelX(){
        return velX;
    }

    public int getVelY(){
        return velY;
    }
}

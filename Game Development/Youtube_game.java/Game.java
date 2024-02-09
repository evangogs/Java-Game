import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    /*
     * A thread in Java is the direction or path that is taken while a program
     * is being executed. Generally, all the programs have at least one thread,
     * known as the main thread, that is provided by the JVM or Java Virtual
     * Machine at the starting of the program's execution.
     */

    private boolean running = false;

    private Random r = new Random();
    private Handler handler;
    private HUD hud;

    public Game() {

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
        handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
        handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));

        handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
        
        hud = new HUD();

        new Window(WIDTH, HEIGHT, "V1.3", this);

    }

    public synchronized void start() {
        /*
         * Synchronized method is used to lock an object for any shared resource.
         * When a thread invokes a synchronized method, it automatically acquires
         * the lock for that object and releases it when the thread completes its
         * task.
         */
        thread = new Thread(this); // calls alternate constructors from within a constructor.
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false; // try's to join thread, if cannnot then catch...
        } catch (Exception e) {
            e.printStackTrace(); // run an error bug in concol;
        }
    }

    public void run() { // game loop, needed to refresh game every millisecond, code is usally copied...
        
        this.requestFocus();   //allows no click before movement;         
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max){

        if(var >= max) return var = max; 
        else if(var <= min) return var = min;
        else return var;

    }

    public static void main(String args[]) {
        new Game();
    }
}
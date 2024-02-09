import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Window extends Canvas {

    public Window (int width, int height, String title, Game game){
        JFrame frame = new JFrame(title); 
        /*JFrame is a top-level container that provides a window on the screen. 
        A frame is actually a base window on which other components rely, namely 
        the menu bar, panels, labels, text fields, buttons, etc. Almost every other 
        Swing application starts with the JFrame window. */

        frame.setPreferredSize(new Dimension(width, height));
        /*Generally, setPreferredSize() will lay out the components as expected
         if a layout manager is present;  */
         frame.setMaximumSize(new Dimension(width, height));
         frame.setMinimumSize(new Dimension(width, height));

         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this allows the x button to work above...
         frame.setResizable(false);
         frame.setLocationRelativeTo(null);//makes it so it starts in the middle of the screen...
         frame.add(game);
         frame.setVisible(true);
         game.start();

    }

}

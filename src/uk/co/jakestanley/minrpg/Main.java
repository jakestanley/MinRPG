package uk.co.jakestanley.minrpg;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import uk.co.jakestanley.minrpg.values.Display;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by stanners on 11/04/2015.
 */
public class Main {

    public static boolean DEVELOPMENT = true;
    public static Display display;
    public static Game game;

    public static void main(String[] args){

        int displayWidth = Display.BASE_WIDTH * Display.DEFAULT_SCALE;
        int displayHeight = Display.BASE_HEIGHT * Display.DEFAULT_SCALE;

        try
        {
            game = new Game("Believe");
            AppGameContainer appgc = new AppGameContainer(game);
            display = new Display(); // use the default scale
            appgc.setDisplayMode(displayWidth, displayHeight, false);
            appgc.setVSync(true); // jesus h christ this needs to be on
            appgc.setTargetFrameRate(display.FRAME_RATE); // TODO remove the /2 after testing screen scrolling
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

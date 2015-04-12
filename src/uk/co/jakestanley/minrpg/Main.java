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

    public static Display display;

    public static void main(String[] args){

        try
        {
            AppGameContainer appgc = new AppGameContainer(new Game("MinRPG"));
            display = new Display(); // use the default scale
            appgc.setDisplayMode(display.getScreenWidth(), display.getScreenHeight(), false);
            appgc.setVSync(true); // jesus h christ this needs to be on
            appgc.setTargetFrameRate(display.FRAME_RATE);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

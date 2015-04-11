import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by stanners on 11/04/2015.
 */
public class Main {

    public static void main(String[] args){

        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Game("MinRPG"));
            appgc.setDisplayMode(640, 576, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

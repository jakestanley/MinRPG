package uk.co.jakestanley.minrpg.tiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by stanners on 11/04/2015.
 */
public class Tile {

    private Image image;

    public Tile(String resPath){
        try {
            image = new Image(resPath, false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace(); // TODO HANDLE
        }
    }

    public Image getImage() {
        return image;
    }

}

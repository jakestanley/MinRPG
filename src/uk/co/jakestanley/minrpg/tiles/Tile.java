package uk.co.jakestanley.minrpg.tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import uk.co.jakestanley.minrpg.values.Display;

/**
 * Created by stanners on 11/04/2015.
 */
public class Tile {

    public static final int TEXTURE_TILE = 0;
    public static final int DIRECT_TILE = 1;

    private Image image;
    private int tileType;
    private boolean isImpassable;

    // TODO different constructors for respaths and drawings, or do some nesting

    public Tile(Color color){
        tileType = DIRECT_TILE;
    }

    public Tile(String resPath){
        tileType = TEXTURE_TILE;
        try {
            image = new Image(resPath, false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            System.out.println("Failed to load image");
            e.printStackTrace(); // TODO HANDLE
        }
    }

    public void render(int x, int y, Graphics graphics){
        if(tileType == TEXTURE_TILE){
            graphics.drawImage(image, x, y);
        } else if(tileType == DIRECT_TILE){
            graphics.drawRect(x, y, Display.TILE_WIDTH, Display.TILE_WIDTH);
        } else {
            System.err.println("Tile type was not specified. Rendering nothing");
        }

    }

}

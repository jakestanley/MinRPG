package uk.co.jakestanley.minrpg.tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
    private Color color;

    // for bounding box generation
    private int boundingStartX, boundingStartY, renderX, renderY;

    public Tile(Color color, boolean isImpassable){ // TODO polish these constructors
        tileType = DIRECT_TILE;
        this.isImpassable = isImpassable;
        this.color = color;
    }

    public Tile(String resPath, boolean isImpassable){
        tileType = TEXTURE_TILE;
        this.isImpassable = isImpassable;
        try {
            image = new Image(resPath, false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            System.out.println("Failed to load image");
            e.printStackTrace(); // TODO HANDLE
        }
    }

    public void setPos(int x, int y){ //

        renderX = x;
        renderY = y;

        // bounding box stuff
        boundingStartX = x;
        boundingStartY = y;

    }

    public void render(Graphics graphics){ // RENDER SHOULDN'T HANDLE THIS MUCH LOGIC
        if(tileType == TEXTURE_TILE){
            graphics.drawImage(image, renderX, renderY);
        } else if(tileType == DIRECT_TILE){
            graphics.setColor(color);
            graphics.fillRect(renderX, renderY, Display.TILE_WIDTH, Display.TILE_WIDTH);
        } else {
            System.err.println("Tile type was not specified. Rendering nothing");
        }
    }

    public Rectangle getBoundingBox(){
        Rectangle boundingBox = null;
        if(isImpassable){
            boundingBox = new Rectangle(boundingStartX, boundingStartY, Display.TILE_WIDTH, Display.TILE_WIDTH);
        }
        return boundingBox;
    }

}

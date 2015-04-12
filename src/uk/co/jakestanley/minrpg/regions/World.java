package uk.co.jakestanley.minrpg.regions;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import uk.co.jakestanley.minrpg.values.Display;
import uk.co.jakestanley.minrpg.world.Chunk;

/**
 * Created by stanners on 12/04/2015.
 */
public class World {

    private Chunk currentChunk;
    private int offsetX;
    private int offsetY;
//    private Chunk northChunk;
//    private Chunk northEastChunk;
//    private Chunk eastChunk;
//    private Chunk southEastChunk;
//    private Chunk southChunk;
//    private Chunk southWestChunk;
//    private Chunk westChunk;
//    private Chunk northWestChunk;

    public World(int startChunkX, int startChunkY, int startTileX, int startTileY  ){
        currentChunk = new Chunk(startChunkX, startChunkY);
    }

    public void modOffsetX(int modX){
        offsetX = offsetX + modX;
        currentChunk.setOffsetX(offsetX);
    }

    public void modOffsetY(int modY){
        offsetY = offsetY + modY;
        currentChunk.setOffsetY(offsetY);
    }

    public void render(Graphics graphics){ // TODO world render from. this shouldn't need to be sent to any chunks really, it is determined by the scale, etc

        currentChunk.render(graphics);

//        graphics.setColor(Color.darkGray); // TODO move this into World class
//        float startEarth = (Display.SKY_HEIGHT / Display.BASE_SCALE);
//        float earthHeight = (Display.BASE_HEIGHT / Display.BASE_SCALE) - (Display.SKY_HEIGHT / Display.BASE_SCALE);
//        float earthWidth = Display.BASE_WIDTH / Display.BASE_SCALE;
//        graphics.fillRect(0, startEarth, earthWidth, earthHeight);


    }

}

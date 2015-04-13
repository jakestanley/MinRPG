package uk.co.jakestanley.minrpg.regions;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import uk.co.jakestanley.minrpg.Main;
import uk.co.jakestanley.minrpg.values.Display;
import uk.co.jakestanley.minrpg.world.Chunk;

/**
 * Created by stanners on 12/04/2015.
 */
public class World {

    private Chunk[][] chunks; // TODO need to load these dynamically. need to offset chunk coordinates like ieee754
    private Chunk currentChunk; // TODO work it out
    private int chunkX;
    private int chunkY;
    private int offsetX;
    private int offsetY;
    private Chunk northChunk;
    private Chunk northEastChunk; // TODO CONSIDER that this could be a 9x9 2d array instead?
    private Chunk eastChunk;
    private Chunk southEastChunk;
    private Chunk southChunk;
    private Chunk southWestChunk;
    private Chunk westChunk; // TODO first
    private Chunk northWestChunk;

    public World(int startChunkX, int startChunkY, int startTileX, int startTileY){
        currentChunk = new Chunk(startChunkX, startChunkY, false);
        eastChunk = new Chunk(startChunkX + (Main.display.getTileWidth() * Main.display.CHUNK_WIDTH), startChunkY, true);
        westChunk = new Chunk(startChunkX - (Main.display.getTileWidth() * Main.display.CHUNK_WIDTH), startChunkY, true);
        northChunk = new Chunk(startChunkX, startChunkY - (Main.display.getTileWidth() * Main.display.CHUNK_WIDTH), true);
        southChunk = new Chunk(startChunkX, startChunkY + (Main.display.getTileWidth() * Main.display.CHUNK_WIDTH), false);
        // chunks should be updated when the player enters a new chunk
        // world should contain all these components and handle rendering and interactions
    }

    public void modOffsetX(int modX){
        offsetX = offsetX + modX;
        currentChunk.setOffsetX(offsetX);
        eastChunk.setOffsetX(offsetX);
        westChunk.setOffsetX(offsetX);
        northChunk.setOffsetX(offsetX);
        southChunk.setOffsetX(offsetX);

    }

    public void modOffsetY(int modY){
        offsetY = offsetY + modY;
        currentChunk.setOffsetY(offsetY);
        eastChunk.setOffsetY(offsetY);
        westChunk.setOffsetY(offsetY);
        northChunk.setOffsetY(offsetY);
        southChunk.setOffsetY(offsetY);
    }

    public void render(Graphics graphics){ // TODO world render from. this shouldn't need to be sent to any chunks really, it is determined by the scale, etc

        currentChunk.render(graphics);
        eastChunk.render(graphics);
        westChunk.render(graphics);
        northChunk.render(graphics);
        southChunk.render(graphics);

//        graphics.setColor(Color.darkGray); // TODO move this into World class
//        float startEarth = (Display.SKY_HEIGHT / Display.BASE_SCALE);
//        float earthHeight = (Display.BASE_HEIGHT / Display.BASE_SCALE) - (Display.SKY_HEIGHT / Display.BASE_SCALE);
//        float earthWidth = Display.BASE_WIDTH / Display.BASE_SCALE;
//        graphics.fillRect(0, startEarth, earthWidth, earthHeight);


    }

}

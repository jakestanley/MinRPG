package uk.co.jakestanley.minrpg.world;

import org.newdawn.slick.Graphics;
import uk.co.jakestanley.minrpg.tiles.Tile;
import uk.co.jakestanley.minrpg.values.Display;

import java.util.Random;

/**
 * Created by stanners on 12/04/2015.
 */
public class Chunk {

    private Tile[][] tiles;
    private int x, y; // the chunk position relative to other chunks
    private int offsetX, offsetY;
    private boolean isGreen;

    public Chunk(boolean isGreen){ // TODO many constructors depending on use case
        tiles = new Tile[Display.CHUNK_WIDTH][Display.CHUNK_WIDTH];
//        this.x = x;
//        this.y = y;
//        this.offsetX = 0; // TODO make sure this gets changed soon
//        this.offsetY = 0;
        this.isGreen = isGreen;
        generateTiles();
    }

    public void generateTiles(){
        Random random = new Random(); // could possible use a static random from somewhere else ? // TODO CONSIDER
//        boolean grass = true;

        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles.length; y++){
                if(isGreen){
                    tiles[x][y] = new Tile("res/concept/scenery/someground.png");
                } else {
                    tiles[x][y] = new Tile("res/concept/scenery/somethingelse.png");
                }

//                grass = !grass;
            }
//            grass = !grass;
        }
    }

    /**
     * Needs to be called before rendering if back into visibility
     * @param x
     * @param y
     */
    public void spawnAt(int x, int y){
        this.x = x;
        this.y = y;
        this.offsetX = 0;
        this.offsetY = 0;
    }

    public Chunk(String data){
        // load chunk from data file // TODO much, much later...
    }

    public void setOffsetX(int offsetX){
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY){
        this.offsetY = offsetY;
    }

    public void setOffsets(int x, int y){
        offsetX = x;
        offsetY = y;
    }

//    public void render(Graphics graphics){
//        renderAt(x + offsetX, y + Main.display.getSkyHeight() + offsetY, graphics); // TODO remove a lot of the bullshit extra code rather than having everything super complex
//    }

    public void renderAt(int renderFromX, int renderFromY, Graphics graphics){ // TODO will also need some kind of offset

        int renderTileX = renderFromX; // ideally the edges of chunks should match up. could PC be a tile?
        int renderTileY = renderFromY;

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                graphics.drawImage(tiles[x][y].getImage(), renderTileX, renderTileY);
                renderTileY = renderTileY + Display.TILE_WIDTH;
            }
            renderTileX = renderTileX + Display.TILE_WIDTH; // TODO rename this to TILE_DIMENSIONS
            renderTileY = renderFromY;
        }
    }

}

package uk.co.jakestanley.minrpg.world;

import org.newdawn.slick.Graphics;
import uk.co.jakestanley.minrpg.tiles.Tile;
import uk.co.jakestanley.minrpg.values.Display;

/**
 * Created by stanners on 12/04/2015.
 */
public class Chunk {

    private Tile[][] tiles;
    private boolean isGreen;
    private boolean isAlternating;

    public Chunk(boolean isGreen){ // TODO many constructors depending on use case
        tiles = new Tile[Display.CHUNK_WIDTH][Display.CHUNK_WIDTH];
        this.isGreen = isGreen;
        this.isAlternating = false; // TODO make nicer
        generateTiles();
    }

    public void generateTiles(){

        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles.length; y++){
                if(isGreen){
                    tiles[x][y] = new Tile("res/concept/scenery/someground.png");
                } else {
                    tiles[x][y] = new Tile("res/concept/scenery/somethingelse.png");
                }

                if(isAlternating) {
                    isGreen = !isGreen;
                }
                
            }

            if(isAlternating) {
                isGreen = !isGreen;
            }

        }
    }

    public Chunk(String data){
        // load chunk from data file // TODO much, much later...
    }

    // generate a random tile
    public Chunk(){
        tiles = new Tile[Display.CHUNK_WIDTH][Display.CHUNK_WIDTH];
        this.isGreen = true;
        this.isAlternating = true;
        generateTiles();
    }

    public void renderAt(int renderFromX, int renderFromY, Graphics graphics){ // TODO will also need some kind of offset

        int renderTileX = renderFromX; // ideally the edges of chunks should match up. could PC be a tile?
        int renderTileY = renderFromY;

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                tiles[x][y].render(renderTileX, renderTileY, graphics);
                renderTileY = renderTileY + Display.TILE_WIDTH;
            }
            renderTileX = renderTileX + Display.TILE_WIDTH; // TODO rename this to TILE_DIMENSIONS
            renderTileY = renderFromY;
        }
    }

}

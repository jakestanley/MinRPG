package uk.co.jakestanley.minrpg.tiles;

import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import uk.co.jakestanley.minrpg.values.Display;

import java.util.Random;

/**
 * Created by stanners on 11/04/2015.
 */
public class TileMap {

    private Tile[][] tiles;
    private int endX; // startX will obviously be zero
    private int startY, endY; // at what point should i start drawing and at what point should I stop?

    public TileMap(){ // TODO for now just generate tiles
        tiles = new Tile[Display.CHUNK_WIDTH][Display.CHUNK_WIDTH];
        populateTileMap();
    }

    public void populateTileMap(){
        Random random = new Random(); // could possible use a static random from somewhere else ? // TODO CONSIDER
        boolean grass = true;

        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles.length; y++){
                grass = random.nextBoolean();

                if(grass){
                    tiles[x][y] = new Tile("res/concept/scenery/someground.png");
                } else {
                    tiles[x][y] = new Tile("res/concept/scenery/somethingelse.png");
                }

//                grass = !grass;
            }
//            grass = !grass;
        }
    }

    public void render(int renderFromX, int renderFromY, Graphics graphics){ // TODO will also need some kind of offset

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

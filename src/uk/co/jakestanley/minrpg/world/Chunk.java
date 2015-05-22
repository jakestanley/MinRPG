package uk.co.jakestanley.minrpg.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import uk.co.jakestanley.minrpg.tiles.Tile;
import uk.co.jakestanley.minrpg.values.Display;

import java.util.ArrayList;
import java.util.Random;

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

        Random random = new Random();

        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles.length; y++){
                if(random.nextInt(9) == 3){
                    tiles[x][y] = new Tile(Color.red, true);
                } else {
                    if(isGreen){
//                    tiles[x][y] = new Tile(Color.green, false); // renders color
                        tiles[x][y] = new Tile("res/concept/scenery/someground.png", false);
                    } else {
//                    tiles[x][y] = new Tile(Color.blue, false); // renders color
                        tiles[x][y] = new Tile("res/concept/scenery/somethingelse.png", false);
                    }
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

    public void setPos(int renderFromX, int renderFromY){

        int renderTileX = renderFromX; // ideally the edges of chunks should match up. could PC be a tile?
        int renderTileY = renderFromY;

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                tiles[x][y].setPos(renderTileX, renderTileY);
                renderTileY = renderTileY + Display.TILE_WIDTH;
            }
            renderTileX = renderTileX + Display.TILE_WIDTH; // TODO CONSIDER renaming this to TILE_DIMENSIONS?
            renderTileY = renderFromY;
        }

    }

    public void render(Graphics graphics){ // TODO will also need some kind of offset

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                tiles[x][y].render(graphics);

            }
        }
    }

    public ArrayList<Rectangle> getBoundingBoxes(){

        ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                Rectangle box = tiles[x][y].getBoundingBox();
                if(box != null){
                    boxes.add(box);
                }
            }
        }

        return boxes;

    }

}

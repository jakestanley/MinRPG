package uk.co.jakestanley.minrpg.values;

/**
 * Created by stanners on 11/04/2015.
 */
public class Display {

    // TODO CONSIDER using nested classes

    // EMULATED RESOLUTION
    public static final int BASE_WIDTH = 160;
    public static final int BASE_HEIGHT = 144;

    // SCALE
    public static final int DEFAULT_SCALE = 4; // SCALE TO BLOW UP ELEMENTS
    public static final int FRAME_RATE = 30; // apparently the original game boy had a max frame rate of 59.7 fps

    // VIEW COMPONENTS
    public static final int SKY_HEIGHT = 36; // TODO this needs to be more dynamic, like a percentage. 36 is 1/4th the screen

    // WORLD COMPONENTS
    public static final int TILE_WIDTH = 8; // tiles will be 8px square, maybe 16, i'm unsure yet...
    public static final int CHUNK_WIDTH = 16;
    public static final int CHUNK_CACHE_WIDTH = 7; // depends on amount of memory available
    public static final int START_CHUNK_X = 3; // what chunk should i spawn in?
    public static final int START_CHUNK_Y = 3;
    public static final int RENDER_INITIAL_X = 0; // offset from the edge (TODO should be zero when not testing)
    public static final int RENDER_INITIAL_Y = 0; // offset from the edge (TODO should be zero when not testing)

    private int renderTileCenterX;
    private int renderTileCenterY;
    private int chunkRenderWidth;

    public Display(){ // TODO CONSIDER renaming to RenderConstants

        // CALCULATE renderTileCenterX
        renderTileCenterX = (BASE_WIDTH / 2) - (TILE_WIDTH / 2);

        // CALCULATE renderTileCenterY
        int viewHeight = BASE_HEIGHT - SKY_HEIGHT;
        renderTileCenterY = SKY_HEIGHT + (viewHeight / 2) - (TILE_WIDTH / 2);

        // CALCULATE chunkRenderWidth
        chunkRenderWidth = TILE_WIDTH * CHUNK_WIDTH;

    }

    public int getChunkRenderWidth(){
        return chunkRenderWidth;
    }

    public int getPlayerRenderX(){
        return renderTileCenterX;
    }
    public int getPlayerRenderY(){
        return renderTileCenterY;
    }

}

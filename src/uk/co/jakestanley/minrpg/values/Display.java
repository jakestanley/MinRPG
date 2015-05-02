package uk.co.jakestanley.minrpg.values;

/**
 * Created by stanners on 11/04/2015.
 */
public class Display {

    // TODO CONSIDER using nested classes

    // I SHOULD TOTALLY IMPLEMENT A GAME BOY MODE
    public static final int GB_WIDTH = 160;
    public static final int GB_HEIGHT = 144;

    // BASE SCALE IS DOUBLE GAME BOY RESOLUTION
    public static final int BASE_WIDTH = 320;
    public static final int BASE_HEIGHT = 288;

    // SCALE
    public static final int BASE_SCALE = 2; // TODO work out what this was for, i forgot, but it's important...
    public static final int DEFAULT_SCALE = 2;
    public static final int FRAME_RATE = 30; // apparently the original game boy had a max frame rate of 59.7 fps

    // VIEW COMPONENTS
    public static final int SKY_HEIGHT = 72; // TODO this needs to be more dynamic, like a percentage.

    // WORLD COMPONENTS
    public static final int TILE_WIDTH = 8; // tiles will be 8px square, maybe 16, i'm unsure yet...
//    public static final int CHUNK_WIDTH = 16;
    public static final int CHUNK_WIDTH = 2; // only for testing chunk render stuff. // TODO bump back up to 16 after
    public static final int CHUNK_CACHE_WIDTH = 7; // depends on amount of memory available
    public static final int START_CHUNK_X = 3; // what chunk should i spawn in?
    public static final int START_CHUNK_Y = 3;
    public static final int RENDER_INITIAL_X = 0; // offset from the edge (TODO should be zero when not testing)
    public static final int RENDER_INITIAL_Y = 0; // offset from the edge (TODO should be zero when not testing)


    private int scale;
    private int renderScale; // TODO FIX so I know what this is, it's used by graphics.scale
    private int width;
    private int height;
    private int skyHeight; // wat

    public Display(){
        this.scale = DEFAULT_SCALE;
        renderScale = BASE_SCALE * scale;
        width = BASE_WIDTH * scale;
        height = BASE_HEIGHT * scale;
    }

    public Display(int scale){
        this.scale = scale;
        renderScale = BASE_SCALE * scale;
        width = BASE_WIDTH * scale;
        height = BASE_HEIGHT * scale;
    }

    public int getScreenWidth(){
        return width;
    }

    public int getScreenHeight(){
        return height;
    }

    public int getRenderScale(){
        return renderScale;
    }

    public int getSkyHeight(){ // get the proper values in real time
        return SKY_HEIGHT / BASE_SCALE; // seems to work ok
    }

    public int getTileWidth(){
        return TILE_WIDTH; // TODO set correct value
    }

    public int getCenterTileX(){
//        return (width / 2) / scale;
        return GB_WIDTH / 2; // TODO FIX
//        return 50;
    }

    public int getCenterTileY(){
        return (GB_HEIGHT + getSkyHeight()) / 2; // TODO FIX and do some stuff with sky height
//        return 50;
    }

    public int getChunkRenderWidth(){
        int chunkRenderWidth = getTileWidth() * CHUNK_WIDTH;
        return chunkRenderWidth;
    }

}

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

    private int renderScale; // TODO FIX so I know what this is, it's used by graphics.scale
    private int width;
    private int height;

    public Display(){ // TODO CONSIDER renaming to RenderConstants

    }

    public Display(int scale){
        // TODO work out what the fuck this was even for
    }

    public int getChunkRenderWidth(){
        int chunkRenderWidth = TILE_WIDTH * CHUNK_WIDTH;
        return chunkRenderWidth;
    }

    public int getPlayerRenderX(){
        return 0; // TODO more than "return 0;"
    }
    public int getPlayerRenderY(){

        // TODO
        int viewHeight = BASE_HEIGHT - SKY_HEIGHT;

        System.out.println("Sky height: " + skyHeight);
        System.out.println("Screen height: " + screenHeight);
        System.out.println("View height: " + viewHeight);


//        int y = skyHeight + (viewHeight / 2) - (getTileWidth() / 2);
//        y = y / 4;
//        System.out.println("Player render Y on screen at : " + y);
        int y = SKY_HEIGHT;
        return y;

    }

}

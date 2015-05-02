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

    private Chunk[][] cachedChunks; // TODO need to load these dynamically. need to offset chunk coordinates like ieee754

    private int currentChunkX, currentChunkY;
    private int localX, localY;
    private int worldX, worldY;
    private int centerRenderX, centerRenderY;
    private int offsetX, offsetY;

    public World(int startChunkX, int startChunkY){

        // set start chunk coordinates
        currentChunkX = startChunkX;
        currentChunkY = startChunkY;

        // initialising cached chunks array
        cachedChunks = new Chunk[Display.CHUNK_CACHE_WIDTH][Display.CHUNK_CACHE_WIDTH];

        // generate the chunks
        generateChunks(); // TODO save/load from file (eventually)

        // set location
        localX = 0;
        localY = 0;

        // set initial world coordinates
        worldX = currentChunkX * Main.display.getChunkRenderWidth();
        worldY = currentChunkY * Main.display.getChunkRenderWidth();

        centerRenderX = Display.RENDER_INITIAL_X;
        centerRenderY = Display.RENDER_INITIAL_Y + Main.display.getSkyHeight(); // trying something different

        offsetX = 0;
        offsetY = 0;

//        updateChunks();
        // chunks should be updated when the player enters a new chunk
        // world should contain all these components and handle rendering and interactions
    }

    /**
     * in its current testing state, this function will generate every chunk as random except the center chunk
     */
    private void generateChunks(){
        boolean green = true;
        for(int x = 0; x < cachedChunks.length; x++){
            for(int y = 0; y < cachedChunks.length; y++){
                cachedChunks[x][y] = new Chunk(green);
                green = !green;
            }
        }

        // preset chunks
        cachedChunks[currentChunkX][currentChunkY] = new Chunk(false);
    }

    // TODO clear up all this messy, wank code

    // TODO modY after modX() is definitely working
    public void modX(int modX){ // TODO shouldn't need to constantly update chunks, should just get do it all seamlessly

        // update reported position as per modX
        localX = localX - modX;
        worldX = worldX - modX;
        offsetX = offsetX + modX;
        // if player has entered a new chunk on the x axis
        if(localX >= Main.display.getChunkRenderWidth()){
            // if player went right
            localX = 0; // TODO change so not hardcoded values
            currentChunkX++;
//            updateChunks();
            offsetX = 0;
        } else if(localX < 0){
            // if player went left
            localX = (Main.display.getChunkRenderWidth() - 1); // TODO change so not hardcoded values
            currentChunkX--; // TODO limit movement so that the player can't walk off 'the edge' (into arrayindexoutofboundsexceptions)
//            updateChunks(); // TODO this should only reload chunks if necessary. make this more complex
            offsetX = -(Main.display.getChunkRenderWidth() - 1);
        }

    }

    public void modY(int modY){
        localY = localY - modY;
        worldY = worldY - modY; // TODO make this all just modY and modX
        offsetY = offsetY + modY;


        if(localY > (Main.display.getChunkRenderWidth() - 1)){
            // if player went down
            localY = 0; // TODO change so not hardcoded values
            currentChunkY++;
//            updateChunks(); // TODO need to update chunks Y and update chunks X separately
            offsetY = 0;
        } else if(localY < 0){
            // if player went up
            localY = (Main.display.getChunkRenderWidth() - 1); // TODO change so not hardcoded values
            currentChunkY--;
//            updateChunks();
            offsetY = -(Main.display.getChunkRenderWidth() - 1);
        }
    }

    public void update(){ // TODO this function
        // TODO check player chunk location, check for any events, etc
        // TODO update chunk information and reload/cache where necessary. need to know the player coordinates
        // TODO recalculate visible chunks
    }

    public void render(Graphics graphics){

        int renderX = centerRenderX + offsetX;
        int renderY = centerRenderY + offsetY;
        int rcw = Main.display.getChunkRenderWidth();

        cachedChunks[currentChunkX-1][currentChunkY-1]  .renderAt(renderX - rcw,    renderY - rcw,  graphics); // northwest chunk
        cachedChunks[currentChunkX][currentChunkY-1]    .renderAt(renderX,          renderY - rcw,  graphics); // north chunk
        cachedChunks[currentChunkX+1][currentChunkY-1]  .renderAt(renderX + rcw,    renderY - rcw,  graphics); // northeast chunk
        cachedChunks[currentChunkX-1][currentChunkY]    .renderAt(renderX - rcw,    renderY,        graphics); // west chunk
        cachedChunks[currentChunkX][currentChunkY]      .renderAt(renderX,          renderY,        graphics); // center chunk
        cachedChunks[currentChunkX+1][currentChunkY]    .renderAt(renderX + rcw,    renderY,        graphics); // east chunk
        cachedChunks[currentChunkX-1][currentChunkY+1]  .renderAt(renderX - rcw,    renderY + rcw,  graphics); // southwest chunk
        cachedChunks[currentChunkX][currentChunkY+1]    .renderAt(renderX,          renderY + rcw,  graphics); // south chunk
        cachedChunks[currentChunkX+1][currentChunkY+1]  .renderAt(renderX + rcw,    renderY + rcw,  graphics); // southeast chunk

    }

    public void renderData(Graphics graphics) {

        graphics.scale(0.5F, 0.5F);
        graphics.setColor(Color.white);
        graphics.scale(0.5F, 0.5F);
        graphics.drawString("Chunk: " + currentChunkX + ", " + currentChunkY, 20, 30);
        graphics.drawString("Local: " + localX + ", " + localY, 20, 50);
        graphics.drawString("World: " + worldX + ", " + worldY, 20, 70);
//        graphics.drawString("Render offset: " + initialRenderX + ", " + initialRenderY, 20, 90);
        graphics.drawString("Offset: " + offsetX + ", " + offsetY, 20, 110);
        graphics.drawString("Chunk render width: " + Main.display.getChunkRenderWidth(), 200, 30);

    }

}

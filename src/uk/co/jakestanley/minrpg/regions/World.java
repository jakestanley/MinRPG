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

//    private Chunk[][] visibleChunks; // TODO need to load these dynamically. need to offset chunk coordinates like ieee754
    private Chunk[][] cachedChunks;

    private static final int renderInitialX = 0;
    private static final int renderInitialY = 0;
    private int currentChunkX;
    private int currentChunkY;
    private int localX;
    private int localY;
    private int offsetX;
    private int offsetY;

    public World(int startChunkX, int startChunkY){

        // initialising array
        cachedChunks = new Chunk[Display.CHUNK_CACHE_WIDTH][Display.CHUNK_CACHE_WIDTH];
        localX = 0;
        localY = 0;

        currentChunkX = startChunkX;
        currentChunkY = startChunkY;

        // generate the chunks
        generateChunks(); // TODO save/load from file (eventually)

        updateChunks();
        // TODO only render chunks surrounding the current location



        // chunks should be updated when the player enters a new chunk
        // world should contain all these components and handle rendering and interactions
    }

    private void generateChunks(){
        boolean rand = true;
        for(int x = 0; x < cachedChunks.length; x++){
            for(int y = 0; y < cachedChunks.length; y++){
                cachedChunks[x][y] = new Chunk(rand);
                rand = !rand;
            }
        }
    }

    /**
     * TODO
     */
    private void generateCachedChunks(){ // initialising
        for(int x = 0; x < cachedChunks.length; x++){
            for(int y = 0; y < cachedChunks.length; y++){
//                cachedChunks[x][y] = new Chunk();
            }
        }
    }

    private void modLocalX(int modX){
        localX = localX - modX;
        if(localX > 127){
            localX = 0; // TODO change so not hardcoded values
            currentChunkX++;
        } else if(localX < 0){
            localX = 127; // TODO change so not hardcoded values
            currentChunkX--; // TODO limit movement so that the player can't walk off 'the edge' (into arrayindexoutofboundsexceptions)
        }
    }

    private void modLocalY(int modY){
        localY = localY - modY;
        if(localY > 127){
            localY = 0; // TODO change so not hardcoded values
            currentChunkY++;
        } else if(localY < 0){
            localY = 127; // TODO change so not hardcoded values
            currentChunkY--;
        }
    }

    private void updateChunks(){

        // TODO despawn old chunks, to stop any events being triggered by them. maybe set them as inactive?

        int chunkRenderWidth = Main.display.getChunkRenderWidth();

        // (re)spawn northwest chunk
        cachedChunks[currentChunkX-1][currentChunkY-1].spawnAt( renderInitialX - chunkRenderWidth,
                                                                renderInitialY - chunkRenderWidth);

        // (re)spawn north chunk
        cachedChunks[currentChunkX][currentChunkY-1].spawnAt(   renderInitialX,
                                                                renderInitialY - chunkRenderWidth);

        // (re)spawn northeast chunk
        cachedChunks[currentChunkX+1][currentChunkY-1].spawnAt( renderInitialX + chunkRenderWidth,
                                                                renderInitialY - chunkRenderWidth);

        // (re)spawn west chunk
        cachedChunks[currentChunkX-1][currentChunkY].spawnAt(   renderInitialX - chunkRenderWidth,
                                                                renderInitialY);

        // (re)spawn center chunk
        cachedChunks[currentChunkX][currentChunkY].spawnAt(     renderInitialX,
                                                                renderInitialY);

        // (re)spawn east chunk
        cachedChunks[currentChunkX+1][currentChunkY].spawnAt(   renderInitialX + chunkRenderWidth,
                                                                renderInitialY); // east chunk

        // (re)spawn southwest chunk
        cachedChunks[currentChunkX-1][currentChunkY+1].spawnAt( renderInitialX - chunkRenderWidth,
                                                                renderInitialY + chunkRenderWidth); // southwest chunk

        // (re)spawn south chunk
        cachedChunks[currentChunkX][currentChunkY+1].spawnAt(   renderInitialX,
                                                                renderInitialY + chunkRenderWidth); // south chunk

        // (re)spawn southeast chunk
        cachedChunks[currentChunkX+1][currentChunkY+1].spawnAt( renderInitialX + chunkRenderWidth,
                                                                renderInitialY + chunkRenderWidth); // southeast chunk



    }

    public void modOffsetX(int modX){
        offsetX = offsetX + modX;
        modLocalX(modX);

        cachedChunks[currentChunkX-1][currentChunkY-1].setOffsetX(offsetX); // northwest chunk
        cachedChunks[currentChunkX][currentChunkY-1].setOffsetX(offsetX); // north chunk
        cachedChunks[currentChunkX+1][currentChunkY-1].setOffsetX(offsetX); // northeast chunk
        cachedChunks[currentChunkX-1][currentChunkY].setOffsetX(offsetX); // west chunk
        cachedChunks[currentChunkX][currentChunkY].setOffsetX(offsetX); // center chunk
        cachedChunks[currentChunkX+1][currentChunkY].setOffsetX(offsetX); // east chunk
        cachedChunks[currentChunkX-1][currentChunkY+1].setOffsetX(offsetX); // southwest chunk
        cachedChunks[currentChunkX][currentChunkY+1].setOffsetX(offsetX); // south chunk
        cachedChunks[currentChunkX+1][currentChunkY+1].setOffsetX(offsetX); // southeast chunk

    }

    public void modOffsetY(int modY){
        offsetY = offsetY + modY;
        modLocalY(modY);

        cachedChunks[currentChunkX-1][currentChunkY-1].setOffsetY(offsetY); // northwest chunk
        cachedChunks[currentChunkX][currentChunkY-1].setOffsetY(offsetY); // north chunk
        cachedChunks[currentChunkX+1][currentChunkY-1].setOffsetY(offsetY); // northeast chunk
        cachedChunks[currentChunkX-1][currentChunkY].setOffsetY(offsetY); // west chunk
        cachedChunks[currentChunkX][currentChunkY].setOffsetY(offsetY); // center chunk
        cachedChunks[currentChunkX+1][currentChunkY].setOffsetY(offsetY); // east chunk
        cachedChunks[currentChunkX-1][currentChunkY+1].setOffsetY(offsetY); // southwest chunk
        cachedChunks[currentChunkX][currentChunkY+1].setOffsetY(offsetY); // south chunk
        cachedChunks[currentChunkX+1][currentChunkY+1].setOffsetY(offsetY); // southeast chunk

    }

    public void update(){
        // TODO check player chunk location, check for any events, etc
        // TODO update chunk information and reload/cache where necessary. need to know the player coordinates
        // TODO recalculate visible chunks
    }

    public void render(Graphics graphics){ // TODO world render from. this shouldn't need to be sent to any chunks really, it is determined by the scale, etc

        cachedChunks[currentChunkX-1][currentChunkY-1].render(graphics); // northwest chunk
        cachedChunks[currentChunkX][currentChunkY-1].render(graphics); // north chunk
        cachedChunks[currentChunkX+1][currentChunkY-1].render(graphics); // northeast chunk
        cachedChunks[currentChunkX-1][currentChunkY].render(graphics); // west chunk
        cachedChunks[currentChunkX][currentChunkY].render(graphics); // center chunk
        cachedChunks[currentChunkX+1][currentChunkY].render(graphics); // east chunk
        cachedChunks[currentChunkX-1][currentChunkY+1].render(graphics); // southwest chunk
        cachedChunks[currentChunkX][currentChunkY+1].render(graphics); // south chunk
        cachedChunks[currentChunkX+1][currentChunkY+1].render(graphics); // southeast chunk

    }

    public void renderData(Graphics graphics){

        graphics.scale(0.5F, 0.5F);
        graphics.setColor(Color.white);
        graphics.drawString("Chunk: " + currentChunkX + ", " + currentChunkY, 20, 20);
        graphics.drawString("Local: " + localX + ", " + localY, 20, 40);

    }

}

package uk.co.jakestanley.minrpg.characters;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import uk.co.jakestanley.minrpg.Main;
import uk.co.jakestanley.minrpg.values.Display;

/**
 * Created by stanners on 12/04/2015.
 */
public class Player extends Character {

    public Player(int worldPosX, int worldPosY) {
        super("res/concept/pc"); // directory of player character assets and animations
        this.worldPosX = worldPosX;
        this.worldPosY = worldPosY;
        renderAtX = Main.display.getPlayerRenderX();
        renderAtY = Main.display.getPlayerRenderY();
    }


    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(renderAtX, renderAtY, Display.TILE_WIDTH, Display.TILE_WIDTH);
    }

    public Line getBoundingNorth(){
        return new Line(        renderAtX, renderAtY,
                                renderAtX + Display.TILE_WIDTH, renderAtY);
    }

    public Line getBoundingEast(){
        return new Line(        renderAtX, renderAtY,
                                renderAtX, renderAtY + Display.TILE_WIDTH);
    }

    public Line getBoundingSouth(){
        return new Line(        renderAtX, renderAtY + Display.TILE_WIDTH,
                                renderAtX + Display.TILE_WIDTH, renderAtY + Display.TILE_WIDTH);
    }

    public Line getBoundingWest(){
        return new Line(        renderAtX + Display.TILE_WIDTH, renderAtY,
                                renderAtX + Display.TILE_WIDTH, renderAtY + Display.TILE_WIDTH);
    }

}

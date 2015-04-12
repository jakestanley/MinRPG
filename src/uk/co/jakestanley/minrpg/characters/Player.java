package uk.co.jakestanley.minrpg.characters;

import uk.co.jakestanley.minrpg.values.Display;

/**
 * Created by stanners on 12/04/2015.
 */
public class Player extends Character {

    private float worldPosX, worldPosY; // where the character is in the world, as opposed to on screen
    private float shiftX, shiftY;

    public Player() {
        super("res/concept/pc");
        worldPosX = 0;
        worldPosY = 0;
        shiftX = worldPosX + (Display.BASE_HEIGHT - Display.SKY_HEIGHT) / 2; // TODO CONSIDER may need to perform an additional arithmetic operation here
        shiftY = worldPosY + Display.BASE_WIDTH / 2;
    }

}

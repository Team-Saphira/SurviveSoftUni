package game.models;

import game.Constants;

public abstract class Enemy extends HumanObject{
    private int currentCellRow;
    private int currentCellCol;
    private int posXReal; //actual pixel position
    private int posYReal;
    private boolean isCentered = false;
    private boolean allowNextCellMove = false;
    private int health = Constants.ZOMBIE_HEALTH;


    protected Enemy(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
    }
}

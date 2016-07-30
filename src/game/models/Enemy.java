package game.models;
import game.models.interfaces.PathFindable;
import game.moveLogic.AStar;
import javafx.scene.image.ImageView;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class Enemy extends HumanObject implements PathFindable{
    public Queue<AStar.Cell> path;

    // zombie position on the "matrix"...
    private int currentCellRow;
    private int currentCellCol;
    private int posXReal; //actual pixel position
    private int posYReal;
    private boolean isCentered;
    private boolean allowNextCellMove;
    private int health;
    private ImageView enemyImageView;
    private char moveDirection; // For use of randomised movement


    protected Enemy(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);

        this.setPath(new LinkedBlockingDeque<>());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void dealDamage(int damage){
        this.health -= damage;
    }

    public int getPosYReal() {
        return posYReal;
    }

    public void setPosYReal(int posYReal) {
        this.posYReal = posYReal;
    }

    public int getPosXReal() {
        return posXReal;
    }

    public void setPosXReal(int posXReal) {
        this.posXReal = posXReal;
    }

    public boolean getIsCentered() {
        return isCentered;
    }

    public void setIsCentered(boolean centered) {
        isCentered = centered;
    }

    public boolean getAllowNextCellMove() {
        return allowNextCellMove;
    }

    public void setAllowNextCellMove(boolean allowNextCellMove) {
        this.allowNextCellMove = allowNextCellMove;
    }

    public ImageView getEnemyImageView() {
        return enemyImageView;
    }

    public void setEnemyImageView(ImageView enemyImageView) {
        this.enemyImageView = enemyImageView;
    }

    public int getCurrentCellRow() {
        return currentCellRow;
    }

    public void setCurrentCellRow(int currentCellRow) {
        this.currentCellRow = currentCellRow;
    }

    public int getCurrentCellCol() {
        return currentCellCol;
    }

    public void setCurrentCellCol(int currentCellCol) {
        this.currentCellCol = currentCellCol;
    }

    public Queue<AStar.Cell> getPath() {
        return path;
    }

    public void setPath(Queue<AStar.Cell> path) {
        this.path = path;
    }

    public char getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(char moveDirection) {
        this.moveDirection = moveDirection;
    }

    public abstract void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix);
}

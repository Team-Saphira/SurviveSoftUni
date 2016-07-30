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

    protected void setHealth(int health) {
        this.health = health;
    }

    protected void dealDamage(int damage){
        this.health -= damage;
    }

    public int getPosYReal() {
        return posYReal;
    }

    protected void setPosYReal(int posYReal) {
        this.posYReal = posYReal;
    }

    public int getPosXReal() {
        return posXReal;
    }

    protected void setPosXReal(int posXReal) {
        this.posXReal = posXReal;
    }

    public boolean getIsCentered() {
        return isCentered;
    }

    protected void setIsCentered(boolean centered) {
        isCentered = centered;
    }

    public boolean getAllowNextCellMove() {
        return allowNextCellMove;
    }

    protected void setAllowNextCellMove(boolean allowNextCellMove) {
        this.allowNextCellMove = allowNextCellMove;
    }

    public ImageView getEnemyImageView() {
        return enemyImageView;
    }

    protected void setEnemyImageView(ImageView enemyImageView) {
        this.enemyImageView = enemyImageView;
    }

    public int getCurrentCellRow() {
        return currentCellRow;
    }

    protected void setCurrentCellRow(int currentCellRow) {
        this.currentCellRow = currentCellRow;
    }

    public int getCurrentCellCol() {
        return currentCellCol;
    }

    protected void setCurrentCellCol(int currentCellCol) {
        this.currentCellCol = currentCellCol;
    }

    public Queue<AStar.Cell> getPath() {
        return path;
    }

    protected void setPath(Queue<AStar.Cell> path) {
        this.path = path;
    }

    public char getMoveDirection() {
        return moveDirection;
    }

    protected void setMoveDirection(char moveDirection) {
        this.moveDirection = moveDirection;
    }

    public abstract void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix);

    public void changeHealth(int health){
        this.setHealth(health);
    }

    public void changeDealDamage(int damage){
        this.dealDamage(damage);
    }

    public  void changePosXPixel(int posXReal){
        this.setPosXReal(posXReal);
    }

    public void changePosYPixel(int posYReal){
        this.setPosYReal(posYReal);
    }

    public void changeCurrentCellRow(int currentCellRow){
        this.setCurrentCellRow(currentCellRow);
    }

    public void changeCurrentCellCol(int currentCellCol){
        this.setCurrentCellCol(currentCellCol);
    }

    public void isCentered(boolean centered){
        this.setIsCentered(centered);
    }

    public void changeAllowNextCellMove(boolean allowNextCellMove){
        this.setAllowNextCellMove(allowNextCellMove);
    }

    public void changeEnemyImageView(ImageView enemyImageView){
        this.setEnemyImageView(enemyImageView);
    }

    public void changePath(Queue<AStar.Cell> path){
        this.setPath(path);
    }

    public void changeMoveDirection(char moveDirection){
        this.setMoveDirection(moveDirection);
    }
}

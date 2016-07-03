package game.models;

import game.moveLogic.AStar;
import game.Constants;
import game.sprites.ImageLoader;
import game.sprites.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.Queue;

public class Zombie extends HumanObject {
    private final int SPRITE_COUNT = 4;
    private final int SPRITE_COLUMNS = 4;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH = 64;
    private final int SPRITE_HEIGHT = 64;

    public Queue<AStar.Cell> path = new ArrayDeque<>();

//    zombie position on the "matrix"...
    private int currentCellRow;
    private int currentCellCol;
    private int posXReal; //actual pixel position
    private int posYReal;
    private boolean isCentered;
    private boolean allowNextCellMove;
    private int health;

    private ImageView zombieImageView;

    public Zombie(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH);
        this.setSpriteHeight(SPRITE_HEIGHT);

        this.setIsCentered(false);
        this.setAllowNextCellMove(false);
        this.setHealth(Constants.ZOMBIE_HEALTH);

        this.setObjectSize(Constants.ENEMY_SIZE);

        this.setZombieImageView(new ImageView(ImageLoader.zombieImage));

        this.getZombieImageView().setFitHeight(Constants.ENEMY_SIZE);
        this.getZombieImageView().setFitWidth(Constants.ENEMY_SIZE);

        this.getZombieImageView().setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.getZombieImageView(),
                Duration.millis(1000),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
        getChildren().addAll(this.getZombieImageView());

        this.setBoundingBox(calcBoundingBox(Constants.ENEMY_SIZE));
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

    public ImageView getZombieImageView() {
        return zombieImageView;
    }

    public void setZombieImageView(ImageView zombieImageView) {
        this.zombieImageView = zombieImageView;
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

    public void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix) {
        path = AStar.findPath(levelWidth, levelHeight, playerX, playerY, zombieX, zombieY, matrix);
    }
}

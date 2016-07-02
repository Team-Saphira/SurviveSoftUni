package game.models;

import game.moveLogic.AStar;
import game.Constants;
import game.sprites.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.Queue;

public class Enemy extends HumanObject {

    private final int SPRITE_COUNT = 4;
    private final int SPRITE_COLUMNS = 4;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH = 64;
    private final int SPRITE_HEIGHT = 64;

    private int HEALTH = 3;

//    TODO: Decouple
    public Queue<AStar.Cell> path = new ArrayDeque<>();
    public Image zombieImg = new Image(getClass().getResourceAsStream("/game/resources/zombie.png"));
    public ImageView imageView = new ImageView(zombieImg);

    //zombie position on the "matrix"...
    private int currentCellRow;
    private int currentCellCol;
    private int posXReal; //actual pixel position
    private int posYReal;
    private boolean isCentered = false;
    private boolean allowNextCellMove = false;
    private int health = HEALTH;


    public Enemy(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH);
        this.setSpriteHeight(SPRITE_HEIGHT);

        imageView.setFitHeight(Constants.ENEMY_SIZE);
        imageView.setFitWidth(Constants.ENEMY_SIZE);

        imageView.setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.imageView,
                Duration.millis(1000),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
        getChildren().addAll(this.imageView);

        this.setBoundingBox(calcBoundingBox(Constants.ENEMY_SIZE));
    }

    public int getHealth() {
        return health;
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



    public void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix) {
        path = AStar.findPath(levelWidth, levelHeight, playerX, playerY, zombieX, zombieY, matrix);
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
}

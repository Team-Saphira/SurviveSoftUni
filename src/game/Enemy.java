package game;

import game.Level.Block;
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

    Queue<AStar.Cell> path = new ArrayDeque<>();
    Image zombieImg = new Image(getClass().getResourceAsStream("Level/res/zombie.png"));
    ImageView imageView = new ImageView(zombieImg);

    //zombie position on the "matrix"...
    int currentCellRow;
    int currentCellCol;
    int posXReal; //actual pixel position
    int posYReal;
    boolean isCentered = false;
    boolean allowNextCellMove = false;
    private int health = 3;

    public static int ENEMY_SIZE = 35;



    public Enemy(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH);
        this.setSpriteHeight(SPRITE_HEIGHT);

        imageView.setFitHeight(ENEMY_SIZE);
        imageView.setFitWidth(ENEMY_SIZE);

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

        this.setBoundingBox(calcBoundingBox(ENEMY_SIZE));
    }

    public int getHealth() {
        return health;
    }

    public void dealDamage(int damage){
        this.health -= damage;
    }

    public void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix) {
        path = AStar.findPath(levelWidth, levelHeight, playerX, playerY, zombieX, zombieY, matrix);
    }

    public boolean isInSameCell() {
        boolean isInSameCell = (this.getPosX() == currentCellRow && this.getPosY() == currentCellCol);
        currentCellRow = this.getPosX();
        currentCellCol = this.getPosY();

        return isInSameCell;
    }

    public void centerZombie() {
        if (posXReal <= (this.getPosX() * Block.BLOCK_SIZE + 1)) {
            moveX(Constants.ENEMY_VELOCITY, ENEMY_SIZE);
            isCentered = false;
        } else if (posXReal >= (this.getPosX() * Block.BLOCK_SIZE + 7)) {
            moveX(-Constants.ENEMY_VELOCITY, ENEMY_SIZE);
            isCentered = false;
        } else if (posYReal <= (this.getPosY() * Block.BLOCK_SIZE + 1)) {
            moveY(Constants.ENEMY_VELOCITY, ENEMY_SIZE);
            isCentered = false;
        } else if (posYReal >= (this.getPosY() * Block.BLOCK_SIZE + 7)) {
            moveY(-Constants.ENEMY_VELOCITY, ENEMY_SIZE);
            isCentered = false;
        } else {
            isCentered = true;
            allowNextCellMove = true;
        }
    }

    @Override
    public void moveX(int value, int size) {
        super.moveX(value, ENEMY_SIZE);
    }

    @Override
    public void moveY(int value, int size) {
        super.moveY(value, ENEMY_SIZE);
    }
}

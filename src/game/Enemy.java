package game;

import game.Level.Block;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.Queue;

public class Enemy extends HumanObject {

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
        count = 4;
        columns = 4;
        offsetX = 0;
        offsetY = 0;
        width = 64;
        height = 64;

        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        this.setTranslateX(setTranslateX);
        this.setTranslateY(setTranslateY);

        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(this.imageView, Duration.millis(1000), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(this.imageView);

        boundingBox = calcBoundingBox(ENEMY_SIZE);
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
        boolean isInSameCell = (posX == currentCellRow && posY == currentCellCol);
        currentCellRow = posX;
        currentCellCol = posY;

        return isInSameCell;
    }

    public void centerZombie() {
        if (posXReal <= (posX * Block.BLOCK_SIZE + 1)) {
            moveX(Constants.ENEMY_VELOCITY, ENEMY_SIZE);
            isCentered = false;
        } else if (posXReal >= (posX * Block.BLOCK_SIZE + 7)) {
            moveX(-Constants.ENEMY_VELOCITY, ENEMY_SIZE);
            isCentered = false;
        } else if (posYReal <= (posY * Block.BLOCK_SIZE + 1)) {
            moveY(Constants.ENEMY_VELOCITY, ENEMY_SIZE);
            isCentered = false;
        } else if (posYReal >= (posY * Block.BLOCK_SIZE + 7)) {
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

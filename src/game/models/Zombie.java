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

public class Zombie extends Enemy {
    private final int SPRITE_COUNT = 4;
    private final int SPRITE_COLUMNS = 4;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH = 64;
    private final int SPRITE_HEIGHT = 64;

    private char moveDirection; // For use of randomised movement

    public Zombie(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH);
        this.setSpriteHeight(SPRITE_HEIGHT);

        this.setMoveDirection('U');
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

    public char getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(char moveDirection) {
        this.moveDirection = moveDirection;
    }

    public void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix) {
        path = AStar.findPath(levelWidth, levelHeight, playerX, playerY, zombieX, zombieY, matrix);
    }
}

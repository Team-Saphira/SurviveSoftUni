package game.models;

import game.Constants;
import game.sprites.ImageLoader;
import game.sprites.SpriteAnimation;
import game.weapons.Gun;
import game.weapons.Weapon;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player extends HumanObject {

    private final int SPRITE_COUNT = 19;
    private final int SPRITE_COLUMNS = 19;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH = 258;
    private final int SPRITE_HEIGHT = 215;

    Weapon weapon = new Gun();
    private boolean isShooting = false;
    private boolean canShoot = false;
    private int canShootTimer = 0;

    private ImageView playerImageView;

    //IB
    private int health;
    private int score;

    public Player(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH);
        this.setSpriteHeight(SPRITE_HEIGHT);

        this.setPlayerImageView(new ImageView(ImageLoader.playerImage));

        this.setObjectSize(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setFitHeight(Constants.PLAYER_SIZE);
        this.getPlayerImageView().setFitWidth(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.getPlayerImageView(),
                Duration.millis(200),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
        this.getChildren().addAll(this.getPlayerImageView());

        this.setBoundingBox(calcBoundingBox(Constants.PLAYER_SIZE));


        //IB testing
        health = 100;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean getIsShooting() {
        return isShooting;
    }

    public void setIsShooting(boolean shooting) {
        isShooting = shooting;
    }

    public boolean getCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public int getCanShootTimer() {
        return canShootTimer;
    }

    public void setCanShootTimer(int canShootTimer) {
        this.canShootTimer = canShootTimer;
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

    public void setPlayerImageView(ImageView playerImageView) {
        this.playerImageView = playerImageView;
    }
}
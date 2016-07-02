package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player extends HumanObject {

    private final int SPRITE_COUNT = 19;
    private final int SPRITE_COLUMNS = 19;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH = 258;
    private final int SPRITE_HEIGHT = 215;

    Image playerImg = new Image(getClass().getResourceAsStream("Level/res/survivor-move_handgun.png"));
    ImageView imageView = new ImageView(playerImg);

    public static int PLAYER_SIZE = 40;
    boolean isShooting = false;
    boolean canShoot = false;
    int canShootTimer = 0;

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

        imageView.setFitHeight(PLAYER_SIZE);
        imageView.setFitWidth(PLAYER_SIZE);

        imageView.setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.imageView,
                Duration.millis(200),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
        this.getChildren().addAll(this.imageView);

        this.setBoundingBox(calcBoundingBox(PLAYER_SIZE));


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

    @Override
    public void moveX(int value, int size) {
        super.moveX(value, size);
    }

    @Override
    public void moveY(int value, int size) {
        super.moveY(value, size);
    }
}
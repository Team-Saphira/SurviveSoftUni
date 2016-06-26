package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player extends HumanObject {

    Image playerImg = new Image(getClass().getResourceAsStream("Level/res/survivor-move_handgun.png"));
    ImageView imageView = new ImageView(playerImg);

    public SpriteAnimation animation;

    public static int PLAYER_SIZE = 40;
    boolean isShooting = false;
    boolean canShoot = false;
    int canShootTimer = 0;

    //IB
    private int health;
    private int score;


    public Player(int setTranslateX, int setTranslateY) {
        count = 19;
        columns = 19;
        offsetX = 0;
        offsetY = 0;
        width = 258;
        height = 215;

        imageView.setFitHeight(PLAYER_SIZE);
        imageView.setFitWidth(PLAYER_SIZE);
        this.setTranslateX(setTranslateX);
        this.setTranslateY(setTranslateY);

        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(this.imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(this.imageView);

        boundingBox = calcBoundingBox(PLAYER_SIZE);


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
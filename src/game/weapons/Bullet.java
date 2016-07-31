package game.weapons;

import game.sprites.ImageLoader;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

import java.util.Random;

public class Bullet extends Pane {
    public Point2D velocity = new Point2D(0, 0);
    private Random rand = new Random();
    private int minDamage;
    private int maxDamage;
    private int speed;
    private ImageView bulletImageView;

    public Bullet(int minDamage, int maxDamage, int speed) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.speed = speed;
        this.setBulletImageView(new ImageView(ImageLoader.BULLET_IMAGE));
        this.getChildren().add(this.getBulletImageView());
    }

    public int getMinDamage() {
        return this.minDamage;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setTarget(double x, double y) {
        this.velocity = new Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(getSpeed());
        double angle = calcAngle(this.velocity.getX(), this.velocity.getY());
        getTransforms().clear();
        getTransforms().add(new Rotate(angle, 0, 0));
    }

    public ImageView getBulletImageView() {
        return this.bulletImageView;
    }

    public void setBulletImageView(ImageView bulletImageView) {
        this.bulletImageView = bulletImageView;
    }

    public void move() {
        setTranslateX(getTranslateX() + velocity.getX());
        setTranslateY(getTranslateY() + velocity.getY());
    }

    public double calcAngle(double vecX, double vecY) {
        double angle = new Point2D(vecX, vecY).angle(1, 0);
        return vecY > 0 ? angle : -angle;
    }

    public int calculateDamage() {
        return (this.rand.nextInt((getMaxDamage() - getMinDamage()) + 1) + getMinDamage());
    }
}

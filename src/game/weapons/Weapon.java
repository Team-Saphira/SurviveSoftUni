package game.weapons;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public abstract class Weapon extends Pane{
    protected Point2D velocity = new Point2D(0, 0);

    public abstract void setTarget(double x, double y);

    public abstract int getDamagePower();

    public void move() {
        setTranslateX(getTranslateX() + velocity.getX());
        setTranslateY(getTranslateY() + velocity.getY());
    }

    public double calcAngle(double vecX, double vecY) {
        double angle = new Point2D(vecX, vecY).angle(1, 0);
        return vecY > 0 ? angle : -angle;
    }
}
package game.gui;

import javafx.scene.layout.Pane;

public class HealthBar extends Pane {
    private int healthLeft;
    private int initialHealth;
    private double offsetX;
    private double offsetY;
    private double healthBarWidth;
    private double healthBarHeight;

    public HealthBar(int initialHealth, double offsetX, double offsetY, int healthBarWidth, int healthBarHeight) {
        this.setInitialHealth(initialHealth);
        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
        this.setHealthBarWidth(healthBarWidth);
        this.setHealthBarHeight(healthBarHeight);
//        this.initRectangles(offsetX, offsetY, healthBarWidth, healthBarHeight);

    }

    public int getHealthLeft() {
        return healthLeft;
    }

    public void setHealthLeft(int healthLeft) {
        this.healthLeft = healthLeft;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getHealthBarWidth() {
        return healthBarWidth;
    }

    public void setHealthBarWidth(double width) {
        this.healthBarWidth = width;
    }

    public double getHealthBarHeight() {
        return healthBarHeight;
    }

    public void setHealthBarHeight(double height) {
        this.healthBarHeight = height;
    }

    //    private void initRectangles(int x, int y, int healthBarWidth, int healthBarHeight) {
//
//        redHealthbar = new Rectangle();
//        redHealthbar.setFill(Color.RED);
//        redHealthbar.setLayoutX(x);
//        redHealthbar.setLayoutY(y);
//        redHealthbar.setHealthBarHeight(healthBarHeight);
//        redHealthbar.setHealthBarWidth(healthBarWidth);
//
//        greenHealthbar = new Rectangle();
//        greenHealthbar.setFill(Color.GREEN);
//        greenHealthbar.setLayoutX(x);
//        greenHealthbar.setLayoutY(y);
//        greenHealthbar.setHealthBarHeight(healthBarHeight);
//        greenHealthbar.setHealthBarWidth(healthBarWidth);
//
//        healthbarFrame = new Rectangle();
//        healthbarFrame.setStroke(Color.BLACK);
//        healthbarFrame.setStrokeWidth(3);
//        healthbarFrame.setLayoutX(x);
//        healthbarFrame.setLayoutY(y);
//        healthbarFrame.setHealthBarHeight(healthBarHeight);
//        healthbarFrame.setHealthBarWidth(healthBarWidth);
//
//
//        this.getChildren().add(healthbarFrame);
//        this.getChildren().add(redHealthbar);
//        this.getChildren().add(greenHealthbar);
//
//    }

    public void reduceHealth(int amount) {

        this.healthLeft =amount;
//        resizeHealthbar(amount);

    }

//    private void resizeHealthbar(int amount) {
//
//        greenHealthbar.setHealthBarWidth(amount);
//
//    }
}

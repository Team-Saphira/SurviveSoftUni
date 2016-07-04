package game.gui;

import javafx.scene.layout.Pane;

public class HealthBar extends Pane {
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
}

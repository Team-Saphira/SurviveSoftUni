package game.gui;

import javafx.scene.layout.Pane;

public class HealthBar extends Pane {
    private double initialHealth;
    private double offsetX;
    private double offsetY;
    private double healthBarWidth;
    private double healthBarHeight;

    public HealthBar(double initialHealth, double offsetX, double offsetY, int healthBarWidth, int healthBarHeight) {
        this.setInitialHealth(initialHealth);
        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
        this.setHealthBarWidth(healthBarWidth);
        this.setHealthBarHeight(healthBarHeight);
    }

    public double getInitialHealth() {
        return initialHealth;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getHealthBarWidth() {
        return healthBarWidth;
    }

    public double getHealthBarHeight() {
        return healthBarHeight;
    }

    private void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    private void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    private void setHealthBarHeight(double height) {
        this.healthBarHeight = height;
    }

    private void setInitialHealth(double initialHealth) {
        this.initialHealth = initialHealth;
    }

    private void setHealthBarWidth(double width) {
        this.healthBarWidth = width;
    }
}

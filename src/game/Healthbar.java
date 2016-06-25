package game;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Healthbar extends Pane {
    private int healthLeft, initialHealth;
    private Rectangle greenHealthbar, redHealthbar, healthbarFrame;
    private int offsetX, offsetY, width, height;

    public Healthbar(int initialHealth, int offsetX, int offsetY, int width, int height) {
        this.initialHealth = initialHealth;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.initRectangles(offsetX, offsetY, width, height);

    }

    private void initRectangles(int x, int y, int width, int height) {

        redHealthbar = new Rectangle();
        redHealthbar.setFill(Color.RED);
        redHealthbar.setTranslateX(x);
        redHealthbar.setTranslateY(y);
        redHealthbar.setHeight(height);
        redHealthbar.setWidth(width);

        greenHealthbar = new Rectangle();
        greenHealthbar.setFill(Color.GREEN);
        greenHealthbar.setTranslateX(x);
        greenHealthbar.setTranslateY(y);
        greenHealthbar.setHeight(height);
        greenHealthbar.setWidth(width);

        healthbarFrame = new Rectangle();
        healthbarFrame.setStroke(Color.BLACK);
        healthbarFrame.setStrokeWidth(3);
        healthbarFrame.setTranslateX(x);
        healthbarFrame.setTranslateY(y);
        healthbarFrame.setHeight(height);
        healthbarFrame.setWidth(width);


        this.getChildren().add(healthbarFrame);
        this.getChildren().add(redHealthbar);
        this.getChildren().add(greenHealthbar);

    }

    public void reduceHealth(int amount) {

        this.healthLeft =amount;
        resizeHealthbar(amount);

    }

    private void resizeHealthbar(int amount) {

        greenHealthbar.setWidth(amount);

    }

}

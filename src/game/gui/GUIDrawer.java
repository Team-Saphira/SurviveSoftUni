package game.gui;

import game.sprites.ImageLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GUIDrawer extends Pane{
    private ImageView healthBarImage;
    private ImageView healthBarBackgroundImage;
    private HealthBar healthBar;

    public GUIDrawer(HealthBar healthBar) {
        this.healthBarImage = new ImageView(ImageLoader.healthBar);
        this.healthBarBackgroundImage = new ImageView(ImageLoader.healthBarBackground);
        this.setHealthBar(healthBar);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public ImageView getHealthBarImage() {
        return healthBarImage;
    }

    public void setHealthBarImage(ImageView healthBarImage) {
        this.healthBarImage = healthBarImage;
    }

    public ImageView getHealthBarBackgroundImage() {
        return healthBarBackgroundImage;
    }

    public void setHealthBarBackgroundImage(ImageView healthBarBackgroundImage) {
        this.healthBarBackgroundImage = healthBarBackgroundImage;
    }

    public void drawHealthBar() {
        this.healthBarImage.setLayoutX(this.getHealthBar().getOffsetX());
        this.healthBarImage.setLayoutY(this.getHealthBar().getOffsetY());
        this.healthBarImage.setFitHeight(this.getHealthBar().getHealthBarHeight());
        this.healthBarImage.setFitWidth(this.getHealthBar().getHealthBarWidth());

        this.healthBarBackgroundImage.setLayoutX(this.getHealthBar().getOffsetX());
        this.healthBarBackgroundImage.setLayoutY(this.getHealthBar().getOffsetY());
        this.healthBarBackgroundImage.setFitHeight(this.getHealthBar().getHealthBarHeight());
        this.healthBarBackgroundImage.setFitWidth(this.getHealthBar().getHealthBarWidth());

        this.getChildren().add(healthBarBackgroundImage);
        this.getChildren().add(healthBarImage);
    }
}

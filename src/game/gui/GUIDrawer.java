package game.gui;

import game.sprites.ImageLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GUIDrawer extends Pane{
    private ImageView healthBarImage;
    private ImageView healthBarBackgroundImage;
    private HealthBar healthBar;
    private ImageView weaponBarImage;
    private WeaponBar weaponBar;

    public GUIDrawer(HealthBar healthBar, WeaponBar weaponBar) {
        this.setHealthBarImage(new ImageView(ImageLoader.healthBar));
        this.setHealthBarBackgroundImage(new ImageView(ImageLoader.healthBarBackground));
        this.setHealthBar(healthBar);

        this.setWeaponBarImage(new ImageView(ImageLoader.pistolImage));
        this.setWeaponBar(weaponBar);
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

    public WeaponBar getWeaponBar() {
        return weaponBar;
    }

    public void setWeaponBar(WeaponBar weaponBar) {
        this.weaponBar = weaponBar;
    }

    public ImageView getWeaponBarImage() {
        return weaponBarImage;
    }

    public void setWeaponBarImage(ImageView weaponBarImage) {
        this.weaponBarImage = weaponBarImage;
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

    public void drawWeaponBar(){
        this.weaponBarImage.setLayoutX(this.getWeaponBar().getOffsetX());
        this.weaponBarImage.setLayoutY(this.getWeaponBar().getOffsetY());
        this.weaponBarImage.setFitWidth(this.getWeaponBar().getWeaponBarWidth());
        this.weaponBarImage.setFitHeight(this.getWeaponBar().getWeaponBarHeight());

        this.getChildren().add(weaponBarImage);
    }

    public void changeWeaponImage(String weaponType) {
        this.getChildren().remove(this.getWeaponBarImage());
        if (weaponType.equals("Pistol")) {
            this.setWeaponBarImage(new ImageView(ImageLoader.pistolImage));
        } else if (weaponType.equals("MachineGun")) {
            this.setWeaponBarImage(new ImageView(ImageLoader.uziImage));
        }
        this.drawWeaponBar();
    }
}
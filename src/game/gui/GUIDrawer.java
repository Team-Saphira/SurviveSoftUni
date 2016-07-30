package game.gui;

import game.sprites.ImageLoader;
import game.weapons.WeaponType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GUIDrawer extends Pane{
    private ImageView healthBarImage;
    private ImageView healthBarBackgroundImage;
    private HealthBar healthBar;
    private ImageView weaponBarImage;
    private WeaponBar weaponBar;
    private HealthPoints healthPoints;
    private ScorePoints scorePoints;
    private CurrentWeaponDisplay currentWeaponDisplay;
    private ImageView currentWeaponDisplayImage;
    private WeaponTextDisplay weaponTextDisplay;

    public GUIDrawer(HealthBar healthBar,
                     WeaponBar weaponBar,
                     HealthPoints healthPoints,
                     ScorePoints scorePoints,
                     CurrentWeaponDisplay currentWeaponDisplay,
                     WeaponTextDisplay weaponTextDisplay) {

        this.setHealthBarImage(new ImageView(ImageLoader.HEALTH_BAR));
        this.setHealthBarBackgroundImage(new ImageView(ImageLoader.HEALTH_BAR_BACKGROUND));
        this.setHealthBar(healthBar);

        this.setWeaponBarImage(new ImageView(ImageLoader.WEAPON_BAR_BACKGROUND));
        this.setWeaponBar(weaponBar);
        this.setHealthPoints(healthPoints);
        this.setScorePoints(scorePoints);
        this.setCurrentWeaponDisplay(currentWeaponDisplay);
        this.setCurrentWeaponDisplayImage(new ImageView(ImageLoader.PISTOL_IMAGE));
        this.setWeaponTextDisplay(weaponTextDisplay);
    }

    private void setWeaponTextDisplay(WeaponTextDisplay weaponTextDisplay) {
        this.weaponTextDisplay = weaponTextDisplay;
    }

    public CurrentWeaponDisplay getCurrentWeaponDisplay() {
        return currentWeaponDisplay;
    }

    private void setCurrentWeaponDisplay(CurrentWeaponDisplay currentWeaponDisplay) {
        this.currentWeaponDisplay = currentWeaponDisplay;
    }

    public ImageView getCurrentWeaponDisplayImage() {
        return currentWeaponDisplayImage;
    }

    private void setCurrentWeaponDisplayImage(ImageView currentWeaponDisplayImage) {
        this.currentWeaponDisplayImage = currentWeaponDisplayImage;
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

    public void setHealthBarBackgroundImage(ImageView healthBarBackgroundImage) {
        this.healthBarBackgroundImage = healthBarBackgroundImage;
    }

    public WeaponBar getWeaponBar() {
        return weaponBar;
    }

    public void setWeaponBar(WeaponBar weaponBar) {
        this.weaponBar = weaponBar;
    }

    public void setWeaponBarImage(ImageView weaponBarImage) {
        this.weaponBarImage = weaponBarImage;
    }

    private void setHealthPoints(HealthPoints healthPoints) {
        this.healthPoints = healthPoints;
    }

    private void setScorePoints(ScorePoints scorePoints) {
        this.scorePoints = scorePoints;
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

    public void drawCurrentWeapon() {
        this.currentWeaponDisplayImage.setLayoutX(this.getCurrentWeaponDisplay().getOffsetX());
        this.currentWeaponDisplayImage.setLayoutY(this.getCurrentWeaponDisplay().getOffsetY());
        this.currentWeaponDisplayImage.setFitWidth(this.getCurrentWeaponDisplay().getCurrentWeaponDisplayWidth());
        this.currentWeaponDisplayImage.setFitHeight(this.getCurrentWeaponDisplay().getCurrentWeaponDisplayHeight());

        this.getChildren().add(currentWeaponDisplayImage);
    }

    public void changeWeaponImage(WeaponType weaponType) {

        this.getChildren().remove(this.getCurrentWeaponDisplayImage());
        if (weaponType == WeaponType.PISTOL) {
            this.setCurrentWeaponDisplayImage(new ImageView(ImageLoader.PISTOL_IMAGE));
        } else if (weaponType == WeaponType.MACHINE_GUN) {
            this.setCurrentWeaponDisplayImage(new ImageView(ImageLoader.UZI_IMAGE));
        }

        this.drawCurrentWeapon();
    }

    public void drawHealthPoints() {
        this.getChildren().add(this.healthPoints);
    }

    public void drawScorePoints() {
        this.getChildren().add(this.scorePoints);
    }

    public void drawWeaponText() {
        this.getChildren().add(this.weaponTextDisplay);
    }
}
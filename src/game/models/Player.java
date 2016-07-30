package game.models;

import game.Constants;
import game.bonusItems.enums.HealthItem;
import game.sprites.ImageLoader;
import game.sprites.SpriteAnimation;
import game.weapons.MachineGun;
import game.weapons.Pistol;
import game.weapons.Weapon;
import game.weapons.WeaponType;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.HashMap;

public class Player extends HumanObjectImpl {

    private final int SPRITE_COUNT = 20;
    private final int SPRITE_COLUMNS = 20;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH = 263;
    private final int SPRITE_HEIGHT = 220;
    private final int PLAYER_INITIAL_SCORE = 0;
    private final int PLAYER_INITIAL_LIVES = 3;

    private Weapon currentWeapon;
    private HashMap<WeaponType, Weapon> weaponList = new HashMap<>();
    private boolean isShooting = false;
    private boolean canShoot = false;
    private int canShootTimer = 0;
    private HealthItem healthItem;
    private ImageView playerImageView;

    //IB
    private double health;
    private int score;
    private int lives;

    public Player(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH);
        this.setSpriteHeight(SPRITE_HEIGHT);


        this.setPlayerImageView(new ImageView(ImageLoader.PLAYER_IMAGE));

        this.setObjectSize(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setFitHeight(Constants.PLAYER_SIZE);
        this.getPlayerImageView().setFitWidth(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.getPlayerImageView(),
                Duration.millis(200),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
        this.getChildren().addAll(this.getPlayerImageView());

        this.setBoundingBox(calcBoundingBox(Constants.PLAYER_SIZE));

        currentWeapon = new Pistol();
        addWeapon(new Pistol());
        addWeapon(new MachineGun());

        //IB testing
        this.setHealth(Constants.PLAYER_INITIAL_HEALTH);
        this.setScore(PLAYER_INITIAL_SCORE);
        this.setLives(PLAYER_INITIAL_LIVES);
        this.setHealthItem(HealthItem.HEARTH);
    }

    public HealthItem getHealthItem() {
        return healthItem;
    }

    private void setHealthItem(HealthItem healthItem) {
        this.healthItem = healthItem;
    }

    public int getLives() {
        return lives;
    }

    private void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public double getHealth() {
        return health;
    }

    private void setHealth(double health) {
        this.health = health;
    }

    public boolean getIsShooting() {
        return isShooting;
    }

    private void setIsShooting(boolean shooting) {
        isShooting = shooting;
    }

    public boolean getCanShoot() {
        return canShoot;
    }

    private void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public int getCanShootTimer() {
        return canShootTimer;
    }

    private void setCanShootTimer(int canShootTimer) {
        this.canShootTimer = canShootTimer;
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

    private void setPlayerImageView(ImageView playerImageView) {
        this.playerImageView = playerImageView;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    private void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void addBonusHealth() {

        if (this.getHealth() <= Constants.PLAYER_INITIAL_HEALTH - this.getHealthItem().getBonusValue()) {
            this.setHealth(this.getHealth() + this.getHealthItem().getBonusValue());
        } else {
            this.setHealth(Constants.PLAYER_INITIAL_HEALTH);
        }
    }

    public void gainLife() {
        this.setLives(this.getLives() + 1);
    }

    public void addWeapon(Weapon weapon) {
        this.weaponList.put(weapon.getWeaponType(), weapon);
    }

    public void changeWeapon(WeaponType weaponType) {
        if (this.weaponList.containsKey(weaponType)) {
            setCurrentWeapon(this.weaponList.get(weaponType));
        }
    }

    public void changePlayerState(String stateName) {
        this.getChildren().remove(getPlayerImageView());
        changePlayerWeaponImage(stateName);
        this.getChildren().addAll(this.getPlayerImageView());
    }

    public void changeHealthItem(HealthItem healthItem){
        this.setHealthItem(healthItem);
    }

    public void changeLives(int lives){
        this.setLives(lives);
    }

    public void changeScore(int score){
        this.setScore(score);
    }

    public void changeHealth(double health){
        this.setHealth(health);
    }

    public void isShooting(boolean shooting){
        this.setIsShooting(shooting);
    }

    public void changeCanShoot(boolean canShoot){
        this.setCanShoot(canShoot);
    }

    public void changeCanShootTimer(int canShootTimer){
        this.setCanShootTimer(canShootTimer);
    }



    private void changePlayerWeaponImage(String stateName) {

        int spriteWidth = 0;

        switch (stateName) {
            case "PistolState":
                spriteWidth = SPRITE_WIDTH;
                break;
            case "MachineGunState":
                spriteWidth = 323;
                break;
        }

        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(0);
        this.setSpriteOffsetY(0);
        this.setSpriteWidth(spriteWidth);
        this.setSpriteHeight(SPRITE_HEIGHT);

        if (stateName.equals("PistolState")) {
            this.setPlayerImageView(new ImageView(ImageLoader.PLAYER_IMAGE));
        } else if (stateName.equals("MachineGunState")) {
            this.setPlayerImageView(new ImageView(ImageLoader.PLAYER_IMAGE_MACHINE_GUN));
        }

        this.setObjectSize(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setFitHeight(Constants.PLAYER_SIZE);
        this.getPlayerImageView().setFitWidth(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.getPlayerImageView(),
                Duration.millis(200),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
    }

    // TODO when player setHealth to private
    //public void dealDamageHealth() {
    //    setHealth(this.getHealth() - Constants.HEALTH_REDUCTION);
    //}
}
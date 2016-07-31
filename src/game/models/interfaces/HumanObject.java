package game.models.interfaces;

import game.bonusItems.enums.HealthItem;
import game.weapons.Weapon;
import game.weapons.WeaponType;
import javafx.scene.image.ImageView;

public interface HumanObject extends GameMovableObject{

    HealthItem getHealthItem();

    int getLives();

    int getScore();

    double getHealth();

    boolean getIsShooting();

    boolean getCanShoot();

    int getCanShootTimer();

    ImageView getPlayerImageView();

    Weapon getCurrentWeapon();

    void addBonusHealth();

    void gainLife();

    void addWeapon(Weapon weapon);

    void changeWeapon(WeaponType weaponType);

    void changePlayerState(String stateName);

    void changeHealthItem(HealthItem healthItem);

    void changeLives(int lives);

    void changeScore(int score);

    void changeHealth(double health);

    void isShooting(boolean shooting);

    void changeCanShoot(boolean canShoot);

    void changeCanShootTimer(int canShootTimer);

}

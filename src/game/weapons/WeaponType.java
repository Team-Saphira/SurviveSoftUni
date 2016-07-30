package game.weapons;

import game.sprites.ImageLoader;
import javafx.scene.image.Image;

public enum WeaponType {
    PISTOL("Pistol", ImageLoader.PISTOL_IMAGE, 12, 1, 2, 30, 7, 21),
    MACHINE_GUN("Machine Gun", ImageLoader.UZI_IMAGE, 20, 2, 4, 15, 30, 90);

    private String weaponName;
    private Image weaponImage;
    private int bulletSpeed;
    private int minDamage;
    private int maxDamage;
    private int shootDelayTime;
    private int clipCapacity;
    private int maxBulletsCapacity;

    WeaponType(String weaponName,
               Image weaponImage,
               int bulletSpeed,
               int minDamage,
               int maxDamage,
               int shootDelayTime,
               int clipCapacity,
               int maxBulletsCapacity) {
        this.weaponName = weaponName;
        this.weaponImage = weaponImage;
        this.bulletSpeed = bulletSpeed;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.shootDelayTime = shootDelayTime;
        this.clipCapacity = clipCapacity;
        this.maxBulletsCapacity = maxBulletsCapacity;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public Image getWeaponImage() {
        return weaponImage;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getShootDelayTime() {
        return shootDelayTime;
    }

    public int getClipCapacity() {
        return clipCapacity;
    }

    public int getMaxBulletsCapacity() {
        return maxBulletsCapacity;
    }
}

package game.weapons;

import javafx.scene.image.Image;

public abstract class Weapon {
    private WeaponType weaponType;

    protected Weapon(WeaponType weaponType) {
        this.setWeaponType(weaponType);

    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    private void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

}
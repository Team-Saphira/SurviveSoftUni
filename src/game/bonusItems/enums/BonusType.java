package game.bonusItems.enums;

import game.bonusItems.HealthBonus;
import game.bonusItems.WeaponPistolBonus;
import game.bonusItems.WeaponUziBonus;

public enum BonusType {

    HEARTH(HealthBonus.class),
    PISTOL(WeaponPistolBonus.class),
    MACHINE_GUN(WeaponUziBonus.class);

    public static final BonusType values[] = values();

    private Class bonusClass;

    BonusType(Class bonusClass) {
        this.bonusClass = bonusClass;
    }

    public Class getBonusClass() {
        return bonusClass;
    }
}


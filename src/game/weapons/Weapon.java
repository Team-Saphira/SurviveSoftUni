package game.weapons;

public abstract class Weapon {
    public abstract String getName();

    public abstract int minDamage();

    public abstract int maxDamage();

    public abstract int bulletSpeed();

    public abstract int shootDelayTime();
}
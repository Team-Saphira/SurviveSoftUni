package game.weapons;

public class Pistol extends Weapon {
    private static final String NAME = "Pistol";
    private static final int BULLET_SPEED = 12;
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 2;
    private static final int SHOOT_DELAY_TIME = 30;

    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public int minDamage() {
        return this.MIN_DAMAGE;
    }

    @Override
    public int maxDamage() {
        return this.MAX_DAMAGE;
    }

    @Override
    public int bulletSpeed() {
        return this.BULLET_SPEED;
    }

    @Override
    public int shootDelayTime() {
        return this.SHOOT_DELAY_TIME;
    }
}

package game.weapons;

public class MachineGun extends Weapon {
    private static final String NAME = "MachineGun";
    private static final int BULLET_SPEED = 20;
    private static final int MIN_DAMAGE = 2;
    private static final int MAX_DAMAGE = 4;
    private static final int SHOOT_DELAY_TIME = 15;

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

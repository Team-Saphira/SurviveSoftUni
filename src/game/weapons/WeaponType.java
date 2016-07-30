package game.weapons;

public enum WeaponType {
    PISTOL("Pistol", 12, 1, 2, 30),
    MACHINE_GUN("Machine Gun", 20, 2, 4, 15);

    private String weaponName;
    private int bulletSpeed;
    private int minDamage;
    private int maxDamage;
    private int shootDelayTime;

    WeaponType(String weaponName, int bulletSpeed, int minDamage, int maxDamage, int shootDelayTime) {
        this.weaponName = weaponName;
        this.bulletSpeed = bulletSpeed;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.shootDelayTime = shootDelayTime;
    }

    public String getWeaponName() {
        return weaponName;
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
}

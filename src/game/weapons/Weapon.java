package game.weapons;

public abstract class Weapon {
    private WeaponType weaponType;
    private int bulletsInClip;
    private int totalBullets;

    protected Weapon(WeaponType weaponType) {
        this.setWeaponType(weaponType);
        this.setBulletsInClip(weaponType.getClipCapacity());
        this.setTotalBullets(weaponType.getMaxBulletsCapacity());
    }

    public WeaponType getWeaponType() {
        return this.weaponType;
    }

    private void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public int getBulletsInClip() {
        return this.bulletsInClip;
    }

    public void setBulletsInClip(int bulletsInClip) {
        this.bulletsInClip = bulletsInClip;
    }

    public int getTotalBullets() {
        return totalBullets;
    }

    public void setTotalBullets(int totalBullets) {
        this.totalBullets = totalBullets;
        if (totalBullets>this.getWeaponType().getMaxBulletsCapacity()){
            this.totalBullets = this.getWeaponType().getMaxBulletsCapacity();
        }
    }

    public boolean shoot() {
        if (this.getBulletsInClip() == 0) {
            return false;
        }
        this.setBulletsInClip(this.getBulletsInClip() - 1);
        return true;
    }

    public boolean addClip(){
        if (this.getTotalBullets()==this.getWeaponType().getMaxBulletsCapacity()){
            return false;
        }
        this.setTotalBullets(this.getTotalBullets()+this.getWeaponType().getClipCapacity());
        return true;
    }

    public boolean reload() {
        if (this.getBulletsInClip() == this.getWeaponType().getClipCapacity()) {
            return false;
        }
        if (this.getTotalBullets() > this.getWeaponType().getClipCapacity()) {
            this.setBulletsInClip(this.getWeaponType().getClipCapacity());
            this.setTotalBullets(this.getTotalBullets() - this.getWeaponType().getClipCapacity());
            return true;
        }
        this.setBulletsInClip(this.getTotalBullets());
        this.setTotalBullets(0);
        return true;
    }
}
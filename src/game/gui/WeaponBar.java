package game.gui;

public class WeaponBar {

    private int playerInitialScore;
    private double offsetX;
    private double offsetY;
    private double weaponBarWidth;
    private double weaponBarHeight;

    public WeaponBar(int playerInitialScore, double offsetX, double offsetY, int weaponBarWidth, int weaponBarHeight) {
        this.setPlayerInitialScore(playerInitialScore);
        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
        this.setWeaponBarWidth(weaponBarWidth);
        this.setWeaponBarHeight(weaponBarHeight);
    }

    private void setPlayerInitialScore(int playerInitialScore) {
        this.playerInitialScore = playerInitialScore;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getWeaponBarWidth() {
        return weaponBarWidth;
    }

    public void setWeaponBarWidth(double weaponBarWidth) {
        this.weaponBarWidth = weaponBarWidth;
    }

    public double getWeaponBarHeight() {
        return weaponBarHeight;
    }

    public void setWeaponBarHeight(double weaponBarHeight) {
        this.weaponBarHeight = weaponBarHeight;
    }
}

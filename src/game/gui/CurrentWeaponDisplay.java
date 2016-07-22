package game.gui;

public class CurrentWeaponDisplay {

    private double offsetX;
    private double offsetY;
    private double currentWeaponDisplayWidth;
    private double currentWeaponDisplayHeight;

    public CurrentWeaponDisplay(double offsetX, double offsetY, int currentWeaponDisplayWidth, int currentWeaponDisplayHeight) {
        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
        this.setCurrentWeaponDisplayWidth(currentWeaponDisplayWidth);
        this.setCurrentWeaponDisplayHeight(currentWeaponDisplayHeight);
    }

    public double getOffsetX() {
        return this.offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getCurrentWeaponDisplayWidth() {
        return this.currentWeaponDisplayWidth;
    }

    public void setCurrentWeaponDisplayWidth(double currentWeaponDisplayWidth) {
        this.currentWeaponDisplayWidth = currentWeaponDisplayWidth;
    }

    public double getCurrentWeaponDisplayHeight() {
        return this.currentWeaponDisplayHeight;
    }

    public void setCurrentWeaponDisplayHeight(double currentWeaponDisplayHeight) {
        this.currentWeaponDisplayHeight = currentWeaponDisplayHeight;
    }
}

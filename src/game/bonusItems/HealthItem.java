package game.bonusItems;

public enum HealthItem {

    HEARTH(10);

    private int bonusValue;

    private HealthItem(int bonusValue) {
        this.bonusValue = bonusValue;
    }

    public int getBonusValue() {
        return bonusValue;
    }
}

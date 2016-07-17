package game.moveLogic;

import game.Constants;
import game.models.Enemy;
import game.models.Zombie;

public class MoveEnemyManager extends MoveManager {
    private Enemy enemy;

    public MoveEnemyManager(Enemy enemy) {
        super(enemy);
        this.enemy = enemy;
    }

    public boolean isInSameCell() {
        boolean isInSameCell = (this.enemy.getPosX() == this.enemy.getCurrentCellRow() &&
                this.enemy.getPosY() == this.enemy.getCurrentCellCol());
        this.enemy.setCurrentCellRow(this.enemy.getPosX());
        this.enemy.setCurrentCellCol(this.enemy.getPosY());

        return isInSameCell;
    }

    public void centerZombie() {
        if (this.enemy.getPosXReal() <= (this.enemy.getPosX() * Constants.BLOCK_SIZE + 1)) {
            moveX(Constants.ZOMBIE_VELOCITY);
            this.enemy.setIsCentered(false);
        } else if (this.enemy.getPosXReal() >= (this.enemy.getPosX() * Constants.BLOCK_SIZE + 7)) {
            moveX(-Constants.ZOMBIE_VELOCITY);
            this.enemy.setIsCentered(false);
        } else if (this.enemy.getPosYReal() <= (this.enemy.getPosY() * Constants.BLOCK_SIZE + 1)) {
            moveY(Constants.ZOMBIE_VELOCITY);
            this.enemy.setIsCentered(false);
        } else if (this.enemy.getPosYReal() >= (this.enemy.getPosY() * Constants.BLOCK_SIZE + 7)) {
            moveY(-Constants.ZOMBIE_VELOCITY);
            this.enemy.setIsCentered(false);
        } else {
            this.enemy.setIsCentered(true);
            this.enemy.setAllowNextCellMove(true);
        }
    }
}

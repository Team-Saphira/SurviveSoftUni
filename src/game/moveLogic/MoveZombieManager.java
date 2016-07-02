package game.moveLogic;

import game.Constants;
import game.models.Enemy;
import game.level.Block;

public class MoveZombieManager extends MoveManager {
    private Enemy enemy;

    public MoveZombieManager(Enemy zombie) {
        super(zombie);
        this.enemy = zombie;
    }

    public boolean isInSameCell() {
        boolean isInSameCell = (this.enemy.getPosX() == this.enemy.getCurrentCellRow() &&
                this.enemy.getPosY() == this.enemy.getCurrentCellCol());
        this.enemy.setCurrentCellRow(this.enemy.getPosX());
        this.enemy.setCurrentCellCol(this.enemy.getPosY());

        return isInSameCell;
    }

    public void centerZombie() {
        if (this.enemy.getPosXReal() <= (this.enemy.getPosX() * Block.BLOCK_SIZE + 1)) {
            moveX(Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
            this.enemy.setIsCentered(false);
        } else if (this.enemy.getPosXReal() >= (this.enemy.getPosX() * Block.BLOCK_SIZE + 7)) {
            moveX(-Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
            this.enemy.setIsCentered(false);
        } else if (this.enemy.getPosYReal() <= (this.enemy.getPosY() * Block.BLOCK_SIZE + 1)) {
            moveY(Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
            this.enemy.setIsCentered(false);
        } else if (this.enemy.getPosYReal() >= (this.enemy.getPosY() * Block.BLOCK_SIZE + 7)) {
            moveY(-Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
            this.enemy.setIsCentered(false);
        } else {
            this.enemy.setIsCentered(true);
            this.enemy.setAllowNextCellMove(true);
        }
    }
}

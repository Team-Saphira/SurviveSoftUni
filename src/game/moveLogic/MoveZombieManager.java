package game.moveLogic;

import game.Constants;
import game.models.Zombie;
import game.level.Block;

public class MoveZombieManager extends MoveManager {
    private Zombie zombie;

    public MoveZombieManager(Zombie zombie) {
        super(zombie);
        this.zombie = zombie;
    }

    public boolean isInSameCell() {
        boolean isInSameCell = (this.zombie.getPosX() == this.zombie.getCurrentCellRow() &&
                this.zombie.getPosY() == this.zombie.getCurrentCellCol());
        this.zombie.setCurrentCellRow(this.zombie.getPosX());
        this.zombie.setCurrentCellCol(this.zombie.getPosY());

        return isInSameCell;
    }

    public void centerZombie() {
        if (this.zombie.getPosXReal() <= (this.zombie.getPosX() * Block.BLOCK_SIZE + 1)) {
            moveX(Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
            this.zombie.setIsCentered(false);
        } else if (this.zombie.getPosXReal() >= (this.zombie.getPosX() * Block.BLOCK_SIZE + 7)) {
            moveX(-Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
            this.zombie.setIsCentered(false);
        } else if (this.zombie.getPosYReal() <= (this.zombie.getPosY() * Block.BLOCK_SIZE + 1)) {
            moveY(Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
            this.zombie.setIsCentered(false);
        } else if (this.zombie.getPosYReal() >= (this.zombie.getPosY() * Block.BLOCK_SIZE + 7)) {
            moveY(-Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
            this.zombie.setIsCentered(false);
        } else {
            this.zombie.setIsCentered(true);
            this.zombie.setAllowNextCellMove(true);
        }
    }
}

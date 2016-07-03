package game;

import game.gui.Healthbar;
import game.gui.ScoreBar;
import game.level.Block;
import game.level.Level;
import game.models.Zombie;
import game.models.Player;
import game.moveLogic.AStar;
import game.moveLogic.Movable;
import game.moveLogic.MoveZombieManager;
import game.weapons.Bullet;
import game.weapons.Weapon;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Controller {
    private static final int HEALTH_REDUCTION = 1;
    private Player player;
    private List<KeyCode> inputKeyCodes;
    private Set<Zombie> zombieSet;
    private Pane root;
    private List<Bullet> bulletList;

    //IB
    private Healthbar healthbar;
    private ScoreBar scoreBar;
    private List<BonusItem> bonusItems;



    public Controller(Player player,
                      List<KeyCode> inputKeyCodes,
                      Set<Zombie> zombieSet,
                      Pane root,
                      List<Bullet> bulletList,
                      Healthbar healthbar,
                      ScoreBar scoreBar,
                      List<BonusItem> bonusItems) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setZombieSet(zombieSet);
        this.setRoot(root);
        this.setBulletList(bulletList);
        this.setHealthbar(healthbar);
        this.setScoreBar(scoreBar);
        this.setBonusItems(bonusItems);
    }

    public ScoreBar getScoreBar() {
        return scoreBar;
    }

    public void setScoreBar(ScoreBar scoreBar) {
        this.scoreBar = scoreBar;
    }

    public Healthbar getHealthbar() {
        return healthbar;
    }

    public void setHealthbar(Healthbar healthbar) {
        this.healthbar = healthbar;
    }

    public List<BonusItem> getBonusItems() {
        return bonusItems;
    }

    public void setBonusItems(List<BonusItem> bonusItems) {
        this.bonusItems = bonusItems;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<KeyCode> getInputKeyCodes() {
        return inputKeyCodes;
    }

    public void setInputKeyCodes(List<KeyCode> inputKeyCodes) {
        this.inputKeyCodes = inputKeyCodes;
    }

    public Set<Zombie> getZombieSet() {
        return zombieSet;
    }

    public void setZombieSet(Set<Zombie> zombieSet) {
        this.zombieSet = zombieSet;
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public void updatePlayer(Movable movePlayerManager) {
        this.getPlayer().setPosX((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinX() / Block.BLOCK_SIZE);
        this.getPlayer().setPosY((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinY() / Block.BLOCK_SIZE);

        for (KeyCode kc : this.getInputKeyCodes()) {
            switch (kc) {
                case W:
                    this.player.getPlayerImageView().setRotate(270);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.moveY(-Constants.PLAYER_VELOCITY);
                    break;
                case S:
                    this.player.getPlayerImageView().setRotate(90);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.moveY(Constants.PLAYER_VELOCITY);
                    break;
                case A:
                    this.player.getPlayerImageView().setRotate(180);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.moveX(-Constants.PLAYER_VELOCITY);
                    break;
                case D:
                    this.player.getPlayerImageView().setRotate(0);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.moveX(Constants.PLAYER_VELOCITY);
                    break;
                case DIGIT1:
                    this.getPlayer().changeWeapon("Pistol");
                    break;
                case DIGIT2:
                    this.getPlayer().changeWeapon("MachineGun");
                    break;
                default:
                    break;
            }
        }

        //IB testing Player health reduction
        for (Zombie zombie : zombieSet) {
            Shape intersect = Shape.intersect(this.player.getBoundingBox(), zombie.getBoundingBox());
            if (intersect.getBoundsInLocal().getWidth()!=-1) {
                this.getPlayer().setHealth(this.getPlayer().getHealth()-HEALTH_REDUCTION);

                //super ugly!!!
                updateHealthbar();


                break;
            }
        }

        if (this.getPlayer().getHealth()<0) {

            //TODO "Game over" screen or lose live
        }
    }

    public void updateEnemies() {

        ArrayList<Zombie> zomblesToRemove= new ArrayList<>();
        for (Zombie zombie : this.getZombieSet()) {

            MoveZombieManager moveZombieManager = new MoveZombieManager(zombie);

            if (zombie.getHealth()<=0){
                zomblesToRemove.add(zombie);
                continue;
            }

            //updates zombie position
            zombie.setPosXReal((int) zombie.localToParent(zombie.getBoundsInLocal()).getMinX());
            zombie.setPosX(zombie.getPosXReal() / Block.BLOCK_SIZE);
            zombie.setPosYReal((int) zombie.localToParent(zombie.getBoundsInLocal()).getMinY());
            zombie.setPosY(zombie.getPosYReal() / Block.BLOCK_SIZE);

            //find shortest path to player
            zombie.updatePath(Level.levelBlockWidth,
                    Level.levelBlockHeight,
                    player.getPosX(),
                    player.getPosY(),
                    zombie.getPosX(),
                    zombie.getPosY(),
                    Level.levelBlockMatrix);

            //first node is current position. If npc is to move it needs the next node.
            AStar.Cell nextNode = zombie.path.poll();


            if (!zombie.getAllowNextCellMove()) {
                moveZombieManager.centerZombie();
                continue;
            }

            if (!moveZombieManager.isInSameCell()) {
                zombie.setAllowNextCellMove(false);
            }

            if (zombie.path.isEmpty()) {
                continue;
            }

            nextNode = zombie.path.poll();

            //TODO fix names in A* to make more sense
            if (nextNode.x < zombie.getPosX()) {
                //moves left -> should become up
                moveZombieManager.moveX(-Constants.ENEMY_VELOCITY);
                zombie.getAnimation().play();
                zombie.getAnimation().setOffsetY(2 * 64);
            } else if (nextNode.x > zombie.getPosX()) {
                //moves right -> should become down
                moveZombieManager.moveX(Constants.ENEMY_VELOCITY);
                zombie.getAnimation().play();
                zombie.getAnimation().setOffsetY(64);
            } else if (nextNode.y < zombie.getPosY()) {
                //moves up -> should become left
                moveZombieManager.moveY(-Constants.ENEMY_VELOCITY);
                zombie.getAnimation().play();
                zombie.getAnimation().setOffsetY(0);
            } else if (nextNode.y > zombie.getPosY()) {
                //moves down -> should become right
                moveZombieManager.moveY(Constants.ENEMY_VELOCITY);
                zombie.getAnimation().play();
                zombie.getAnimation().setOffsetY(3 * 64);
            }
        }

        for (Zombie zombie : zomblesToRemove) {

            //IB testing bonus system
            double rnd = Math.random();

            //IB Threshold set to 0.8 for testing purpose only!
            if (rnd < BonusItem.RANDOM_DROP_THRESHOLD) {
                updateBonusItems(zombie.getPosXReal(), zombie.getPosYReal());
            }

            this.zombieSet.remove(zombie);
            this.getRoot().getChildren().remove(zombie);

            this.player.setScore(this.player.getScore()+1);
            updateScoreBar();
        }
    }

    public void updateBullets() {
        this.getPlayer().setCanShootTimer(this.getPlayer().getCanShootTimer() + 1);
        if (this.getPlayer().getCanShootTimer() > this.player.getCurrentWeapon().shootDelayTime()) {
            this.getPlayer().setCanShoot(true);
            this.getPlayer().setCanShootTimer(0);
        }

        if (this.getPlayer().getIsShooting()) {
            this.getBulletList().forEach(Bullet::move);
        }

        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        ArrayList<Block> wallsToRemove = new ArrayList<>();
        for (Bullet bullet : this.getBulletList()) {
            boolean bulletRemoved = false;
            for (Zombie zombie : this.getZombieSet()) {
                if (bullet.getBoundsInParent().intersects(zombie.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);

                    int damage = bullet.calculateDamage();
                    System.out.println(damage);
                    zombie.dealDamage(damage);

                    bulletRemoved = true;
                    break;
                }
            }

            if (bulletRemoved) {
                continue;
            }

            for (Block wall : Level.platforms)
                if (bullet.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);
                    if (wall.getBlockType() == Block.BlockType.BRICK && wall.getOpacity() - 0.35 < 0) {
                        this.getRoot().getChildren().remove(wall.getBlockBBox());
                        this.getRoot().getChildren().remove(wall);
                        wallsToRemove.add(wall);
                    } else if (wall.getBlockType() == Block.BlockType.BRICK) {
                        wall.setOpacity(wall.getOpacity() - 0.35);
                    }
                    break;
                }
        }

        for (Bullet bullet : bulletsToRemove) {
            bulletList.remove(bullet);
        }

        for (Block wall: wallsToRemove) {
            int index = Level.platforms.indexOf(wall);
            Level.platforms.remove(wall);
            Level.boxes.remove(index);
        }
    }

    private void updateHealthbar() {
        this.healthbar.reduceHealth(this.player.getHealth());
    }

    private void updateScoreBar() {
        this.scoreBar.changeScore(this.player.getScore());
    }

    private void updateBonusItems(int posXReal, int posYReal) {
        BonusItem bonusItem = new BonusItem(posXReal, posYReal);

        this.bonusItems.add(bonusItem);
        this.getRoot().getChildren().add(bonusItem);
    }
}

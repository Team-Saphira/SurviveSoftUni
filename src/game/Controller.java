package game;

import game.Level.Block;
import game.Level.Level;
import game.moveLogic.Movable;
import game.moveLogic.MoveManager;
import game.moveLogic.MoveZombieManager;
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
    private byte weaponKeyCode;
    private Set<Enemy> zombieSet;
    private Pane root;
    private List<Weapon> weaponList;

    //IB
    private Healthbar healthbar;
    private ScoreBar scoreBar;
    private List<BonusItem> bonusItems;



    public Controller(Player player,
                      List<KeyCode> inputKeyCodes,
                      Set<Enemy> zombieSet,
                      Pane root,
                      List<Weapon> weaponList,
                      byte weaponKeyCode,
                      Healthbar healthbar,
                      ScoreBar scoreBar,
                      List<BonusItem> bonusItems) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setZombieSet(zombieSet);
        this.setRoot(root);
        this.setWeaponList(weaponList);
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

    public void setWeaponKeyCode(byte weaponsKeyCode) {
        this.weaponKeyCode = weaponsKeyCode;
    }

    public Set<Enemy> getZombieSet() {
        return zombieSet;
    }

    public void setZombieSet(Set<Enemy> zombieSet) {
        this.zombieSet = zombieSet;
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(List<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    public void updatePlayer(Movable movePlayerManager) {
        this.getPlayer().setPosX((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinX() / Block.BLOCK_SIZE);
        this.getPlayer().setPosY((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinY() / Block.BLOCK_SIZE);

        for (KeyCode kc : this.getInputKeyCodes()) {
            switch (kc) {
                case W:
                    this.getPlayer().imageView.setRotate(270);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.moveY(-Constants.PLAYER_VELOCITY, Constants.PLAYER_SIZE);
                    break;
                case S:
                    this.getPlayer().imageView.setRotate(90);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.moveY(Constants.PLAYER_VELOCITY, Constants.PLAYER_SIZE);
                    break;
                case A:
                    this.getPlayer().imageView.setRotate(180);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.moveX(-Constants.PLAYER_VELOCITY, Constants.PLAYER_SIZE);
                    break;
                case D:
                    this.getPlayer().imageView.setRotate(0);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.moveX(Constants.PLAYER_VELOCITY, Constants.PLAYER_SIZE);
                    break;
                default:
                    break;
            }
        }

        //IB testing Player health reduction
        for (Enemy enemy: zombieSet) {
            Shape intersect = Shape.intersect(this.player.getBoundingBox(), enemy.getBoundingBox());
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

        ArrayList<Enemy> zomblesToRemove= new ArrayList<>();
        for (Enemy enemy : this.getZombieSet()) {

            MoveZombieManager moveZombieManager = new MoveZombieManager(enemy);

            if (enemy.getHealth()<=0){
                zomblesToRemove.add(enemy);
                continue;
            }

            //updates enemy position
            enemy.setPosXReal((int) enemy.localToParent(enemy.getBoundsInLocal()).getMinX());
            enemy.setPosX(enemy.getPosXReal() / Block.BLOCK_SIZE);
            enemy.setPosYReal((int) enemy.localToParent(enemy.getBoundsInLocal()).getMinY());
            enemy.setPosY(enemy.getPosYReal() / Block.BLOCK_SIZE);

            //find shortest path to player
            enemy.updatePath(Level.levelBlockWidth,
                    Level.levelBlockHeight,
                    player.getPosX(),
                    player.getPosY(),
                    enemy.getPosX(),
                    enemy.getPosY(),
                    Level.levelBlockMatrix);

            //first node is current position. If npc is to move it needs the next node.
            AStar.Cell nextNode = enemy.path.poll();


            if (!enemy.getAllowNextCellMove()) {
                moveZombieManager.centerZombie();
                continue;
            }

            if (!moveZombieManager.isInSameCell()) {
                enemy.setAllowNextCellMove(false);
            }

            if (enemy.path.isEmpty()) {
                continue;
            }

            nextNode = enemy.path.poll();

            //TODO fix names in A* to make more sense
            if (nextNode.x < enemy.getPosX()) {
                //moves left -> should become up
                moveZombieManager.moveX(-Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(2 * 64);
            } else if (nextNode.x > enemy.getPosX()) {
                //moves right -> should become down
                moveZombieManager.moveX(Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(64);
            } else if (nextNode.y < enemy.getPosY()) {
                //moves up -> should become left
                moveZombieManager.moveY(-Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(0);
            } else if (nextNode.y > enemy.getPosY()) {
                //moves down -> should become right
                moveZombieManager.moveY(Constants.ENEMY_VELOCITY, Constants.ENEMY_SIZE);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(3 * 64);
            }
        }

        for (Enemy enemy : zomblesToRemove) {

            //IB testing bonus system
            double rnd = Math.random();

            //IB Threshold set to 0.8 for testing purpose only!
            if (rnd < BonusItem.RANDOM_DROP_THRESHOLD) {
                updateBonusItems(enemy.getPosXReal(), enemy.getPosYReal());
            }

            this.zombieSet.remove(enemy);
            this.getRoot().getChildren().remove(enemy);

            this.player.setScore(this.player.getScore()+1);
            updateScoreBar();
        }
    }

    public void updateBullets() {
        this.getPlayer().setCanShootTimer(this.getPlayer().getCanShootTimer() + 1);
        if ( this.getPlayer().getCanShootTimer() > 20) {
            this.getPlayer().setCanShoot(true);
            this.getPlayer().setCanShootTimer(0);
        }

        if (this.getPlayer().getIsShooting()) {
            this.getWeaponList().forEach(Weapon::move);
        }

        ArrayList<Weapon> weaponToRemove = new ArrayList<>();
        ArrayList<Block> wallsToRemove = new ArrayList<>();
        for (Weapon currWeapon : this.getWeaponList()) {
            boolean weaponRemoved = false;
            for (Enemy enemy : this.getZombieSet()) {
                if (currWeapon.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(currWeapon);
                    weaponToRemove.add(currWeapon);

                    int damage = (int) (Math.random() * currWeapon.getDamagePower() + 1);
                    System.out.println(damage);
                    enemy.dealDamage(damage);

                    weaponRemoved = true;
                    break;
                }
            }
            if (weaponRemoved) {
                continue;
            }
            for (Block wall : Level.platforms)
                if (currWeapon.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(currWeapon);
                    weaponToRemove.add(currWeapon);
                    if (wall.getBlockType()==Block.BlockType.BRICK&&wall.getOpacity()-0.35<0) {
                        this.getRoot().getChildren().remove(wall.getBlockBBox());
                        this.getRoot().getChildren().remove(wall);
                        wallsToRemove.add(wall);
                    } else if (wall.getBlockType()== Block.BlockType.BRICK){
                        wall.setOpacity(wall.getOpacity()-0.35);
                    }
                    break;
                }
        }

        for (Weapon weapon : weaponToRemove) {
            weaponList.remove(weapon);
        }

        for (Block wall: wallsToRemove) {
            int index = Level.platforms.indexOf(wall);
            Level.platforms.remove(wall);
            Level.bboxes.remove(index);
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

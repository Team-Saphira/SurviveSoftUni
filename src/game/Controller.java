package game;

import game.Level.Block;
import game.Level.Level;
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



    public Controller(Player player,
                      List<KeyCode> inputKeyCodes,
                      Set<Enemy> zombieSet,
                      Pane root,
                      List<Weapon> weaponList,
                      byte weaponKeyCode,
                      Healthbar healthbar,
                      ScoreBar scoreBar) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setZombieSet(zombieSet);
        this.setRoot(root);
        this.setWeaponList(weaponList);
        this.setHealthbar(healthbar);
        this.setScoreBar(scoreBar);
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

    public void updatePlayer() {
        this.getPlayer().posX = (int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinX() / Block.BLOCK_SIZE;
        this.getPlayer().posY = (int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinY() / Block.BLOCK_SIZE;

        for (KeyCode kc : this.getInputKeyCodes()) {
            switch (kc) {
                case W:
                    this.getPlayer().imageView.setRotate(270);
                    this.getPlayer().animation.play();
                    this.getPlayer().moveY(-Constants.PLAYER_VELOCITY, Player.PLAYER_SIZE);
                    break;
                case S:
                    this.getPlayer().imageView.setRotate(90);
                    this.getPlayer().animation.play();
                    this.getPlayer().moveY(Constants.PLAYER_VELOCITY, Player.PLAYER_SIZE);
                    break;
                case A:
                    this.getPlayer().imageView.setRotate(180);
                    this.getPlayer().animation.play();
                    this.getPlayer().moveX(-Constants.PLAYER_VELOCITY, Player.PLAYER_SIZE);
                    break;
                case D:
                    this.getPlayer().imageView.setRotate(0);
                    this.getPlayer().animation.play();
                    this.getPlayer().moveX(Constants.PLAYER_VELOCITY, Player.PLAYER_SIZE);
                    break;
                default:
                    break;
            }
        }

        //IB testing
        for (Enemy enemy: zombieSet) {
            Shape intersect = Shape.intersect(player.boundingBox, enemy.boundingBox);
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

            if (enemy.getHealth()<=0){
                zomblesToRemove.add(enemy);
                continue;
            }

            //updates enemy position
            enemy.posXReal = (int) enemy.localToParent(enemy.getBoundsInLocal()).getMinX();
            enemy.posX = enemy.posXReal / Block.BLOCK_SIZE;
            enemy.posYReal = (int) enemy.localToParent(enemy.getBoundsInLocal()).getMinY();
            enemy.posY = enemy.posYReal / Block.BLOCK_SIZE;

            //find shortest path to player
            enemy.updatePath(Level.levelBlockWidth, Level.levelBlockHeight, player.posX, player.posY, enemy.posX, enemy.posY, Level.levelBlockMatrix);

            //first node is current position. If npc is to move it needs the next node.
            AStar.Cell nextNode = enemy.path.poll();


            if (!enemy.allowNextCellMove) {
                enemy.centerZombie();
                continue;
            }

            if (!enemy.isInSameCell()) {
                enemy.allowNextCellMove = false;
            }

            if (enemy.path.isEmpty()) {
                continue;
            }

            nextNode = enemy.path.poll();

            //TODO fix names in A* to make more sense
            if (nextNode.x < enemy.posX) {
                //moves left -> should become up
                enemy.moveX(-Constants.ENEMY_VELOCITY, Enemy.ENEMY_SIZE);
                enemy.animation.play();
                enemy.animation.setOffsetY(2 * 64);
            } else if (nextNode.x > enemy.posX) {
                //moves right -> should become down
                enemy.moveX(Constants.ENEMY_VELOCITY, Enemy.ENEMY_SIZE);
                enemy.animation.play();
                enemy.animation.setOffsetY(64);
            } else if (nextNode.y < enemy.posY) {
                //moves up -> should become left
                enemy.moveY(-Constants.ENEMY_VELOCITY, Enemy.ENEMY_SIZE);
                enemy.animation.play();
                enemy.animation.setOffsetY(0);
            } else if (nextNode.y > enemy.posY) {
                //moves down -> should become right
                enemy.moveY(Constants.ENEMY_VELOCITY, Enemy.ENEMY_SIZE);
                enemy.animation.play();
                enemy.animation.setOffsetY(3 * 64);
            }
        }

        for (Enemy enemy : zomblesToRemove) {
            zombieSet.remove(enemy);
            this.getRoot().getChildren().remove(enemy);

            player.setScore(player.getScore()+1);
            updateScoreBar();
        }
    }

    public void updateBullets() {

        if (this.getPlayer().canShootTimer++ > 20) {
            this.getPlayer().canShoot = true;
            this.getPlayer().canShootTimer = 0;
        }

        if (this.getPlayer().isShooting) {
            this.getWeaponList().forEach(Weapon::move);
        }

        ArrayList<Weapon> weaponToRemove = new ArrayList<>();
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
                    //bullet.setOpacity(0);
                    this.getRoot().getChildren().remove(currWeapon);
                    weaponToRemove.add(currWeapon);
                    break;
                }
        }

        for (Weapon weapon : weaponToRemove) {
            weaponList.remove(weapon);
        }
    }

    private void updateHealthbar() {
        this.healthbar.reduceHealth(this.player.getHealth());
    }

    private void updateScoreBar() {
        this.scoreBar.changeScore(player.getScore());
    }
}

package game;

import game.Level.Block;
import game.Level.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Controller {
    private Player player;
    private List<KeyCode> inputKeyCodes;
    private Set<Enemy> zombieSet;
    private Pane root;
    private List<Bullet> bulletList;

    public Controller(Player player, List<KeyCode> inputKeyCodes, Set<Enemy> zombieSet, Pane root, List<Bullet> bulletList) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setZombieSet(zombieSet);
        this.setRoot(root);
        this.setBulletList(bulletList);
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

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
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
        }
    }

    public void updateBullets() {

        if (this.getPlayer().canShootTimer++ > 20) {
            this.getPlayer().canShoot = true;
            this.getPlayer().canShootTimer = 0;
        }

        if (this.getPlayer().isShooting) {
            this.getBulletList().forEach(Bullet::move);
        }

        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : this.getBulletList()) {
            boolean bulletRemoved = false;
            for (Enemy enemy : this.getZombieSet()) {
                if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);

                    int damage = (int)(Math.random()*player.pistolMaxDamage+1);
                    System.out.println(damage);
                    enemy.dealDamage(damage);

                    bulletRemoved=true;
                    break;
                }
            }
            if (bulletRemoved){
                continue;
            }
            for (Block wall : Level.platforms)
                if (bullet.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    //bullet.setOpacity(0);
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);
                    break;
                }
        }
        for (Bullet bullet : bulletsToRemove) {
            bulletList.remove(bullet);
        }
    }
}

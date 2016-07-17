package game;

import game.gui.GUIDrawer;
import game.gui.ScoreBar;
import game.level.Block;
import game.level.Level;
import game.models.Enemy;
import game.models.Player;
import game.models.Zombie;
import game.moveLogic.AStar;
import game.moveLogic.Movable;
import game.moveLogic.MoveEnemyManager;
import game.weapons.Bullet;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Controller {
    private static final double HEALTH_REDUCTION = 0.3;
    private static Random rand;
    private Player player;
    private List<KeyCode> inputKeyCodes;
    private Set<Enemy> enemySet;
    private Pane root;
    private List<Bullet> bulletList;


    //IB
    private ScoreBar scoreBar;
    private List<BonusItem> bonusItems;
    private GUIDrawer guiDrawer;

    public Controller(Player player,
                      List<KeyCode> inputKeyCodes,
                      Set<Enemy> enemySet,
                      Pane root,
                      List<Bullet> bulletList,
                      GUIDrawer guiDrawer,
                      ScoreBar scoreBar,
                      List<BonusItem> bonusItems) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setEnemySet(enemySet);
        this.setRoot(root);
        this.setBulletList(bulletList);
        this.setScoreBar(scoreBar);
        this.setBonusItems(bonusItems);
        this.setGuiDrawer(guiDrawer);

        this.rand = new Random();
    }

    public ScoreBar getScoreBar() {
        return scoreBar;
    }

    public void setScoreBar(ScoreBar scoreBar) {
        this.scoreBar = scoreBar;
    }

    public GUIDrawer getGuiDrawer() {
        return guiDrawer;
    }

    public void setGuiDrawer(GUIDrawer guiDrawer) {
        this.guiDrawer = guiDrawer;
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

    public Set<Enemy> getEnemySet() {
        return enemySet;
    }

    public void setEnemySet(Set<Enemy> enemySet) {
        this.enemySet = enemySet;
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

    //TODO create an input manager
    public void updatePlayer(Movable movePlayerManager) {
        this.getPlayer().setPosX((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinX() / Constants.BLOCK_SIZE);
        this.getPlayer().setPosY((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinY() / Constants.BLOCK_SIZE);

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
                    this.getPlayer().changePlayerState("PistolState");
                    this.getGuiDrawer().changeWeaponImage("Pistol");
                    break;
                case DIGIT2:
                    this.getPlayer().changeWeapon("MachineGun");
                    this.getPlayer().changePlayerState("MachineGunState");
                    this.getGuiDrawer().changeWeaponImage("MachineGun");
                    break;
                default:
                    break;
            }
        }

        for (BonusItem bonusItem : bonusItems) {
            Shape intersect = Shape.intersect(this.player.getBoundingBox(), bonusItem.getBoundingBox());
//          if (this.player.getBoundingBox().getBoundsInParent().intersects(bonusItem.getBoundsInParent())) {
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                updateBonusItems(bonusItem);
                break;
            }
        }
    }

    public void updateEnemies() {

        for (Enemy enemy : enemySet) {
            if (this.getPlayer().getBoundingBox().getBoundsInParent().intersects(enemy.getBoundingBox().getBoundsInParent())) {
                this.getPlayer().setHealth(this.getPlayer().getHealth() - HEALTH_REDUCTION);
                break;
            }
        }

        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy : this.getEnemySet()) {

            MoveEnemyManager moveZombieManager = new MoveEnemyManager(enemy);

            if (enemy.getHealth() <= 0) {
                enemiesToRemove.add(enemy);
                continue;
            }

            //updates zombie position
            enemy.setPosXReal((int) enemy.localToParent(enemy.getBoundsInLocal()).getMinX());
            enemy.setPosX(enemy.getPosXReal() / Constants.BLOCK_SIZE);
            enemy.setPosYReal((int) enemy.localToParent(enemy.getBoundsInLocal()).getMinY());
            enemy.setPosY(enemy.getPosYReal() / Constants.BLOCK_SIZE);

            //find shortest path to player
            enemy.updatePath(Level.levelBlockWidth,
                    Level.levelBlockHeight,
                    player.getPosX(),
                    player.getPosY(),
                    enemy.getPosX(),
                    enemy.getPosY(),
                    Level.levelBlockMatrix);
            //if A* returns an empty path then the zombie is too far away to hone in on player
            if (enemy.path.isEmpty()) {
                if (enemy.getIsInCollision()) {
                    int pos = rand.nextInt(Constants.ENEMY_DIRECTIONS.length);
                    enemy.setMoveDirection(Constants.ENEMY_DIRECTIONS[pos]);
                }
                if (rand.nextInt(1000) < 5) {
                    int pos = rand.nextInt(Constants.ENEMY_DIRECTIONS.length);
                    enemy.setMoveDirection(Constants.ENEMY_DIRECTIONS[pos]);
                }
                switch (enemy.getMoveDirection()) {
                    case 'U':
                        moveZombieManager.moveY(-Constants.ZOMBIE_VELOCITY);
                        enemy.getAnimation().play();
                        enemy.getAnimation().setOffsetY(0);
                        break;
                    case 'D':
                        moveZombieManager.moveY(Constants.ZOMBIE_VELOCITY);
                        enemy.getAnimation().play();
                        enemy.getAnimation().setOffsetY(3 * 64);
                        break;
                    case 'L':
                        moveZombieManager.moveX(-Constants.ZOMBIE_VELOCITY);
                        enemy.getAnimation().play();
                        enemy.getAnimation().setOffsetY(2 * 64);
                        break;
                    case 'R':
                        moveZombieManager.moveX(Constants.ZOMBIE_VELOCITY);
                        enemy.getAnimation().play();
                        enemy.getAnimation().setOffsetY(64);
                        break;
                }
            } else {
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

                if (nextNode.getX() < enemy.getPosX()) {
                    moveZombieManager.moveX(-Constants.ZOMBIE_VELOCITY);
                    enemy.getAnimation().play();
                    enemy.getAnimation().setOffsetY(2 * 64);
                } else if (nextNode.getX() > enemy.getPosX()) {
                    moveZombieManager.moveX(Constants.ZOMBIE_VELOCITY);
                    enemy.getAnimation().play();
                    enemy.getAnimation().setOffsetY(64);
                } else if (nextNode.getY() < enemy.getPosY()) {
                    moveZombieManager.moveY(-Constants.ZOMBIE_VELOCITY);
                    enemy.getAnimation().play();
                    enemy.getAnimation().setOffsetY(0);
                } else if (nextNode.getY() > enemy.getPosY()) {
                    moveZombieManager.moveY(Constants.ZOMBIE_VELOCITY);
                    enemy.getAnimation().play();
                    enemy.getAnimation().setOffsetY(3 * 64);
                }
            }
        }
        for (Enemy enemy : enemiesToRemove) {
            //IB Threshold set to 0.8 for testing purpose only!
            if (Math.random() < Constants.RANDOM_DROP_THRESHOLD) {
                addBonusItem(enemy.getPosXReal(), enemy.getPosYReal());
            }

            this.enemySet.remove(enemy);
            this.getRoot().getChildren().remove(enemy);

            this.player.setScore(this.player.getScore() + 1);
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
            for (Enemy enemy : this.getEnemySet()) {
                if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);

                    int damage = bullet.calculateDamage();
                    // System.out.println(damage);
                    enemy.dealDamage(damage);

                    bulletRemoved = true;
                    break;
                }
            }

            if (bulletRemoved) {
                continue;
            }

            for (Block wall : Level.impassableBlocks) {
                if (bullet.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);
                    bulletRemoved = true;
                    break;
                }
            }

            if (bulletRemoved) {
                continue;
            }

            for (Block wall : Level.destructibleBlocks)
                if (bullet.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);
                    if (wall.getBlockType() == Block.BlockType.BRICK && wall.getOpacity() - 0.2 <= 0) {
                        this.getRoot().getChildren().remove(wall.getBlockBBox());
                        this.getRoot().getChildren().remove(wall);
                        wallsToRemove.add(wall);
                        Level.levelBlockMatrix[(int) wall.getTranslateX() / Constants.BLOCK_SIZE][(int) wall.getTranslateY() / Constants.BLOCK_SIZE] = 0;
                    } else if (wall.getBlockType() == Block.BlockType.BRICK) {
                        wall.setOpacity(wall.getOpacity() - 0.2);
                    }
                    break;
                }
        }

        for (Bullet bullet : bulletsToRemove) {
            bulletList.remove(bullet);
        }

        for (Block wall : wallsToRemove) {
            int index = Level.destructibleBlocks.indexOf(wall);
            Level.destructibleBlocks.remove(wall);
            Level.destructibleBlockBBoxes.remove(index);
        }
    }

    public void updateHealthBar() {
        Rectangle imageCutter = new Rectangle((int) (((double) this.getPlayer().getHealth() / (double) this.getGuiDrawer().getHealthBar().getInitialHealth()) * 150), 30);
        this.getGuiDrawer().getHealthBarImage().setClip(imageCutter);
        this.guiDrawer.setLayoutX(0 - this.root.getLayoutX());
        this.guiDrawer.setLayoutY(0 - this.root.getLayoutY());
    }

    private void updateScoreBar() {
        this.scoreBar.changeScore(this.player.getScore());
    }

    private void addBonusItem(int posXReal, int posYReal) {
        BonusItem bonusItem = new BonusItem(posXReal, posYReal);

        this.bonusItems.add(bonusItem);
        this.getRoot().getChildren().add(bonusItem);
    }

    private void updateBonusItems(BonusItem bonusItem) {
        this.bonusItems.remove(bonusItem);
        this.getRoot().getChildren().remove(bonusItem);
        this.getPlayer().setHealth(this.getPlayer().getHealth() + 10);
    }
}
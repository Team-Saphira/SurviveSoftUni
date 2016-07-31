package game;

import game.bonusItems.BonusItem;
import game.gui.GUIDrawer;
import game.gui.HealthPoints;
import game.gui.ScorePoints;
import game.gui.WeaponTextDisplay;
import game.level.Block;
import game.level.Level;

import game.models.DumbZombie;

import game.level.enums.BlockType;
import game.level.interfaces.LevelManageable;
import game.models.interfaces.Enemy;

import game.models.Player;
import game.models.SmartZombie;
import game.models.interfaces.Enemy;
import game.models.interfaces.PathFindable;
import game.models.interfaces.RandomDirectionMovable;
import game.moveLogic.AStar;
import game.moveLogic.Axis;
import game.moveLogic.MoveEnemyManager;
import game.moveLogic.interfaces.Movable;
import game.weapons.Bullet;
import game.weapons.WeaponType;
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
    private Set<Enemy> enemyImplSet;
    private Pane root;
    private List<Bullet> bulletList;
    private LevelManageable levelManager;

    // HEALTH POINTS TEST
    private HealthPoints healthPoints;
    private ScorePoints scorePoints;
    private WeaponTextDisplay weaponTextDisplay;
    private List<BonusItem> bonusItems;
    private GUIDrawer guiDrawer;

    public Controller(Player player,
                      List<KeyCode> inputKeyCodes,
                      Set<Enemy> enemyImplSet,
                      Pane root,
                      List<Bullet> bulletList,
                      GUIDrawer guiDrawer,
                      HealthPoints healthPoints,
                      ScorePoints scorePoints,
                      WeaponTextDisplay weaponTextDisplay,
                      List<BonusItem> bonusItems,
                      LevelManageable levelManager) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setEnemyImplSet(enemyImplSet);
        this.setRoot(root);
        this.setBulletList(bulletList);
        this.setHealthPoints(healthPoints);
        this.setScorePoints(scorePoints);
        this.setWeaponTextDisplay(weaponTextDisplay);
        this.setBonusItems(bonusItems);
        this.setGuiDrawer(guiDrawer);
        this.setLevelManager(levelManager);
        this.rand = new Random();
    }

    private LevelManageable getLevelManager() {
        return levelManager;
    }

    private void setLevelManager(LevelManageable levelManager) {
        this.levelManager = levelManager;
    }

    private void setWeaponTextDisplay(WeaponTextDisplay weaponTextDisplay) {
        this.weaponTextDisplay = weaponTextDisplay;
    }

    private void setScorePoints(ScorePoints scorePoints) {
        this.scorePoints = scorePoints;
    }

    public void setHealthPoints(HealthPoints healthPoints) {
        this.healthPoints = healthPoints;
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

    public Set<Enemy> getEnemyImplSet() {
        return enemyImplSet;
    }

    public void setEnemyImplSet(Set<Enemy> enemyImplSet) {
        this.enemyImplSet = enemyImplSet;
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
        this.getPlayer().changePosXGrid((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinX() / Constants.BLOCK_SIZE);
        this.getPlayer().changePosYGrid((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinY() / Constants.BLOCK_SIZE);

        for (KeyCode kc : this.getInputKeyCodes()) {
            switch (kc) {
                case W:
                    this.player.getPlayerImageView().setRotate(270);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.move(-Constants.PLAYER_VELOCITY, Axis.Y);
                    break;
                case S:
                    this.player.getPlayerImageView().setRotate(90);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.move(Constants.PLAYER_VELOCITY, Axis.Y);
                    break;
                case A:
                    this.player.getPlayerImageView().setRotate(180);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.move(-Constants.PLAYER_VELOCITY, Axis.X);
                    break;
                case D:
                    this.player.getPlayerImageView().setRotate(0);
                    this.getPlayer().getAnimation().play();
                    movePlayerManager.move(Constants.PLAYER_VELOCITY, Axis.X);
                    break;
                case DIGIT1:
                    this.getPlayer().changeWeapon(WeaponType.PISTOL);
                    this.getPlayer().changePlayerState("PistolState");
                    this.getGuiDrawer().changeWeaponImage(WeaponType.PISTOL);
                    break;
                case DIGIT2:
                    this.getPlayer().changeWeapon(WeaponType.MACHINE_GUN);
                    this.getPlayer().changePlayerState("MachineGunState");
                    this.getGuiDrawer().changeWeaponImage(WeaponType.MACHINE_GUN);
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

        for (Enemy enemy : enemyImplSet) {
            if (this.getPlayer().getBoundingBox().getBoundsInParent().intersects(enemy.getBoundingBox().getBoundsInParent())) {
                this.getPlayer().changeHealth(this.getPlayer().getHealth() - HEALTH_REDUCTION);
                break;
            }
        }

        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy : this.getEnemyImplSet()) {

            MoveEnemyManager moveZombieManager = new MoveEnemyManager(enemy);

            if (enemy.getHealth() <= 0) {
                enemiesToRemove.add(enemy);
                continue;
            }

            //updates zombie position
            enemy.changePosXPixel((int) enemy.localToParent(enemy.getBoundsInLocal()).getMinX());
            enemy.changePosXGrid(enemy.getPosXReal() / Constants.BLOCK_SIZE);
            enemy.changePosYPixel((int) enemy.localToParent(enemy.getBoundsInLocal()).getMinY());
            enemy.changePosYGrid(enemy.getPosYReal() / Constants.BLOCK_SIZE);

            //find shortest path to player
            if (enemy instanceof SmartZombie) {
                SmartZombie smartZombie = (SmartZombie) enemy;
                smartZombie.updatePath(Level.levelBlockWidth,
                        Level.levelBlockHeight,
                        player.getPosX(),
                        player.getPosY(),
                        enemy.getPosX(),
                        enemy.getPosY(),
                        Level.levelBlockMatrix);
                if (smartZombie.getPath().isEmpty()) {
                    MoveInRandomDirection((RandomDirectionMovable) enemy, moveZombieManager);
                } else {
                    //first node is current position. If npc is to move it needs the next node.
                    AStar.Cell nextNode = smartZombie.path.poll();


                    if (!enemy.getAllowNextCellMove()) {
                        moveZombieManager.centerZombie();
                        continue;
                    }

                    if (!moveZombieManager.isInSameCell()) {
                        enemy.changeAllowNextCellMove(false);
                    }

                    if (smartZombie.path.isEmpty()) {
                        continue;
                    }

                    nextNode = smartZombie.path.poll();

                    if (nextNode.getX() < smartZombie.getPosX()) {
                        moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.X);
                        smartZombie.getAnimation().play();
                        smartZombie.getAnimation().setOffsetY(2 * 64);
                    } else if (nextNode.getX() > smartZombie.getPosX()) {
                        moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.X);
                        smartZombie.getAnimation().play();
                        smartZombie.getAnimation().setOffsetY(64);
                    } else if (nextNode.getY() < smartZombie.getPosY()) {
                        moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.Y);
                        smartZombie.getAnimation().play();
                        smartZombie.getAnimation().setOffsetY(0);
                    } else if (nextNode.getY() > smartZombie.getPosY()) {
                        moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.Y);
                        smartZombie.getAnimation().play();
                        smartZombie.getAnimation().setOffsetY(3 * 64);
                    }
                }
            } else if (enemy instanceof DumbZombie){
                MoveInRandomDirection((RandomDirectionMovable) enemy, moveZombieManager);
            }
        }

        for (Enemy enemy : enemiesToRemove) {
            //IB Threshold set to 0.8 for testing purpose only!
            if (Math.random() < Constants.RANDOM_DROP_THRESHOLD) {
                addBonusItem(enemy.getPosXReal(), enemy.getPosYReal());
            }

            this.enemyImplSet.remove(enemy);
            this.getRoot().getChildren().remove(enemy);

            this.player.changeScore(this.player.getScore() + 1);
        }
    }

    public void updateBullets() {
        this.getPlayer().changeCanShootTimer(this.getPlayer().getCanShootTimer() + 1);
        if (this.getPlayer().getCanShootTimer() > this.player.getCurrentWeapon().getWeaponType().getShootDelayTime()) {
            this.getPlayer().changeCanShoot(true);
            this.getPlayer().changeCanShootTimer(0);
        }

        if (this.getPlayer().getIsShooting()) {
            this.getBulletList().forEach(Bullet::move);
        }

        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        ArrayList<Block> wallsToRemove = new ArrayList<>();
        for (Bullet bullet : this.getBulletList()) {
            boolean bulletRemoved = false;
            for (Enemy enemyImpl : this.getEnemyImplSet()) {
                if (bullet.getBoundsInParent().intersects(enemyImpl.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);

                    int damage = bullet.calculateDamage();
                    // System.out.println(damage);
                    enemyImpl.changeDealDamage(damage);

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
                    if (wall.getBlockType() == BlockType.BRICK && wall.getOpacity() - 0.2 <= 0) {
                        this.getRoot().getChildren().remove(wall.getBlockBBox());
                        this.getRoot().getChildren().remove(wall);
                        wallsToRemove.add(wall);
                        Level.levelBlockMatrix[(int) wall.getTranslateX() / Constants.BLOCK_SIZE][(int) wall.getTranslateY() / Constants.BLOCK_SIZE] = 0;
                    } else if (wall.getBlockType() == BlockType.BRICK) {
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
        Rectangle imageCutter = new Rectangle((int) ((this.getPlayer().getHealth() /
                this.getGuiDrawer().getHealthBar().getInitialHealth()) * 190), 50);
        this.getGuiDrawer().getHealthBarImage().setClip(imageCutter);
        this.guiDrawer.setLayoutX(0 - this.root.getLayoutX());
        this.guiDrawer.setLayoutY(0 - this.root.getLayoutY());
    }

    public void updateHealthPoints() {
        this.healthPoints.changeHealthPoints((int) this.player.getHealth());
    }

    public void updateScorePoints() {
        this.scorePoints.changeScorePoints(this.player.getScore());
    }

    public void updateWeaponDisplayText() {
        this.weaponTextDisplay.changeWeaponDisplayText(this.player.getCurrentWeapon().getWeaponType().name());
    }

    private void addBonusItem(int posXReal, int posYReal) {
        BonusItem bonusItem = new BonusItem(posXReal, posYReal);

        this.bonusItems.add(bonusItem);
        this.getRoot().getChildren().add(bonusItem);
    }

    private void updateBonusItems(BonusItem bonusItem) {
        this.bonusItems.remove(bonusItem);
        this.getRoot().getChildren().remove(bonusItem);
        this.getPlayer().addBonusHealth();
    }

    private void MoveInRandomDirection(RandomDirectionMovable enemy, MoveEnemyManager moveZombieManager) {
        if (enemy.getIsInCollision()) {
            int pos = rand.nextInt(Constants.ENEMY_DIRECTIONS.length);
            enemy.changeMoveDirection(Constants.ENEMY_DIRECTIONS[pos]);
        }
        if (rand.nextInt(1000) < 5) {
            int pos = rand.nextInt(Constants.ENEMY_DIRECTIONS.length);
            enemy.changeMoveDirection(Constants.ENEMY_DIRECTIONS[pos]);
        }
        switch (enemy.getMoveDirection()) {
            case 'U':
                moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.Y);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(0);
                break;
            case 'D':
                moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.Y);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(3 * 64);
                break;
            case 'L':
                moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.X);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(2 * 64);
                break;
            case 'R':
                moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.X);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(64);
                break;
        }
    }
}
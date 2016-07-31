package game;

import game.bonusItems.HealthBonus;
import game.bonusItems.interfaces.Bonus;
import game.gui.GUIDrawer;
import game.level.Level;
import game.level.enums.BlockType;
import game.level.interfaces.Block;
import game.level.interfaces.LevelManageable;
import game.models.interfaces.HumanObject;
import game.models.interfaces.RandomDirectionMovable;
import game.models.interfaces.SmartMovable;
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
    private HumanObject player;
    private List<KeyCode> inputKeyCodes;
    private Set<SmartMovable> smartMovableEnemies;
    private Set<RandomDirectionMovable> randomDirectionMovableEnemies;
    private Pane root;
    private List<Bullet> bulletList;
    private LevelManageable levelManager;

    // HEALTH POINTS TEST
    private List<Bonus> bonusItems;
    private GUIDrawer guiDrawer;

    public Controller(HumanObject player,
                      List<KeyCode> inputKeyCodes,
                      Set<SmartMovable> smartMovableEnemies,
                      Set<RandomDirectionMovable> randomDirectionMovableEnemies,
                      Pane root,
                      List<Bullet> bulletList,
                      GUIDrawer guiDrawer,
                      List<Bonus> bonusItems,
                      LevelManageable levelManager) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setSmartMovableEnemies(smartMovableEnemies);
        this.setRandomDirectionMovableEnemies(randomDirectionMovableEnemies);
        this.setRoot(root);
        this.setBulletList(bulletList);
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

    public GUIDrawer getGuiDrawer() {
        return guiDrawer;
    }

    public void setGuiDrawer(GUIDrawer guiDrawer) {
        this.guiDrawer = guiDrawer;
    }

    public List<Bonus> getBonusItems() {
        return bonusItems;
    }

    public void setBonusItems(List<Bonus> bonusItems) {
        this.bonusItems = bonusItems;
    }

    public HumanObject getPlayer() {
        return player;
    }

    public void setPlayer(HumanObject player) {
        this.player = player;
    }

    public List<KeyCode> getInputKeyCodes() {
        return inputKeyCodes;
    }

    public void setInputKeyCodes(List<KeyCode> inputKeyCodes) {
        this.inputKeyCodes = inputKeyCodes;
    }

    public Set<SmartMovable> getSmartMovableEnemies() {
        return smartMovableEnemies;
    }

    public void setSmartMovableEnemies(Set<SmartMovable> smartMovableEnemies) {
        this.smartMovableEnemies = smartMovableEnemies;
    }

    public Set<RandomDirectionMovable> getRandomDirectionMovableEnemies() {
        return randomDirectionMovableEnemies;
    }

    private void setRandomDirectionMovableEnemies(Set<RandomDirectionMovable> randomDirectionMovableEnemies) {
        this.randomDirectionMovableEnemies = randomDirectionMovableEnemies;
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

        for (Bonus bonusItem : bonusItems) {
            Shape intersect = Shape.intersect(this.player.getBoundingBox(), bonusItem.getBoundingBox());
//          if (this.player.getBoundingBox().getBoundsInParent().intersects(bonusItem.getBoundsInParent())) {
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                updateBonusItems(bonusItem);
                break;
            }
        }
    }

    public void updateSmartEnemies() {

        for (SmartMovable smartMovableEnemy : this.getSmartMovableEnemies()){
            if (this.getPlayer().getBoundingBox().getBoundsInParent().intersects(smartMovableEnemy.getBoundingBox().getBoundsInParent())) {
                this.getPlayer().changeHealth(this.getPlayer().getHealth() - HEALTH_REDUCTION);
                break;
            }
        }

        ArrayList<SmartMovable> enemiesToRemove = new ArrayList<>();
        for (SmartMovable smartMovableEnemy : this.getSmartMovableEnemies()) {


            MoveEnemyManager moveZombieManager = new MoveEnemyManager(smartMovableEnemy);

            if (smartMovableEnemy.getHealth() <= 0) {
                enemiesToRemove.add(smartMovableEnemy);
                continue;
            }

            //updates zombie position
            smartMovableEnemy.changePosXPixel((int) smartMovableEnemy.localToParent(smartMovableEnemy.getBoundsInLocal()).getMinX());
            smartMovableEnemy.changePosXGrid(smartMovableEnemy.getPosXReal() / Constants.BLOCK_SIZE);
            smartMovableEnemy.changePosYPixel((int) smartMovableEnemy.localToParent(smartMovableEnemy.getBoundsInLocal()).getMinY());
            smartMovableEnemy.changePosYGrid(smartMovableEnemy.getPosYReal() / Constants.BLOCK_SIZE);

            //find shortest path to player
            smartMovableEnemy.updatePath(Level.levelBlockWidth,
                    Level.levelBlockHeight,
                    player.getPosX(),
                    player.getPosY(),
                    smartMovableEnemy.getPosX(),
                    smartMovableEnemy.getPosY(),
                    Level.levelBlockMatrix);

            if (smartMovableEnemy.getPath().isEmpty()) {
                MoveInRandomDirection(smartMovableEnemy, moveZombieManager);
            } else {
                //first node is current position. If npc is to move it needs the next node.
                AStar.Cell nextNode = smartMovableEnemy.getPath().poll();


                if (!smartMovableEnemy.getAllowNextCellMove()) {
                    moveZombieManager.centerZombie();
                    continue;
                }

                if (!moveZombieManager.isInSameCell()) {
                    smartMovableEnemy.changeAllowNextCellMove(false);
                }

                if (smartMovableEnemy.getPath().isEmpty()) {
                    continue;
                }

                nextNode = smartMovableEnemy.getPath().poll();

                if (nextNode.getX() < smartMovableEnemy.getPosX()) {
                    moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.X);
                    smartMovableEnemy.getAnimation().play();
                    smartMovableEnemy.getAnimation().setOffsetY(2 * 64);
                } else if (nextNode.getX() > smartMovableEnemy.getPosX()) {
                    moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.X);
                    smartMovableEnemy.getAnimation().play();
                    smartMovableEnemy.getAnimation().setOffsetY(64);
                } else if (nextNode.getY() < smartMovableEnemy.getPosY()) {
                    moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.Y);
                    smartMovableEnemy.getAnimation().play();
                    smartMovableEnemy.getAnimation().setOffsetY(0);
                } else if (nextNode.getY() > smartMovableEnemy.getPosY()) {
                    moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.Y);
                    smartMovableEnemy.getAnimation().play();
                    smartMovableEnemy.getAnimation().setOffsetY(3 * 64);
                }
            }
        }

        for (SmartMovable smartMovableEnemy : enemiesToRemove) {
            //IB Threshold set to 0.8 for testing purpose only!
            if (Math.random() < Constants.RANDOM_DROP_THRESHOLD) {
                addBonusItem(smartMovableEnemy.getPosXReal(), smartMovableEnemy.getPosYReal());
            }

            this.smartMovableEnemies.remove(smartMovableEnemy);
            this.getRoot().getChildren().remove(smartMovableEnemy);

            this.player.changeScore(this.player.getScore() + 1);
        }
    }

    public void updateRandomMovableEnemies() {

        for (RandomDirectionMovable randomDirectionMovableEnemy : this.getRandomDirectionMovableEnemies()) {
            if (this.getPlayer().getBoundingBox().getBoundsInParent().intersects(randomDirectionMovableEnemy.getBoundingBox().getBoundsInParent())) {
                this.getPlayer().changeHealth(this.getPlayer().getHealth() - HEALTH_REDUCTION);
                break;
            }
        }

        ArrayList<RandomDirectionMovable> enemiesToRemove = new ArrayList<>();
        for (RandomDirectionMovable randomDirectionMovableEnemy : this.getRandomDirectionMovableEnemies()) {

            MoveEnemyManager moveZombieManager = new MoveEnemyManager(randomDirectionMovableEnemy);

            if (randomDirectionMovableEnemy.getHealth() <= 0) {
                enemiesToRemove.add(randomDirectionMovableEnemy);
                continue;
            }

            //updates zombie position
            randomDirectionMovableEnemy.changePosXPixel((int) randomDirectionMovableEnemy.localToParent(randomDirectionMovableEnemy.getBoundsInLocal()).getMinX());
            randomDirectionMovableEnemy.changePosXGrid(randomDirectionMovableEnemy.getPosXReal() / Constants.BLOCK_SIZE);
            randomDirectionMovableEnemy.changePosYPixel((int) randomDirectionMovableEnemy.localToParent(randomDirectionMovableEnemy.getBoundsInLocal()).getMinY());
            randomDirectionMovableEnemy.changePosYGrid(randomDirectionMovableEnemy.getPosYReal() / Constants.BLOCK_SIZE);

            MoveInRandomDirection(randomDirectionMovableEnemy, moveZombieManager);
        }

        for (RandomDirectionMovable randomDirectionMovableEnemy : enemiesToRemove) {
            //IB Threshold set to 0.8 for testing purpose only!
            if (Math.random() < Constants.RANDOM_DROP_THRESHOLD) {
                addBonusItem(randomDirectionMovableEnemy.getPosXReal(), randomDirectionMovableEnemy.getPosYReal());
            }

            this.smartMovableEnemies.remove(randomDirectionMovableEnemy);
            this.getRoot().getChildren().remove(randomDirectionMovableEnemy);

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

        List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Block> wallsToRemove = new ArrayList<>();
        for (Bullet bullet : this.getBulletList()) {
            boolean bulletRemoved = false;
            for (SmartMovable smartMovableEnemy : this.getSmartMovableEnemies()) {
                if (bullet.getBoundsInParent().intersects(smartMovableEnemy.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);

                    int damage = bullet.calculateDamage();
                    // System.out.println(damage);
                    smartMovableEnemy.changeDealDamage(damage);

                    bulletRemoved = true;
                    break;
                }
            }

            for (RandomDirectionMovable randomDirectionMovableEnemy : this.getRandomDirectionMovableEnemies()) {
                if (bullet.getBoundsInParent().intersects(randomDirectionMovableEnemy.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);

                    int damage = bullet.calculateDamage();
                    // System.out.println(damage);
                    randomDirectionMovableEnemy.changeDealDamage(damage);

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
        this.getGuiDrawer().getHealthPoints().changeHealthPoints((int) this.player.getHealth());
    }

    public void updateScorePoints() {
        this.getGuiDrawer().getScorePoints().changeScorePoints(this.player.getScore());
    }

    public void updateWeaponDisplayText() {
        this.getGuiDrawer().getWeaponTextDisplay().changeWeaponDisplayText(this.player.getCurrentWeapon().getWeaponType().name());
    }

    private void addBonusItem(int posXReal, int posYReal) {
        HealthBonus bonusItem = new HealthBonus(posXReal, posYReal);

        this.bonusItems.add(bonusItem);
        this.getRoot().getChildren().add(bonusItem);
    }

    private void updateBonusItems(Bonus bonusItem) {
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
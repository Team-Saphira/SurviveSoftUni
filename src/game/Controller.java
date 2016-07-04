package game;

import game.gui.GUIDrawer;
import game.gui.ScoreBar;
import game.level.Block;
import game.level.Level;
import game.models.Zombie;
import game.models.Player;
import game.moveLogic.AStar;
import game.moveLogic.Movable;
import game.moveLogic.MoveZombieManager;
import game.weapons.Bullet;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Controller {
    private static final double HEALTH_REDUCTION = 0.3;
    private static Random rand;
    private Player player;
    private List<KeyCode> inputKeyCodes;
    private Set<Zombie> zombieSet;
    private Pane root;
    private List<Bullet> bulletList;


    //IB
    private ScoreBar scoreBar;
    private List<BonusItem> bonusItems;
    private GUIDrawer guiDrawer;

    public Controller(Player player,
                      List<KeyCode> inputKeyCodes,
                      Set<Zombie> zombieSet,
                      Pane root,
                      List<Bullet> bulletList,
                      GUIDrawer guiDrawer,
                      ScoreBar scoreBar,
                      List<BonusItem> bonusItems) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setZombieSet(zombieSet);
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
                    this.getGuiDrawer().changeWeaponImage("Pistol");
                    break;
                case DIGIT2:
                    this.getPlayer().changeWeapon("MachineGun");
                    this.getGuiDrawer().changeWeaponImage("MachineGun");
                    break;
                default:
                    break;
            }
        }

        //IB testing Player health reduction
        for (Zombie zombie : zombieSet) {
//            Shape intersect = Shape.intersect(this.player.getBoundingBox(), zombie.getBoundingBox());
            if (this.getPlayer().getBoundingBox().getBoundsInParent().intersects(zombie.getBoundingBox().getBoundsInParent())) {
//                if (intersect.getBoundsInLocal().getWidth() != -1) {
                this.getPlayer().setHealth(this.getPlayer().getHealth() - HEALTH_REDUCTION);

//                updateHealthBar();
                break;
            }
        }

        for (BonusItem bonusItem: bonusItems) {
            if (this.player.getBoundsInParent().intersects(bonusItem.getBoundsInParent())) {
                updateBonusItems(bonusItem);
                break;
            }
        }

        if (this.getPlayer().getHealth() < 0) {

            //TODO "Game over" screen or lose live
        }
    }

    public void updateEnemies() {

        ArrayList<Zombie> zombiesToRemove = new ArrayList<>();
        for (Zombie zombie : this.getZombieSet()) {

            MoveZombieManager moveZombieManager = new MoveZombieManager(zombie);

            if (zombie.getHealth() <= 0) {
                zombiesToRemove.add(zombie);
                continue;
            }

            //updates zombie position
            zombie.setPosXReal((int) zombie.localToParent(zombie.getBoundsInLocal()).getMinX());
            zombie.setPosX(zombie.getPosXReal() / Constants.BLOCK_SIZE);
            zombie.setPosYReal((int) zombie.localToParent(zombie.getBoundsInLocal()).getMinY());
            zombie.setPosY(zombie.getPosYReal() / Constants.BLOCK_SIZE);

            //find shortest path to player
            zombie.updatePath(Level.levelBlockWidth,
                    Level.levelBlockHeight,
                    player.getPosX(),
                    player.getPosY(),
                    zombie.getPosX(),
                    zombie.getPosY(),
                    Level.levelBlockMatrix);
            //if A* returns an empty path then the zombie is too far away to hone in on player
            if (zombie.path.isEmpty()) {
                if (zombie.getIsInCollision()) {
                    int pos = rand.nextInt(Constants.CREATURE_DIRECTIONS.length);
                    System.out.println("new Direction " + pos);
                    zombie.setMoveDirection(Constants.CREATURE_DIRECTIONS[pos]);
                }
                if (rand.nextInt(1000) < 5) {
                    int pos = rand.nextInt(Constants.CREATURE_DIRECTIONS.length);
                    System.out.println("new Direction " + pos);
                    zombie.setMoveDirection(Constants.CREATURE_DIRECTIONS[pos]);
                }
                switch (zombie.getMoveDirection()) {
                    case 'U':
                        moveZombieManager.moveY(-Constants.ENEMY_VELOCITY);
                        zombie.getAnimation().play();
                        zombie.getAnimation().setOffsetY(0);
                        break;
                    case 'D':
                        moveZombieManager.moveY(Constants.ENEMY_VELOCITY);
                        zombie.getAnimation().play();
                        zombie.getAnimation().setOffsetY(3 * 64);
                        break;
                    case 'L':
                        moveZombieManager.moveX(-Constants.ENEMY_VELOCITY);
                        zombie.getAnimation().play();
                        zombie.getAnimation().setOffsetY(2 * 64);
                        break;
                    case 'R':
                        moveZombieManager.moveX(Constants.ENEMY_VELOCITY);
                        zombie.getAnimation().play();
                        zombie.getAnimation().setOffsetY(64);
                        break;
                }
            } else {
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

                if (nextNode.getX() < zombie.getPosX()) {
                    moveZombieManager.moveX(-Constants.ENEMY_VELOCITY);
                    zombie.getAnimation().play();
                    zombie.getAnimation().setOffsetY(2 * 64);
                } else if (nextNode.getX() > zombie.getPosX()) {
                    moveZombieManager.moveX(Constants.ENEMY_VELOCITY);
                    zombie.getAnimation().play();
                    zombie.getAnimation().setOffsetY(64);
                } else if (nextNode.getY() < zombie.getPosY()) {
                    moveZombieManager.moveY(-Constants.ENEMY_VELOCITY);
                    zombie.getAnimation().play();
                    zombie.getAnimation().setOffsetY(0);
                } else if (nextNode.getY() > zombie.getPosY()) {
                    moveZombieManager.moveY(Constants.ENEMY_VELOCITY);
                    zombie.getAnimation().play();
                    zombie.getAnimation().setOffsetY(3 * 64);
                }
            }
        }
        for (Zombie zombie : zombiesToRemove) {
            //IB Threshold set to 0.8 for testing purpose only!
            if (Math.random() < BonusItem.RANDOM_DROP_THRESHOLD) {
                addBonusItem(zombie.getPosXReal(), zombie.getPosYReal());
            }

            this.zombieSet.remove(zombie);
            this.getRoot().getChildren().remove(zombie);

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
                    if (wall.getBlockType() == Block.BlockType.BRICK && wall.getOpacity() - 0.15 < 0) {
                        this.getRoot().getChildren().remove(wall.getBlockBBox());
                        this.getRoot().getChildren().remove(wall);
                        wallsToRemove.add(wall);
                        Level.levelBlockMatrix[(int) wall.getTranslateX() / Constants.BLOCK_SIZE][(int) wall.getTranslateY() / Constants.BLOCK_SIZE] = 0;
                    } else if (wall.getBlockType() == Block.BlockType.BRICK) {
                        wall.setOpacity(wall.getOpacity() - 0.15);
                    }
                    break;
                }
        }

        for (Bullet bullet : bulletsToRemove) {
            bulletList.remove(bullet);
        }

        for (Block wall : wallsToRemove) {
            int index = Level.platforms.indexOf(wall);
            Level.platforms.remove(wall);
            Level.boxes.remove(index);
        }
    }

    public void updateHealthBar() {
//        this.getGuiDrawer().getHealthBar().reduceHealth(this.player.getHealth());
//        this.healthBar.setLayoutX(0 - this.root.getLayoutX());
//        this.healthBar.setLayoutY(0 - this.root.getLayoutY());

        Rectangle imageCutter = new Rectangle((int)(((double)this.getPlayer().getHealth()/(double)this.getGuiDrawer().getHealthBar().getInitialHealth())*150), 30);
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
        this.getPlayer().gainLife();
    }
}

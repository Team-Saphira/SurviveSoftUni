package game.core;

import game.bonusItems.interfaces.Bonus;
import game.gui.*;
import game.level.Level;
import game.level.LevelData;
import game.level.TerrainGenerator;
import game.menus.MainMenu;
import game.menus.MenuBox;
import game.menus.Title;
import game.models.Player;
import game.models.SmartZombie;
import game.models.interfaces.Enemy;
import game.sprites.ImageLoader;
import game.staticData.Constants;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Content {
    private Pane root;
    private Player player;
    private Set<Enemy> enemies;
    private AnimationTimer timer;
    private ImageView menuView;

    private HealthBar healthbar;
    private CurrentWeaponDisplay currentWeaponDisplay;
    private HealthPoints healthPoints;
    private ScorePoints scorePoints;
    private WeaponTextDisplay weaponTextDisplay;
    private List<Bonus> bonusItemList;
    private GUIDrawer guiDrawer;

    public Content(Pane root,
                   Player player,
                   Set<Enemy> enemies,
                   AnimationTimer timer,
                   HealthBar healthbar,
                   CurrentWeaponDisplay currentWeaponDisplay,
                   HealthPoints healthPoints,
                   ScorePoints scorePoints,
                   WeaponTextDisplay weaponTextDisplay,
                   List<Bonus> bonusItems,
                   GUIDrawer guiDrawer) {
        this.setRoot(root);
        this.setPlayer(player);
        this.setEnemies(enemies);
        this.setTimer(timer);
        this.setHealthbar(healthbar);
        this.setCurrentWeaponDisplay(currentWeaponDisplay);
        this.setHealthPoints(healthPoints);
        this.setScorePoints(scorePoints);
        this.setWeaponTextDisplay(weaponTextDisplay);
        this.setBonusItemList(bonusItems);
        this.setGuiDrawer(guiDrawer);
        this.setMenuView(new ImageView(ImageLoader.MAIN_MENU_IMAGE));
    }

    private void setWeaponTextDisplay(WeaponTextDisplay weaponTextDisplay) {
        this.weaponTextDisplay = weaponTextDisplay;
    }

    private void setCurrentWeaponDisplay(CurrentWeaponDisplay currentWeaponDisplay) {
        this.currentWeaponDisplay = currentWeaponDisplay;
    }

    public void setScorePoints(ScorePoints scorePoints) {
        this.scorePoints = scorePoints;
    }

    public void setHealthPoints(HealthPoints healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setHealthbar(HealthBar healthbar) {
        this.healthbar = healthbar;
    }

    public void setBonusItemList(List<Bonus> bonusItemList) {
        this.bonusItemList = bonusItemList;
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Set<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Set<Enemy> enemies) {
        this.enemies = enemies;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public GUIDrawer getGuiDrawer() {
        return guiDrawer;
    }

    public void setGuiDrawer(GUIDrawer guiDrawer) {
        this.guiDrawer = guiDrawer;
    }

    private void setMenuView(ImageView menuView) {
        this.menuView = menuView;
    }

    public Parent createContent() {

        this.menuView.setFitWidth(1000);
        this.menuView.setFitHeight(640);
        this.root.getChildren().add(this.menuView);

        Title title = new Title("S U R V I V Е   S O F T U N I");
        title.setTranslateX(25);
        title.setTranslateY(110);

        MainMenu itemStart = new MainMenu("START GAME");
        MainMenu itemExit = new MainMenu("EXIT");

        itemStart.setOnMouseClicked(event -> {
            this.menuView.setVisible(false);
            itemStart.setVisible(false);
            itemExit.setVisible(false);
            title.setVisible(false);

            this.getRoot().setPrefSize(1000, 640);
            LevelData leveldata = new LevelData();

            if (Constants.RANDOMISE_LEVELS) {
                leveldata = generateRandomLevel(leveldata);
            }
            Level.initLevel(leveldata);
            this.getRoot().getChildren().addAll(Level.impassableBlocks);
            this.getRoot().getChildren().addAll(Level.impassableBlockBBoxes);
            this.getRoot().getChildren().addAll(Level.passableBlocks);
            this.getRoot().getChildren().addAll(Level.passableBlockBBoxes);
            this.getRoot().getChildren().addAll(Level.destructibleBlocks);
            this.getRoot().getChildren().addAll(Level.destructibleBlockBBoxes);


            this.getRoot().getChildren().add(this.player);
            this.getRoot().getChildren().add(this.player.getBoundingBox());
            this.getRoot().getChildren().add(this.guiDrawer);

            this.getPlayer().translateXProperty().addListener((obs, old, newValue) -> {
                int offset = newValue.intValue();
                if (offset > 350 && offset < Level.getLevelWidth()) {
                    this.getRoot().setLayoutX(-(offset - 350));
                }
            });
            this.getPlayer().translateYProperty().addListener((obs, old, newValue) -> {
                int offset = newValue.intValue();
                if (offset > 350 && offset < Level.getLevelHeight() - 340) {
                    this.getRoot().setLayoutY(-(offset - 350));
                }
            });

            spawnEnemies();

            this.guiDrawer.toFront();
            this.guiDrawer.drawHealthBar();
            this.guiDrawer.drawWeaponBar();
            this.guiDrawer.drawHealthPoints();
            this.guiDrawer.drawScorePoints();
            this.guiDrawer.drawCurrentWeapon();
            this.guiDrawer.drawWeaponText();

            this.getTimer().start();
        });

        itemExit.setOnMouseClicked(event -> System.exit(0));

        MenuBox menu = new MenuBox(
                itemStart,
                itemExit);
        menu.setTranslateX(25);
        menu.setTranslateY(230);
        this.getRoot().getChildren().addAll(title, menu);

        return this.getRoot();
    }

    public Parent loadNextLevel() {
        this.getRoot().setPrefSize(1000, 640);
        LevelData leveldata = new LevelData();

        if (Constants.RANDOMISE_LEVELS) {
            generateRandomLevel(leveldata);
        }
        Level.initLevel(leveldata);
        this.getRoot().getChildren().addAll(Level.impassableBlocks);
        this.getRoot().getChildren().addAll(Level.impassableBlockBBoxes);
        this.getRoot().getChildren().addAll(Level.passableBlocks);
        this.getRoot().getChildren().addAll(Level.passableBlockBBoxes);
        this.getRoot().getChildren().addAll(Level.destructibleBlocks);
        this.getRoot().getChildren().addAll(Level.destructibleBlockBBoxes);


        this.getRoot().getChildren().add(this.player);
        this.getRoot().getChildren().add(this.player.getBoundingBox());
        this.getRoot().getChildren().add(this.guiDrawer);

        this.getPlayer().translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 350 && offset < Level.getLevelWidth()) {
                this.getRoot().setLayoutX(-(offset - 350));
            }
        });
        this.getPlayer().translateYProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 350 && offset < Level.getLevelHeight() - 340) {
                this.getRoot().setLayoutY(-(offset - 350));
            }
        });

        spawnEnemies();

        this.getTimer().start();

        return this.getRoot();
    }

    private void spawnEnemies() {
        Random rand = new Random();
        for (int i = 0; i < Constants.ZOMBIE_SPAWN_NUM; i++) {
            int x = rand.nextInt(Level.levelBlockWidth);
            int y = rand.nextInt(Level.levelBlockHeight);

            if (Constants.RANDOMISE_LEVELS) {
                while (!TerrainGenerator.getPassableArea().contains(new TerrainGenerator.Tuple<>(x, y))) {
                    x = rand.nextInt(Level.levelBlockWidth);
                    y = rand.nextInt(Level.levelBlockHeight);
                }
            } else {
                while (Level.levelBlockMatrix[x][y] != 0) {
                    x = rand.nextInt(Level.levelBlockWidth);
                    y = rand.nextInt(Level.levelBlockHeight);
                }
            }
            SmartZombie smartZombie = new SmartZombie(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE);
            this.getRoot().getChildren().add(smartZombie);
            this.getEnemies().add(smartZombie);
        }
    }

    private LevelData generateRandomLevel(LevelData leveldata) {
        leveldata.clearLevels();
        leveldata.addLevel(TerrainGenerator.generateNewLevel());
        this.setPlayerPosition();
        return leveldata;
    }

    private void setPlayerPosition() {
        this.player.setTranslateX(TerrainGenerator.getPlayerStartX() * Constants.BLOCK_SIZE + 1);
        this.player.setTranslateY(TerrainGenerator.getPlayerStartY() * Constants.BLOCK_SIZE + 1);
        this.player.changeBoundingBox(this.player.calcBoundingBox(Constants.PLAYER_SIZE));
    }

}
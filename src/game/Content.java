package game;

import game.gui.GUIDrawer;
import game.gui.HealthBar;
import game.gui.ScoreBar;
import game.level.Level;
import game.models.Player;
import game.models.Zombie;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Content {
    private Pane root;
    private Player player;
    private Set<Zombie> zombieSet;
    private AnimationTimer timer;


    private HealthBar healthbar;
    private ScoreBar scoreBar;
    private List<BonusItem> bonusItemList;
    private GUIDrawer guiDrawer;

    public Content(Pane root,
                   Player player,
                   Set<Zombie> zombieSet,
                   AnimationTimer timer,
                   HealthBar healthbar,
                   ScoreBar scoreBar,
                   List<BonusItem> bonusItems,
                   GUIDrawer guiDrawer) {
        this.setRoot(root);
        this.setPlayer(player);
        this.setZombieSet(zombieSet);
        this.setTimer(timer);
        this.setHealthbar(healthbar);
        this.setScoreBar(scoreBar);
        this.setBonusItemList(bonusItems);
        this.setGuiDrawer(guiDrawer);
    }

    public void setHealthbar(HealthBar healthbar) {
        this.healthbar = healthbar;
    }

    public void setScoreBar(ScoreBar scoreBar) {
        this.scoreBar = scoreBar;
    }

    public void setBonusItemList(List<BonusItem> bonusItemList) {
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

    public Set<Zombie> getZombieSet() {
        return zombieSet;
    }

    public void setZombieSet(Set<Zombie> zombieSet) {
        this.zombieSet = zombieSet;
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

    public Parent createContent() {
        this.getRoot().setPrefSize(1000, 640);

        Level.initLevel();
        this.getRoot().getChildren().addAll(Level.platforms);
        this.getRoot().getChildren().addAll(Level.boxes);


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

        Random rand = new Random();
        for (int i = 0; i < Constants.ENEMY_SPAWN_NUM; i++) {
            int x = rand.nextInt(Level.levelBlockWidth);
            int y = rand.nextInt(Level.levelBlockHeight);

            while (Level.levelBlockMatrix[x][y] != 0) {
                x = rand.nextInt(Level.levelBlockWidth);
                y = rand.nextInt(Level.levelBlockHeight);
            }

            Zombie zombie = new Zombie(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE);
            this.getRoot().getChildren().add(zombie);
            this.getZombieSet().add(zombie);
        }


        this.guiDrawer.drawHealthBar();
        this.guiDrawer.drawWeaponBar();


        this.getTimer().start();

        return this.getRoot();
    }
}

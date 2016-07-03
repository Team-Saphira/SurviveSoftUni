package game;

import game.gui.Healthbar;
import game.gui.ScoreBar;
import game.level.Block;
import game.level.Level;
import game.models.Zombie;
import game.models.Player;
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

    //IB
    private Healthbar healthbar;
    private ScoreBar scoreBar;
    private List<BonusItem> bonusItemList;



    public Content(Pane root,
                   Player player,
                   Set<Zombie> zombieSet,
                   AnimationTimer timer,
                   Healthbar healthbar,
                   ScoreBar scoreBar,
                   List<BonusItem> bonusItems) {
        this.setRoot(root);
        this.setPlayer(player);
        this.setZombieSet(zombieSet);
        this.setTimer(timer);
        this.setHealthbar(healthbar);
        this.setScoreBar(scoreBar);
        this.setBonusItemList(bonusItems);
    }


    //IB
    public void setHealthbar(Healthbar healthbar) {
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

    public Parent createContent() {
        this.getRoot().setPrefSize(1000, 640);

        Level.initLevel();
        this.getRoot().getChildren().addAll(Level.platforms);
        this.getRoot().getChildren().addAll(Level.bboxes);


        this.getRoot().getChildren().add(this.player);
        this.getRoot().getChildren().add(this.player.getBoundingBox());

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

            while (Level.levelBlockMatrix[y][x] != 0) {
                x = rand.nextInt(Level.levelBlockWidth);
                y = rand.nextInt(Level.levelBlockHeight);
            }

            Zombie zombie = new Zombie(x * Block.BLOCK_SIZE, y * Block.BLOCK_SIZE);
            this.getRoot().getChildren().add(zombie);
            this.getZombieSet().add(zombie);
        }

        //IB Problem: make it stick to the top of the window.
        this.getRoot().getChildren().add(healthbar);

        this.getRoot().getChildren().add(scoreBar);

//        this.getRoot().getChildren().addAll(bonusItemList);

        this.getTimer().start();

        return this.getRoot();
    }
}

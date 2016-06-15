package game;

import game.Level.Block;
import game.Level.Level;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.Random;
import java.util.Set;

public class Content {
    private Pane root;
    private Player player;
    private Set<Enemy> zombieSet;
    private AnimationTimer timer;

    public Content(Pane root, Player player, Set<Enemy> zombieSet, AnimationTimer timer) {
        this.setRoot(root);
        this.setPlayer(player);
        this.setZombieSet(zombieSet);
        this.setTimer(timer);
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

    public Set<Enemy> getZombieSet() {
        return zombieSet;
    }

    public void setZombieSet(Set<Enemy> zombieSet) {
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


        this.getRoot().getChildren().add(player);
        this.getRoot().getChildren().add(player.boundingBox);

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

            Enemy zombie = new Enemy(x * Block.BLOCK_SIZE, y * Block.BLOCK_SIZE);
            this.getRoot().getChildren().add(zombie);
            this.getZombieSet().add(zombie);
        }

        this.getTimer().start();

        return this.getRoot();
    }
}

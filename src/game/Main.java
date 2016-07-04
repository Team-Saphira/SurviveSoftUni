package game;

import game.gui.GUIDrawer;
import game.gui.HealthBar;
import game.gui.ScoreBar;
import game.gui.WeaponBar;
import game.level.Level;
import game.models.Zombie;
import game.models.Player;
import game.moveLogic.Movable;
import game.moveLogic.MovePlayerManager;
import game.weapons.Bullet;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    public Pane root = new Pane();
    //    private List<Node> platforms = new ArrayList<>();
    public List<Node> enemies = new ArrayList<>();
    public static Set<Zombie> zombieSet = new LinkedHashSet<>();
    public Player player = new Player(270, 270);
    public List<KeyCode> inputKeyCodes = new ArrayList<>();
    public byte weaponCode = 1;
    public List<Bullet> bulletList = new ArrayList<>();
    //IB
    public HealthBar healthbar = new HealthBar(player.getHealth(), 20, Constants.DISPLAY_HEIGHT - 50, 150, 30);
    public WeaponBar weaponBar = new WeaponBar(Constants.DISPLAY_WIDTH - 120, Constants.DISPLAY_HEIGHT - 80, 100, 64);
    public ScoreBar scoreBar = new ScoreBar(0);
    public List<BonusItem> bonusItems = new ArrayList<>();
    public GUIDrawer guiDrawer = new GUIDrawer(healthbar, weaponBar);

    public Controller controller = new Controller(
            player,
            inputKeyCodes,
            zombieSet,
            root,
            bulletList,
            guiDrawer,
            scoreBar,
            bonusItems);

    public AnimationTimer timer = new AnimationTimer() {
        Movable movePlayerManager = new MovePlayerManager(player);
        @Override
        public void handle(long now) {
            controller.updateBullets();
            controller.updatePlayer(movePlayerManager);
            controller.updateEnemies();
            controller.updateHealthBar();

            //onUpdate();
        }
    };

    public Content content = new Content(root, player, zombieSet, timer, healthbar, scoreBar, bonusItems, guiDrawer);

    //TODO add controller and all sub-update methods in ↓ method?
    private void onUpdate() {
        for (Node enemy : enemies) {
            //move enemies according to chosen algorithm - AI?
        }

        if (Math.random() < 0.075) { // on certain or random time interval
            //spawnEnemy to be written by Kamen
//            enemies.add(spawnEnemy());
        }
        //checkState();
    }

    private boolean checkCollision() {
        for (Shape bbox : Level.boxes) {
            Shape intersect = Shape.intersect(bbox, this.player.getBoundingBox());
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                return true;
            }
        }
        return false;
    }

    private void checkState() {
        /// TODO:
        /// check if all enemies are killed. If true -> win text OR next level at later stage of development.

//        if (enemies.size() == 0) {
//            timer.stop();
//            String win = "YOU WIN";
//
//            HBox hBox = new HBox();
//            hBox.setTranslateX(300);
//            hBox.setTranslateY(250);
//            root.getChildren().add(hBox);
//
//            for (int i = 0; i < win.toCharArray().length; i++) {
//                char letter = win.charAt(i);
//
//                Text text = new Text(String.valueOf(letter));
//                text.setFont(Font.font(48));
//                text.setOpacity(0);
//
//                hBox.getChildren().add(text);
//
//                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
//                ft.setToValue(1);
//                ft.setDelay(Duration.seconds(i * 0.15));
//                ft.play();
//            }
//        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(content.createContent()));

        stage.getScene().setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (!inputKeyCodes.contains(keyCode)) {
                inputKeyCodes.add(keyCode);
            }
        });

        stage.getScene().setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            inputKeyCodes.remove(keyCode);
        });

        stage.getScene().setCursor(Cursor.CROSSHAIR);
        //TODO enable when collisions are fully fixed.
//        stage.getScene().setOnMouseMoved(e -> {
//            double mouseX = e.getSceneX();
//            double mouseY = e.getSceneY();
//            double xDistance = mouseX - (player.getTranslateX() + 60);
//            double yDistance = mouseY - (player.getTranslateY() + 60);
//            double rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance));
//            player.setRotate(rotationAngle);
//        });

        // TODO: bullets physics / collsions
        stage.getScene().setOnMouseClicked(event -> {
            MouseButton clickedButton = event.getButton();
            if (!clickedButton.equals(MouseButton.PRIMARY)) {
                return;
            }
            double mousePosX = event.getX() - this.root.getLayoutX();
            double mousePosY = event.getY() - this.root.getLayoutY();

            if (this.player.getCanShoot()) {
                this.player.setCanShoot(false);
                System.out.println("Shot bullet!");
                Bullet newBullet = new Bullet(player.getCurrentWeapon().minDamage(), player.getCurrentWeapon().maxDamage(), player.getCurrentWeapon().bulletSpeed());

                newBullet.setTranslateX(player.getTranslateX());
                newBullet.setTranslateY(player.getTranslateY());
                newBullet.setTarget(mousePosX, mousePosY);
                root.getChildren().add(newBullet);

                this.player.setIsShooting(true);
                bulletList.add(newBullet);
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

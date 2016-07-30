package game;

import game.gui.*;
import game.level.Level;
import game.menus.GameOver;
import game.models.Enemy;
import game.models.Player;
import game.moveLogic.interfaces.Movable;
import game.moveLogic.MovePlayerManager;
import game.weapons.Bullet;
import game.weapons.WeaponType;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    public Pane root = new Pane();
    public static Set<Enemy> enemySet = new LinkedHashSet<>();
    public Player player = new Player(270, 270);
    public List<KeyCode> inputKeyCodes = new ArrayList<>();
    public List<Bullet> bulletList = new ArrayList<>();
    //IB
    public HealthBar healthbar = new HealthBar(player.getHealth(), 20, Constants.DISPLAY_HEIGHT - 80, 190, 50); // 50, 150, 30
    //public WeaponBar weaponBar = new WeaponBar(Constants.DISPLAY_WIDTH - 120, Constants.DISPLAY_HEIGHT - 80, 100, 64);

    // TEST NEW WEAPON BAR VANCHO
    //public WeaponBar weaponBar = new WeaponBar(Constants.DISPLAY_WIDTH - 170, Constants.DISPLAY_HEIGHT - 50, 150, 30);
    public WeaponBar weaponBar = new WeaponBar(player.getScore(), Constants.DISPLAY_WIDTH - 290, Constants.DISPLAY_HEIGHT - 80, 190, 50);

    // HEALTH POINTS TEST VANCHO
    public CurrentWeaponDisplay currentWeaponDisplay = new CurrentWeaponDisplay(Constants.DISPLAY_WIDTH - 100, Constants.DISPLAY_HEIGHT - 80, 100, 51);
    public HealthPoints healthPoints = new HealthPoints((int) player.getHealth());
    public ScorePoints scorePoints = new ScorePoints(player.getScore());
    public WeaponTextDisplay weaponTextDisplay = new WeaponTextDisplay(this.player.getCurrentWeapon().getWeaponType().getWeaponName());

    public List<BonusItem> bonusItems = new ArrayList<>();
    public GUIDrawer guiDrawer = new GUIDrawer(healthbar, weaponBar, healthPoints, scorePoints, currentWeaponDisplay, weaponTextDisplay);

    public Controller controller = new Controller(
            player,
            inputKeyCodes,
            enemySet,
            root,
            bulletList,
            guiDrawer,
            healthPoints,
            scorePoints,
            weaponTextDisplay,
            bonusItems);

    public AnimationTimer timer = new AnimationTimer() {
        Movable movePlayerManager = new MovePlayerManager(player);

        @Override
        public void handle(long now) {
            doHandle();
        }

        private void doHandle() {

            if (player.getHealth() <= 0) {
                stop();
                gameOver();
            }
            if (Level.shouldChangeLevel) {
                System.out.println("TODO.....Change level pls ☺");
                Level.shouldChangeLevel = false;
            }
            controller.updateBullets();
            controller.updatePlayer(movePlayerManager);
            controller.updateEnemies();
            controller.updateHealthBar();
            controller.updateHealthPoints();
            controller.updateScorePoints();
            controller.updateWeaponDisplayText();
        }
    };

    public Content content = new Content(root, player, enemySet, timer, healthbar, currentWeaponDisplay, healthPoints, scorePoints, weaponTextDisplay, bonusItems, guiDrawer);

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

        stage.getScene().setOnMouseClicked(event -> {
            MouseButton clickedButton = event.getButton();
            if (!clickedButton.equals(MouseButton.PRIMARY)) {
                return;
            }
            double mousePosX = event.getX() - this.root.getLayoutX();
            double mousePosY = event.getY() - this.root.getLayoutY();

            if (this.player.getCanShoot()) {
                this.player.changeCanShoot(false);
                WeaponType playerCurrentWeapon = player.getCurrentWeapon().getWeaponType();
                Bullet newBullet = new Bullet(playerCurrentWeapon.getMinDamage(),
                        playerCurrentWeapon.getMaxDamage(),
                        playerCurrentWeapon.getBulletSpeed());

                newBullet.setTranslateX(player.getTranslateX() + Constants.PLAYER_SIZE / 2);
                newBullet.setTranslateY(player.getTranslateY() + Constants.PLAYER_SIZE / 2);
                newBullet.setTarget(mousePosX, mousePosY);
                root.getChildren().add(newBullet);

                this.player.isShooting(true);
                bulletList.add(newBullet);
            }
        });

        stage.show();
    }

    private void gameOver() {
        root.getChildren().addAll(GameOver.gameOverTitle());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

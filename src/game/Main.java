package game;

import game.Level.Block;
import game.Level.Level;
import game.weapons.Gun;
import game.weapons.MachineGun;
import game.weapons.Weapon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    public static Set<Enemy> zombieSet = new LinkedHashSet<>();
    public Player player = new Player(270, 270);
    public List<KeyCode> inputKeyCodes = new ArrayList<>();
    public byte weaponCode = 1;
    public List<Weapon> weaponList = new ArrayList<>();
    //IB
    public Healthbar healthbar = new Healthbar(player.getHealth(), 20, 20, 100, 15);
    public ScoreBar scoreBar = new ScoreBar(0);
    public List<BonusItem> bonusItems = new ArrayList<>();

    public Controller controller = new Controller(
            player,
            inputKeyCodes,
            zombieSet,
            root,
            weaponList,
            weaponCode,
            healthbar,
            scoreBar,
            bonusItems);

    public AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            controller.updateBullets();
            controller.updatePlayer();
            controller.updateEnemies();

            //onUpdate();
        }
    };

    public Content content = new Content(root, player, zombieSet, timer, healthbar, scoreBar, bonusItems);

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
        for (Shape bbox : Level.bboxes) {
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

        stage.getScene().setOnKeyTyped(ev -> {
            String weaponSelect = ev.getCharacter();
            switch (weaponSelect) {
                case "1":
                    weaponCode = 1;
                    System.out.println("!!! GUN SELECTED !!!");
                    break;
                case "2":
                    weaponCode = 2;
                    System.out.println("!!! MACHINE_GUN SELECTED !!!");
                    break;
            }
        });

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

            if (player.canShoot) {
                player.canShoot = false;
                System.out.println("Shot bullet!");
                Weapon weaponType = null;

                switch (weaponCode) {
                    case 1:
                        weaponType = new Gun();
                        System.out.println("!!! GUN ACTIVATED !!!");
                        break;
                    case 2:
                        weaponType = new MachineGun();
                        System.out.println("!!! MACHINE_GUN ACTIVATED !!!");
                        break;
                }

                weaponType.setTranslateX(player.getTranslateX());
                weaponType.setTranslateY(player.getTranslateY());
                weaponType.setTarget(mousePosX, mousePosY);
                root.getChildren().add(weaponType);

                player.isShooting = true;
                weaponList.add(weaponType);
            }
        });


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

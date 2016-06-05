package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public double x = 0;
    public double y = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Survive SoftUni");
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(1000, 600);
        ArrayList<String> input = new ArrayList<>();



        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image soldier = new Image("game\\survivor-move_handgun_0.png");
        ImageView imageView = new ImageView(soldier);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    // only add once... prevent duplicates
                    if (!input.contains(code))
                        input.add(code);
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });
        scene.setCursor(Cursor.CROSSHAIR);
        scene.setOnMouseMoved(e ->{
            double mouseX = e.getX();
            double mouseY = e.getY();
            double xDistance = mouseX - (imageView.getX() + 60);
            double yDistance = mouseY - (imageView.getY() + 60);
            double rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance));
            imageView.setRotate(rotationAngle);

        });

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Clear the canvas
                gc.clearRect(0, 0, 1000, 600);

                if (input.contains("A") && imageView.getX() >= -10) {
//                    x -= 5;
                    imageView.setX(imageView.getX() - 4);
                }
                if (input.contains("D") && imageView.getX() <= 920) {
//                    x += 5;
                    imageView.setX(imageView.getX() + 4);

                }
                if (input.contains("W") && imageView.getY() >= -10) {
//                    y -= 5;
                    imageView.setY(imageView.getY() - 4);

                }
                if (input.contains("S") && imageView.getY() <= 520) {
//                    y += 5;
                    imageView.setY(imageView.getY() + 4);

                }

//                gc.drawImage(soldier, x, y);
            }
        }.start();
        primaryStage.setScene(scene);
        root.getChildren().add(canvas);
        root.getChildren().add( imageView);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

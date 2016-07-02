package game.weapons;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class MachineGun extends Weapon{

    public MachineGun(){
        Image bulletImg = new Image(getClass().getResourceAsStream("/game/resources/weapons/MachineGun/machine_bullets.png"));
        ImageView imageView = new ImageView(bulletImg);
        getChildren().add(imageView);
    }

    @Override
    public void setTarget(double x, double y) {
        velocity = new Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(13);
        double angle = calcAngle(velocity.getX(),velocity.getY());
        getTransforms().clear();
        getTransforms().add(new Rotate(angle,0,0));
    }

    @Override
    public int getDamagePower() {
        return 3;
    }
}

package game.sprites;


import javafx.scene.image.Image;

public class ImageLoader {
    public static Image zombieImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/zombie.png"));
    public static Image playerImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/survivor-move_handgun.png"));
}

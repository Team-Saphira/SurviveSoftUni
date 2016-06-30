package game;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BonusItem extends Pane {

    private final static int BONUS_IMAGE_WIDTH = 30;
    private final static int BONUS_IMAGE_HEIGHT = 30;
    public final static double RANDOM_DROP_THRESHOLD = 0.8;

    private Image image;
    private ImageView imageView;
    private int posX;
    private int posY;

    public BonusItem(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.image = new Image("resources/life.png");
        this.imageView = new ImageView(this.getImage());
        this.imageView.setFitHeight(BONUS_IMAGE_HEIGHT);
        this.imageView.setFitWidth(BONUS_IMAGE_WIDTH);
        this.imageView.setTranslateX(this.getPosX());
        this.imageView.setTranslateY(this.getPosY());
        this.getChildren().addAll(this.imageView);
    }

    private Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}

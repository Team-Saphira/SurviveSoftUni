package game;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BonusItem extends Pane {



    private Image image;
    private ImageView imageView;
    private int posX;
    private int posY;
    private Rectangle boundingBox;



    public BonusItem(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.image = new Image("game/resources/life.png");
        this.imageView = new ImageView(this.getImage());
        this.imageView.setFitHeight(Constants.BONUS_IMAGE_HEIGHT);
        this.imageView.setFitWidth(Constants.BONUS_IMAGE_WIDTH);
        this.imageView.setTranslateX(this.getPosX());
        this.imageView.setTranslateY(this.getPosY());
        this.boundingBox = new Rectangle(this.getImageView().getFitWidth(), this.getImageView().getFitHeight());
        this.boundingBox.setTranslateX(this.posX);
        this.boundingBox.setTranslateY(this.posY);
        this.boundingBox.setFill(Color.BLACK);

        this.boundingBox.setOpacity(1);

        this.getChildren().addAll(this.imageView, this.boundingBox);
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
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

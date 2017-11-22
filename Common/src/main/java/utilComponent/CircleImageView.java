package utilComponent;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class CircleImageView extends Circle{

    private Image image;
    public CircleImageView(){
        this("/default/timg.jpg");
    }

    public CircleImageView(String url){
        super();
        this.setSmooth(true);
        this.setCache(true);
        image=new Image(getClass().getResource(url).toExternalForm());
        this.setFill(new ImagePattern(image));
        this.setVisible(true);
    }

    public void setImage(String url) {
        this.image = new Image(url);
        this.setFill(new ImagePattern(image));
    }

    public void setImage(Image image) {
        this.image = image;
        this.setFill(new ImagePattern(image));
    }


}

import java.io.FileInputStream;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ZeroBullet extends TroopControl
{
	public ZeroBullet(double x, double y, Attacker a, Pane root) throws Exception
	{
		double x2 = a.getX();
		FileInputStream input = new FileInputStream("res/imgs/one.png");
		Image m = new Image(input, window.getHeight() * .20, window.getHeight() * .20, false, false);
		ImageView img = new ImageView(m);
		img.setTranslateX(x);
		img.setTranslateY(y);
		root.getChildren().add(img);
		TranslateTransition t = new TranslateTransition();
		t.setNode(img);
		double durate = window.getWidth() * (Math.abs(x2 - x) * .005);
		t.setDuration(Duration.millis(durate));
		t.setByX(x2-x);
		t.setOnFinished(e -> a.decreaseHealth(15));
		t.play();
	}
}

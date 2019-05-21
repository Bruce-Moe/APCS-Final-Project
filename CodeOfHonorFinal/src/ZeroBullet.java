import java.io.InputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemNotFoundException;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ZeroBullet extends TroopControl
{
	private Pane root;
	private int unique;
	private ImageView img;
	private boolean canGoFriendly;
	private boolean canGoEnemy;
	public ZeroBullet()
	{
		canGoFriendly = true;
		canGoEnemy = true;
		
	}
	
	public void setRoot(Pane root)
	{
		this.root = root;
	}
	
	public void setImage(ImageView img)
	{
		this.img = img;
	}
	
	public void createBullet(double x, double y, Attacker a, boolean friendly) throws FileNotFoundException
	{
		
		if(friendly && canGoFriendly)
		{
			double x2 = 0;
			if(a == null)
				x2 = window.getWidth() * .88125;
			else
				x2 = a.getX() + (window.getWidth() * .0125);
			canGoFriendly = false;
			double x1 = x + window.getWidth() * .0275;
			double y1 = y + window.getHeight() * .035;
			TranslateTransition t = new TranslateTransition();
			t.setNode(img);
			double durate = window.getWidth() * (Math.abs(x2 - x1) * .0035);
			t.setFromX(x1);
			t.setFromY(y1);
			t.setDuration(Duration.millis(durate));
			t.setToX(x2);
			t.setOnFinished(e -> {
				if(a == null)
					enemyHealth -= 10;
				else
					a.decreaseHealth(10);
				img.setTranslateX(-10000);
				TranslateTransition coolDown = new TranslateTransition();
				coolDown.setNode(img);
				coolDown.setByX(-1);
				coolDown.setDuration(Duration.millis(1000));
				coolDown.setOnFinished(f -> canGoFriendly = true);
				coolDown.play();
			});
			t.play();
			
		}else if(!friendly && canGoEnemy)
		{
			double x2 = 0;
			if(a == null)
				x2 = window.getWidth() * .0725;
			else
				x2 = a.getX() + (window.getWidth() * .0325);
			canGoEnemy = false;
			double x1 = x + window.getWidth() * .0275;
			double y1 = y + window.getHeight() * .035;
			TranslateTransition t = new TranslateTransition();
			t.setNode(img);
			double durate = window.getWidth() * (Math.abs(x2 - x1) * .0035);
			t.setFromX(x1);
			t.setFromY(y1);
			t.setDuration(Duration.millis(durate));
			t.setToX(x2);
			t.setOnFinished(e -> {
				if(a == null)
					friendlyHealth -= 10;
				else
					a.decreaseHealth(8);
				img.setTranslateX(-10000);
				TranslateTransition coolDown = new TranslateTransition();
				coolDown.setNode(img);
				coolDown.setByX(-1);
				coolDown.setDuration(Duration.millis(1000));
				coolDown.setOnFinished(f -> canGoEnemy = true);
				coolDown.play();
			});
			t.play();
		}
			
	}
	
}

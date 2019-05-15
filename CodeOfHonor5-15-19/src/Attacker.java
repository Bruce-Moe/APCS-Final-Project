import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;



public abstract class Attacker extends TroopControl
{
	public abstract void decreaseHealth(int d);
	public abstract int getHealth();
	public abstract int getDamage();
	public abstract void move();
	public abstract void setImg(ImageView i);
	public abstract void setImgE(ImageView i);
	//public abstract void setClosest(Button x);
	public abstract ImageView getImg();
	public abstract double getX();
}

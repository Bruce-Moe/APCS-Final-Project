import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class TroopControl extends GameMenuDemo
{
	private Button troop;
	private Pane root;
	private Button one;
	public TroopControl(Pane root) throws FileNotFoundException
	{
		
		
		this.root = root;
		
		
	}
	
	public void setMenu() throws FileNotFoundException
	{
		troop.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 250);
		troop.setTranslateY(80);
		root.getChildren().add(troop);
		FileInputStream inputstream2 = new FileInputStream("res/imgs/1.png"); 
		Image imageOne = new Image(inputstream2, 50, 50, false, false); 
		one = new Button();
		one.setGraphic(new ImageView(imageOne));
		one.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 600);
		one.setTranslateY(80);
		root.getChildren().add(one);
		listenOne();
	}
	
	public void setTroop(Button troop)
	{
		this.troop = troop;
	}
	
	public void removeTroops()
	{
		root.getChildren().removeAll(one, troop);
	}
	
	public void listenOne()
	{
		one.setOnMouseClicked(e -> {
			One o = new One();
			try
			{
				ImageView img = createTroop(o);
				root.getChildren().add(img);
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	public ImageView createTroop(Attacker a) throws Exception
	{
		ImageView img = new ImageView();
		if(a instanceof One)
		{
			Image m = new Image(new FileInputStream("H:\\melee.png"));
			img = new ImageView(m);
			img.setTranslateX(240);
			img.setTranslateY(830);
			a.setImg(img);
			
			
		}
		
		return img;
	}
	
	
}

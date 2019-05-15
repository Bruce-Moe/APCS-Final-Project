import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EnemyControl extends TroopControl
{
	private Pane root;
	private ImageView img;
	public void setRoot(Pane root)
	{
		this.root = root;
			
		stop = true;
			logic();
	}
	
	public void logic()
	{	

		if(!stop)
			return;

			Timeline timeline = new Timeline(new KeyFrame(
		    
				Duration.millis((int)(Math.random() * 4000) + 5000),
		        ae -> produceOne()));
			timeline.play();
		
	}
	
	public void produceOne()
	{
			int num = (int)(Math.random() * 2);
			try
			{
				if(num == 1)
					img = createTroop(new One(false), false);
				else if(num == 0)
					img = createTroop(new Zero(false), false);
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			root.getChildren().add(img);
			logic();
	}
}

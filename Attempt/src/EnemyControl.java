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
		
			logic();
	}
	
	public void logic()
	{
	
		/*System.out.println("Hey");
		Timer t = new TranslateTransition();
		t.setDuration(Duration.millis(50000));
		t.setByX(10);
		t.setOnFinished(e -> {
			
				System.out.println("Hey2");
				try
				{
				img = createTroop(new One(false), false);
				} catch (Exception e1)
				{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
				root.getChildren().add(img);
			
			
			
		});
		t.play();*/
		
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis((int)(Math.random() * 3000) + 2000),
		        ae -> produceOne()));
		timeline.play();
	}
	
	public void produceOne()
	{
		try
		{
		img = createTroop(new One(false), false);
		} catch (Exception e1)
		{
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		root.getChildren().add(img);
		if(stop)
			logic();
	}
}

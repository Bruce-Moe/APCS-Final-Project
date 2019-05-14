import javafx.animation.TranslateTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EnemyControl extends TroopControl
{
	private Stage window;
	public EnemyControl()
	{
		window = null;
	}
	
	public void setWindow(Stage w)
	{
		window = w;
		logic();
	}
	public void logic()
	{
		TranslateTransition t = new TranslateTransition();
		t.setDuration(Duration.millis(5000));
		t.setOnFinished(e -> {
			try
			{
				createTroop(new One(window, false), false);
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		t.play();
	}
}

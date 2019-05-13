import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application
{

	Stage window;
	Stage scene1;
	Scene scene2;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		
		Label label1 = new Label("Welcome to the first scene!");
		Button button1 = new Button("Go to scene 2");
		button1.setOnAction(e -> window.setScene(scene2));
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}

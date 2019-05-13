import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Battlefield extends Application /*implements EventHandler<ActionEvent>*/ {
	
	Button button;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("Code of Honor");
		
		button = new Button();
		button.setText("Spawn Units");
		
		button.setOnAction(e -> System.out.println("xd"));

		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
	
}

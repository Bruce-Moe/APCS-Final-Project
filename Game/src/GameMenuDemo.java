import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class GameMenuDemo extends Application
{

	Stage window;
	Scene main;
	Scene play;
	Scene instructions;
	Scene credits;
	boolean val = true;

	private Parent createContent()
	{
		Pane root = new Pane();
		root.setPrefSize(800, 600);

		try (InputStream is = Files.newInputStream(Paths.get("res/imgs/Code_of_Honor.jpg")))
		{
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(800);
			img.setFitHeight(600);
			root.getChildren().add(img);
		} catch (IOException e)
		{
			System.out.println("Couldn't load image");
		}

		MenuItem itemExit = new MenuItem("EXIT");
		itemExit.setOnMouseClicked(event -> System.exit(0));

		MenuItem playMenu = new MenuItem("PLAY");
		playMenu.setOnMouseClicked(event -> {
			playAudio(val);
			window.setScene(play);
			window.setFullScreen(true);	
		});

		MenuItem instructionMenu = new MenuItem("INSTRUCTIONS");
		instructionMenu.setOnMouseClicked(event -> window.setScene(instructions));

		MenuItem creditsMenu = new MenuItem("CREDITS");
		creditsMenu.setOnMouseClicked(event -> window.setScene(credits));

		MenuBox menu = new MenuBox(playMenu, instructionMenu, creditsMenu, itemExit);
		menu.setTranslateX(290);
		menu.setTranslateY(225);

		root.getChildren().addAll(menu);
		return root;
	}

	private Parent createInstructions()
	{
		Pane root = new Pane();
		root.setPrefSize(800, 600);

		try (InputStream is = Files.newInputStream(Paths.get("res/imgs/Code_of_Honor.jpg")))
		{
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(800);
			img.setFitHeight(600);
			root.getChildren().add(img);
		} catch (IOException e)
		{
			System.out.println("Couldn't load image");
		}

		MenuItem back = new MenuItem("BACK");
		back.setOnMouseClicked(event -> window.setScene(main));

		MenuBox menu = new MenuBox(back);
		menu.setTranslateX(290);
		menu.setTranslateY(500);

		root.getChildren().addAll(menu);
		return root;
	}

	private Parent createCredits()
	{
		Pane root = new Pane();
		root.setPrefSize(800, 600);

		try (InputStream is = Files.newInputStream(Paths.get("res/imgs/Code_of_Honor.jpg")))
		{
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(800);
			img.setFitHeight(600);
			root.getChildren().add(img);
		} catch (IOException e)
		{
			System.out.println("Couldn't load image");
		}

		MenuItem back = new MenuItem("BACK");
		back.setOnMouseClicked(event -> window.setScene(main));

		MenuBox menu = new MenuBox(back);
		menu.setTranslateX(290);
		menu.setTranslateY(500);

		root.getChildren().addAll(menu);
		return root;
	}

	private Parent createPlay() throws FileNotFoundException
	{
		Pane root = new Pane();
		StackPane moneyContainer = new StackPane();
		
		//Background Image
		try (InputStream is = Files.newInputStream(Paths.get("res/imgs/TYLERKERCHISGAY.png")))
		{
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
			img.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight() + 50);
			root.getChildren().add(img);
		} catch (IOException e)
		{
			System.out.println("Couldn't load image");
		}

		//Train  Button
		FileInputStream inputstream1 = new FileInputStream("res/imgs/troop.jpg"); 
		Image imageTrain = new Image(inputstream1, 50, 50, false, false); 
		Button trainTroops = new Button();
		trainTroops.setGraphic(new ImageView(imageTrain));
		trainTroops.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 350);
		trainTroops.setTranslateY(80);
		
		//Turret Button
		FileInputStream inputstream2 = new FileInputStream("res/imgs/turret.png"); 
		Image imageTroop = new Image(inputstream2, 50, 50, false, false); 
		Button buildTurret = new Button();
		buildTurret.setGraphic(new ImageView(imageTroop));
		buildTurret.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 450);
		buildTurret.setTranslateY(80);
		
		//Evolve Button
		FileInputStream inputstream4 = new FileInputStream("res/imgs/evolve.png"); 
		Image imageEvolve = new Image(inputstream4, 50, 50, false, false); 
		Button evolve = new Button();
		evolve.setGraphic(new ImageView(imageEvolve));
		evolve.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 550);
		evolve.setTranslateY(80);
		
		//Special Ability Button
		FileInputStream inputstream5 = new FileInputStream("res/imgs/star.png"); 
		Image imageSpecial = new Image(inputstream5, 50, 50, false, false); 
		Button special = new Button();
		special.setGraphic(new ImageView(imageSpecial));
		special.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 650);
		special.setTranslateY(80);
		
		//Audio Button
		FileInputStream inputstream6 = new FileInputStream("res/imgs/music.png"); 
		Image imageAudio = new Image(inputstream6, 50, 50, false, false); 
		Button audio = new Button();
		audio.setGraphic(new ImageView(imageAudio));
		audio.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 250);
		audio.setTranslateY(80);
		audio.setOnAction(e -> {
			val = !val;
			playAudio(val);
		});
		
		//Cashbag Icon next to Money
		FileInputStream inputstream3 = new FileInputStream("res/imgs/cashbag.png"); 
		Image imageDollar = new Image(inputstream3, 25, 25, false, false); 
		ImageView money = new ImageView(imageDollar);
	
		//Right Menu Container(train, turrets, etc.)
		Rectangle rectangle = new Rectangle(Screen.getPrimary().getVisualBounds().getWidth() - 675, 25, 525, 150);
		rectangle.setFill(Color.rgb(235, 213, 179));
		rectangle.setStroke(Color.BLACK);
		
		
		//Left Menu Container(money, exp)
		Rectangle square = new Rectangle(50, 25, 200, 200);
		square.setFill(Color.rgb(235, 213, 179));
		square.setStroke(Color.BLACK);
		
		//Menu Text
		Label label1 = new Label("Menu");
		label1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		label1.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 465);
		label1.setTranslateY(30);
		
		//Money text
		Label label2 = new Label("Money:");
		label2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		
		//XP Text
		Label label3 = new Label("XP:");
		label3.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		
		//Quit Button
		Hyperlink back = new Hyperlink("QUIT");
		back.setOnMouseClicked(event -> {
			window.setScene(main);
			playAudio(false);
		});
		back.setTextFill(Color.BLACK);
		back.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)));
		back.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth() - 100);
		back.setTranslateY(25);
		
		//StackFrame for Right Menu
		moneyContainer.getChildren().addAll(square, label2, label3, money);
		moneyContainer.setTranslateX(50);
		moneyContainer.setTranslateY(25);
		label2.setTranslateY(-60);
		label2.setTranslateX(-15);
		label3.setTranslateX(-50);
		money.setTranslateX(-80);
		money.setTranslateY(-60);
		
		//HP Text
		int hp = 500;
		Text text1 = new Text("HP: " + hp);
		text1.setX(20);
		text1.setY(620);
		text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
		text1.setFill(Color.RED);
		
		int enemyHP = 500;
		Text text2 = new Text("HP: " + enemyHP);
		text2.setX(Screen.getPrimary().getVisualBounds().getWidth() - 130);
		text2.setY(620);
		text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
		text2.setFill(Color.RED);
		
		//root.getChildren().addAll(rectangle);
		root.getChildren().addAll(rectangle, text1, text2, trainTroops, buildTurret, evolve, label1, special, audio, back);
		//root.getChildren().addAll(label1);
		root.getChildren().addAll(moneyContainer);
		
		return root;
	}

	private void playAudio(boolean val)
	{
		AudioClip music = new AudioClip(this.getClass().getResource("soundtrack.mp3").toString());
		music.setVolume(0.7);
		if(val)
		{
			music.play();
			music.setCycleCount(AudioClip.INDEFINITE);
		}
		else
			music.stop();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		main = new Scene(createContent());
		instructions = new Scene(createInstructions());
		credits = new Scene(createCredits());
		play = new Scene(createPlay());
		primaryStage.setTitle("Code of Honor");
		primaryStage.setScene(main);
		primaryStage.show();
	}

	/*
	 * private static class Title extends StackPane { public Title(String name) {
	 * Rectangle bg = new Rectangle(250, 60); bg.setStroke(Color.WHITE);
	 * bg.setStrokeWidth(2); bg.setFill(null);
	 * 
	 * Text text = new Text(name); text.setFill(Color.WHITE);
	 * text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));
	 * 
	 * setAlignment(Pos.CENTER); getChildren().addAll(bg, text); } }
	 */
	private static class MenuBox extends VBox
	{
		public MenuBox(MenuItem... items)
		{
			getChildren().add(createSeparator());

			for (MenuItem item : items)
			{
				getChildren().addAll(item, createSeparator());
			}
		}

		private Line createSeparator()
		{
			Line sep = new Line();
			sep.setEndX(200);
			sep.setStroke(Color.WHITE);
			return sep;
		}
	}

	private static class MenuItem extends StackPane
	{
		public MenuItem(String name)
		{
			LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
					new Stop[] { new Stop(0, Color.ORANGE), new Stop(0.1, Color.BLACK), new Stop(0.9, Color.BLACK),
							new Stop(1, Color.ORANGE) });

			Rectangle bg = new Rectangle(200, 40);
			bg.setOpacity(0.4);

			Text text = new Text(name);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));

			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text);

			setOnMouseEntered(event -> {
				bg.setFill(gradient);
				text.setFill(Color.WHITE);
			});

			setOnMouseExited(event -> {
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});

			setOnMousePressed(event -> {
				bg.setFill(Color.ORANGE);
			});

			setOnMouseReleased(event -> {
				bg.setFill(gradient);
			});
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}

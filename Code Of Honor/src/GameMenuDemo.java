import java.io.InputStream;
import java.io.Reader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	static Stage window;
	Scene main;
	Scene play;
	Scene instructions;
	Scene credits;
	Pane root;
	boolean val = true;
	private AnimationTimer timer;
	private Timeline timeline;
	static Integer money = 175;
	static Integer xp = 0;
	static Integer enemyHealth = 500;
	static Integer friendlyHealth = 500;
	protected static boolean stop = true;
	protected static boolean godLevel = false;
	EnemyControl enemy;

	private Parent createContent()
	{
		Pane root = new Pane();
		root.resize(790, 590);
		
		try (InputStream is = getClass().getResourceAsStream("res/Code_of_Honor.jpg"))
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
			playAudio(val, null, null, null);
			
			
			
			try
			{
				play = new Scene(createPlay());
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			window.setScene(play);
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

		try (InputStream is = getClass().getResourceAsStream("res/Code_of_Honor.jpg"))
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
		back.setOnMouseClicked(event -> { 
			
			try
			{
				start(window);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		});

		Label text = new Label("The goal of the game is to survive and destroy the enemy base. \nThe game is divided into 2 stages. To move to the next stage,\nyou need to gain XP points. \nTo gain these points, you have to kill enemy units. \nYou also gain XP points when one of your units are killed. \nYou cannot repair your base, so protect your base at all costs!");
		text.setTextFill(Color.WHITE);
		text.setFont(Font.font("Verdana", FontWeight.NORMAL, window.getHeight() * .03));
		text.setTranslateX(100);
		text.setTranslateY(225);
		MenuBox menu = new MenuBox(back);
		menu.setTranslateX(290);
		menu.setTranslateY(500);

		root.getChildren().addAll(menu, text);
		return root;
	}

	private Parent createCredits() throws FileNotFoundException
	{
		Pane root = new Pane();
		root.setPrefSize(800, 600);

		try (InputStream is = getClass().getResourceAsStream("res/Code_of_Honor.jpg"))
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
		
		InputStream tyler = getClass().getResourceAsStream("res/tyler.jpg"); 
		Image tylerk = new Image(tyler, 175, 200, false, false); 
		ImageView tylerkerch = new ImageView(tylerk);
		tylerkerch.setTranslateX(15);
		tylerkerch.setTranslateY(175);
		Label textT = new Label("Tyler Kerch");
		textT.setTextFill(Color.WHITE);
		textT.setFont(Font.font("Verdana", FontWeight.NORMAL, window.getHeight() * .03));
		textT.setTranslateX(15);
		textT.setTranslateY(375);

		
		InputStream bruce = getClass().getResourceAsStream("res/bruce.jpg"); 
		Image brucem = new Image(bruce, 175, 200, false, false); 
		ImageView brucemoe = new ImageView(brucem);
		brucemoe.setTranslateX(215);
		brucemoe.setTranslateY(175);
		Label textB = new Label("Bruce Moe");
		textB.setTextFill(Color.WHITE);
		textB.setFont(Font.font("Verdana", FontWeight.NORMAL, window.getHeight() * .03));
		textB.setTranslateX(215);
		textB.setTranslateY(375);
		
		
		InputStream conrad = getClass().getResourceAsStream("res/conrad.jpg"); 
		Image conradb = new Image(conrad, 175, 200, false, false); 
		ImageView conradbarron = new ImageView(conradb);
		conradbarron.setTranslateX(415);
		conradbarron.setTranslateY(175);
		Label textC = new Label("Conrad Barron");
		textC.setTextFill(Color.WHITE);
		textC.setFont(Font.font("Verdana", FontWeight.NORMAL, window.getHeight() * .03));
		textC.setTranslateX(415);
		textC.setTranslateY(375);
		
		InputStream akhil = getClass().getResourceAsStream("res/akhil.jpg"); 
		Image akhilm = new Image(akhil, 175, 200, false, false); 
		ImageView akhilmathew = new ImageView(akhilm);
		akhilmathew.setTranslateX(615);
		akhilmathew.setTranslateY(175);
		Label textA = new Label("Akhil Mathew");
		textA.setTextFill(Color.WHITE);
		textA.setFont(Font.font("Verdana", FontWeight.NORMAL, window.getHeight() * .03));
		textA.setTranslateX(615);
		textA.setTranslateY(375);
		
		root.getChildren().addAll(menu, tylerkerch, brucemoe, conradbarron, akhilmathew, textA, textT, textB, textC);
		return root;
	}

	private Parent createPlay() throws FileNotFoundException
	{
		Pane root = new Pane();
		StackPane moneyContainer = new StackPane();
		window.setWidth(Screen.getPrimary().getVisualBounds().getWidth() * .75);
		window.setHeight(Screen.getPrimary().getVisualBounds().getWidth() * .75 / 2);	
		window.setX(Screen.getPrimary().getVisualBounds().getWidth() * .125);
		window.setY(Screen.getPrimary().getVisualBounds().getHeight() * .125);
		//Background Image
		try (InputStream is = getClass().getResourceAsStream("res/Background Main.jpg"))
		{
			ImageView img = new ImageView(new Image(is));
		
			img.setFitWidth(window.getWidth());
			img.setFitHeight(window.getHeight());
			
			root.getChildren().add(img);
		} catch (IOException e)
		{
			System.out.println("Couldn't load image");
		}

		
		//Evolution Base 1
		InputStream inputstream0 = getClass().getResourceAsStream("res/Evolution1.png"); 
		Image imageBase1 = new Image(inputstream0, window.getHeight() * .31, window.getHeight() * .26, false, false); 
		ImageView base1 = new ImageView(imageBase1);
		base1.setTranslateX(0 - window.getWidth() * .022);
		base1.setTranslateY(window.getHeight() * .7062308);
		
		InputStream inputstream00 = getClass().getResourceAsStream("res/Evolution1E.png"); 
		Image imageEBase1 = new Image(inputstream00, window.getHeight() * .31, window.getHeight() * .26, false, false); 
		ImageView eBase1 = new ImageView(imageEBase1);
		eBase1.setScaleX(-1);
		eBase1.setTranslateX(window.getWidth() * .869);
		eBase1.setTranslateY(window.getHeight() * .7062308);

		//Train  Button
		InputStream inputstream1 = getClass().getResourceAsStream("res/troop.png"); 
		Image imageTrain = new Image(inputstream1, window.getHeight() * .065, window.getHeight() * .065, false, false); 
		Button trainTroops = new Button();
		trainTroops.setGraphic(new ImageView(imageTrain));
		trainTroops.setTranslateX(window.getWidth() * .81770833);
		trainTroops.setTranslateY(window.getHeight() * .07692308);
		TroopControl troopControl = new TroopControl();
		troopControl.setRoot(root);
		
		
		
		//Turret Button
		InputStream inputstream2 = getClass().getResourceAsStream("res/noTurret.png"); 
		Image imageTurret = new Image(inputstream2, window.getHeight() * .065, window.getHeight() * .065, false, false); 
		Button buildTurret = new Button();
		buildTurret.setGraphic(new ImageView(imageTurret));
		buildTurret.setTranslateX(window.getWidth() * .765625);
		buildTurret.setTranslateY(window.getHeight() * .07692308);
		
		//Evolve Button
		InputStream inputstream4 = getClass().getResourceAsStream("res/evolve.png"); 
		Image imageEvolve = new Image(inputstream4, window.getHeight() * .065, window.getHeight() * .065, false, false); 
		Button evolve = new Button();
		evolve.setGraphic(new ImageView(imageEvolve));
		evolve.setTranslateX(window.getWidth() * .71354167);
		evolve.setTranslateY(window.getHeight() * .07692308);
		
		//Special Ability Button
		InputStream inputstream5 = getClass().getResourceAsStream("res/noSpecial.png"); 
		Image imageSpecial = new Image(inputstream5, window.getHeight() * .065, window.getHeight() * .065, false, false); 
		Button special = new Button();
		special.setGraphic(new ImageView(imageSpecial));
		special.setTranslateX(window.getWidth() * .66145833);
		special.setTranslateY(window.getHeight() * .07692308);
		
		//Audio Button
		InputStream inputstream6 = getClass().getResourceAsStream("res/music.png"); 
		Image imageAudio = new Image(inputstream6, window.getHeight() * .065, window.getHeight() * .065, false, false); 
		InputStream inputstream7 = getClass().getResourceAsStream("res/MusicOff.png"); 
		Image imageAudioOff = new Image(inputstream7, window.getHeight() * .065, window.getHeight() * .065, false, false); 
		Button audio = new Button();
		audio.setGraphic(new ImageView(imageAudio));
		audio.setTranslateX(window.getWidth() * .86979167);
		audio.setTranslateY(window.getHeight() * .07692308);
		audio.setOnAction(e -> {
			val = !val;
			playAudio(val, audio, imageAudio, imageAudioOff);
		});
		
		//Cashbag Icon next to Money
		InputStream inputstream3 = getClass().getResourceAsStream("res/cashbag.png"); 
		Image imageDollar = new Image(inputstream3, window.getHeight() * .0725, window.getHeight() * .0725, false, false); 
		
		ImageView money = new ImageView(imageDollar);
	
		//Right Menu Container(train, turrets, etc.)
		Rectangle rectangle = new Rectangle(window.getWidth() * .6484375, window.getHeight() * .02403846, window.getWidth() * .2734375, window.getHeight() * .14423077);
		rectangle.setFill(Color.rgb(235, 213, 179));
		rectangle.setStroke(Color.BLACK);
		
		
		//Left Menu Container(money, exp)
		Rectangle square = new Rectangle(window.getWidth() * .02604167, window.getHeight() * .02403846, window.getWidth() * .15416667, window.getHeight() * .19230769);
		square.setFill(Color.rgb(235, 213, 179));
		square.setStroke(Color.BLACK);
		
		//Menu Text
		Label label1 = new Label("Menu");
		label1.setFont(Font.font("Verdana", FontWeight.BOLD, window.getHeight() * .04));
		label1.setTranslateX(window.getWidth() * .7578125);
		label1.setTranslateY(window.getHeight() * .02425);
		
		//Money text
		Label label2 = new Label(":");
		label2.setFont(Font.font("Verdana", FontWeight.BOLD, window.getHeight() * .05));
		
		//XP Text
		Label label3 = new Label("XP:");
		label3.setFont(Font.font("Verdana", FontWeight.BOLD, window.getHeight() * .05));
		
		//Quit Button
		Hyperlink back = new Hyperlink("QUIT");
		back.setOnMouseClicked(event -> {
			System.exit(0);
			stop = false;
			troopControl.reset();
			System.out.println("hey");
			window.setWidth(800);
			window.setHeight(600);
			window.setX(window.getWidth() * .29166667);
			window.setY(window.getHeight() * .21153846);
			window.setScene(main);
			
			playAudio(false, audio, imageAudio, imageAudioOff);
		});
		back.setTextFill(Color.BLACK);
		back.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, window.getHeight() * .02470588)));
		back.setTranslateX(window.getWidth() * .92791667);
		back.setTranslateY(window.getHeight() * .02403846);
		
		//StackFrame for Right Menu
		moneyContainer.getChildren().addAll(square, label2, label3, money, xp(), money());
		moneyContainer.setTranslateX(window.getWidth() * .02604167);
		moneyContainer.setTranslateY(window.getHeight() * .02403846);
		label2.setTranslateY(0 - window.getWidth() * .02777);
		label2.setTranslateX(0 - window.getWidth() * .0264125);
		label3.setTranslateX(0 - window.getWidth() * .04604167);
		label3.setTranslateY(window.getHeight() * .0404167);
		money.setTranslateX(0 - window.getWidth() * .053666667);
		money.setTranslateY(0 - window.getHeight() * .04768231);
		
		//XP Text
		Label xpLabel = new Label("1000xp");
		xpLabel.setFont(Font.font("Verdana", FontWeight.BOLD, window.getHeight() * .02));
		xpLabel.setTranslateX(window.getWidth() * .7138125);
		xpLabel.setTranslateY(window.getHeight() * .17425);
		
		
		
		//Enemy
		enemy.setRoot(root);
	
		
		
		
		
		
		//root.getChildren().addAll(rectangle);
		root.getChildren().addAll(rectangle, friendlyHealth(), enemyHealth(), trainTroops, buildTurret, evolve, label1, xpLabel, audio, special, back, base1, eBase1);
		//root.getChildren().addAll(label1);
		root.getChildren().addAll(moneyContainer);
		
		trainTroops.setOnMouseClicked(e -> {
			root.getChildren().removeAll(trainTroops, buildTurret, evolve, label1, special, audio, xpLabel);
			
			
				InputStream inputstream8 = null;
				try
				{
					inputstream8 = getClass().getResourceAsStream("res/troop.png");
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				Image imageTroopClone = new Image(inputstream8, window.getHeight() * .065, window.getHeight() * .065, false, false); 
				Button troopClone = new Button();
				troopClone.setGraphic(new ImageView(imageTrain));
				troopControl.setTroop(troopClone);
				try
				{
					
					troopControl.setMenu();
				} catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			troopClone.setOnMouseClicked(f ->{
				root.getChildren().addAll(trainTroops, buildTurret, evolve, label1, special, audio, xpLabel);
				troopControl.removeTroops();
			});
		});
		
		evolve.setOnMouseClicked(e -> {
			if(xp >= 1000)
			{
				root.getChildren().remove(base1);
				try
				{
					Image imageBase2 = new Image(getClass().getResourceAsStream("res/Evolution3.png"), window.getHeight() * .31, window.getHeight() * .26, false, false);
					ImageView base2 = new ImageView(imageBase2);
					base2.setTranslateX(0 - window.getWidth() * .022);
					base2.setTranslateY(window.getHeight() * .7062308);
					root.getChildren().add(base2);
					InputStream inputstream10 = getClass().getResourceAsStream("res/noEvolve.png"); 
					Image imageNoEvolve = new Image(inputstream10, window.getHeight() * .065, window.getHeight() * .065, false, false); 
					evolve.setGraphic(new ImageView(imageNoEvolve));
					evolve.setTranslateX(window.getWidth() * .71354167);
					evolve.setTranslateY(window.getHeight() * .07692308);
					xpLabel.setText("");
					friendlyHealth += 100;
					godLevel = true;
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		
		stop = true;
		this.root = root;
		
		return root;
	}
	
	

	private void playAudio(boolean val, Button b, Image on, Image off)
	{
		/*File path = new File("src/res/soundtrack.mp3");
		Media music = new Media(path.toURI().toString());
		MediaPlayer song = new MediaPlayer(music);
		song.setVolume(0.7);
		if(val)
		{
			if(b != null)
				b.setGraphic(new ImageView(on));
			song.play();
			song.setCycleCount(AudioClip.INDEFINITE);
		}
		else if(b != null)
		{
			b.setGraphic(new ImageView(off));
			song.stop();
		}*/
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		enemy = new EnemyControl();
		window = primaryStage;
		window.setWidth(800);
		window.setHeight(600);
		main = new Scene(createContent());
		instructions = new Scene(createInstructions());
		credits = new Scene(createCredits());
		
		primaryStage.setTitle("Code of Honor");
		primaryStage.setScene(main);
		primaryStage.setResizable(false);
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

	public Node xp()
	{
		final Text text = new Text(xp.toString());
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, window.getHeight() * .05));
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timer = new AnimationTimer()
		{
			@Override
			public void handle(long l)
			{
				text.setText(xp.toString());
			}

		};

		Duration duration = Duration.hours(10);
		KeyFrame keyFrame = new KeyFrame(duration);
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
		timer.start();

		text.setTranslateX(window.getWidth() * 0.021547);
		text.setTranslateY(window.getHeight() * .0404167);
		

		return text;
	}
	
	public Node	money()
	{
		final Text text = new Text(money.toString());
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, window.getHeight() * .05));
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timer = new AnimationTimer()
		{
			@Override
			public void handle(long l)
			{
				text.setText(money.toString());
			}

		};

		Duration duration = Duration.hours(10);
		KeyFrame keyFrame = new KeyFrame(duration);
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
		timer.start();

		text.setTranslateY(0 - window.getWidth() * .02777);
		text.setTranslateX(window.getWidth() * 0.021547);

		

		return text;
	}
	
	public Node	enemyHealth()
	{
		final Text text = new Text("HP: " + enemyHealth.toString());
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, window.getWidth() * .01302083));
		text.setTranslateX(window.getWidth() * .92229167);
		text.setTranslateY(window.getHeight() * .6075385);
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timer = new AnimationTimer()
		{
			@Override
			public void handle(long l)
			{
				
				if(enemyHealth <= 0)
				{
					text.setText("HP: " + enemyHealth.toString());
					try
					{
						InputStream img = getClass().getResourceAsStream("res/happyStites.jpg");
						Image img2 = new Image(img);
						ImageView imgV = new ImageView(img2);
						imgV.setTranslateX(window.getWidth() * .5);
						root.getChildren().add(imgV);
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Timeline timeline = new Timeline(new KeyFrame(
					    
							Duration.millis(5000),
							ae -> System.exit(0)));
						timeline.play();
				}
			}

		};

		Duration duration = Duration.hours(10);
		KeyFrame keyFrame = new KeyFrame(duration);
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
		timer.start();
		
		
		text.setFill(Color.RED);

		return text;
	}
	
	public Node	friendlyHealth()
	{
		
		
	
		final Text text = new Text("HP: " + friendlyHealth.toString());
		text.setX(window.getWidth() * .01041667);
		text.setY(window.getHeight() * .6075385);
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, window.getWidth() * .01302083));
		text.setFill(Color.RED);
		
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timer = new AnimationTimer()
		{
			@Override
			public void handle(long l)
			{
				
				text.setText("HP: " + friendlyHealth.toString());
				if(friendlyHealth <= 0)
				{

					try
					{
						Image img = new Image(getClass().getResourceAsStream("res/sponge.jpg"));
						ImageView imgV = new ImageView(img);
						root.getChildren().add(imgV);
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Timeline timeline = new Timeline(new KeyFrame(
						    
							Duration.millis(5000),
					        ae -> System.exit(0)));
						timeline.play();
				}
			}

		};

		Duration duration = Duration.hours(10);
		KeyFrame keyFrame = new KeyFrame(duration);
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
		timer.start();
		
		
		text.setFill(Color.RED);

		return text;
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
}
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TroopControl extends GameMenuDemo
{
	private Button troop;
	private Pane root;
	private Button one;
	private Button zero;
	private Button god;
	private Label label1;
	private Label oneLabel;
	private Label zeroLabel;
	private Label godLabel;
	protected static ArrayList<Attacker> closestFriend = new ArrayList<Attacker>();
	protected static ArrayList<Attacker> closestEnemy = new ArrayList<Attacker>();
	protected static int logicE;
	protected static int logic;
	protected static int friendlyKilled;
	protected static int enemyKilled;
	protected static ArrayList<ZeroBullet> zeroBulletsFriendly = new ArrayList<ZeroBullet>();
	protected static ArrayList<ZeroBullet> zeroBulletsEnemy = new ArrayList<ZeroBullet>();
	protected static int friendlyZeros;
	protected static int enemyZeros;
	public TroopControl()
	{

	}
	
	public void setRoot(Pane root)
	{
		
		this.root = root;
		
	}
	
	public Pane getRoot()
	{
		return root;
	}
	
	
	public void setMenu() throws FileNotFoundException
	{
		//1 Text
		oneLabel = new Label("$15");
		oneLabel.setFont(Font.font("Verdana", FontWeight.BOLD, window.getHeight() * .02));
		oneLabel.setTranslateX(window.getWidth() * .67138125);
		oneLabel.setTranslateY(window.getHeight() * .17425);
		//0 Text
		zeroLabel = new Label("$25");
		zeroLabel.setFont(Font.font("Verdana", FontWeight.BOLD, window.getHeight() * .02));
		zeroLabel.setTranslateX(window.getWidth() * .7208125);
		zeroLabel.setTranslateY(window.getHeight() * .17425);
		
		troop.setTranslateX(window.getWidth() * .86979167);
		troop.setTranslateY(window.getHeight() * .07692308);
		root.getChildren().add(troop);
		FileInputStream inputstream2 = new FileInputStream("res/imgs/one.png"); 
		Image imageOne = new Image(inputstream2, window.getHeight() * .065, window.getHeight() * .065, false, false); 
		one = new Button();
		one.setGraphic(new ImageView(imageOne));
		one.setTranslateX(window.getWidth() * .66145833);
		one.setTranslateY(window.getHeight() * .07692308);
		FileInputStream inputstream3 = new FileInputStream("res/imgs/zero.png"); 
		Image imageZero = new Image(inputstream3, window.getHeight() * .065, window.getHeight() * .065, false, false);
		zero = new Button();
		zero.setGraphic(new ImageView(imageZero));
		zero.setTranslateX(window.getWidth() * .71145833);
		zero.setTranslateY(window.getHeight() * .07692308);
		label1 = new Label("Troop");
		label1.setFont(Font.font("Verdana", FontWeight.BOLD, window.getHeight() * .04));
		label1.setTranslateX(window.getWidth() * .7578125);
		label1.setTranslateY(window.getHeight() * .02425);
		root.getChildren().addAll(one, zero, label1, oneLabel, zeroLabel);
		listenOne();
		listenZero();
		if(godLevel)
		{
			FileInputStream inputstream4 = new FileInputStream("res/imgs/EZMoney.png"); 
			Image imageGod = new Image(inputstream4, window.getHeight() * .065, window.getHeight() * .065, false, false); 
			god = new Button();
			god.setGraphic(new ImageView(imageGod));
			god.setTranslateX(window.getWidth() * .765625);
			god.setTranslateY(window.getHeight() * .07692308);
			
			godLabel = new Label("$300");
			godLabel.setFont(Font.font("Verdana", FontWeight.BOLD,  window.getHeight() * .02));
			godLabel.setTranslateX(window.getWidth() * .7738125);
			godLabel.setTranslateY(window.getHeight() * .17425);
			root.getChildren().addAll(god, godLabel);
			listenGod();
		}
	}
	
	public void setTroop(Button troop)
	{
		this.troop = troop;
	}
	
	public void removeTroops()
	{
		root.getChildren().removeAll(one, troop, label1, oneLabel, zeroLabel);
		if(godLevel)
			root.getChildren().removeAll(god, godLabel);
	}

	
	public void listenOne()
	{
		one.setOnMouseClicked(e -> {
			
			if (money >= 15)
			{
				money-=15;
				One o = new One(true);
				try
				{
					ImageView img = createTroop(o, true);
					root.getChildren().add(img);
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	public void listenZero()
	{
		zero.setOnMouseClicked(e -> {
			if(money >= 25)
			{
				money -= 25;
				Zero o = new Zero(true);
				ZeroBullet z = new ZeroBullet();
			try
			{
			
				
				Image m = new Image(new FileInputStream("res/imgs/dot.png"), window.getHeight() * .02, window.getHeight() * .02, false, false);
				ImageView img2 = new ImageView(m);
				img2.setTranslateX(-window.getWidth());
				img2.setTranslateY(0);
				img2.setRotate(90);
				z.setImage(img2);
				zeroBulletsFriendly.add(z);
				ImageView img = createTroop(o, true);
				root.getChildren().addAll(img, img2);
				
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
	}
	

	public void listenGod()
	{
		god.setOnMouseClicked(e -> {
			
			if (money >= 300)
			{
				money-=300;
				One o = new One(true);
				try
				{
					ImageView img = createTroop(o, true);
					root.getChildren().add(img);
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try
				{
					o.god();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public ImageView createTroop(Attacker a, boolean friendly) throws Exception
	{
		ImageView img = new ImageView();
		
		if(friendly)
		{
			
			if(a instanceof One)
			{
				
				Image m = new Image(new FileInputStream("res/imgs/one.png"), window.getHeight() * .105, window.getHeight() * .105, false, false);
				img = new ImageView(m);
				img.setTranslateX(window.getWidth() * .117333);
				img.setTranslateY(window.getHeight() * .814269231);
				closestFriend.add(a);
				a.setImg(img);
			}
			if(a instanceof Zero)
			{
				Image m = new Image(new FileInputStream("res/imgs/zero.png"), window.getHeight() * .105, window.getHeight() * .105, false, false);
				img = new ImageView(m);
				img.setTranslateX(window.getWidth() * .117333);
				img.setTranslateY(window.getHeight() * .814269231);
				closestFriend.add(a);
				a.setImg(img);
			}
		}
		else if(!friendly)
		{
			if(a instanceof One)
			{
				FileInputStream inputstream2 = new FileInputStream("res/imgs/one.png");
				Image m = new Image(inputstream2, window.getHeight() * .105, window.getHeight() * .105, false, false);
				img = new ImageView(m);
				img.setTranslateX(window.getWidth() * .827333);
				img.setTranslateY(window.getHeight() * .814269231);
				img.setScaleX(-1);
				a.setImgE(img);
				closestEnemy.add(a);
				
			}
			if(a instanceof Zero)
			{
				
				Image m = new Image(new FileInputStream("res/imgs/zero.png"), window.getHeight() * .105, window.getHeight() * .105, false, false);
				img = new ImageView(m);
				img.setTranslateX(window.getWidth() * .827333);
				img.setTranslateY(window.getHeight() * .814269231);
				img.setScaleX(-1);
				a.setImgE(img);
				closestEnemy.add(a);
				
				
				
			}
		}
		
		
		return img;
	}
	
	
	public void reset()
	{
		logic = 0;
		logicE = 0;
		closestFriend.clear();
		closestEnemy.clear();
		friendlyKilled = 0;
		enemyKilled = 0;
	}
	
	public void incrementLogic()
	{
		logic++;
	}
	
	public void incrementLogicE()
	{
		logicE++;
	}
	
	public int getLogic()
	{
		return logic;
	}
	
	public int getLogicE()
	{
		return logicE;
	}
	
	public ImageView createEnemyBullet() throws Exception
	{
		ZeroBullet z = new ZeroBullet();
		Image mo = new Image(new FileInputStream("res/imgs/dot.png"), window.getHeight() * .02, window.getHeight() * .02, false, false);
		ImageView img2 = new ImageView(mo);
		img2.setTranslateX(-window.getWidth());
		img2.setTranslateY(0);
		img2.setRotate(90);
		zeroBulletsEnemy.add(z);
		z.setImage(img2);
		return img2;
	}
	
	
		
}

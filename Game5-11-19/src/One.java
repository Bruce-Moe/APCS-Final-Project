import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.*;

public class One extends Attacker
{
	private int damage;
	private int health;
	private ImageView img;
	private ImageView imgEnemy;
	private Image closest;
	private ImageView enemy;
	private boolean canGo;
	private static ArrayList<ImageView> closestFriend = new ArrayList<ImageView>();
	private static int logic;
	private Stage window;
	private int logicUnique;
	public One(Stage window)
	{
		damage = 20;
		health = 100;
		canGo = true;
		img = new ImageView();
		logic++;
		this.window = window;
		logicUnique = logic;
	}
	
	public void decreaseHealth(int d)
	{
		health -= d;
	}
	
	
	
	public int getDamage()
	{
		return damage;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setImg(ImageView i)
	{
		img = i;
		closestFriend.add(img);
		move();
	}
	
	public ImageView getImg()
	{
		return img;
	}
	
	public void setImgE(ImageView i)
	{
		imgEnemy = i;
	}
	
	public void setEnemy(ImageView i)
	{
		enemy = i;
	}
	

	
	public void move()
	{
		
		//System.out.println(img.getTranslateX());
		double closest = window.getWidth() * .86125;
		if(logicUnique > 1)
		{
			closest = closestFriend.get(logicUnique-2).getTranslateX();
		}
				
		
	
			
			if(img.getTranslateX() < closest - window.getWidth() * .05208333) //img.getTranslateX() < enemy.getTranslateX() - 100)
			{
				TranslateTransition t = new TranslateTransition();
				t.setDuration(Duration.millis(1));
				t.setNode(img);
				t.setToX(img.getTranslateX() + window.getWidth() * .00052083);
				t.setOnFinished(e -> {
					move();
					
				});
				t.play();
			}
			else if(img.getTranslateX() < window.getWidth() * .86125 - window.getWidth() * .05208333)
			{
				
				TranslateTransition t = new TranslateTransition();
				t.setDuration(Duration.millis(1));
				t.setNode(img);
				t.setOnFinished(e -> {
					move();
					
				});
				t.play();
			}else
			{
				
				RotateTransition rot = new RotateTransition();
				rot.setDuration(Duration.millis(200));
				rot.setNode(img);
				rot.setFromAngle(0);
				rot.setToAngle(90);
				rot.setOnFinished(e -> {
					
					RotateTransition rota = new RotateTransition();
					rota.setDuration(Duration.millis(500));
					rota.setNode(img);
					rota.setFromAngle(90);
					rota.setToAngle(0);
					rota.setOnFinished(f -> {
						
						RotateTransition rotat = new RotateTransition();
						rotat.setDuration(Duration.millis(300));
						rotat.setNode(img);
						
						rotat.setOnFinished(g -> {
							move();
							
						});
						rotat.play();
					});
					rota.play();
				});
				rot.play();
			}
			
			
		
			
		
		
	}
	
	/*public void moveEnemy()
	{
		TranslateTransition t = new TranslateTransition();
		
		t.setDuration(Duration.millis(1));
		t.setNode(imgEnemy);
		t.setToX(imgEnemy.getTranslateX() - 5);
		if(imgEnemy.getTranslateX() > 0 && imgEnemy.getTranslateX() > enemy.getTranslateX() + 100)
			t.setOnFinished(e -> {
				
				moveEnemy();
				
				
			});
		t.play();
	}*/
}

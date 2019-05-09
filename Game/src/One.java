import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
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

	public One()
	{
		damage = 20;
		health = 100;
		canGo = true;
		img = new ImageView();
		logic++;
		
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
		double closest = 1500;
		if(logic > 1)
				closest = closestFriend.get(logic-2).getTranslateX();
		
	
			TranslateTransition t = new TranslateTransition();
			t.setDuration(Duration.millis(1));
			t.setNode(img);
			t.setToX(img.getTranslateX() + 5);
			if(img.getTranslateX() < closest - 100) //img.getTranslateX() < enemy.getTranslateX() - 100)
			{
				t.setOnFinished(e -> {
					
					move();
					
				});
			}
			
			t.play();
			
			
		
		
		
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

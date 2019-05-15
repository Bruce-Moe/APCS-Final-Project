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
	private int logicUnique;
	private boolean friendly;
	private boolean permaStop;
	public One(boolean friendly)
	{
		permaStop = true;
		damage = 20;
		health = 120;
		this.friendly = friendly;
		
		if(!stop)
		{
			logic = 0;
			logicE = 0;
			logicUnique = 0;
		}
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
		logicUnique = logic;
		closestFriend.add(i);
		logic++;
		System.out.println(logicUnique);
		move();
	}
	
	public ImageView getImg()
	{
		return img;
	}
	
	public void setImgE(ImageView i)
	{
		imgEnemy = i;
		closestEnemy.add(i);
		logicUnique = logicE;
		logicE++;
		
		move();
	}
	

	
	public void move()
	{
		//System.out.println(img.getTranslateX());
		double closest;
		
		if(!stop)
		{
			closestEnemy.clear();
			System.out.println("Hey");
			permaStop = false;
		}
			
			
		if(friendly)
			closest = window.getWidth() * .88125;
		else
			closest = window.getWidth() * .0775;
		
		if(friendly && logicUnique >= 1 && stop)
		{
			
			closest = closestFriend.get(logicUnique-1).getTranslateX();
		}
		else if(logicUnique >= 1 && stop)
		{
			
			closest = closestEnemy.get(logicUnique-1).getTranslateX();
		}
		
		System.out.println(closestEnemy.size());
		if(friendly && closestEnemy.size() > enemyKilled && logicUnique == friendlyKilled && closestFriend.size() > 0)
		{
			
			closest = closestEnemy.get(enemyKilled).getTranslateX();
		}else if(!friendly && closestFriend.size() > friendlyKilled && logicUnique == enemyKilled && closestFriend.size() > 0)
			closest = closestFriend.get(friendlyKilled).getTranslateX();
		
		if(permaStop)
		{
			if(friendly)
			{
				
			if(img.getTranslateX() < closest - window.getWidth() * .03208333) //img.getTranslateX() < enemy.getTranslateX() - 100)
			{
				
				TranslateTransition t = new TranslateTransition();
				t.setDuration(Duration.millis(1));
				t.setNode(img);
				t.setToX(img.getTranslateX() + window.getWidth() * .00042083);
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
					
					enemyHealth -= damage;
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
			}else
			{
				
				if(imgEnemy.getTranslateX() > closest + window.getWidth() * .03208333) //img.getTranslateX() < enemy.getTranslateX() - 100)
				{
					TranslateTransition t = new TranslateTransition();
					t.setDuration(Duration.millis(1));
					t.setNode(imgEnemy);
					t.setToX(imgEnemy.getTranslateX() - window.getWidth() * .00042083);
					t.setOnFinished(e -> {
						move();
						
					});
					t.play();
				}
				else if(imgEnemy.getTranslateX() > window.getWidth() * .0775 + window.getWidth() * .03208333)
				{
					
					TranslateTransition t = new TranslateTransition();
					t.setDuration(Duration.millis(1));
					t.setNode(imgEnemy);
					t.setOnFinished(e -> {
						move();
						
					});
					t.play();
				}else
				{
					
					RotateTransition rot = new RotateTransition();
					rot.setDuration(Duration.millis(200));
					rot.setNode(imgEnemy);
					rot.setFromAngle(0);
					rot.setToAngle(-90);
					rot.setOnFinished(e -> {
						
						//enemyHealth -= damage;
						RotateTransition rota = new RotateTransition();
						rota.setDuration(Duration.millis(500));
						rota.setNode(imgEnemy);
						rota.setFromAngle(-90);
						rota.setToAngle(0);
						rota.setOnFinished(f -> {
							
							RotateTransition rotat = new RotateTransition();
							rotat.setDuration(Duration.millis(300));
							rotat.setNode(imgEnemy);
							
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
			}else
			{
			
				if(friendly)
				{
					System.out.println("Activated");
					img.setTranslateX(0);
					
				}
				else
					imgEnemy.setTranslateX(1000000);
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


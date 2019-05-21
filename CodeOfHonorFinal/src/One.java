import java.io.InputStream;
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
	private boolean friendly = false;
	private boolean permaStop;
	private boolean fight;
	public One(boolean friendly)
	{
		permaStop = true;
		damage = 20;
		health = 70;
		this.friendly = friendly;
		fight = false;
		if(!stop)
		{
			logic = 0;
			logicE = 0;
			logicUnique = 0;
		}
	}
	
	public void god() throws Exception
	{
		Image m = new Image(getClass().getResourceAsStream("res/EZMoney.png"), window.getHeight() * .125, window.getHeight() * .125, false, false);
		img.setImage(m);
		img.setTranslateX(window.getWidth() * .117333);
		img.setTranslateY(window.getHeight() * .794269231);
		damage = 100;
		health = 175;
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
		logic++;
		
		move();
	}
	
	public ImageView getImg()
	{
		return img;
	}
	
	public void setImgE(ImageView i)
	{
		imgEnemy = i;
		logicUnique = logicE;
		logicE++;
		
		move();
	}
	
	public double getX()
	{
		if(friendly)
			return img.getTranslateX();
		else
			return imgEnemy.getTranslateX();
	}
	
	public void move()
	{
		fight = false;

		if(health <= 0 && !friendly)
		{
			money += 20;
			xp += 40;
			imgEnemy.setTranslateX(10000);
			enemyKilled++;
			return;
		}
		
		if(health <= 0 && friendly)
		{
			img.setTranslateX(-1000);
			friendlyKilled++;
			return;
		}
		
		double closest;
		if(!stop)
		{
			closestEnemy.clear();
		
			permaStop = false;
		}
			
			
		if(friendly)
			closest = window.getWidth() * .88125;
		else
			closest = window.getWidth() * .0725;
		
		if(friendly && logicUnique > friendlyKilled && stop)
		{
			closest = closestFriend.get(logicUnique-1).getX();
		}
		else if(!friendly && logicUnique > enemyKilled && stop)
		{
			
			closest = closestEnemy.get(logicUnique-1).getX();
		}
		
	
		if(friendly && closestEnemy.size() > enemyKilled && logicUnique == friendlyKilled && closestFriend.size() > 0)
		{
			fight = true;
			closest = closestEnemy.get(enemyKilled).getX();
		}else if(!friendly && closestFriend.size() > friendlyKilled && logicUnique == enemyKilled && closestFriend.size() > 0)
		{
			fight = true;
			closest = closestFriend.get(friendlyKilled).getX();
		}
		
		if(permaStop)
		{
			if(friendly)
			{
				
			if(img.getTranslateX() < closest - window.getWidth() * .04008333)
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
			else if(!fight && logicUnique != friendlyKilled)
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
				if(health <= 0)
				{
					img.setTranslateX(-1000);
					friendlyKilled++;
					return;
				}
				RotateTransition rot = new RotateTransition();
				rot.setDuration(Duration.millis(200));
				rot.setNode(img);
				rot.setFromAngle(0);
				rot.setToAngle(90);
				rot.setOnFinished(e -> {
					if(health <= 0)
					{
						img.setTranslateX(-1000);
						friendlyKilled++;
						return;
					}
					
					if(fight)
					{
						if(closestEnemy.size() > enemyKilled)
							closestEnemy.get(enemyKilled).decreaseHealth(damage);
					}else
						enemyHealth -= damage;
					RotateTransition rota = new RotateTransition();
					rota.setDuration(Duration.millis(500));
					rota.setNode(img);
					rota.setFromAngle(90);
					rota.setToAngle(0);
					rota.setOnFinished(f -> {
						if(health <= 0)
						{
							img.setTranslateX(-1000);
							friendlyKilled++;
							return;
						}
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
				
				if(imgEnemy.getTranslateX() > closest + window.getWidth() * .04008333)
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
				else if(!fight && logicUnique != enemyKilled)
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
					if(health <= 0)
					{
						money += 20;
						xp += 40;
						imgEnemy.setTranslateX(10000);
						enemyKilled++;
						return;
					}
					RotateTransition rot = new RotateTransition();
					rot.setDuration(Duration.millis(200));
					rot.setNode(imgEnemy);
					rot.setFromAngle(0);
					rot.setToAngle(-90);
					rot.setOnFinished(e -> {
						
						if(health <= 0)
						{
							money += 20;
							xp += 40;
							imgEnemy.setTranslateX(10000);
							enemyKilled++;
							return;
						}
						if(fight)
						{
							closestFriend.get(friendlyKilled).decreaseHealth(damage);
						}
						else
							friendlyHealth -= damage;
						RotateTransition rota = new RotateTransition();
						rota.setDuration(Duration.millis(500));
						rota.setNode(imgEnemy);
						rota.setFromAngle(-90);
						rota.setToAngle(0);
						rota.setOnFinished(f -> {
							
							if(health <= 0)
							{
								money += 20;
								xp += 40;
								imgEnemy.setTranslateX(10000);
								enemyKilled++;
								return;
							}
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
					
					img.setTranslateX(0);
					
				}
				else
					imgEnemy.setTranslateX(1000000);
			}
		
			
		
			
	
	}
}


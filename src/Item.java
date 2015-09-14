import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Item {

	protected int x = 0;
	protected int y = 0;
	protected int startX = 0;
	protected int startY = 0;
	private boolean movingRight = false;
	private boolean movingDown = false;
	private boolean moved = false;
	protected int type = 0;
	private int bombPic = 0;
	gamePanel panel;
	BufferedImage oneUpImage;
	BufferedImage bomb1Image;
	BufferedImage bomb2Image;
	BufferedImage starImage;
	BufferedImage coinImage;
	BufferedImage redImage;
	
	Sounds oneUpSound;
	Sounds bombSound;
	Sounds starSound;
	Sounds redSound;
	
	public Item(int x,int y,gamePanel panel, boolean coin)
	{
		this.x = x;
		this.y = y;
		startX = x;
		startY = y;
		this.panel = panel;

		Random r = new Random();
		
		bombPic = 1 + r.nextInt(2);
		
		if(coin)
			type=10;
		else 
			type = 1 + r.nextInt(4);
		
		if(type==1)
			oneUpSound = new Sounds("/sounds/1_up.wav");
		if(type==2)
			bombSound = new Sounds("/sounds/bomb.wav");
		if(type==3)
			starSound = new Sounds("/sounds/star.wav");
		if(type==4)
			redSound = new Sounds("/sounds/red_mushroom.wav");
		
		try 
		{
			if(type==1)
				oneUpImage = ImageIO.read((this.getClass().getResource("/imgs/one_up.png")));
			if(type==2)
				bomb1Image = ImageIO.read((this.getClass().getResource("/imgs/bomb1.png")));
			if(type==2)
				bomb2Image = ImageIO.read((this.getClass().getResource("/imgs/bomb2.png")));
			if(type==3)
				starImage = ImageIO.read((this.getClass().getResource("/imgs/star.png")));
			if(type==10)
				coinImage = ImageIO.read((this.getClass().getResource("/imgs/coin2.png")));
			if(type==4)
				redImage = ImageIO.read((this.getClass().getResource("/imgs/red_mushroom.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) 
	{
		
			

		if(type==1)
		{
			y++;
			g.drawImage(oneUpImage, x, y, null);
		}
			
		if(type==2)
		{
			y++;
			if(bombPic==1)
				g.drawImage(bomb1Image, x, y, null);
			else
				g.drawImage(bomb2Image, x, y, null);
		}
		if(type==3)
		{
			y++;
			g.drawImage(starImage, x, y, null);
		}
		if(type==4)
		{
			y++;
			g.drawImage(redImage, x, y, null);
		}	
		if(type==10)
		{
			y--;
			g.drawImage(coinImage, x+5, y+5, null);
		}
			
			
	}
	
	public void reward()
	{
		if(type==1)
		{
			System.out.println("One Up!");
			panel.lives++;
			panel.frame.lblLives.setText(Integer.toString(panel.lives));

			oneUpSound.play();
		}

		if(type==2)
		{
			System.out.println("Boom!");
			
			if(panel.paddle.width > 50)
				panel.paddle.width = panel.paddle.width - 50;
			
			bombSound.play();
		}
		if(type==3)
		{
			System.out.println("Zoom Zoom!");
			if(panel.timerDelay>1)
				panel.timerDelay--;
			
			panel.ballTimer.stop();
			panel.ballTimer.setDelay(panel.timerDelay);
			panel.ballTimer.start();
			
			starSound.play();
		}
		if(type==4)
		{
			System.out.println("Red Mushroom");
			
			if(panel.paddle.width < 300)
				panel.paddle.width = panel.paddle.width + 50;
			
			redSound.play();
		}
	}
}



























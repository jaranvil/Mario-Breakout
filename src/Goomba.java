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

public class Goomba {

	protected int x = 0;
	protected int y = 0;
	private int fallCounter=0;
	private int counter=0;
	private int delay=50;
	private boolean right = true;
	
	private gamePanel panel;
	
	BufferedImage goomba1;
	BufferedImage goomba2;
	
	public Goomba(int x,int y, gamePanel panel)
	{
		this.x = x;
		this.y = y;
		this.panel = panel;
		
		try 
		{
			goomba1 = ImageIO.read((this.getClass().getResource("/imgs/goomba1.PNG")));
			goomba2 = ImageIO.read((this.getClass().getResource("/imgs/goomba2.PNG")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void move()
	{
		if(x >= -50 && x <= 1000)
		{
			if(supported())
			{
				x = x - 2;
			} else {
				if(fallCounter>2)
					y=y+4;
				else
					y++;
				x--;
			}
		} else {
			x--;
		}
	}
	
	public boolean supported()
	{
		for(int i=0;i<panel.blocks.size();i++)
		{
			if(panel.blocks.get(i).x >= -50 && panel.blocks.get(i).x <= 1000)
			{
				if(y+49 <= panel.blocks.get(i).y && y+49 >= panel.blocks.get(i).y-5)
					if(x+49 >= panel.blocks.get(i).x && x <= panel.blocks.get(i).x+49  )
					{
						fallCounter=0;
						return true;	
					}
			}
		}
		fallCounter++;
		return false;
	}
	
	public void paddleHit()
	{
		panel.lives--;
		panel.frame.lblLives.setText(Integer.toString(panel.lives));
	}
	
	public void draw(Graphics g, JPanel square) 
	{
		if(right)
		{
			g.drawImage(goomba1, x, y, null);
			counter++;
			if(counter>delay)
			{
				right = false;
				counter=0;
			}
				
		} else {
			g.drawImage(goomba2, x, y, null);
			counter++;
			if(counter>delay)
			{
				right = true;
				counter=0;
			}
				
		}	
	}
}
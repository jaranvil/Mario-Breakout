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

public class Ball {
	Random r = new Random();
	
	protected int x =0;
	protected int y =615;
	protected int height =15;
	protected int width =15;
	
	gamePanel panel;
	BufferedImage ballImage;
	
	protected boolean movingRight = true;
	protected boolean movingDown = false;

	
	public Ball(gamePanel panel)
	{
		this.panel = panel;
		
		this.x = r.nextInt(1000);
		
		try 
		{
			ballImage = ImageIO.read((this.getClass().getResource("/imgs/ball.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void draw(Graphics g, JPanel panel) {
		if(movingRight)
		{
			if(x >= (panel.getWidth() - width))
			{
				movingRight=false;
			} else {
				x=x+1;
			}
		} else {
			if(x <= 0)
			{
				movingRight=true;
			} else {
				x=x-1;
			}
		}
		
		if(movingDown)
		{
			if(y == (panel.getHeight() - height))
			{
				this.panel.ballDrop();
				movingDown = false;
				this.x = r.nextInt(1000);
				this.y = 615;
			} else {
				y=y+1;
			}
		} else {
			if(y <= 0)
			{
				movingDown=true;
			} else {
				y=y-1;
			}
		}
		
//		Ellipse2D ball = new Ellipse2D.Double(70, 70, 100, 100);
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.fill(ball);
		
		g.drawImage(ballImage, x, y, null);
	
		
		
	}
}

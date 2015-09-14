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

public class Cloud {

	protected int x = 0;
	protected int y = 0;
	private int cloud = 0;
	
	BufferedImage cloud1;
	
	public Cloud(int x,int y,int cloud)
	{
		this.x = x;
		this.y = y;
		this.cloud = cloud;
		
		try 
		{
			cloud1 = ImageIO.read((this.getClass().getResource("/imgs/cloud1.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, JPanel square) 
	{
		if(cloud==1)
		{
			g.drawImage(cloud1, x, y, null);
		}
			
	}
}

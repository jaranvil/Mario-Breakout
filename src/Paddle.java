import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Paddle {
	
	protected int x = 100;
	protected int y = 600;
	protected int width = 200;
	protected int height = 10;
	private int currentImageWidth = 200;

	BufferedImage image;
	
	public Paddle(JPanel panel)
	{	
		try 
		{
			image = ImageIO.read((this.getClass().getResource("/imgs/platform_200px.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, JPanel panel) 
	{
		if((currentImageWidth != width))
		{
			try 
			{
				image = ImageIO.read((this.getClass().getResource("/imgs/platform_"+width+"px.png")));
				currentImageWidth = width;
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		g.drawImage(image, x, y, null);
	}
	


	
}


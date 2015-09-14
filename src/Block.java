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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Block {

	protected int x = 0;
	protected int y = 0;
	protected int width = 0;
	protected int height = 0;
	protected int item = 0;
	
	BufferedImage image;
	BufferedImage goldImage;
	BufferedImage brick;
	BufferedImage pole;
	BufferedImage cap;
	BufferedImage flag;
	
	public Block(int x, int y, int item)
	{
		this.x = x;
		this.y = y;
		this.width = 50;
		this.height = 50;
		
		this.item = item;
		
		try 
		{
			if(item==1)
				image = ImageIO.read((this.getClass().getResource("/imgs/block4.png")));
			if(item==2)
				goldImage = ImageIO.read((this.getClass().getResource("/imgs/gold_block.png")));
			if(item==3)
				brick = ImageIO.read((this.getClass().getResource("/imgs/brick.png")));
			if(item==4)
				pole = ImageIO.read((this.getClass().getResource("/imgs/pole.png")));
			if(item==5)
				cap = ImageIO.read((this.getClass().getResource("/imgs/cap.png")));
			if(item==6)
				flag = ImageIO.read((this.getClass().getResource("/imgs/flag.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, JPanel square) 
	{
		if(item==1)
			g.drawImage(image, x, y, null);
		if(item==2)
			g.drawImage(goldImage, x, y, null);
		if(item==3)
			g.drawImage(brick, x, y, null);
		if(item==4)
			g.drawImage(pole, x, y, null);
		if(item==5)
			g.drawImage(cap, x, y, null);
		if(item==6)
			g.drawImage(flag, x, y, null);

	}
}

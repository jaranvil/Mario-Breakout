

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class MainMenu extends JPanel {

	private SplashPanel splash;
	private BufferedImage bgImage;
	private BufferedImage menuPanel;
	protected Timer timer = new Timer(30,new levelHandler());
	
	public MainMenu(SplashPanel splash) {

		this.splash = splash;
		
		try 
		{
			bgImage = ImageIO.read((this.getClass().getResource("/imgs/bg2.png")));
			menuPanel = ImageIO.read((this.getClass().getResource("/imgs/menu_panel_2.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(bgImage, 0, 0, null);
		
		
		for(int i=0;i<splash.clouds.size();i++)
		{
			splash.clouds.get(i).x--;
			splash.clouds.get(i).draw(g, this);
		}

		g.drawImage(menuPanel, 200, 40, null);
		
	}
	
	private class levelHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			repaint();
		}
	}

	

}

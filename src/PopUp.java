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


public class PopUp extends JPanel {

	private Action spaceBarAction;
	protected int message = 1;
	Frame frame;
	BufferedImage pause;
	
	public PopUp(Frame frame)  {
		
		this.frame = frame;
		
		spaceBarAction = new spaceBarAction();
		this.getInputMap().put( KeyStroke.getKeyStroke( "SPACE" ), "spaceBar" );
		this.getActionMap().put( "spaceBar", spaceBarAction );
		
		try {
			pause = ImageIO.read((this.getClass().getResource("/imgs/paused_screen.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(pause, 250, 100, null);	
	}
	
	public void pause()
	{
		repaint();
	}
	
	class spaceBarAction extends AbstractAction
	 {
	     public void actionPerformed( ActionEvent tf2 )
	     {
	    	frame.panel.requestFocus();
	    	frame.panel.start();
	    	 
	     } // end method actionPerformed()
	 } // end class EnterAction
}




























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


public class HelpTextPanel extends JPanel {

	private Action spaceBarAction;
	protected int message = 1;
	gamePanel panel;
	
	public HelpTextPanel(gamePanel panel) {
		repaint();
		this.panel = panel;
		
		spaceBarAction = new spaceBarAction();
		this.getInputMap().put( KeyStroke.getKeyStroke( "SPACE" ), "spaceBar" );
		this.getActionMap().put( "spaceBar", spaceBarAction );
	}
	
	public void displayText(int message)
	{
		this.message = message;
		this.requestFocus();
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(message==1)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Bank Gothic", Font.BOLD, 40)); 
			g.drawString("Press space to begin...", 250, 500);
		}
		if(message==2)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Bank Gothic", Font.BOLD, 40)); 
			g.drawString("Ball dropped", 350, 500);
			g.setFont(new Font("Bank Gothic", Font.BOLD, 25)); 
			g.drawString("Press space to continue...", 325, 550);
		}
		if(message==3)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Bank Gothic", Font.BOLD, 40)); 
			g.drawString("Game Over", 375, 500);
			g.setFont(new Font("Bank Gothic", Font.BOLD, 25)); 
			g.drawString("Press space to continue...", 325, 550);
		}
		if(message==4)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Bank Gothic", Font.BOLD, 40)); 
			g.drawString("Level Complete!", 350, 500);
			g.setFont(new Font("Bank Gothic", Font.BOLD, 25)); 
			g.drawString("Press space to continue...", 350, 550);
		}
		
	}
	
	class spaceBarAction extends AbstractAction
	 {
	     public void actionPerformed( ActionEvent tf2 )
	     {
	    	 //if game over, space bar goes back to main menu
	    	 //just restarts the game otherwise
	    	 if(message==3)
	    	 {
	    		 panel.frame.gameOver();
	    	 } else if (message == 4) {
	    		 panel.frame.levelComplete();
	    	 } else {
	    		 
	    		 if(!panel.ballTimer.isRunning())
		    		 panel.start();
	    	 } 
	     } // end method actionPerformed()
	 } // end class EnterAction
}




























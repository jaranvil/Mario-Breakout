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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.*;

import javax.sound.*;
import javax.sound.sampled.*;




public class gamePanel extends JPanel {

	//timers
	protected int timerDelay = 3;
	protected Timer ballTimer = new Timer(timerDelay,new TimerHandler());
	protected Timer levelTimer = new Timer(timerDelay*10,new levelHandler());
	protected Timer rightTimer = new Timer(timerDelay,new RightHandler());
	protected Timer leftTimer = new Timer(timerDelay,new leftHandler());
	protected Timer flagTimer = new Timer(timerDelay,new flagHandler());
	
	protected Frame frame;
	
	Paddle paddle = new Paddle(this);
	Ball ball = new Ball(this);
	
	//actions for key bindings, paddle movement
	private Action startRightAction;
	private Action stopRightAction;
	private Action startLeftAction;
	private Action stopLeftAction;
	private Action spaceBarAction;
	private Action escAction;
	
	protected ArrayList<Block> blocks = new ArrayList<>();
	protected ArrayList<Cloud> clouds = new ArrayList<>();
	protected ArrayList<Goomba> goombas = new ArrayList<>();
	protected ArrayList<Item> items = new ArrayList<>();
	
	protected int levelPanel = 1;
	BufferedImage waterImage;
	BufferedImage background;
	Sounds deadSound = new Sounds("/sounds/die.wav");
	Sounds oneUpSound = new Sounds("/sounds/1_up.wav");
	Sounds paddleSound = new Sounds("/sounds/paddle_hit.wav");
	Sounds gameOverSound = new Sounds("/sounds/game_over.wav");
	Sounds levelCompleteSound = new Sounds("/sounds/level_complete.wav");
	
	protected boolean flagRising = false;
	protected boolean playingFromEditor=false;
	
	protected int level = 1;
	protected int coins = 0;
	protected int lives = 3;
	protected int loadingWidth = 0;

	//panel constructor
	public gamePanel(Frame frame) {
		startRightAction = new startRightAction();
		this.getInputMap().put( KeyStroke.getKeyStroke( "pressed RIGHT" ), "startRight" );
		this.getActionMap().put( "startRight", startRightAction );
		
		stopRightAction = new stopRightAction();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released RIGHT" ), "stopRight" );
		this.getActionMap().put( "stopRight", stopRightAction );
		
		startLeftAction = new startLeftAction();
		this.getInputMap().put( KeyStroke.getKeyStroke( "pressed LEFT" ), "startLeft" );
		this.getActionMap().put( "startLeft", startLeftAction );
		
		stopLeftAction = new stopLeftAction();
		this.getInputMap().put( KeyStroke.getKeyStroke( "released LEFT" ), "stopLeft" );
		this.getActionMap().put( "stopLeft", stopLeftAction );
		
		spaceBarAction = new spaceBarAction();
		this.getInputMap().put( KeyStroke.getKeyStroke( "SPACE" ), "spaceBar" );
		this.getActionMap().put( "spaceBar", spaceBarAction );
		
		escAction = new escAction();
		this.getInputMap().put( KeyStroke.getKeyStroke( "ESCAPE" ), "esc" );
		this.getActionMap().put( "esc", escAction );

		this.frame = frame;
		
		try 
		{
			waterImage = ImageIO.read((this.getClass().getResource("/imgs/water.png")));
			background = ImageIO.read((this.getClass().getResource("/imgs/bg2.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void start()
	{
		
		this.requestFocus();
		ballTimer.start();		
		levelTimer.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, null);
		g.drawImage(waterImage, 0, 630, null);
		Color color = new Color(0f,0f,0f, 0.5f );
		g.setColor(color);
		g.fillRect(0, 0, 1000, 50);
		
		if(!flagRising)
		{
			for(int i=0;i<clouds.size();i++)
			{
				if(clouds.get(i).x >= -100 && clouds.get(i).x <= 1000)
				clouds.get(i).draw(g, this);
			}
			for(int i=0;i<blocks.size();i++)
			{
				if(blocks.get(i).x >= -50 && blocks.get(i).x <= 1000)
				blocks.get(i).draw(g, this);
			}
			for(int i=0;i<goombas.size();i++)
			{
				if(goombas.get(i).x >= -50 && goombas.get(i).x <= 1000)
				goombas.get(i).draw(g, this);
			}
			for(int i=0;i<items.size();i++)
			{
				if(items.get(i).type==10)
				{
					if(items.get(i).y > items.get(i).startY-40)
						items.get(i).draw(g);
					else
						items.remove(i);
				} else {
					items.get(i).draw(g);
				}
			}
			
			paddle.draw(g, this);
			ball.draw(g, this);
			
			paddleCollision();
			blockCollision();
		} 
		
		if(flagRising)
		{
			for(int i=0;i<blocks.size();i++)
			{
				if(blocks.get(i).x >= -50 && blocks.get(i).x <= 1000)
				blocks.get(i).draw(g, this);
			}
		}
	}
	
	public void drawLevel(Graphics g, ArrayList<Block> blocks, ArrayList<Cloud> clouds)
	{
		
	}
	
	//check for a collision with the paddle
	public void paddleCollision()
	{
		if((ball.y+15) >= paddle.y && (ball.y+15) <= (paddle.y + paddle.height))
			if((ball.x+7.5) > paddle.x && (ball.x) < (paddle.x + paddle.width))
			{
				ball.movingDown = false;
				paddleSound.play();
			}
		if(!(items.size()==0))
		{
			for(int i=0;i<items.size();i++)
			{
				if((items.get(i).y+50) >= paddle.y && (items.get(i).y+50) <= (paddle.y + paddle.height))
				{
					if((items.get(i).x+25) > paddle.x && (items.get(i).x) < (paddle.x + paddle.width))
					{
						items.get(i).reward();
						items.remove(i);
					}  	
				} else {
					if(items.get(i).y > 700)
						items.remove(i);
				}		
			}
		}
		if(!(goombas.size()==0))
		{
			for(int i=0;i<goombas.size();i++)
			{
				if((goombas.get(i).y+50) >= paddle.y && (goombas.get(i).y+50) <= (paddle.y + paddle.height))
				{
					if((goombas.get(i).x+25) > paddle.x && (goombas.get(i).x) < (paddle.x + paddle.width))
					{
						goombas.get(i).paddleHit();
						goombas.remove(i);
					}  	
				} else {
					if(goombas.get(i).y > 700)
						goombas.remove(i);
				}		
			}
		}
	}
	
	public void blockCollision()
	{
		for(int b=0;b<blocks.size();b++)
		{
			int bx = ball.x;
			int by = ball.y;
			int x1 = blocks.get(b).x;
			int x2 = blocks.get(b).x + blocks.get(b).width;
			int y1 = blocks.get(b).y;
			int y2 = blocks.get(b).y + blocks.get(b).height;
			
			
			if(!ball.movingDown)
			{
				if(by <= y2 && by >= y2-15)
					if(bx + ball.width > x1 && bx < x2)
					{
						ball.movingDown = true;
						blockHit(blocks.get(b), b);
					}
			} else {
				if((by + ball.height) >= y1 && by + ball.height <= y1+15)
					if(bx + ball.width > x1 && bx < x2)
					{
						ball.movingDown = false;
						blockHit(blocks.get(b), b);
					}
			}
			if(!ball.movingRight)
			{
				if(bx <= x2 && bx >= x2-15)
					if(by + ball.height > y1 && by < y2)
					{
						ball.movingRight = true;
						blockHit(blocks.get(b), b);
					}
			} else {
				if((bx + ball.width) >= x1 && bx + ball.width <= x1+15)
					if(by + ball.height > y1 && by < y2)
					{
						ball.movingRight = false;
						blockHit(blocks.get(b), b);
						
					}
			}
		}
	}
	
	public void blockHit(Block block, int index)
	{
		if(block.item == 2) //item block
		{
			items.add(new Item(block.x, block.y, this, false));
			blocks.remove(index);
		}
		if(block.item == 1) //normal block
		{
			items.add(new Item(blocks.get(index).x, blocks.get(index).y, this, true));
			coins++;
			frame.lblCoins.setText(Integer.toString(coins));
			blocks.remove(index);
		}
		if(block.item == 4) //flagpole
		{
			raiseFlag();
			flagRising=true;
			System.out.println("moving up");
			System.out.println("Hit block x:"+blocks.get(index).x+" y: "+blocks.get(index).y);
			System.out.println("Type: "+blocks.get(index).item);
		}
	}
	
	//called from ball class when it hits the bottom
	public void ballDrop()
	{
		ballTimer.stop();
		levelTimer.stop();
		
		System.out.println("Ball drop");
		lives--;
		if(lives<=0)
			gameOver();
		else {
			deadSound.play();
			frame.lblLives.setText(Integer.toString(lives));
			frame.helpText.message = 2;
			frame.helpText.requestFocus();
			frame.helpText.repaint();
		}
	}
	
	public void pause()
	{
		ballTimer.stop();
		levelTimer.stop();
		frame.helpText.setVisible(false);
		frame.popUp.setVisible(true);
		frame.popUp.requestFocus();
		frame.popUp.pause();
	}
	
	public void raiseFlag()
	{
		ballTimer.stop();
		levelCompleteSound.play();
		flagTimer.start();
	}
	
	public void levelComplete()
	{
		System.out.println("Level Complete!");
		levelTimer.stop();
		frame.helpText.displayText(4);
	}
	
	public void gameOver()
	{
		frame.helpText.setVisible(true);
		frame.helpText.requestFocus();
		frame.helpText.displayText(3);
		gameOverSound.play();
	}
	
	public void setLevel(ArrayList<Block> blocks, ArrayList<Cloud> clouds, ArrayList<Goomba> goombas)
	{
		this.blocks = blocks;
		this.clouds = clouds;
		this.goombas = goombas;
		
		repaint();
		
		System.out.println("Block array size at setLevel : gamePanel: "+ blocks.size());
		System.out.println("i=0.x = "+blocks.get(0).x);
	}
	
	public void loadLevel(String level)
	{
		String[] lines;
		String path = "levels\\"+level+".txt";
        File f = new File(path);

        if (f.exists()) {
            try {
                
                BufferedReader br = new BufferedReader(new FileReader(path));
                
                lines = new String[15];
                for(int i=0;i<lines.length;i++)
                {
                	lines[i] =  br.readLine();
                }

                br.close();
                
                int length = lines[0].length();
                int[][] levelArray = new int[15][length+1];
                
                for(int row=0;row<14;row++){
                	String[] line = lines[row].split("(?!^)");
                	for(int col=0;col<length;col++){
                		levelArray[row][col] = Integer.parseInt(line[col]);
                	}
                }
	            	
                for(int row=0;row<14;row++) {
        			for(int col=0;col<length;col++) {
        				if(levelArray[row][col] == 1)
        					blocks.add(new Block(col*50, row*50,1));
        				if(levelArray[row][col] == 2)
        					blocks.add(new Block(col*50, row*50,2));
        				if(levelArray[row][col] == 3)
        					clouds.add(new Cloud(col*50, row*50,1));
        				if(levelArray[row][col] == 4)
        					goombas.add(new Goomba(col*50, row*50,this));
        				if(levelArray[row][col] == 5)
        					blocks.add(new Block(col*50, row*50,3));
        				if(levelArray[row][col] == 6)
        					blocks.add(new Block(col*50, row*50,4));
        				if(levelArray[row][col] == 7)
        					blocks.add(new Block(col*50, row*50,5));
        				if(levelArray[row][col] == 8)
        					blocks.add(new Block(col*50, row*50,6));
        				
        				if(col%10 == 0)
        				{
        					System.out.println("tick");

        				}
        					
        			}
        		}
        		
        		frame.startLevel();
                
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }  
        } else {
        	System.out.println("Level cannot be loaded. File not found.");
        }
	}
	
	//Timer handlers
	
	//handler for timer that moves the ball
	private class TimerHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			repaint();
		}
	}
	
	private class levelHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			for(int i=0;i<blocks.size();i++)
			{
				blocks.get(i).x--;
			}
			for(int i=0;i<clouds.size();i++)
			{
				clouds.get(i).x = clouds.get(i).x - 2;
			}
			for(int i=0;i<goombas.size();i++)
			{
				goombas.get(i).move();
			}
		}
	}
	
	//handler for timer that moves paddle to the right
	private class RightHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			paddle.x = paddle.x + 3;
			if(paddle.x + paddle.width >= 1000)
				paddle.x = 1000 - paddle.width;
			
		}
	}
	
	//handler for timer that moves the paddle left
	private class leftHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			paddle.x = paddle.x - 3;
			if(paddle.x <=0)
				paddle.x = 0;
			
		}
	}

	private class flagHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			for(int i=0;i<blocks.size();i++)
			{
				if(blocks.get(i).item == 6)
				{
					if(blocks.get(i).y + blocks.get(i).height <= ball.y)
					{
						flagTimer.stop();
						flagRising=false;
						levelComplete();
					} else {
						blocks.get(i).y--;
						repaint();
					}
						
				}
			}
			
		}
	}
	
	//Event handlers for paddle movement
	
	//http://www.dreamincode.net/forums/topic/245148-java-key-binding-tutorial-and-demo-program/
	
	class startRightAction extends AbstractAction
	 {
	     public void actionPerformed( ActionEvent tf )
	     {
	    	 if(leftTimer.isRunning())
	    		 leftTimer.stop();
	    	 
	         rightTimer.start();
	     } // end method actionPerformed()
	 } // end class EnterAction
	
	class stopRightAction extends AbstractAction
	 {
	     public void actionPerformed( ActionEvent tf )
	     {
	         rightTimer.stop();
	     } // end method actionPerformed()
	 } // end class EnterAction
	
	class startLeftAction extends AbstractAction
	 {
	     public void actionPerformed( ActionEvent tf2 )
	     {
	    	 if(rightTimer.isRunning())
	    		 rightTimer.stop();
	    	 
	    	 leftTimer.start();
	     } // end method actionPerformed()
	 } // end class EnterAction
	
	class stopLeftAction extends AbstractAction
	 {
	     public void actionPerformed( ActionEvent tf2 )
	     {
	    	 leftTimer.stop();
	     } // end method actionPerformed()
	 } // end class EnterAction
	
	class spaceBarAction extends AbstractAction
	 {
	     public void actionPerformed( ActionEvent tf2 )
	     {
	    	 if(!ballTimer.isRunning())
	    		 start();
	     } // end method actionPerformed()
	 } // end class EnterAction
	
	class escAction extends AbstractAction
	 {
	     public void actionPerformed( ActionEvent tf2 )
	     {
	    	System.out.println("ESC");
	    	pause();
	     } // end method actionPerformed()
	 } // end class EnterAction
}



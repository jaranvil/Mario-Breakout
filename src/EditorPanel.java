import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


public class EditorPanel extends JPanel {

	private Frame frame;
	private BufferedImage bgImage;
	private BufferedImage sidebar;
	protected int currentTool = 0;
	protected JTextField txtLevelName;
	protected JButton btnSave;
	protected JButton btnEditorPlay;
	protected JButton btnGoomba;
	
	protected Timer rightTimer = new Timer(60,new rightHandler());
	protected Timer leftTimer = new Timer(30,new leftHandler());
	protected Timer placeTimer = new Timer(60,new placeHandler());

	public MouseEvent e2;
	
	protected ArrayList<Block> blocks = new ArrayList<>();
	protected ArrayList<Cloud> clouds = new ArrayList<>();
	protected ArrayList<Goomba> goombas = new ArrayList<>();
	
	public EditorPanel(Frame frame) {

		this.frame = frame;
		
		try 
		{
			bgImage = ImageIO.read((this.getClass().getResource("/imgs/bg2.png")));
			sidebar = ImageIO.read((this.getClass().getResource("/imgs/sidebar.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		repaint();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {	
				e2 = e;
				placeTimer.start();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				placeTimer.stop();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClick(e.getX(), e.getY());
			}
		});
		
		
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(bgImage, 0, 0, null);
		g.drawImage(sidebar, 0, 0, null);
		g.drawImage(sidebar, 900, 0, null);
		Color color = new Color(0f,0f,0f, 0.5f );
		g.setColor(color);
		g.fillRect(100, 0, 800, 50);
		
		for(int i=0;i<clouds.size();i++)
		{
			if(clouds.get(i).x >= 100 && clouds.get(i).x <= 800)
			clouds.get(i).draw(g, this);
		}
		for(int i=0;i<blocks.size();i++)
		{
			if(blocks.get(i).x >= 100 && blocks.get(i).x <= 850)
			blocks.get(i).draw(g, this);
		}
		for(int i=0;i<goombas.size();i++)
		{
			if(goombas.get(i).x >= 100 && goombas.get(i).x <= 850)
			goombas.get(i).draw(g, this);
		}
	}
	
	public void newLevel()
	{
		blocks.clear();
		goombas.clear();
		clouds.clear();
		
		for(int y=0;y<750;y=y+50)
		{
			blocks.add(new Block(100, y, 1));
		}
		
		repaint();
	}
	
	public void selectTool(int tool)
	{
		if(tool==1) //normal block
		{
			deselect(currentTool);
			currentTool = tool;
			frame.btnCoinBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block1_button_selected.png")));
		}
		if(tool==2) //item block
		{
			deselect(currentTool);
			currentTool = tool;
			frame.btnItemBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block2_button_selected.png")));
		}
		if(tool==3) //eraser
		{
			deselect(currentTool);
			currentTool = tool;
			frame.btnEraser.setIcon(new ImageIcon(Frame.class.getResource("/imgs/eraser_button_selected.png")));
		}
		if(tool==4) //goomba
		{
			deselect(currentTool);
			currentTool = tool;
			frame.btnGoomba.setIcon(new ImageIcon(Frame.class.getResource("/imgs/goomba_button_selected.png")));
		}
	}
	
	public void deselect(int tool)
	{
		if(tool==1) //normal block
		{
			frame.btnCoinBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block1_button.png")));
		}
		if(tool==2) //item block
		{
			frame.btnItemBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block2_button.png")));
		}
		if(tool==3) //eraser
		{
			frame.btnEraser.setIcon(new ImageIcon(Frame.class.getResource("/imgs/eraser_button.png")));
		}
		if(tool==4) //goomba
		{
			frame.btnGoomba.setIcon(new ImageIcon(Frame.class.getResource("/imgs/goomba_button.png")));
		}
	}

	public void mouseClick(int mouseX, int mouseY)
	{
		//round to lowest multible of 50
		int x = (((mouseX + 49) / 50 ) * 50)-50;
		int y = (((mouseY + 49) / 50 ) * 50)-50;
		
		if(currentTool == 1 || currentTool == 2)
		{
			if(x>=100 && x<=850)
				if(!searchForBlocks(x,y))
					blocks.add(new Block(x, y, currentTool));
		}
		if(currentTool == 3)
		{
			if(searchForBlocks(x,y))
				deleteBlock(x, y);
			if(searchForGoombas(x,y))
				deleteGoomba(x,y);
		}
		if(currentTool == 4)
		{
			goombas.add(new Goomba(x,y,frame.panel));
		}

		repaint();
	}
	
	public boolean searchForBlocks(int x, int y)
	{
		for(int i=0;i<blocks.size();i++)
		{
			if(blocks.get(i).x == x && blocks.get(i).y == y)
				return true;
		}
		return false;
	}
	
	public boolean searchForGoombas(int x, int y)
	{
		for(int i=0;i<goombas.size();i++)
		{
			if(goombas.get(i).x == x && goombas.get(i).y == y)
				return true;
		}
		return false;
	}
	
	public void deleteBlock(int x, int y)
	{
		for(int i=0;i<blocks.size();i++)
		{
			if(blocks.get(i).x == x && blocks.get(i).y == y)
				blocks.remove(i);
		}
	}
	
	public void deleteGoomba(int x, int y)
	{
		for(int i=0;i<goombas.size();i++)
		{
			if(goombas.get(i).x == x && goombas.get(i).y == y)
				goombas.remove(i);
		}
	}
	
	public void scroll(boolean right)
	{
		if(right)
			if(rightTimer.isRunning())
				rightTimer.stop();
			else
				rightTimer.start();
		if(!right)
			if(leftTimer.isRunning())
				leftTimer.stop();
			else
				leftTimer.start();
	}
	
	public void exit()
	{

	}
	
	public void ResetArrayPosition()
	{
		int firstBlockIndex = 0;
		
		for(int i=0;i<blocks.size();i++)
		{
			if(blocks.get(i).x < blocks.get(firstBlockIndex).x)
				firstBlockIndex = i;
		}
		
		if(blocks.get(firstBlockIndex).x < 0)
		{
			while(blocks.get(firstBlockIndex).x < 0)
			{
				for(int i=0;i<blocks.size();i++)
				{
					blocks.get(i).x = blocks.get(i).x + 50;
				}
				for(int i=0;i<goombas.size();i++)
				{
					goombas.get(i).x = goombas.get(i).x + 50;
				}
			}
		}
		
		if(blocks.get(firstBlockIndex).x > 0)
		{
			while(blocks.get(firstBlockIndex).x > 0)
			{
				for(int i=0;i<blocks.size();i++)
				{
					blocks.get(i).x = blocks.get(i).x - 50;
				}
				for(int i=0;i<goombas.size();i++)
				{
					goombas.get(i).x = goombas.get(i).x - 50;
				}
			}
		}
	}
	
	public boolean saveLevel()
	{
		
		ResetArrayPosition();
		
		// Get length of level
		// aka block with the largest y value
		
		int lastIndex = 0;
		for(int i=0;i<blocks.size();i++)
		{
			if(blocks.get(i).x > blocks.get(lastIndex).x)
				lastIndex = i;
		}
		int levelLength = ((blocks.get(lastIndex).x) / 50) + 1;
		
		if(!(levelLength > 2))
		{
			// Create array to print to file
			int[][] levelSave = new int[15][levelLength+1];
			
			// place ID num of blocks in there spots in the array
			for(int i=0;i<blocks.size();i++)
			{
				int x = (blocks.get(i).x) / 50;
				int y = (blocks.get(i).y) / 50;
				
				levelSave[y][x] = blocks.get(i).item;
			}
			
			String name = frame.txtLevelName.getText();
			name.replace(" ", "_");
	        
			File dir = new File("levels");
			dir.mkdirs();
			File f = new File(dir, name+".txt");

	        try {
	            if (!f.exists()) 
	            {
	                f.createNewFile();
	                System.out.println("File created");
	                FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);

		            for(int rows=0;rows<14;rows++){
		            	for(int cols=0;cols<levelLength;cols++)
		            	{
		            		fw.write(Integer.toString(levelSave[rows][cols]));
		            	}
		            	fw.write("\r\n");
		            }
		            
	  	          
	  	           fw.close();
	  	           return true;
	            } else {
	            	System.out.println("Level Name alrady exists");
	            	JOptionPane.showMessageDialog (null, "Level name already exists", "Error", JOptionPane.ERROR_MESSAGE);
	            	return false;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
		} else {
			System.out.println("Level Empty");
			JOptionPane.showMessageDialog (null, "Level Empty", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
	}
	
	public void runLevel()
	{
		if(saveLevel())
			frame.panel.loadLevel(frame.txtLevelName.getText());
	}

	private class placeHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent actionEvent) {	
			PointerInfo a = MouseInfo.getPointerInfo();
			Point point = new Point(a.getLocation());
			SwingUtilities.convertPointFromScreen(point, e2.getComponent());
			int x=(int) point.getX();
			int y=(int) point.getY();

			mouseClick(x,y);
		}
	}
	
	private class rightHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			for(int i=0;i<blocks.size();i++)
			{
				blocks.get(i).x = blocks.get(i).x - 50;
			}
			for(int i=0;i<goombas.size();i++)
			{
				goombas.get(i).x = goombas.get(i).x - 50;
			}
			repaint();
		}
	}
	
	private class leftHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			for(int i=0;i<blocks.size();i++)
			{
				blocks.get(i).x = blocks.get(i).x + 50;
			}
			for(int i=0;i<goombas.size();i++)
			{
				goombas.get(i).x = goombas.get(i).x + 50;
			}
			repaint();
		}
	}
}



















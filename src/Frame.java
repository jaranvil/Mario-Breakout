import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import java.awt.SystemColor;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class Frame extends JFrame {

	private JPanel contentPane;
	protected gamePanel panel;
	private JLabel label;
	protected JLabel lblLives;
	
	protected JLabel lblPopupLives;
	protected JLabel lblLives2;
	protected JLabel lblPopupCoins;
	protected JLabel lblCoins2;
	protected JTextField txtLevelName;
	
	protected HelpTextPanel helpText;
	protected SplashPanel splash;
	protected MainMenu mainMenu;
	protected EditorPanel editor;
	protected PopUp popUp;
	private JLabel label_1;
	protected JLabel lblCoins;
	private JButton btnPlay;
	private JButton btnLevel1;
	private JLabel lblMainMenuTitle;
	private JButton btnLevel2;
	private JButton btnLevel3;
	private JButton btnLevel4;
	private JButton btnNewEditorLevel;
	
	
	Theme themeSong = new Theme();
	private JButton btnLoadEditorLevel;
	protected JButton btnCoinBlock;
	protected JButton btnItemBlock;
	protected JButton btnUp;
	protected JButton btnRight;
	protected JButton btnLeft;
	protected JButton btnExitEditor;
	protected JButton btnEditorPlay;
	protected JButton btnEraser;
	protected JButton btnSave;
	protected JButton btnGoomba;
	protected JLabel lblPan;
	
	protected ArrayList<Block> blocks = new ArrayList<>();
	protected ArrayList<Cloud> clouds = new ArrayList<>();
	protected ArrayList<Goomba> goombas = new ArrayList<>();
	protected ArrayList<Item> items = new ArrayList<>();
	
	protected int[][] level1 =  {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,4,0,0,0,0,0,0,0,4,0,0,3,1,0,0,0,0,0,0,4,0,4,0,0,4,0,0,0,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,4,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,3,4,0,0,0,0,0,0,0,0,4,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5},
										{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5},
										{0,0,0,0,3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,3,1,0,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,1,1,0,0,0,0,4,0,0,0,0,0,0,0,4,0,0,0,0,3,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,5},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,3,1,0,0,0,1,1,1,1,1,1,1,1,1,0,0,3,1,0,1,1,1,1,2,1,1,1,1,2,1,1,1,1,0,1,0,2,0,1,0,0,0,1,2,0,1,2,0,1,2,0,1,2,0,1,0,0,1,1,0,0,0,1,0,2,0,0,0,0,0,0,2,0,1,0,0,0,1,1,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,7,0,0,0,5},
										{0,0,0,1,2,1,0,0,1,2,1,0,0,1,2,1,0,0,0,0,1,0,0,2,1,1,0,0,0,4,0,0,0,1,1,2,0,0,1,1,0,0,3,1,0,2,0,2,0,2,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,1,3,0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,3,1,1,0,0,1,2,1,1,0,0,0,0,1,2,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,6,0,0,0,5},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,1,0,0,0,0,1,1,0,0,1,1,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,1,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,6,0,0,0,5},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,1,0,0,1,0,0,0,0,2,1,2,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,2,1,0,0,0,0,2,1,1,2,0,0,0,0,1,2,1,0,0,0,0,0,1,2,1,0,2,1,2,0,1,2,1,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,6,0,0,0,5},
										{0,0,0,0,0,1,1,1,1,0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,2,0,0,2,0,0,2,0,0,0,3,1,0,0,0,0,1,1,1,0,0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,1,2,1,0,1,2,1,0,1,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,6,0,0,0,5},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,1,1,0,1,1,0,1,1,0,0,1,0,0,0,0,0,0,0,0,3,0,0,0,3,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,6,0,0,0,5},
										{0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,6,0,0,0,5},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,5},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,6,0,0,0,5},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,0,0,5},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,0,0,5}};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1015, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		// Main Game Panel
		
		panel = new gamePanel(this);
		
		// In-game help text panel
		
		helpText = new HelpTextPanel(panel);
		helpText.setOpaque(false);
		contentPane.add(helpText);
		
		// Pause Screen 
		// Level Complete Screen
		
		popUp = new PopUp(this);
		popUp.setOpaque(false);
		popUp.setLayout(null);
		contentPane.add(popUp);
		
		lblPopupLives = new JLabel("");
		lblPopupLives .setIcon(new ImageIcon(Frame.class.getResource("/imgs/one_up.png")));
		lblPopupLives .setBounds(350, 250, 50, 50);
		popUp.add(lblPopupLives);
		
		lblLives2 = new JLabel("0");
		lblLives2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLives2.setForeground(Color.WHITE);
		lblLives2.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
		lblLives2.setBounds(370, 260, 75, 23);
		popUp.add(lblLives2);
		
		lblPopupCoins = new JLabel("");
		lblPopupCoins .setIcon(new ImageIcon(Frame.class.getResource("/imgs/coin50.png")));
		lblPopupCoins .setBounds(350, 350, 50, 50);
		popUp.add(lblPopupCoins);
		
		lblCoins2 = new JLabel("0");
		lblCoins2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCoins2.setForeground(Color.WHITE);
		lblCoins2.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
		lblCoins2.setBounds(370, 360, 75, 23);
		popUp.add(lblCoins2);
		
		// Opening Splash Panel
		
		splash = new SplashPanel(this);
		splash.setBackground(new Color(51, 153, 255));
		contentPane.add(splash);
		splash.setLayout(null);
		
		// Main Menu Panel
		
		mainMenu = new MainMenu(splash);
		contentPane.add(mainMenu);
		mainMenu.setLayout(null);
		
		// Level Editor Panel
		
		editor = new EditorPanel(this);
		contentPane.add(editor);
		editor.setLayout(null);
		
		btnCoinBlock = new JButton("");
		btnCoinBlock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCoinBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block1_button_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(editor.currentTool==1))
					btnCoinBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block1_button.png")));
			}
		});
		btnCoinBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.selectTool(1);
			}
		});
		btnCoinBlock.setContentAreaFilled(false);
		btnCoinBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block1_button.png")));
		btnCoinBlock.setOpaque(false);
		btnCoinBlock.setFocusable(false);
		btnCoinBlock.setBorderPainted(false);
		btnCoinBlock.setBounds(0, 50, 100, 100);
		btnCoinBlock.setToolTipText("Coin Block");
		editor.add(btnCoinBlock);
		
		btnItemBlock = new JButton("");
		btnItemBlock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnItemBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block2_button_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(editor.currentTool==2))
					btnItemBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block2_button.png")));
			}
		});
		btnItemBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.selectTool(2);
			}
		});
		btnItemBlock.setContentAreaFilled(false);
		btnItemBlock.setIcon(new ImageIcon(Frame.class.getResource("/imgs/block2_button.png")));
		btnItemBlock.setOpaque(false);
		btnItemBlock.setFocusable(false);
		btnItemBlock.setBorderPainted(false);
		btnItemBlock.setBounds(0, 150, 100, 100);
		btnItemBlock.setToolTipText("Item Block");
		editor.add(btnItemBlock);
		
		btnUp = new JButton("");
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnUp.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_up_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnUp.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_up.png")));
			}
		});
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnUp.setContentAreaFilled(false);
		btnUp.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_up.png")));
		btnUp.setOpaque(false);
		btnUp.setFocusable(false);
		btnUp.setBorderPainted(false);
		btnUp.setBounds(0, 0, 100, 50);
		editor.add(btnUp);
		
		btnLeft = new JButton("");
		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLeft.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_left_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLeft.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_left.png")));
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				editor.scroll(false);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				editor.scroll(false);
			}
		});
		btnLeft.setContentAreaFilled(false);
		btnLeft.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_left.png")));
		btnLeft.setOpaque(false);
		btnLeft.setFocusable(false);
		btnLeft.setBorderPainted(false);
		btnLeft.setBounds(900, 125, 50, 50);
		btnLeft.setToolTipText("Scroll Left");
		editor.add(btnLeft);
		
		btnRight = new JButton("");
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRight.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_right_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRight.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_right.png")));
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				editor.scroll(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				editor.scroll(true);
			}
		});
		btnRight.setContentAreaFilled(false);
		btnRight.setIcon(new ImageIcon(Frame.class.getResource("/imgs/arrow_right.png")));
		btnRight.setOpaque(false);
		btnRight.setFocusable(false);
		btnRight.setBorderPainted(false);
		btnRight.setBounds(950, 125, 50, 50);
		btnRight.setToolTipText("Scroll Right");
		editor.add(btnRight);
		
		btnExitEditor = new JButton("");
		btnExitEditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExitEditor.setIcon(new ImageIcon(Frame.class.getResource("/imgs/delete_button_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
					btnExitEditor.setIcon(new ImageIcon(Frame.class.getResource("/imgs/delete_button.png")));
			}
		});
		btnExitEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.exit();
			}
		});
		btnExitEditor.setContentAreaFilled(false);
		btnExitEditor.setIcon(new ImageIcon(Frame.class.getResource("/imgs/delete_button.png")));
		btnExitEditor.setOpaque(false);
		btnExitEditor.setFocusable(false);
		btnExitEditor.setBorderPainted(false);
		btnExitEditor.setBounds(900, 0, 100, 100);
		btnExitEditor.setToolTipText("Return to Main Menu");
		editor.add(btnExitEditor);
		
		btnEraser = new JButton("");
		btnEraser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEraser.setIcon(new ImageIcon(Frame.class.getResource("/imgs/eraser_button_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(editor.currentTool==3))
					btnEraser.setIcon(new ImageIcon(Frame.class.getResource("/imgs/eraser_button.png")));
			}
		});
		btnEraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.selectTool(3);
			}
		});
		btnEraser.setContentAreaFilled(false);
		btnEraser.setIcon(new ImageIcon(Frame.class.getResource("/imgs/eraser_button.png")));
		btnEraser.setOpaque(false);
		btnEraser.setFocusable(false);
		btnEraser.setBorderPainted(false);
		btnEraser.setBounds(900, 200, 100, 100);
		btnEraser.setToolTipText("Erase Tool");
		editor.add(btnEraser);
		
		// Level Title
		
		txtLevelName = new JTextField();
		txtLevelName.setText("Untitled_Level_1");
		txtLevelName.setFont(new Font("Simplified Arabic", Font.BOLD, 16));
		txtLevelName.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtLevelName.setBackground(Color.GRAY);
		txtLevelName.setBounds(400, 12, 200, 25);
		txtLevelName.setForeground(Color.WHITE);
		editor.add(txtLevelName);
		
		// Level Save Button
		
		btnSave = new JButton("");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSave.setIcon(new ImageIcon(Frame.class.getResource("/imgs/save_button_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
					btnSave.setIcon(new ImageIcon(Frame.class.getResource("/imgs/save_button.png")));
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				editor.saveLevel();
				
			}
		});
		btnSave.setContentAreaFilled(false);
		btnSave.setIcon(new ImageIcon(Frame.class.getResource("/imgs/save_button.png")));
		btnSave.setOpaque(false);
		btnSave.setFocusable(false);
		btnSave.setBorderPainted(false);
		btnSave.setBounds(900, 300, 100, 100);
		btnSave.setToolTipText("Save Level");
		editor.add(btnSave);
		
		// Play Level Button
		
		btnEditorPlay = new JButton("");
		btnEditorPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditorPlay.setIcon(new ImageIcon(Frame.class.getResource("/imgs/editor_play_button_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
					btnEditorPlay.setIcon(new ImageIcon(Frame.class.getResource("/imgs/editor_play_button.png")));
			}
		});
		btnEditorPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				editor.runLevel();
				
			}
		});
		btnEditorPlay.setContentAreaFilled(false);
		btnEditorPlay.setIcon(new ImageIcon(Frame.class.getResource("/imgs/editor_play_button.png")));
		btnEditorPlay.setOpaque(false);
		btnEditorPlay.setFocusable(false);
		btnEditorPlay.setBorderPainted(false);
		btnEditorPlay.setBounds(900, 550, 100, 100);
		btnEditorPlay.setToolTipText("Play Level");
		editor.add(btnEditorPlay);
		
		// Create Goomba Tool
		
		btnGoomba = new JButton("");
		btnGoomba.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGoomba.setIcon(new ImageIcon(Frame.class.getResource("/imgs/goomba_button_selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(editor.currentTool==4))
					btnGoomba.setIcon(new ImageIcon(Frame.class.getResource("/imgs/goomba_button.png")));
			}
		});
		btnGoomba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.selectTool(4);
			}
		});
		btnGoomba.setContentAreaFilled(false);
		btnGoomba.setIcon(new ImageIcon(Frame.class.getResource("/imgs/goomba_button.png")));
		btnGoomba.setOpaque(false);
		btnGoomba.setFocusable(false);
		btnGoomba.setBorderPainted(false);
		btnGoomba.setBounds(0, 250, 100, 100);
		btnGoomba.setToolTipText("Goomba");
		editor.add(btnGoomba);

		// Start Main Menu

		btnLevel1 = new JButton("");
		btnLevel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				level1();
			}
		});
		btnLevel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnLevel1.setIcon(new ImageIcon(Frame.class.getResource("/imgs/1_70.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLevel1.setIcon(new ImageIcon(Frame.class.getResource("/imgs/1_50.png")));
			}
		});
		btnLevel1.setFocusable(false);
		btnLevel1.setIcon(new ImageIcon(Frame.class.getResource("/imgs/1_50.png")));
		btnLevel1.setContentAreaFilled(false);
		btnLevel1.setOpaque(false);
		btnLevel1.setBorderPainted(false);
		btnLevel1.setBounds(267, 150, 66, 81);
		mainMenu.add(btnLevel1);
		
		lblMainMenuTitle = new JLabel("");
		lblMainMenuTitle.setIcon(new ImageIcon(Frame.class.getResource("/imgs/levels_title.png")));
		lblMainMenuTitle.setBounds(394, 57, 228, 69);
		mainMenu.add(lblMainMenuTitle);
		
		btnLevel2 = new JButton("");
		btnLevel2.setFocusable(false);
		btnLevel2.setIcon(new ImageIcon(Frame.class.getResource("/imgs/2_gray.png")));
		btnLevel2.setOpaque(false);
		btnLevel2.setContentAreaFilled(false);
		btnLevel2.setBorderPainted(false);
		btnLevel2.setBounds(392, 150, 66, 81);
		mainMenu.add(btnLevel2);
		
		btnLevel3 = new JButton("");
		btnLevel3.setFocusable(false);
		btnLevel3.setIcon(new ImageIcon(Frame.class.getResource("/imgs/3_gray.png")));
		btnLevel3.setOpaque(false);
		btnLevel3.setContentAreaFilled(false);
		btnLevel3.setBorderPainted(false);
		btnLevel3.setBounds(517, 150, 66, 81);
		mainMenu.add(btnLevel3);
		
		btnLevel4 = new JButton("");
		btnLevel4.setIcon(new ImageIcon(Frame.class.getResource("/imgs/4_gray.png")));
		btnLevel4.setOpaque(false);
		btnLevel4.setFocusable(false);
		btnLevel4.setContentAreaFilled(false);
		btnLevel4.setBorderPainted(false);
		btnLevel4.setBounds(642, 150, 66, 81);
		mainMenu.add(btnLevel4);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Frame.class.getResource("/imgs/level_builder.png")));
		label_3.setBounds(282, 289, 439, 69);
		mainMenu.add(label_3);
		
		btnNewEditorLevel = new JButton("");
		btnNewEditorLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createNewLevel();
			}
		});
		btnNewEditorLevel.setIcon(new ImageIcon(Frame.class.getResource("/imgs/new2.png")));
		btnNewEditorLevel.setOpaque(false);
		btnNewEditorLevel.setFocusable(false);
		btnNewEditorLevel.setContentAreaFilled(false);
		btnNewEditorLevel.setBorderPainted(false);
		btnNewEditorLevel.setBounds(337, 376, 153, 81);
		mainMenu.add(btnNewEditorLevel);
		
		btnLoadEditorLevel = new JButton("");
		btnLoadEditorLevel.setIcon(new ImageIcon(Frame.class.getResource("/imgs/load2.png")));
		btnLoadEditorLevel.setOpaque(false);
		btnLoadEditorLevel.setFocusable(false);
		btnLoadEditorLevel.setContentAreaFilled(false);
		btnLoadEditorLevel.setBorderPainted(false);
		btnLoadEditorLevel.setBounds(497, 371, 159, 86);
		mainMenu.add(btnLoadEditorLevel);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Frame.class.getResource("/imgs/title4.png")));
		label_2.setBounds(229, 11, 571, 322);
		splash.add(label_2);
		
		btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startGame();
			}
		});
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnPlay.setIcon(new ImageIcon(Frame.class.getResource("/imgs/play_button_20.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPlay.setIcon(new ImageIcon(Frame.class.getResource("/imgs/play_button.png")));
			}
		});
		btnPlay.setOpaque(false);
		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setIcon(new ImageIcon(Frame.class.getResource("/imgs/play_button.png")));
		btnPlay.setBounds(333, 405, 333, 111);
		splash.add(btnPlay);
		
		panel.setBackground(new Color(102, 153, 255));
		contentPane.add(panel, "name_593975429628557");
		panel.setLayout(null);
		
		JLabel lblLevel = new JLabel("Level 1");
		lblLevel.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLevel.setBounds(10, 11, 86, 23);
		panel.add(lblLevel);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Frame.class.getResource("/imgs/one_up_pixel.png")));
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		label.setBounds(247, 11, 38, 23);
		panel.add(label);
		
		lblLives = new JLabel("3");
		lblLives.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLives.setForeground(Color.WHITE);
		lblLives.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblLives.setBounds(275, 11, 30, 23);
		panel.add(lblLives);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Frame.class.getResource("/imgs/coin2.png")));
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		label_1.setBounds(375, 11, 30, 23);
		panel.add(label_1);
		
		lblCoins = new JLabel("0");
		lblCoins.setHorizontalAlignment(SwingConstants.LEFT);
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblCoins.setBounds(410, 11, 80, 23);
		panel.add(lblCoins);
		
		splash.setVisible(true);
		panel.setVisible(false);
		editor.setVisible(false);
		helpText.setVisible(false);
	}
	
	public void changePanel(int choice)
	{
		boolean[] values = {false,
							false,
							false,
							false,
							false};
		
		values[choice] = true;
		
		splash.setVisible(values[0]);
		editor.setVisible(values[1]);
		panel.setVisible(values[2]);
		helpText.setVisible(values[3]);
		mainMenu.setVisible(values[4]);
		
	}
	
	public void startGame()
	{
		splash.timer.stop();
		mainMenu.timer.start();
		changePanel(4);
		themeSong.play();
	}
	
	public void gameOver()
	{
		System.out.println("Game over");
		panel.lives=3;
		panel.coins=0;
		panel.paddle.width = 200;

		panel.timerDelay=3;
		panel.ballTimer.setDelay(panel.timerDelay);

		lblLives.setText(Integer.toString(panel.lives));
		lblCoins.setText(Integer.toString(panel.coins));
		
		editor.blocks = panel.blocks;
		
		panel.blocks.clear();
		panel.goombas.clear();
		panel.clouds.clear();
		
		if(panel.playingFromEditor)
		{
			changePanel(4); //editor
			
			editor.requestFocus();
		} else {
			changePanel(4); //main menu
			
			mainMenu.timer.start();
			mainMenu.requestFocus();
		}
	}
	
	public void levelComplete()
	{
		panel.paddle.width = 200;
		panel.timerDelay=3;
		panel.ballTimer.setDelay(panel.timerDelay);
		changePanel(4); //main menu
		mainMenu.timer.start();
		mainMenu.requestFocus();
	}
	
	public void startLevel()
	{
		mainMenu.timer.stop();
		helpText.displayText(1);
		
		
		mainMenu.setVisible(false);
		splash.setVisible(false);
		editor.setVisible(false);
		panel.setVisible(true);
		helpText.setVisible(true);
		
		panel.requestFocus();
	}
	
	public void level1()
	{
		for(int row=0;row<14;row++) {
			for(int col=0;col<151;col++) {
				if(level1[row][col] == 1)
					blocks.add(new Block(col*50, row*50,1));
				if(level1[row][col] == 2)
					blocks.add(new Block(col*50, row*50,2));
				if(level1[row][col] == 3)
					clouds.add(new Cloud(col*50, row*50,1));
				if(level1[row][col] == 4)
					goombas.add(new Goomba(col*50, row*50,panel));
				if(level1[row][col] == 5)
					blocks.add(new Block(col*50, row*50,3));
				if(level1[row][col] == 6)
					blocks.add(new Block(col*50, row*50,4));
				if(level1[row][col] == 7)
					blocks.add(new Block(col*50, row*50,5));
				if(level1[row][col] == 8)
					blocks.add(new Block(col*50, row*50,6));
				
				if(col%10 == 0)
				{
					System.out.println("tick");

				}
					
			}
		}
		panel.setLevel(blocks, clouds, goombas);
		panel.playingFromEditor = false;
		startLevel();
	}
	
	public void createNewLevel()
	{
		changePanel(1); //editor
		
		editor.newLevel();
	}
}



























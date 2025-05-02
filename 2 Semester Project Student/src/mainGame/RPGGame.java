package mainGame;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;

public class RPGGame extends JFrame {
	JPanel mainPanel;
	GamePanel gamePanel;
	CardLayout cardLayout; 
	OverworldData data;
	Clip clip;
	String heroName;
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	
	public static void main(String[] args) {
		RPGGame game = new RPGGame();

		game.setUpMainContainerPanel();

		game.setContentPane(game.mainPanel);
		game.setSize(RPGGame.WIDTH, RPGGame.HEIGHT);
		game.setLocation(100, 100);
		game.setVisible(true);
		game.setResizable(false); // This is fine for the game, keep it simple. 
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setUpMainContainerPanel() {
		//set content pane as window (card view)
		mainPanel = new JPanel();  // just a CONTAINER...Regular JPanel is fine...don't need a custom paintComponent()
		
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);
		JPanel titleScreenPanel = setUpTitleScreen(); 
		JPanel secondScreenPanel = setUpSecondScreen(); 
		
		data = new OverworldData(heroName); 
		gamePanel = new GamePanel(data);
	
		mainPanel.add(titleScreenPanel, "TitleScreen");	
		mainPanel.add(secondScreenPanel, "SecondScreen");
		mainPanel.add(gamePanel, "GamePanel");
		cardLayout.show(mainPanel, "TitleScreen");
	}
	
	public JPanel setUpTitleScreen() {
		playMusic("terra.wav");
		JPanel title = new JPanel();  
		
		title.setLayout(new BorderLayout());
		
		JLabel gameText = createJLabelWithImageAndText("fantasyCastle.jpg", "The Adventure of a Lifetime!", Color.WHITE, 36);
		
		title.add(gameText, BorderLayout.CENTER);
		JPanel titleScreenSouth = new JPanel(); // default flowLayout
		
		JLabel nameLabel = new JLabel("Hero, enter thy name and push enter: ");
		JTextField nameField = new JTextField(10);  
		nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroName = nameField.getText();   // Must set name in the Listener because you don't know WHEN
				data.getHero().setName(heroName); // the user will push enter -- this is event-driven programming
				gamePanel.getNameDisplay().setText("***Hero: " + heroName + "***");
				cardLayout.show(mainPanel, "SecondScreen");
			}
		});
		
		titleScreenSouth.add(nameLabel);
		titleScreenSouth.add(nameField);
		title.add(titleScreenSouth, BorderLayout.SOUTH);
		
		return title;
	}
	
	public JPanel setUpSecondScreen() {
		JPanel second = new JPanel();  
		
		second.setLayout(new BorderLayout());
		
		String textToDisplay = "<html>You are on a quest. <br> Monsters have invaded the land. <br>"
				+ " You must defeat the monsters. <br> and arrive at the castle to save your people <br>  Good luck!</html>";
		JLabel introText = createJLabelWithImageAndText("castle.jpg", textToDisplay, Color.BLACK, 28);
		
		second.add(introText, BorderLayout.CENTER);
		
		JButton onward = new JButton("Onward!");
		onward.addActionListener(new ActionListener() {  // Use Anonyomous inner class 
			public void actionPerformed(ActionEvent e) { // create a new object (that has no name).  It implements
				cardLayout.show(mainPanel, "GamePanel"); // ActionListener interface, and we define what is does inside the block.
				gamePanel.requestFocusInWindow(); 		 // ** so that gamePanel can receive key inputs 
			}
		});
	
		second.add(onward, BorderLayout.SOUTH);
		return second;
	}
	
	public JLabel createJLabelWithImageAndText(String fileName, String text, Color color, int fontSize) {
		// http://stackoverflow.com/questions/20617695/cant-dispay-text-on-jlabel-having-background-image
		// http://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
		JLabel label = null;  
		try {
			Image i = ImageIO.read(new File(fileName));
			label = new JLabel(new ImageIcon(i.getScaledInstance(RPGGame.WIDTH, RPGGame.HEIGHT, 0)));
		} catch (IOException e) {
			e.printStackTrace();
			label = new JLabel(text);
		}
		label.setFont(new Font("Courier", Font.BOLD, fontSize));
		label.setForeground(color);
		label.setText(text);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		return label;
	}
	
	public void playMusic(String fileName) {
		// https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
		try {
			File soundFile = new File(fileName); // Open an audio input stream from a wave File
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip(); // Get a sound clip resource.
			clip.open(audioIn); // Open audio clip and load samples from the audio input stream.
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
	
}

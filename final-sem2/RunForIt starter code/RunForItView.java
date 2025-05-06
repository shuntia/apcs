import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class RunForItView extends JFrame {
	
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private AnimationPanel animationPanel;
	private JButton[] difficultyButtons;
	private JButton mainMenuButton;
	private ArrayList<Runner> runners = new ArrayList<Runner>();
	private int score = 0;
	private int numEscapedRunners = 0;
	private JLabel numEscapedRunnersLabel;
	private JLabel messageLabel;
	private JLabel scoreLabel;
	
	public RunForItView() {
		super("Run For It!");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		this.setContentPane(mainPanel);
		
		WelcomePanel welcomePanel = new WelcomePanel();
		mainPanel.add(welcomePanel, "WelcomePanel");
		JPanel buttonPanel = new JPanel();
		welcomePanel.add(buttonPanel);
		buttonPanel.add(new JLabel("Choose your level of difficulty"));
		String[] buttonLabels = {"Easy", "Medium", "Hard"};
		difficultyButtons = new JButton[buttonLabels.length];
		for (int i = 0; i < buttonLabels.length; i++) {
			difficultyButtons[i] = new JButton(buttonLabels[i]);
			buttonPanel.add(difficultyButtons[i]);
		}
		
		JPanel gamePanel = new JPanel(new BorderLayout());
		mainPanel.add(gamePanel, "GamePanel");
		JPanel statsPanel = new JPanel();
		gamePanel.add(statsPanel, BorderLayout.NORTH);
		
		numEscapedRunnersLabel = new JLabel("Number of escaped runners: " + numEscapedRunners);
		numEscapedRunnersLabel.setFont(new Font("Courier", Font.BOLD, 24));
		statsPanel.add(numEscapedRunnersLabel);
		statsPanel.add( Box.createHorizontalStrut(50) );
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setFont(new Font("Courier", Font.BOLD, 24));
		statsPanel.add(scoreLabel);

		animationPanel = new AnimationPanel();
		gamePanel.add(animationPanel, BorderLayout.CENTER);
		mainMenuButton = new JButton("Main Menu");
		animationPanel.add(mainMenuButton);
		mainMenuButton.setVisible(false);
		
		cardLayout.show(mainPanel, "WelcomePanel");
	}
	
	public void showPanel(String panelName) {
		cardLayout.show(mainPanel, panelName);
	}
	
	public int getAnimationPanelWidth() {
		return animationPanel.getWidth();
	}
	
	public int getAnimationPanelHeight() {
		return animationPanel.getHeight();
	}
	
	public void setRunners(ArrayList<Runner> runners) {
		this.runners = runners;
	}
	
	public void setStats(RunForItStats stats) {
		score = stats.score;
		numEscapedRunners = stats.numEscapedRunners;
		scoreLabel.setText("Score: " + score);
		numEscapedRunnersLabel.setText("Number of escaped runners: " + numEscapedRunners);
		repaint();
	}
	
	public void setMainMenuButtonVisible(boolean visible) {
		mainMenuButton.setVisible(visible);
	}
	public void addActionListener(ActionListener listener) {
		for (JButton button : difficultyButtons) {
			button.addActionListener(listener);
		}
		mainMenuButton.addActionListener(listener);
	}
	
	@Override
	public void addMouseListener(MouseListener listener) {
		animationPanel.addMouseListener(listener);
	}
	
	private class WelcomePanel extends JPanel {
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(122, 236, 22));
			g.fillRect(getWidth() / 10, getHeight() / 10, 
					getWidth() * 8 / 10, getHeight() * 8 / 10);
			g.setColor(new Color(255,121,255));
			g.setFont(new Font("Courier", Font.BOLD, 64));
			g.drawString("Run For It!", 
					(getWidth() - g.getFontMetrics().stringWidth("Run For It!")) / 2, 
					getHeight() / 2);
		}
	}
	
	private class AnimationPanel extends JPanel {
		
		@Override
		public void paintComponent(Graphics g) {

			for (Runner runner : runners) {
				//This code uses the runner's memory address to generate a color
				//I could have also created a color member variable in each Runner
				int rgb = runner.hashCode();
				int red = (rgb >> 16) & 0xFF;
				int green = (rgb >> 8) & 0xFF;
				int blue = rgb & 0xFF;
				g.setColor(new Color(red, green, blue));
				g.fillRect(runner.getX(), runner.getY(), 
						runner.getWidth(), runner.getHeight());
				g.setColor(Color.BLACK);
				g.drawRect(runner.getX(), runner.getY(), 
						runner.getWidth(), runner.getHeight());
			}
		}
	}
	
	
}

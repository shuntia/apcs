package mainGame;

import gameEntities.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// View.
// Presentation of the Model.  Gets state and data it needs directly from the Model 
// Controller can tell it to change its display.
public class GamePanel extends JPanel {  
		private JLabel nameDisplay;
		private JLabel hpDisplay;  
	    private JLabel mpDisplay;  
	    private JLabel expDisplay;  
	    private JLabel levelDisplay;  
	    
	    private OverworldData model;
	    private GamePanelListener controller;
	    private CardLayout cardLayout;
	    private JPanel statsPanel;
	    
	    public GamePanel(OverworldData d) {
	    	model = d;
	    	controller = new GamePanelListener(model, this);
	    	this.addKeyListener(controller);
	    	this.addFocusListener(controller);
	    	this.addMouseListener(controller);
	
		    /* Create the components and add them to the panel. */
		    nameDisplay = new JLabel("***Hero: " + model.getHero().getName() + "***");
	    	hpDisplay = new JLabel("Health Points: " + model.getHero().getHp());
	    	mpDisplay = new JLabel("Magic Points: " + model.getHero().getMp());
	    	expDisplay = new JLabel("Experience Points: " + model.getHero().getExperiencePoints());
	    	levelDisplay = new JLabel("Level: " + model.getHero().getLevel());
	    	
	    	this.setLayout(new BorderLayout());
	    	statsPanel = new JPanel();
	    	statsPanel.add(nameDisplay);
	    	statsPanel.add(hpDisplay);
	    	statsPanel.add(mpDisplay);
	    	statsPanel.add(expDisplay);
	    	statsPanel.add(levelDisplay);
	        this.add(statsPanel, BorderLayout.NORTH);
	        model.registerView(this);
	        
	        
	        OverworldPanel overworldPanel = new OverworldPanel();  
	        BattlePanel battlePanel = new BattlePanel();
	        JPanel overworldAndBattlePanel = new JPanel();  // Added to allow switching between battle and map (not implemented)
	        
	        cardLayout = new CardLayout();
	        overworldAndBattlePanel.setLayout(cardLayout);
	        overworldAndBattlePanel.add(overworldPanel, "MapScreen");	
	        overworldAndBattlePanel.add(battlePanel, "BattleScreen");
	    	cardLayout.show(overworldAndBattlePanel, "MapScreen");
	        this.add(overworldAndBattlePanel, BorderLayout.CENTER);
	        

	    } // end constructor
	    
	 
	    private class OverworldPanel extends JPanel {
	    	
	        public void paintComponent(Graphics g) {
	            /* Draw the squares of the hero, enemies, tree, and castle. */
	        	int rows = model.getNumRowsInGameWorld();
	        	int cols = model.getNumColsInGameWorld();
	        	int widthOfEachSquare = RPGGame.WIDTH / cols;	
	        	//have to account for height of statsPanel + space of each line from both squares
	        	int heightOfEachSquare = (RPGGame.HEIGHT - statsPanel.getHeight()) / rows - 2; 
	        	
	        	
	        	GameEntity[][] entities = model.getGameEntities();
	            for (int i = 0; i < rows; i++) {
	                for (int j = 0; j < cols; j++) {
	                	g.setColor(Color.GREEN);
	                    g.fillRect(j*widthOfEachSquare, i*heightOfEachSquare, widthOfEachSquare, heightOfEachSquare);
	                	g.setColor(Color.BLACK);
	                	g.drawRect(j*widthOfEachSquare, i*heightOfEachSquare, widthOfEachSquare, heightOfEachSquare);	                	
	             
	                	g.drawImage(entities[i][j].getImage(),j*widthOfEachSquare, i*heightOfEachSquare, widthOfEachSquare, heightOfEachSquare, null);
	                }
	            }
	        }
	    }
	    
	    public JLabel getNameDisplay() {
			return nameDisplay;
		}


		public void setNameDisplay(JLabel nameDisplay) {
			this.nameDisplay = nameDisplay;
		}

		// This way Model doesn't need an explicit reference to View, it just says 'OK I've changed, notify!'
		// and then I go and do the specific thing for this View to update (e.g. update the text() after pulling 
		// the state from the model
		public void updateStatsPanel() {
			JPanel fightPanel = new JPanel();
			this.add(fightPanel, BorderLayout.CENTER);
			hpDisplay.setText("Health Points: " + model.getHero().getHp());
			mpDisplay.setText("Magic Points: " + model.getHero().getMp());
		    expDisplay.setText("Experience Points: " + model.getHero().getExperiencePoints());
		    levelDisplay.setText("Level: " + model.getHero().getLevel());
		}

}

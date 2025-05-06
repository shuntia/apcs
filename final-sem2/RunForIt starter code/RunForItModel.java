import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

public class RunForItModel implements ActionListener {

	private static double PERCENTAGE_OF_PLAY_AREA_WIDTH_USED_FOR_SPAWNING = 0.6;
	private static double PERCENTAGE_OF_PLAY_AREA_HEIGHT_USED_FOR_SPAWNING = 0.6;
	private static int RUNNER_WIDTH = 30, RUNNER_HEIGHT = 30;
	private static int NUM_RUNNERS_ESCAPED_FOR_GAME_OVER = 20;
	private int playAreaWidth;
	private int playAreaHeight;
	private ArrayList<StatsObserver> statsObservers;
	private ArrayList<RunnerObserver> runnerObservers;
	private ArrayList<Runner> runners;
	private 	Timer runnerCreationTimer;
	private Timer updatePositionsTimer;
	private int score = 0;
	private int numEscaped = 0;
	private boolean isGameOver;
	
// The Model is the "Subject" in the Observer pattern. 
	public RunForItModel() {
		statsObservers = new ArrayList<StatsObserver>();
		runnerObservers = new ArrayList<RunnerObserver>();
		runners = new ArrayList<Runner>();
		updatePositionsTimer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRunnerPositions();
			}
		});
	}
	
	public void setupNewGame(int timeBetweenRunnerCreation, int playAreaWidth, int playAreaHeight) {
		this.playAreaWidth = playAreaWidth;
		this.playAreaHeight = playAreaHeight;
		runnerCreationTimer = new Timer(timeBetweenRunnerCreation, this);
		runners.clear();
		score = 0;
		numEscaped = 0;
		isGameOver = false;
	}

	public void startGame() {
		runnerCreationTimer.start();
		updatePositionsTimer.start();
	}
	public void resumeGame() {
		if (runnerCreationTimer != null)
			runnerCreationTimer.start();
	}
	
	public void pauseGame() {
		if (runnerCreationTimer != null)
			runnerCreationTimer.stop();
	}
	
	public void gameOver() {
		isGameOver = true;
		runnerCreationTimer.stop();
		runnerCreationTimer = null;
		updatePositionsTimer.stop();
		notifyStatsObservers();
	}
	
	public boolean catchRunner(int x, int y) {
		for (Runner runner : runners) {
			if (runner.containsPoint(x, y)) {
				runner.setCaught(true);
				score += runner.getSpeed() * 10;
				notifyStatsObservers();
				return true;
			}
		}
		return false;
	}
	
	public void updateRunnerPositions() {
		Iterator iterator = runners.iterator();
		while (iterator.hasNext()) {
			Runner runner = (Runner)iterator.next();
			runner.updatePosition();
			int x = runner.getX();
			int y = runner.getY();
			int w = runner.getWidth();
			int h = runner.getHeight();
			if (x + w < 0 || x > playAreaWidth || y + h < 0 || y > playAreaHeight) {
				iterator.remove();
				numEscaped++;
				notifyStatsObservers();
			}
			else if (runner.isCaught()) {
				iterator.remove();
			}
		}
		notifyRunnerObservers();
		if (numEscaped >= NUM_RUNNERS_ESCAPED_FOR_GAME_OVER)
			gameOver();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double percentageX = PERCENTAGE_OF_PLAY_AREA_WIDTH_USED_FOR_SPAWNING;
		double percentageY = PERCENTAGE_OF_PLAY_AREA_HEIGHT_USED_FOR_SPAWNING;
		int x = (int)(Math.random() * percentageX * playAreaWidth 
				 	  + playAreaWidth * (1 - percentageX) / 2);
		int y = (int)(Math.random() * percentageY * playAreaHeight
			 	  + playAreaHeight * (1 - percentageY) / 2);
		int direction = (int)(Math.random() * 4 - 1);
		int speed = (int)(Math.random() * 10 + 1);
		runners.add(new Runner(x, y, RUNNER_WIDTH, RUNNER_HEIGHT, direction, speed));
		notifyRunnerObservers();
		
	}
	public void registerStatsObserver(StatsObserver observer) {
		statsObservers.add(observer);
	}
		
	public void registerRunnerObserver(RunnerObserver observer) {
		runnerObservers.add(observer);
	}

	// Notice the ***loose coupling*** of the Observer pattern. Model doesn't even know that 
	// the Observer is the Controller (could add more Observers later)
	public void notifyStatsObservers() {
		for (StatsObserver observer : statsObservers) {
			observer.statsChanged();
		}
	}
	
	public void notifyRunnerObservers() {
		for (RunnerObserver observer : runnerObservers) {
			observer.runnersChanged();
		}
	}
	
	public RunForItStats getStats() {
		return new RunForItStats(score, numEscaped, isGameOver);
	}
	
	public ArrayList<Runner> getRunners() {
		return runners;
	}
	
}

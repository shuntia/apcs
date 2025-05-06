import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Controller is an Observer (but not the main identify of the Controller)
public class RunForItController implements ActionListener, MouseListener,
								RunnerObserver , StatsObserver {

	private RunForItModel model;
	private RunForItView view;
	
	public RunForItController(RunForItModel model, RunForItView view) {
		this.model = model;
		this.view = view;
		model.registerRunnerObserver(this);
		model.registerStatsObserver(this);
		view.addActionListener(this);
		view.addMouseListener(this);
	}

	@Override
	public void mousePressed(MouseEvent ev) {
		model.catchRunner(ev.getX(), ev.getY());
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		String command = ev.getActionCommand();
		if (command.equals("Main Menu")) {
			view.showPanel("WelcomePanel");
			view.setMainMenuButtonVisible(false);
			return;
		}
		if (command.equals("Easy")) {
			model.setupNewGame(1000, view.getAnimationPanelWidth(), 
									 view.getAnimationPanelHeight());
		}
		else if (command.equals("Medium")) {
			model.setupNewGame(800, view.getAnimationPanelWidth(), 
					 view.getAnimationPanelHeight());
		}
		else if (command.equals("Hard")) {
			model.setupNewGame(500, view.getAnimationPanelWidth(), 
					 view.getAnimationPanelHeight());
		}
		model.startGame();
		view.showPanel("GamePanel");

	}
	
	//*** Update view with new stats.
	// Don't do continuously.  Let Model notify me, the Observer, when
	// it is time to grab stats.  This is the PULL method
	@Override
	public void statsChanged() {
		RunForItStats stats = model.getStats();
		if (stats.isGameOver) {
			view.setMainMenuButtonVisible(true);
		}
		view.setStats(model.getStats());
		view.repaint();
		
	}

	@Override
	public void runnersChanged() {
		view.setRunners(model.getRunners());
		view.repaint();
		
	}
	@Override
	public void mouseClicked(MouseEvent ev) {}

	@Override
	public void mouseEntered(MouseEvent ev) {}

	@Override
	public void mouseExited(MouseEvent ev) {}

	@Override
	public void mouseReleased(MouseEvent ev) {}



}

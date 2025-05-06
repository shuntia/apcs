public class RunForItMain {
	public static void main(String[] args) {
		RunForItModel model = new RunForItModel();
		RunForItView view = new RunForItView();
		new RunForItController(model, view);
		view.setSize(800, 600);
		view.setLocation(100, 100);
		view.setVisible(true);
	}

}

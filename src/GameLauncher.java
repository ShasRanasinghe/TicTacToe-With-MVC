
public class GameLauncher {

	public static void main(String[] args) {
		View view = new View();
		
		Model model = new Model();
		model.addObserver(view);
		
		Controller controller = new Controller(model,view);
		
		view.setController(controller);

	}

}

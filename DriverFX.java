
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class DriverFX extends Application {

	private static final double CANVAS_WIDTH = 600;
	private static final double CANVAS_HEIGHT = 3500;
	private static final double WINDOW_HEIGHT = 350;
	public static void main(String[] args) {
		launch();
	}

	@Override
	/**
	 * start is required by the class Application and is called by launch
	 * It initializes MainPaneFX, which returns the main panel
	 */
	public void start(Stage stage) throws Exception {
		MainPaneFX mainPane = new MainPaneFX(CANVAS_WIDTH, CANVAS_HEIGHT);
		BorderPane root = mainPane.getMainPane();
		Scene scene = new Scene(root, CANVAS_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Ship Registry");
		stage.show();
	   }

}

package foury.gui;

import foury.data.AppState;
import foury.data.ImageData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

	ImageData imageData = null;
	AppState appState =null;

	@FXML
	private VBox topPane;
	@FXML
	private VBox leftPane;
	@FXML
	private VBox rightPane;

	public MenuArea menuArea;
	private DisplayArea displayArea;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		appState = AppState.getCurrentState();
		imageData = new ImageData();

		displayArea = new DisplayArea(imageData, appState);
		leftPane.getChildren().add(displayArea);

		menuArea = new MenuArea(imageData, appState);
		topPane.getChildren().add(menuArea);

		displayArea.setTabNo(4);

		appState.setImagePaneNo(2);

	}
}

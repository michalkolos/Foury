package foury.gui;

import foury.actions.FourierAction;
import foury.data.AppState;
import foury.data.ImageData;
import foury.gui.widgets.ImageTileWidget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {



	ImageData imageData = null;
	AppState appState =null;

	@FXML
	private VBox centerPane;
	@FXML
	private VBox topPane;
	@FXML
	private VBox leftPane;
	@FXML
	private VBox rightPane;
	@FXML
	private VBox bottomPane;

	private MenuArea menuArea;
	private DisplayArea displayArea;
	private InfoArea infoArea;
	private ToolsArea toolsArea;

	private FourierAction fourierAction;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		appState = AppState.getCurrentState();
		imageData = new ImageData();

		fourierAction = new FourierAction(imageData, appState);

		displayArea = new DisplayArea(imageData, appState);
		centerPane.getChildren().add(displayArea);

		menuArea = new MenuArea(imageData, appState);
		topPane.getChildren().add(menuArea);

		infoArea = new InfoArea(imageData, appState);
		bottomPane.getChildren().add(infoArea);

//		toolsArea = new ToolsArea(imageData, appState);
//		rightPane.getChildren().add(toolsArea);




		imageData.readyToDisplayProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				System.out.println("ReadyToDisplay listener triggered");

				displayArea.updateImages();


				imageData.setReadyToDisplay(false);
			}
		});

		imageData.readyToCalculateProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				System.out.println("ReadyToCalculate listener triggered");

				fourierAction.transform();

				imageData.setReadyToCalculate(false);
			}
		});

		imageData.readyToCalculateSelectionProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				System.out.println("ReadyToCalculateSelection listener triggered");

				fourierAction.transformSelections();

				imageData.setReadyToCalculateSelection(false);
			}
		});

	}
}

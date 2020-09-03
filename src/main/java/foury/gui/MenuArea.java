package foury.gui;

import foury.actions.FileAction;
import foury.data.AppState;
import foury.data.ImageData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class MenuArea extends Area {

	public MenuArea(ImageData imageData, AppState appState){
		super(imageData, appState);
	}


	@FXML
	public void initialize() {

	}

	@FXML
	private void handleOpenFile(final ActionEvent actionEvent) {
		FileAction fileAction = new FileAction(imageData, appState);
		fileAction.open();
	}

	@FXML
	private void handleSaveMagnitude(final ActionEvent actionEvent) {
		FileAction fileAction = new FileAction(imageData, appState);
		fileAction.saveMagnitude();
	}

	@FXML
	private void handleExitAction(ActionEvent actionEvent) {
		Platform.exit();
	}

	@FXML
	private void handleSwitchToOneTile(final ActionEvent actionEvent) {
		appState.setImagePaneNo(1);
	}

	@FXML
	private void handleSwitchToTwoTiles(final ActionEvent actionEvent) {
		appState.setImagePaneNo(2);
	}

	@FXML
	private void handleSwitchToThreeTiles(final ActionEvent actionEvent) {
		appState.setImagePaneNo(3);
	}

	@FXML
	private void handleSwitchToFourTiles(final ActionEvent actionEvent) {
		appState.setImagePaneNo(4);
	}
}

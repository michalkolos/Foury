package foury.gui;

import foury.actions.FileAction;
import foury.data.AppState;
import foury.data.ImageData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;


public class MenuArea extends Area {

	public MenuArea(ImageData imageData, AppState appState){
		super(imageData, appState);
	}

	@FXML
	MenuItem saveMagnitudeOption;
	@FXML
	MenuItem saveSelectionOption;
	@FXML
	MenuItem saveOutsideOption;


	FileAction fileAction;

	@FXML
	public void initialize() {
		fileAction = new FileAction(imageData, appState);

		imageData.readyToDisplayProperty().addListener((obs, oldVal, newVal) -> {
			if(imageData.getFourierMagnitudeImage() == null){
				saveMagnitudeOption.setDisable(true);
			}else{
				saveMagnitudeOption.setDisable(false);
			}

			if(imageData.getOutputInnerImage() == null){
				saveSelectionOption.setDisable(true);
			}else{
				saveSelectionOption.setDisable(false);
			}

			if(imageData.getOutputOuterImage() == null){
				saveOutsideOption.setDisable(true);
			}else{
				saveOutsideOption.setDisable(false);
			}
		});
	}

	@FXML
	private void handleOpenFile(final ActionEvent actionEvent) {
		fileAction.open();
	}

	@FXML
	private void handleSaveMagnitude(final ActionEvent actionEvent) {
		fileAction.saveMagnitude();
	}

	@FXML
	private void handleSaveSelection(final ActionEvent actionEvent) {
		fileAction.saveSelection();
	}

	@FXML
	private void handleSaveOutside(final ActionEvent actionEvent) {
		fileAction.saveOutside();
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

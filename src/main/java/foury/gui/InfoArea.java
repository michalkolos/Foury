package foury.gui;

import foury.data.AppState;
import foury.data.ImageData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoArea extends Area {

	@FXML
	public Label label01;
	@FXML
	private Label label02;

	protected InfoArea(ImageData imageData, AppState appState) {
		super(imageData, appState);
	}

	@FXML
	public void initialize(){
		updateLabels();

		appState.fileNameProperty().addListener((obs, oldVal, newVal) -> {
		updateLabels();
		});

		appState.fileExtensionProperty().addListener((obs, oldVal, newVal) -> {
			updateLabels();
		});

		appState.filePathProperty().addListener((obs, oldVal, newVal) -> {
			updateLabels();
		});

		appState.imagePaneNoProperty().addListener((obs, oldVal, newVal) -> {
			updateLabels();
		});
	}

	public void updateLabels(){

		String dot = "";

		if(appState.getFileExtension().length() > 0){
			dot = ".";
		}

		label01.setText("Panels: " + appState.getImagePaneNo());
		label02.setText( appState.getFilePath() + appState.getFileName() + dot + appState.getFileExtension());
	}
}

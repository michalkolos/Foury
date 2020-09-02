package foury.gui.widgets;

import foury.data.AppState;
import foury.data.EditingRectangles;
import foury.data.ImageData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorWidget extends Widget {

	@FXML
	private VBox editorRoot;
	@FXML
	private ImageView editorImage;
	@FXML
	private VBox rectangleWidgetStack;
	@FXML
	private Button addButton;
	@FXML
	private Button finishButton;
	@FXML
	private Button cancelButton;
	@FXML
	private HBox editorLayerInactive;
	@FXML
	private HBox editorLayerActive;


	EditingRectangles rectangles;

	public EditorWidget(ImageData imageData, AppState appState) {
		super(imageData, appState);

		rectangles = new EditingRectangles();
	}




	@FXML
	private void initialize() {
		switchToInactiveLayer();
	}

	@FXML
	private void setAddButton(){
		switchToActiveLayer();
	}

	@FXML
	private void setCancelButton(){
		switchToInactiveLayer();
	}

	@FXML
	private void setFinishButton(){
		switchToInactiveLayer();
	}








	private void switchToActiveLayer(){
		editorLayerActive.setDisable(false);
		editorLayerActive.setVisible(true);

		editorLayerInactive.setDisable(true);
		editorLayerInactive.setVisible(false);
	}

	private void switchToInactiveLayer(){
		editorLayerActive.setDisable(true);
		editorLayerActive.setVisible(false);

		editorLayerInactive.setDisable(false);
		editorLayerInactive.setVisible(true);
	}



}

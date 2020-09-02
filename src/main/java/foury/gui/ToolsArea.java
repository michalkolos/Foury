package foury.gui;

import foury.data.AppState;
import foury.data.ImageData;
import foury.gui.widgets.BorderSelectWidget;
import foury.gui.widgets.EditorWidget;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SegmentedButton;

public class ToolsArea extends Area{

	@FXML
	private VBox root;

	BorderSelectWidget borderSelectWidget;
	SegmentedButton borderSegmentedButton;

	EditorWidget editorWidget;

	public ToolsArea(ImageData imageData, AppState appState) {
		super(imageData, appState);

		borderSelectWidget = new BorderSelectWidget(imageData, appState);
		root.getChildren().add(borderSelectWidget);

		borderSegmentedButton = new SegmentedButton();
		root.getChildren().add(borderSegmentedButton);

		editorWidget = new EditorWidget(imageData, appState);
		root.getChildren().add(editorWidget);
	}

	@FXML
	public void initialize() {

	}

}

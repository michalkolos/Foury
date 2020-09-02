package foury.gui.widgets;

import foury.data.AppState;
import foury.data.ImageData;
import foury.utils.MiscUtils;
import javafx.scene.layout.VBox;

public abstract class Widget extends VBox {

	ImageData imageData;
	AppState appState;

	public Widget(ImageData imageData, AppState appState) {
		this.imageData = imageData;
		this.appState = appState;
		MiscUtils.loadFXML(this);
	}

}

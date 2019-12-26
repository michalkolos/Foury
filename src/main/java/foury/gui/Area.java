package foury.gui;

import foury.data.AppState;
import foury.data.ImageData;
import foury.utils.MiscUtils;
import javafx.scene.layout.VBox;

import java.awt.*;

public class Area extends VBox {
	protected ImageData imageData;
	protected AppState appState;

	protected Area(ImageData imageData, AppState appState){
		this.imageData = imageData;
		this.appState = appState;
		MiscUtils.loadFXML(this);
	}

	public void setImageData(ImageData imageData) {
		this.imageData = imageData;

	}

	public void setAppState(AppState appState) {
		this.appState = appState;

	}
}

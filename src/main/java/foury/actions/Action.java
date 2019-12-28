package foury.actions;

import foury.data.AppState;
import foury.data.ImageData;

public class Action {
	protected ImageData imageData;
	protected AppState appState;

	public Action(ImageData imageData, AppState appState){
		this.imageData = imageData;
		this.appState = appState;
	}

	public void run(){

	}

	public void undo(){

	}
}


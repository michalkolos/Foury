package foury.data;

import javafx.beans.property.SimpleIntegerProperty;

public class AppState {
	private static AppState appStateObject = new AppState();

	private SimpleIntegerProperty imagePaneNo;


	private AppState(){
		imagePaneNo = new SimpleIntegerProperty(1);
	}





	public static AppState getCurrentState() {
		return appStateObject;
	}



	public int getImagePaneNo() {
		return imagePaneNo.get();
	}

	public SimpleIntegerProperty imagePaneNoProperty() {
		return imagePaneNo;
	}

	public void setImagePaneNo(int imagePaneNo) {
		this.imagePaneNo.set(imagePaneNo);
	}
}

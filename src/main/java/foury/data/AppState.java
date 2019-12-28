package foury.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AppState {
	private static AppState appStateObject = new AppState();

	private SimpleIntegerProperty imagePaneNo;

	private SimpleStringProperty fileName;
	private SimpleStringProperty fileExtension;
	private SimpleStringProperty filePath;



	private AppState(){

		imagePaneNo = new SimpleIntegerProperty(0);

		fileName = new SimpleStringProperty("noName");
		fileExtension = new SimpleStringProperty("");
		filePath = new SimpleStringProperty("~/");
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

	public static AppState getAppStateObject() {
		return appStateObject;
	}

	public static void setAppStateObject(AppState appStateObject) {
		AppState.appStateObject = appStateObject;
	}

	public String getFileName() {
		return fileName.get();
	}

	public SimpleStringProperty fileNameProperty() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName.set(fileName);
	}

	public String getFileExtension() {
		return fileExtension.get();
	}

	public SimpleStringProperty fileExtensionProperty() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension.set(fileExtension);
	}

	public String getFilePath() {
		return filePath.get();
	}

	public SimpleStringProperty filePathProperty() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath.set(filePath);
	}
}

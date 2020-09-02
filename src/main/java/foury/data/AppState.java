package foury.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.opencv.core.Core;
import org.opencv.core.CvType;

public class AppState {
	private static AppState appStateObject = new AppState();

	private SimpleIntegerProperty imagePaneNo;

	private SimpleStringProperty fileName;
	private SimpleStringProperty fileExtension;
	private SimpleStringProperty filePath;

	private SimpleIntegerProperty borderType;
	private SimpleIntegerProperty borderConstVal;

	private SimpleIntegerProperty matType;



	private AppState(){

		imagePaneNo = new SimpleIntegerProperty(0);

		fileName = new SimpleStringProperty("noName");
		fileExtension = new SimpleStringProperty("");
		filePath = new SimpleStringProperty("~/");

		borderType = new SimpleIntegerProperty(Core.BORDER_REFLECT);
		borderConstVal = new SimpleIntegerProperty(0);

		matType = new SimpleIntegerProperty(CvType.CV_8UC1);
	}





	// =================================================================================================================
	//                                              Getters and Setters:
	// =================================================================================================================


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


	public int getBorderType() { return borderType.get(); }
	public SimpleIntegerProperty borderTypeProperty() { return borderType; }
	public void setBorderType(int borderType) {	this.borderType.set(borderType); }

	public int getBorderConstVal() { return borderConstVal.get(); }
	public SimpleIntegerProperty borderConstValProperty() { return borderConstVal; }
	public void setBorderConstVal(int borderConstVal) { this.borderConstVal.set(borderConstVal); }

	public int getMatType() { return matType.get(); }
	public SimpleIntegerProperty matTypeProperty() { return matType; }
	public void setMatType(int matType) { this.matType.set(matType); }
}

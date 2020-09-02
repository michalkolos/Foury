package foury.actions;

import foury.data.AppState;
import foury.data.ImageData;
import foury.utils.ImageUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.opencv.core.CvType;

import java.io.File;

import static org.apache.commons.io.FilenameUtils.getExtension;

public class FileAction extends Action {

	public FileAction(ImageData imageData, AppState appState) {
		super(imageData, appState);
	}

	public void open(){

		FileChooser fileChooser = new FileChooser();

		File selectedFile = fileChooser.showOpenDialog(new Stage());
		Image image  = new Image(selectedFile.toURI().toString());

		String fullFileName = selectedFile.getName();

		appState.setFilePath(FilenameUtils.getPath(selectedFile.toURI().toString()));
		appState.setFileName(FilenameUtils.getBaseName(selectedFile.toURI().toString()));
		appState.setFileExtension(FilenameUtils.getExtension(selectedFile.toURI().toString()));

		appState.setMatType(CvType.CV_8UC1);

		imageData.setOriginalImage(image);
		appState.setImagePaneNo(1);
	}
}

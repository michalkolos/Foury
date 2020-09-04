package foury.actions;

import foury.data.AppState;
import foury.data.ImageData;
import foury.utils.ImageUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FilenameUtils.getExtension;

public class FileAction extends Action {

	public FileAction(ImageData imageData, AppState appState) {
		super(imageData, appState);
	}

	public void open(){

		FileChooser fileChooser = new FileChooser();
//		fileChooser.getExtensionFilters().addAll(
//				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//				new FileChooser.ExtensionFilter("JPG", "*.jpeg"),
//				new FileChooser.ExtensionFilter("JPG", "*.JPG"),
//				new FileChooser.ExtensionFilter("JPG", "*.JPEG"),
//				new FileChooser.ExtensionFilter("PNG", "*.png"),
//				new FileChooser.ExtensionFilter("PNG", "*.PNG")
//		);

		File selectedFile = fileChooser.showOpenDialog(new Stage());
		Image image  = new Image(selectedFile.toURI().toString());

		String fullFileName = selectedFile.getName();

		appState.setFilePath(FilenameUtils.getPath(selectedFile.toURI().toString()));
		appState.setFileName(FilenameUtils.getBaseName(selectedFile.toURI().toString()));
		appState.setFileExtension(FilenameUtils.getExtension(selectedFile.toURI().toString()));

		appState.setMatType(CvType.CV_8UC1);

		Mat mat = ImageUtils.image2Mat(image, 1);
		image = ImageUtils.mat2Image(mat, "." + appState.getFileExtension());

		imageData.setOriginalImage(image);

		if(appState.getImagePaneNo() == 0){
			appState.setImagePaneNo(1);
		}

	}

	public void saveMagnitude(){
		saveImage(imageData.getFourierMagnitudeImage());
	}

	public void saveSelection(){
		saveImage(imageData.getOutputInnerImage());
	}

	public void saveOutside(){
		saveImage(imageData.getOutputOuterImage());
	}

	private void saveImage(Image image){

		if(image == null){ return; }

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter(appState.getFileExtension(), "*." + appState.getFileExtension())
		);

		fileChooser.setTitle("Save Image");

		File file = fileChooser.showSaveDialog(new Stage());


		if (file != null) {
			try {
				if(!file.getName().endsWith("." + appState.getFileExtension())){
					file = new File(file.getAbsolutePath() + "." + appState.getFileExtension());
				}

				ImageIO.write(SwingFXUtils.fromFXImage(image,
						null), appState.getFileExtension(), file);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

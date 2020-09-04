package foury;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;


public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("gui/mainScene.fxml"));

		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("gui/mainSceneStyle.css").toExternalForm());

		stage.setTitle("Foury");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}

	public static void main(String[] args) {

		// load the native OpenCV library
		nu.pattern.OpenCV.loadShared();
//		nu.pattern.OpenCV.loadLocally();
//		nu.pattern.OpenCV.loadLibrary();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);

		launch(args);
	}


}


package foury;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.Arrays;


public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("gui/mainScene.fxml"));

		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("gui/mainSceneStyle.css").toExternalForm());

		stage.setTitle("miniDFT");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}

	public static void main(String[] args) {

		String currentDirectory = System.getProperty("user.dir");
		System.out.println("The current working directory is " + currentDirectory);
		System.out.println("Running on " + System.getProperty("os.name"));

		File libraryFile = new File("libopencv_java430.so");
		


		String absPath = libraryFile.getAbsolutePath();
		absPath = absPath.substring(0, absPath.length() - 20);

		try {
			addDirJava14(absPath);
		} catch (IOException e) {
			e.printStackTrace();
		}


		// load the native OpenCV library
//		nu.pattern.OpenCV.loadShared();
		nu.pattern.OpenCV.loadLocally();

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);

		launch(args);
	}



	public static void addDirJava14(String s) throws IOException {
		MethodHandles.Lookup cl = null;

		try {
			cl = MethodHandles.privateLookupIn(ClassLoader.class, MethodHandles.lookup());
		} catch (IllegalAccessException e) {
			throw new IOException("Failed to create MethodHandle");
		}


		VarHandle usr_paths = null;

		try {
			usr_paths = cl.findStaticVarHandle(ClassLoader.class, "usr_paths", String[].class);
		} catch (NoSuchFieldException e) {
			throw new IOException("Cannot find usr_paths field");
		} catch (IllegalAccessException e) {
			throw new IOException("Access to usr_paths field was denied");
		}

		//		usr_paths.set(null);

		String[] pathStrings = (String[])usr_paths.get();
		String[] newPathStrings = new String[pathStrings.length + 1];
		System.arraycopy(pathStrings, 0, newPathStrings, 0, pathStrings.length);
		newPathStrings[pathStrings.length] = s;

		usr_paths.set(newPathStrings);

		System.out.println("Usr paths: " +  Arrays.toString((String[])usr_paths.get()));

	}


	public static void addDir(String s) throws IOException {
		try {
			// This enables the java.library.path to be modified at runtime
			// From a Sun engineer at http://forums.sun.com/thread.jspa?threadID=707176
			//
			Field field = ClassLoader.class.getDeclaredField("usr_paths");
			field.setAccessible(true);
			String[] paths = (String[])field.get(null);
			for (int i = 0; i < paths.length; i++) {
				if (s.equals(paths[i])) {
					return;
				}
			}
			String[] tmp = new String[paths.length+1];
			System.arraycopy(paths,0,tmp,0,paths.length);
			tmp[paths.length] = s;
			field.set(null,tmp);
			System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + s);
		} catch (IllegalAccessException e) {
			throw new IOException("Failed to get permissions to set library path");
		} catch (NoSuchFieldException e) {
			throw new IOException("Failed to get field handle to set library path");
		}
	}
}




package foury.gui;

import foury.data.AppState;
import foury.data.ImageData;
import foury.gui.widgets.ImageTileWidget;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;


public class DisplayArea extends Area {

	@FXML
	public HBox imageDisplayWindows;


	public DisplayArea(ImageData imageData, AppState appState){
		super(imageData, appState);

	}

	@FXML
	public void initialize() {

		imageDisplayWindows.getChildren().add(new ImageTileWidget(imageData));

		appState.imagePaneNoProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println("Image Pane number changed from " + oldVal +  " to " + newVal);
			setTabNo((int)newVal);
		});
	}

	public void setTabNo(int no){

		imageDisplayWindows.getChildren().clear();

		for (int i = 0; i < no; i++) {
			imageDisplayWindows.getChildren().add(new ImageTileWidget());
		}

//		int currentTabNo = imageDisplayWindows.getChildren().size();
//
//		if(no > currentTabNo){
//			for(int i = currentTabNo; i < no; i++) {
//				imageDisplayWindows.getChildren().add(new ImageTileWidget(imageData));
//			}
//		}else if(no < currentTabNo){
//			imageDisplayWindows.getChildren().remove(no + 1, currentTabNo);
//		}
	}

}

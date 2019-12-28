package foury.gui;

import foury.data.AppState;
import foury.data.ImageData;
import foury.gui.widgets.ImageTileWidget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class DisplayArea extends Area {

	@FXML
	public HBox imageDisplayWindows;


	public DisplayArea(ImageData imageData, AppState appState){
		super(imageData, appState);
	}

	@FXML
	public void initialize() {
//		imageDisplayWindows.getChildren().add(new ImageTileWidget(imageData));

		appState.imagePaneNoProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println("Image Pane number changed from " + oldVal +  " to " + newVal);
			setTabNo((int)newVal);
		});



		imageData.readyToDisplayProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				System.out.println("ReadyToDisplay listener triggered");
				imageDisplayWindows.getChildren().add(new ImageView(imageData.getOriginalImage())); }
		});
	}


	public void updateImages(){
		setTabNo(appState.getImagePaneNo());

	}

	public void setTabNo(int no){

		imageDisplayWindows.getChildren().clear();

		for (int i = 0; i < no; i++) {
			imageDisplayWindows.getChildren().add(new ImageTileWidget(imageData));
		}

	}

}

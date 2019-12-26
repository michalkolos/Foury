package foury.gui.widgets;

import foury.data.ImageData;
import foury.utils.MiscUtils;
import foury.utils.ImageUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTileWidget extends TabPane {

	@FXML
	public ImageView originalView;
	@FXML
	public ImageView magnitudeView;
	@FXML
	public ImageView outputFullView;
	@FXML
	public ImageView outputSelectionView;
	@FXML
	public ImageView outputOutsideView;

	@FXML
	public Tab originalTab;
	@FXML
	public Tab magnitudeTab;
	@FXML
	public Tab outputFullTab;
	@FXML
	public Tab outputSelectionTab;
	@FXML
	public Tab outputOutsideTab;


	ImageData imageData;

	public ImageTileWidget(){
		this.imageData = new ImageData();
		MiscUtils.loadFXML(this);
		loadImages();
	}

	public ImageTileWidget(ImageData imageData){
		this.imageData = imageData;
		MiscUtils.loadFXML(this);
		loadImages();
	}

	@FXML
	public void initialize() {

	}

	private void loadImages(){

//		if(imageData.getOriginalMat() == null){
//			byte[] color = new byte[]{127, -120, 127};
//			originalView.setImage(ImageUtils.mat2Image(
//					ImageUtils.generatePlaceholderMat(400, 400, color),
//					".png"));
//		}else{
//			originalView.setImage(ImageUtils.mat2Image(imageData.getOriginalMat(), ".png"));
//		}
//
//		if(imageData.getFourierMagnitude() == null){
//			magnitudeView.setImage(null);
//			magnitudeTab.setDisable(true);
//		}else{
//			magnitudeView.setImage(ImageUtils.mat2Image(imageData.getFourierMagnitude(), ".png"));
//			magnitudeTab.setDisable(false);
//		}
//
//		if(imageData.getOutputTotal() == null){
//			outputFullView.setImage(null);
//			outputFullTab.setDisable(true);
//		}else{
//			outputFullView.setImage(ImageUtils.mat2Image(imageData.getOutputTotal(), ".png"));
//			outputFullTab.setDisable(false);
//		}
//
//		if(imageData.getOutputInner() == null){
//			outputSelectionView.setImage(null);
//			outputSelectionTab.setDisable(true);
//		}else{
//			outputSelectionView.setImage(ImageUtils.mat2Image(imageData.getOutputInner(), ".png"));
//			outputSelectionTab.setDisable(false);
//		}
//
//		if(imageData.getOutputOuter() == null){
//			outputOutsideView.setImage(null);
//			outputOutsideTab.setDisable(true);
//		}else{
//			outputOutsideView.setImage(ImageUtils.mat2Image(imageData.getOutputOuter(), ".png"));
//			outputOutsideTab.setDisable(false);
//		}


		if(imageData.getOriginalImage() == null){
			byte[] color = new byte[]{127, -120, 127};
			originalView.setImage(ImageUtils.mat2Image(
					ImageUtils.generatePlaceholderMat(400, 400, color),
					".png"));
		}else{
			originalView.setImage(ImageUtils.mat2Image(imageData.getOriginalMat(), ".png"));
		}

		magnitudeView.setImage(imageData.getFourierMagnitudeImage());
		if(imageData.getFourierMagnitude() == null){
			magnitudeTab.setDisable(true);
		}else{
			magnitudeTab.setDisable(false);
		}

		outputFullView.setImage(imageData.getOutputTotalImage());
		if(imageData.getOutputTotal() == null){
			outputFullTab.setDisable(true);
		}else{
			outputFullTab.setDisable(false);
		}

		outputSelectionView.setImage(imageData.getOutputInnerImage());
		if(imageData.getOutputInner() == null){
			outputSelectionTab.setDisable(true);
		}else{
			outputSelectionTab.setDisable(false);
		}

		outputOutsideView.setImage(imageData.getOutputOuterImage());
		if(imageData.getOutputOuter() == null){
			outputOutsideTab.setDisable(true);
		}else{
			outputOutsideTab.setDisable(false);
		}
	}
}

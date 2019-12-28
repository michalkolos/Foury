package foury.gui.widgets;
import foury.data.ImageData;
import foury.utils.MiscUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class ImageTileWidget extends TabPane {

	@FXML
	private TabPane tabPaneRoot;

	@FXML
	private ImageView originalView;
	@FXML
	private ImageView magnitudeView;
	@FXML
	private ImageView outputFullView;
	@FXML
	private ImageView outputSelectionView;
	@FXML
	private ImageView outputOutsideView;

	@FXML
	private Tab originalTab;
	@FXML
	private Tab magnitudeTab;
	@FXML
	private Tab outputFullTab;
	@FXML
	private Tab outputSelectionTab;
	@FXML
	private Tab outputOutsideTab;



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
		loadImages();
	}

	public void loadImages(){

		originalView.setImage(imageData.getOriginalImage());
		originalView.setVisible(true);

		magnitudeView.setImage(imageData.getFourierMagnitudeImage());
		outputFullView.setImage(imageData.getOutputTotalImage());
		outputSelectionView.setImage(imageData.getOutputInnerImage());
		outputOutsideView.setImage(imageData.getOutputOuterImage());

		requestLayout();

		if(imageData.getFourierMagnitudeImage() == null){
			magnitudeTab.setDisable(true);
		}else{
			magnitudeTab.setDisable(false);
		}


		if(imageData.getOutputTotalImage() == null){
			outputFullTab.setDisable(true);
		}else{
			outputFullTab.setDisable(false);
		}


		if(imageData.getOutputInnerImage() == null){
			outputSelectionTab.setDisable(true);
		}else{
			outputSelectionTab.setDisable(false);
		}


		if(imageData.getOutputOuterImage() == null){
			outputOutsideTab.setDisable(true);
		}else{
			outputOutsideTab.setDisable(false);
		}


	}
}

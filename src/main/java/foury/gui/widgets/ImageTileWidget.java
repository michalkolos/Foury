package foury.gui.widgets;
import foury.data.ImageData;
import foury.data.MouseBox;
import foury.utils.ImageUtils;
import foury.utils.MiscUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;


public class ImageTileWidget extends TabPane {

	@FXML
	private TabPane tabPaneRoot;

	@FXML
	private ImageView originalView;
	@FXML
	private ImageView magnitudeView;
	@FXML
	private ImageView maskView;
	@FXML
	private ImageView outputFullView;
	@FXML
	private ImageView outputSelectionView;
	@FXML
	private ImageView outputOutsideView;

	@FXML
	private ImageAreaWidget originalViewController;

	@FXML
	private Canvas selectionCanvas;
	@FXML
	private Canvas maskCanvas;

	@FXML
	private Tab originalTab;
	@FXML
	private Tab magnitudeTab;
	@FXML
	private Tab maskTab;
	@FXML
	private Tab outputFullTab;
	@FXML
	private Tab outputSelectionTab;
	@FXML
	private Tab outputOutsideTab;

	private MouseBox currentMouseBox;

	private ImageData imageData;
	private Mat maskMat;
	private boolean erase = false;

	public ImageTileWidget(){
		this.imageData = new ImageData();
		MiscUtils.loadFXML(this);

		currentMouseBox = new MouseBox();

		loadImages();

//		drawRectangle(selectionCanvas);
		setMouselisteners(selectionCanvas);
	}

	public ImageTileWidget(ImageData imageData){
		this.imageData = imageData;
		MiscUtils.loadFXML(this);

		currentMouseBox = new MouseBox();

		loadImages();

//		drawRectangle(selectionCanvas);
		setMouselisteners(selectionCanvas);

	}

	@FXML
	public void initialize() {
		loadImages();
	}

	@FXML
	private void handleSaveMaskButtonAction(final ActionEvent actionEvent) {
		saveMask();
		loadImages();
	}

	@FXML
	private void handleEraseButtonAction(final ActionEvent actionEvent) { erase = !erase; }

	@FXML
	private void handleClearButtonAction(final ActionEvent actionEvent) { erase = !erase; }


	public void loadImages(){

		originalView.setImage(imageData.getOriginalImage());
		originalView.setVisible(true);

//		originalViewController.displayImage(imageData.getOriginalImage());

		magnitudeView.setImage(imageData.getFourierMagnitudeImage());
		maskView.setImage(imageData.getMaskImage());
		outputFullView.setImage(imageData.getOutputTotalImage());
		outputSelectionView.setImage(imageData.getOutputInnerImage());
		outputOutsideView.setImage(imageData.getOutputOuterImage());

		requestLayout();

		if(imageData.getFourierMagnitudeImage() == null){
			magnitudeTab.setDisable(true);
		}else{
			magnitudeTab.setDisable(false);
		}

		if(imageData.getMaskImage() == null){
			maskTab.setDisable(true);
		}else{
			maskTab.setDisable(false);
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


		selectionCanvas.resize(0.0,0.0);
		selectionCanvas.widthProperty().bind(magnitudeView.getImage().widthProperty());
		selectionCanvas.heightProperty().bind(magnitudeView.getImage().heightProperty());

		maskCanvas.resize(0.0,0.0);
		maskCanvas.widthProperty().bind(magnitudeView.getImage().widthProperty());
		maskCanvas.heightProperty().bind(magnitudeView.getImage().heightProperty());
		maskCanvas.setOpacity(0.3);

		maskMat = new Mat((int)magnitudeView.getImage().getHeight(), (int)magnitudeView.getImage().getWidth(), CvType.CV_8U, new Scalar(255));

	}

	private void setMouselisteners(Node listenedArea){


		listenedArea.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				currentMouseBox = new MouseBox();
				currentMouseBox.setStarted(true);
				currentMouseBox.setStartx(event.getX());
				currentMouseBox.setStarty(event.getY());
//				System.out.println("Selection rect start: " + event.getX() + " " + event.getY());
			}
		});

		listenedArea.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
//				System.out.println("Mouse dragged: " + event.getX() + " " + event.getY());
				drawRectangle(selectionCanvas);

				currentMouseBox.setEndx(event.getX());
				currentMouseBox.setEndy(event.getY());
			}
		});

		listenedArea.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				currentMouseBox.setFinished(true);

				imageData.setMouseBox(currentMouseBox);
				drawMask(maskCanvas);

				System.out.println("Selection rect end: " + event.getX() + " " + event.getY());
			}
		});
	}

	private void drawRectangle(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.GREEN);
		gc.setLineWidth(1);


		gc.strokeLine(currentMouseBox.getStartx(), currentMouseBox.getStarty(),
				currentMouseBox.getEndx(), currentMouseBox.getStarty());

		gc.strokeLine(currentMouseBox.getEndx(), currentMouseBox.getStarty(),
				currentMouseBox.getEndx(), currentMouseBox.getEndy());

		gc.strokeLine(currentMouseBox.getEndx(), currentMouseBox.getEndy(),
				currentMouseBox.getStartx(), currentMouseBox.getEndy());

		gc.strokeLine(currentMouseBox.getStartx(), currentMouseBox.getEndy(),
				currentMouseBox.getStartx(), currentMouseBox.getStarty());


	}

	private void drawMask(Canvas canvas){

		double startx = currentMouseBox.getStartx();
		double starty = currentMouseBox.getStarty();
		double endx = currentMouseBox.getEndx();
		double endy = currentMouseBox.getEndy();

		GraphicsContext gc = canvas.getGraphicsContext2D();

		if(!this.erase) {
			gc.setFill(Color.RED);
//			gc.setGlobalAlpha(0.3);

			gc.fillRect(Math.min(startx, endx), Math.min(starty, endy), Math.abs(endx - startx), Math.abs(endy - starty));
			Imgproc.rectangle (	maskMat, new Point(startx, starty), new Point(endx, endy), new Scalar(0),-1);


			startx = canvas.getWidth() - startx;
			endx = canvas.getWidth() - endx;
			starty = canvas.getHeight() - starty;
			endy = canvas.getHeight() - endy;

			gc.fillRect(Math.min(startx, endx), Math.min(starty, endy), Math.abs(endx - startx), Math.abs(endy - starty));
			Imgproc.rectangle (	maskMat, new Point(startx, starty), new Point(endx, endy), new Scalar(0),-1);

		}else{
			gc.setGlobalAlpha(1);

			gc.clearRect(Math.min(startx, endx), Math.min(starty, endy), Math.abs(endx - startx), Math.abs(endy - starty));
			Imgproc.rectangle (	maskMat, new Point(startx, starty), new Point(endx, endy), new Scalar(255),-1);

			startx = canvas.getWidth() - startx;
			endx = canvas.getWidth() - endx;
			starty = canvas.getHeight() - starty;
			endy = canvas.getHeight() - endy;

			gc.clearRect(Math.min(startx, endx), Math.min(starty, endy), Math.abs(endx - startx), Math.abs(endy - starty));
			Imgproc.rectangle (	maskMat, new Point(startx, starty), new Point(endx, endy), new Scalar(255),-1);
		}

	}

	private void saveMask(){

		imageData.setFourierMask(maskMat);
		imageData.setMaskImage(ImageUtils.mat2Image(maskMat, ".png"));

		imageData.setReadyToCalculateSelection(true);
//		imageData.setReadyToDisplay(true);

	}

	private void clearMask(){
		imageData.getFourierMask().setTo(new Scalar(0));
		GraphicsContext gc = maskCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, maskCanvas.getWidth(), maskCanvas.getHeight());

		imageData.setReadyToCalculateSelection(true);
	}
}

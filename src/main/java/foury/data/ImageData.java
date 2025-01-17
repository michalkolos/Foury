package foury.data;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ImageData {


	private Image originalImage = null;
	private Mat fourierComplex = null;
	private Mat fourierMask = null;
	private Image fourierMagnitudeImage = null;
	private Image maskImage = null;
	private Image outputTotalImage = null;
	private Image outputInnerImage = null;
	private Image outputOuterImage = null;

	private MouseBox mouseBox = null;

	private SimpleBooleanProperty readyToCalculate;
	private SimpleBooleanProperty readyToDisplay;
	private SimpleBooleanProperty readyToCalculateSelection;


	public ImageData(){

		readyToCalculate = new SimpleBooleanProperty(false);
		readyToDisplay = new SimpleBooleanProperty(false);
		readyToCalculateSelection = new SimpleBooleanProperty(false);
	}




	private Mat generatePlaceholderMat(int xSize, int ySize){

		Mat outputMat = new Mat(ySize, xSize, CvType.CV_8UC3);

		byte[] color = new byte[]{(byte)255 - 127, (byte)255 - 127, (byte)255 - 127};

		for(int i = 0; i < outputMat.rows(); i++){
			for(int j = 0; j < outputMat.cols(); j++) {

				outputMat.put(i, j, color);
			}
		}

		return outputMat;
	}

	public void reset(){
		fourierMask = null;
		fourierMagnitudeImage = null;
		maskImage = null;
		outputTotalImage = null;
		outputInnerImage = null;
		outputOuterImage = null;
	}







	// =================================================================================================================
	//                                              Getters and Setters:
	// =================================================================================================================


	public boolean isReadyToCalculate() {
		return readyToCalculate.get();
	}

	public SimpleBooleanProperty readyToCalculateProperty() {
		return readyToCalculate;
	}

	public void setReadyToCalculate(boolean readyToCalculate) {
		this.readyToCalculate.set(readyToCalculate);
	}


	public boolean isReadyToDisplay() {
		return readyToDisplay.get();
	}

	public SimpleBooleanProperty readyToDisplayProperty() {
		return readyToDisplay;
	}

	public void setReadyToDisplay(boolean readyToDisplay) {
		this.readyToDisplay.set(readyToDisplay);
	}


	public boolean isReadyToCalculateSelection() {
		return readyToCalculateSelection.get();
	}

	public SimpleBooleanProperty readyToCalculateSelectionProperty() {
		return readyToCalculateSelection;
	}

	public void setReadyToCalculateSelection(boolean readyToCalculateSelection) {
		this.readyToCalculateSelection.set(readyToCalculateSelection);
	}



	public Image getOriginalImage() { return originalImage;	}

	public void setOriginalImage(Image originalImage) {
		this.originalImage = originalImage;
//		this.readyToDisplay.set(true);
		this.readyToCalculate.set(true);
	}

	public Image getFourierMagnitudeImage() {
		return fourierMagnitudeImage;
	}

	public void setFourierMagnitudeImage(Image fourierMagnitudeImage) {
		this.fourierMagnitudeImage = fourierMagnitudeImage;
	}

	public Image getMaskImage() {
		return maskImage;
	}

	public void setMaskImage(Image maskImage) {
		this.maskImage = maskImage;
	}

	public Image getOutputTotalImage() {
		return outputTotalImage;
	}

	public void setOutputTotalImage(Image outputTotalImage) {
		this.outputTotalImage = outputTotalImage;
	}

	public Image getOutputInnerImage() {
		return outputInnerImage;
	}

	public void setOutputInnerImage(Image outputInnerImage) {
		this.outputInnerImage = outputInnerImage;
	}

	public Image getOutputOuterImage() {
		return outputOuterImage;
	}

	public void setOutputOuterImage(Image outputOuterImage) {
		this.outputOuterImage = outputOuterImage;
	}

	public MouseBox getMouseBox() {
		return mouseBox;
	}

	public void setMouseBox(MouseBox mouseBox) {
		this.mouseBox = mouseBox;
	}

	public Mat getFourierComplex() {
		return fourierComplex;
	}

	public void setFourierComplex(Mat fourierComplex) {
		this.fourierComplex = fourierComplex;
	}

	public Mat getFourierMask() {
		return fourierMask;
	}

	public void setFourierMask(Mat fourierMask) {
		this.fourierMask = fourierMask;
	}
}

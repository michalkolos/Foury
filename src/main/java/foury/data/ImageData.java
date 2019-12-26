package foury.data;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ImageData {

	private Mat originalMat = null;
//	private Mat fourierComplex = null;
	private Mat fourierMagnitude = null;
	private Mat mask = null;
	private Mat outputTotal = null;
	private Mat outputInner = null;
	private Mat outputOuter = null;

	private Image originalImage = null;
	private Mat fourierComplex = null;
	private Image fourierMagnitudeImage = null;
	private Image maskImage = null;
	private Image outputTotalImage = null;
	private Image outputInnerImage = null;
	private Image outputOuterImage = null;

	private SimpleBooleanProperty readyToCalculate;
	private SimpleBooleanProperty readyToDisplay;


	public ImageData(){
		originalMat = generatePlaceholderMat(400, 400);
//		fourierComplex = generatePlaceholderMat(400, 400);
//		fourierMagnitude = generatePlaceholderMat(400, 400);
//		mask  = generatePlaceholderMat(400, 400);
//		outputTotal = generatePlaceholderMat(400, 400);
//		outputInner = generatePlaceholderMat(400, 400);
//		outputOuter = generatePlaceholderMat(400, 400);

		readyToCalculate = new SimpleBooleanProperty(false);
		readyToDisplay = new SimpleBooleanProperty(false);
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



	public Mat getOriginalMat() {
		return originalMat;
	}

	public void setOriginalMat(Mat originalMat) {
		this.originalMat = originalMat;
	}

	public Mat getFourierComplex() {
		return fourierComplex;
	}

	public void setFourierComplex(Mat fourierComplex) {
		this.fourierComplex = fourierComplex;
	}

	public Mat getFourierMagnitude() {
		return fourierMagnitude;
	}

	public void setFourierMagnitude(Mat fourierMagnitude) {
		this.fourierMagnitude = fourierMagnitude;
	}

	public Mat getMask() {
		return mask;
	}

	public void setMask(Mat mask) {
		this.mask = mask;
	}

	public Mat getOutputTotal() {
		return outputTotal;
	}

	public void setOutputTotal(Mat outputTotal) {
		this.outputTotal = outputTotal;
	}

	public Mat getOutputInner() {
		return outputInner;
	}

	public void setOutputInner(Mat outputInner) {
		this.outputInner = outputInner;
	}

	public Mat getOutputOuter() {
		return outputOuter;
	}

	public void setOutputOuter(Mat outputOuter) {
		this.outputOuter = outputOuter;
	}


	public Image getOriginalImage() {
		return originalImage;
	}

	public void setOriginalImage(Image originalImage) {
		this.originalImage = originalImage;
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
}

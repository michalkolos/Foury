package foury.actions;

import foury.data.AppState;
import foury.data.ImageData;
import foury.utils.ImageUtils;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class FourierAction extends Action {

//	private Mat image;
//	private List<Mat> planes;
	// the final complex image
//	private Mat complexImage;

	private int originalCols = 0;
	private int originalRows = 0;

	public FourierAction(ImageData imageData, AppState appState) {
		super(imageData, appState);

//		this.image = new Mat();
//		this.planes = new ArrayList<>();
//		this.complexImage = new Mat();

	}




	public void transform(){

		Mat image = ImageUtils.image2Mat(imageData.getOriginalImage(), appState.getMatType());

		Mat complexImage = dftOperation(image);

		Mat mag = this.createOptimizedMagnitude(complexImage);

		imageData.setFourierComplex(complexImage);
		imageData.setFourierMagnitudeImage(ImageUtils.mat2Image(mag, "." + appState.getFileExtension()));
		imageData.setReadyToDisplay(true);

		antitransformImage(complexImage);


	}


	public void transformSelections(){

		Mat selectionComplexImage = new Mat();
		Mat outsideComplexImage = new Mat();
		imageData.getFourierComplex().copyTo(selectionComplexImage);
		imageData.getFourierComplex().copyTo(outsideComplexImage);

		Mat mask = imageData.getFourierMask();
		unshiftMask(mask);

		applyMask(selectionComplexImage, mask, true);
		applyMask(outsideComplexImage, mask, false);

		Mat restoredSelection = dftReverse(selectionComplexImage);
		Mat restoredOutside = dftReverse(outsideComplexImage);

		imageData.setOutputInnerImage(ImageUtils.mat2Image(restoredSelection, "." + appState.getFileExtension()));
		imageData.setOutputOuterImage(ImageUtils.mat2Image(restoredOutside, "." + appState.getFileExtension()));
		imageData.setReadyToDisplay(true);
		imageData.setReadyToCalculate(false);

	}

	private void unshiftMask(Mat originalMask){

		int cx = originalMask.cols() / 2;
		int cy = originalMask.rows() / 2;

		Mat q0 = new Mat(originalMask, new Rect(0, 0, cx, cy));
		Mat q1 = new Mat(originalMask, new Rect(cx, 0, cx, cy));
		Mat q2 = new Mat(originalMask, new Rect(0, cy, cx, cy));
		Mat q3 = new Mat(originalMask, new Rect(cx, cy, cx, cy));

		Mat tmp = new Mat();

		q0.copyTo(tmp);
		q3.copyTo(q0);
		tmp.copyTo(q3);

		q1.copyTo(tmp);
		q2.copyTo(q1);
		tmp.copyTo(q2);

		int addPixelRows = Core.getOptimalDFTSize(originalMask.rows());
		int addPixelCols = Core.getOptimalDFTSize(originalMask.cols());

//		Mat padded = new Mat();

		Core.copyMakeBorder(originalMask, originalMask,
				0, addPixelRows - originalMask.rows(),
				0, addPixelCols - originalMask.cols(),
				Core.BORDER_CONSTANT, Scalar.all(0)
		);
	}

	private void applyMask(Mat image, Mat mask, boolean reverse){

		int compareVal = 0;
		if(reverse){
			compareVal = 255;
		}

		ArrayList<Mat> chanels = new ArrayList<>();

		Core.split(image, chanels);

		for (int j = 0; j < image.rows(); ++j) {
			for (int i = 0; i < image.cols(); ++i) {

				if(mask.get(j, i)[0] == compareVal){
//					chanels.get(0).put(j, i, 0);
					image.put(j, i, 0, 0);
				}
			}
		}


	}


	protected Mat dftOperation(Mat image){


		originalCols = image.cols();
		originalRows = image.rows();

		Mat padded = new Mat();
		List<Mat> planes = new ArrayList<>();
//		Mat complexImage = new Mat();


		// Resize image to optimal pixel count for Fourier transform:

		int addPixelRows = Core.getOptimalDFTSize(image.rows());
		int addPixelCols = Core.getOptimalDFTSize(image.cols());

		System.out.println("Applied row padding: " + addPixelRows);
		System.out.println("Applied col padding: " + addPixelCols);



		Core.copyMakeBorder(image, padded,
				0, addPixelRows - image.rows(),
				0, addPixelCols - image.cols(),
				Core.BORDER_CONSTANT, Scalar.all(0)
		);




		// Fourier transform produces complex number from pixel values. Both real and imaginary parts are stored as floats.
		// To store these results image values are converted to float and add another float layer is added for imaginary
		// values.

		padded.convertTo(padded, CvType.CV_32F);
		planes.add(padded);
		planes.add(Mat.zeros(padded.size(), CvType.CV_32F));

		Mat complexImage = new Mat();

		Core.merge(planes, complexImage);


		// Applying the DFT. Imaginary and real parts are stored in the newPlanes List.
		Core.dft(complexImage, complexImage);


		return complexImage;
	}

	private Mat dftReverse(Mat complexImage){

		Mat reversedImage = new Mat();

		Core.idft(complexImage, reversedImage);

		Mat restoredImage = new Mat();
		ArrayList<Mat> planes = new ArrayList<>();

		Core.split(reversedImage, planes);
		Core.normalize(planes.get(0), restoredImage, 0, 255, Core.NORM_MINMAX);

		// move back the Mat to 8 bit, in order to proper show the result
		restoredImage.convertTo(restoredImage, CvType.CV_8U);
		restoredImage = deoptimizeImageDim(restoredImage);

		return restoredImage;
	}


	/**
	 * The action triggered by pushing the button for apply the inverse dft to
	 * the loaded image
	 */

	protected void antitransformImage(Mat complexImage)
	{
		Mat restoredImage = dftReverse(complexImage);

		imageData.setOutputTotalImage(ImageUtils.mat2Image(restoredImage, "." + appState.getFileExtension()));
		imageData.setReadyToDisplay(true);
		imageData.setReadyToCalculate(false);
	}

	/**
	 * Optimize the image dimensions
	 *
	 * @param image
	 *            the {@link Mat} to optimize
	 * @return the image whose dimensions have been optimized
	 */
	private Mat optimizeImageDim(Mat image)
	{
		// init
		Mat padded = new Mat();
		// get the optimal rows size for dft
		int addPixelRows = Core.getOptimalDFTSize(image.rows());
		// get the optimal cols size for dft
		int addPixelCols = Core.getOptimalDFTSize(image.cols());
		// apply the optimal cols and rows size to the image
		Core.copyMakeBorder(image, padded, 0, addPixelRows - image.rows(), 0, addPixelCols - image.cols(),
				Core.BORDER_CONSTANT, Scalar.all(0));

		return padded;
	}

	private Mat deoptimizeImageDim(Mat image)
	{

		Mat OutMat;
		OutMat = image.submat(new Range(0, originalRows), new Range(0, originalCols));
		return OutMat;
	}

	/**
	 * Optimize the magnitude of the complex image obtained from the DFT, to
	 * improve its visualization
	 *
	 * @param complexImage
	 *            the complex image obtained from the DFT
	 * @return the optimized image
	 */
	private Mat createOptimizedMagnitude(Mat complexImage)
	{
		// init
		List<Mat> newPlanes = new ArrayList<>();
		Mat mag = new Mat();
		// split the comples image in two planes
		Core.split(complexImage, newPlanes);
		// compute the magnitude
		Core.magnitude(newPlanes.get(0), newPlanes.get(1), mag);

		// move to a logarithmic scale
		Core.add(Mat.ones(mag.size(), CvType.CV_32F), mag, mag);
		Core.log(mag, mag);
		// optionally reorder the 4 quadrants of the magnitude image
		this.shiftDFT(mag);
		// normalize the magnitude image for the visualization since both JavaFX
		// and OpenCV need images with value between 0 and 255
		// convert back to CV_8UC1
		mag.convertTo(mag, CvType.CV_8UC1);
		Core.normalize(mag, mag, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);

		// you can also write on disk the resulting image...
		// Imgcodecs.imwrite("../magnitude.png", mag);

		return mag;
	}

	/**
	 * Reorder the 4 quadrants of the image representing the magnitude, after
	 * the DFT
	 *
	 * @param image
	 *            the {@link Mat} object whose quadrants are to reorder
	 */
	private void shiftDFT(Mat image)
	{
		image = image.submat(new Rect(0, 0, image.cols() & -2, image.rows() & -2));
		int cx = image.cols() / 2;
		int cy = image.rows() / 2;

		Mat q0 = new Mat(image, new Rect(0, 0, cx, cy));
		Mat q1 = new Mat(image, new Rect(cx, 0, cx, cy));
		Mat q2 = new Mat(image, new Rect(0, cy, cx, cy));
		Mat q3 = new Mat(image, new Rect(cx, cy, cx, cy));

		Mat tmp = new Mat();
		q0.copyTo(tmp);
		q3.copyTo(q0);
		tmp.copyTo(q3);

		q1.copyTo(tmp);
		q2.copyTo(q1);
		tmp.copyTo(q2);
	}

}

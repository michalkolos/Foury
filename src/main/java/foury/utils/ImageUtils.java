package foury.utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

public class ImageUtils {

	private static final double rPerc = 0.2;
	private static final double gPerc = 0.6;
	private static final double bPerc = 0.2;

	public static Image mat2Image(Mat frame, String encoding) {
		// create a temporary buffer
		MatOfByte buffer = new MatOfByte();
		// encode the frame in the buffer, according to the PNG format
		Imgcodecs.imencode(encoding, frame, buffer);
		// build and return an Image created from the image encoded in the buffer
		return new Image(new ByteArrayInputStream(buffer.toArray()));
	}

	//CvType.CV_8UC4

	public static Mat image2Mat(Image image, int type) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		byte[] buffer = new byte[width * height * 4];

		PixelReader reader = image.getPixelReader();
		WritablePixelFormat<ByteBuffer> format = WritablePixelFormat.getByteBgraInstance();
		reader.getPixels(0, 0, width, height, format, buffer, 0, width * 4);

		Mat mat = new Mat(height, width, CvType.CV_8UC4);
		mat.put(0, 0, buffer);

		mat = mat2mono(mat);

		return mat;
	}

	public static Mat generatePlaceholderMat(int xSize, int ySize, byte[] color){

		Mat outputMat = new Mat(ySize, xSize, CvType.CV_8UC3);

		for(int i = 0; i < outputMat.rows(); i++){
			for(int j = 0; j < outputMat.cols(); j++) {

				outputMat.put(i, j, color);
			}
		}

		return outputMat;
	}

	public static boolean checkIfMono(Image image){

		PixelReader pixelReader = image.getPixelReader();
		if (pixelReader == null) {
			return false;
		}


		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int argb = pixelReader.getArgb(x, y);

				int r = (0xff & (argb >> 16));
				int g = (0xff & (argb >> 8));
				int b = (0xff & argb);

				if((r != b) || (g != b)){
					return false;
				}
			}
		}

		return true;
	}

	public static BufferedImage rgbToGrayscale(BufferedImage bufferedImage){
		int channels = bufferedImage.getColorModel().getNumComponents();
		if (channels==1) return bufferedImage;

		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

		final byte[] a = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
		final byte[] gray = ((DataBufferByte) grayscaleImage.getRaster().getDataBuffer()).getData();
		for (int p = 0; p < width * height * channels; p += channels) {
			double r = a[p+2] & 0xFF;
			double g = a[p+1] & 0xFF;
			double b = a[p] & 0xFF;
			gray[p/channels] = (byte) Math.round((r * rPerc) + (g * gPerc) + (b * bPerc));

		}
		return grayscaleImage;
	}

	public static Mat mat2mono(Mat colorMat){
		Mat monoMat = new Mat(colorMat.rows(), colorMat.cols(), CvType.CV_8UC3);
		Imgproc.cvtColor(colorMat, monoMat, Imgproc.COLOR_BGRA2GRAY);

		return monoMat;
	}
}

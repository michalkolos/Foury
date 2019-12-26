package foury.utils;

import javafx.scene.image.Image;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.ByteArrayInputStream;

public class ImageUtils {

	public static Image mat2Image(Mat frame, String encoding) {
		// create a temporary buffer
		MatOfByte buffer = new MatOfByte();
		// encode the frame in the buffer, according to the PNG format
		Imgcodecs.imencode(encoding, frame, buffer);
		// build and return an Image created from the image encoded in the buffer
		return new Image(new ByteArrayInputStream(buffer.toArray()));
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

}

package foury.gui;

import foury.data.AppState;
import foury.data.ImageData;
import foury.data.MouseBox;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import javax.swing.plaf.synth.Region;
import javax.swing.text.html.ImageView;

public class MouseSelector {
	private Canvas observedView;
	private ImageData imageData;

	private MouseBox currentMouseBox;

	public MouseSelector(Canvas observedView, ImageData imageData) {
		this.observedView = observedView;
		this.imageData = imageData;

		observedView.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Mouse pos: " + event.getX() + " " + event.getY());
			}
		});

		currentMouseBox = new MouseBox();

		observedView.setOnMouseDragEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				currentMouseBox = new MouseBox();
				currentMouseBox.setStarted(true);
				currentMouseBox.setStartx(event.getX());
				currentMouseBox.setStarty(event.getY());
				System.out.println("Selection rect start: " + event.getX() + " " + event.getY());
			}
		});

		observedView.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				drawRectangle();
			}
		});

		observedView.setOnMouseDragReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				currentMouseBox.setFinished(true);
				currentMouseBox.setEndx(event.getX());
				currentMouseBox.setEndy(event.getY());

				imageData.setMouseBox(currentMouseBox);

				System.out.println("Selection rect end: " + event.getX() + " " + event.getY());
			}
		});


	}

	private void drawRectangle() {
		GraphicsContext gc = observedView.getGraphicsContext2D();

		gc.clearRect(0, 0, observedView.getWidth(), observedView.getHeight());

		gc.setStroke(Color.RED);
		gc.setLineWidth(3);

		gc.strokeLine(currentMouseBox.getStartx(), currentMouseBox.getStarty(),
				currentMouseBox.getEndx(), currentMouseBox.getStarty());

		gc.strokeLine(currentMouseBox.getStartx(), currentMouseBox.getEndy(),
				currentMouseBox.getEndx(), currentMouseBox.getEndy());

		gc.strokeLine(currentMouseBox.getEndx(), currentMouseBox.getEndy(),
				currentMouseBox.getStartx(), currentMouseBox.getEndy());

		gc.strokeLine(currentMouseBox.getStartx(), currentMouseBox.getEndy(),
				currentMouseBox.getStartx(), currentMouseBox.getStarty());


	}



}

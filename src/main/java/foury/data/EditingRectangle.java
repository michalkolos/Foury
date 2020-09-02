package foury.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

public class EditingRectangle extends Rectangle {
	private IntegerProperty value;

	public EditingRectangle(double x, double y, double width, double height, int value) {
		super(x, y, width, height);
		this.value = new SimpleIntegerProperty(value);
	}

	public int getValue() {
		return value.get();
	}

	public IntegerProperty valueProperty() {
		return value;
	}

	public void setValue(int value) {
		this.value.set(value);
	}
}

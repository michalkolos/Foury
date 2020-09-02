package foury.gui;

import javafx.scene.Node;
import javafx.scene.control.ToggleButton;

public class SegmentableToggleButton extends ToggleButton {
	@Override
	public void fire() {
		// we don't toggle from selected to not selected if part of a group
		if (getToggleGroup() == null || !isSelected()) {
			super.fire();
		}
	}
	public SegmentableToggleButton() {
		super();
	}
	public SegmentableToggleButton(String text, Node graphic) {
		super(text, graphic);
	}
	public SegmentableToggleButton(String text) {
		super(text);
	}
}
package foury.gui.widgets;

import foury.data.AppState;
import foury.data.ImageData;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.util.converter.NumberStringConverter;
import org.opencv.core.Core;


public class BorderSelectWidget extends Widget{

	@FXML
	private ToggleButton borderCons;
	@FXML
	private ToggleButton borderRepl;
	@FXML
	private ToggleButton borderRefl;
	@FXML
	private ToggleButton borderWrap;

	@FXML
	public TextField borderValField;
	@FXML
	public Slider borderValSlider;

	public BorderSelectWidget(ImageData imageData, AppState appState) {
		super(imageData, appState);
	}

	@FXML
	private void initialize(){

		appState.borderTypeProperty().addListener((obs, oldVal, newVal) -> {

			switch ((int)newVal) {
				case Core.BORDER_CONSTANT: {
					borderCons.setSelected(true);
					borderRepl.setSelected(false);
					borderRefl.setSelected(false);
					borderWrap.setSelected(false);
					break;
				}
				case Core.BORDER_REPLICATE: {
					borderCons.setSelected(false);
					borderRepl.setSelected(true);
					borderRefl.setSelected(false);
					borderWrap.setSelected(false);
					break;
				}
				case Core.BORDER_REFLECT: {
					borderCons.setSelected(false);
					borderRepl.setSelected(false);
					borderRefl.setSelected(true);
					borderWrap.setSelected(false);
					break;
				}
				case Core.BORDER_WRAP: {
					borderCons.setSelected(false);
					borderRepl.setSelected(false);
					borderRefl.setSelected(false);
					borderWrap.setSelected(true);
					break;
				}
				default: {
					borderCons.setSelected(false);
					borderRepl.setSelected(false);
					borderRefl.setSelected(false);
					borderWrap.setSelected(false);
					break;
				}
			}
		});


		borderCons.setOnAction(o -> {
			appState.borderTypeProperty().setValue(Core.BORDER_CONSTANT);
		});
		borderRepl.setOnAction(o -> {
			appState.borderTypeProperty().setValue(Core.BORDER_REPLICATE);
		});
		borderRefl.setOnAction(o -> {;
			appState.borderTypeProperty().setValue(Core.BORDER_REFLECT);
		});
		borderWrap.setOnAction(o -> {
			appState.borderTypeProperty().setValue(Core.BORDER_WRAP);
		});


		borderValSlider.valueProperty().bindBidirectional(appState.borderConstValProperty());

		Bindings.bindBidirectional(
				borderValField.textProperty(), appState.borderConstValProperty(), new NumberStringConverter()
		);
	}
}

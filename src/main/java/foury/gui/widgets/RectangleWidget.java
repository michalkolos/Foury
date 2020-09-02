package foury.gui.widgets;

import foury.data.AppState;
import foury.data.EditingRectangle;
import foury.data.ImageData;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class RectangleWidget extends Widget {

	@FXML
	public TextField xCoordTextField;
	@FXML
	public TextField yCoordTextField;
	@FXML
	public TextField widthTextField;
	@FXML
	public TextField heightTextField;
	@FXML
	public TextField valueTextField;
	@FXML
	public Button editButton;
	@FXML
	public Button deleteButton;

	private EditingRectangle rectangle;

	private BooleanProperty editFlag;
	private BooleanProperty deleteFlag;


	public RectangleWidget(ImageData imageData, AppState appState, EditingRectangle rectangle) {
		super(imageData, appState);

		this.rectangle = rectangle;

		this.editFlag = new SimpleBooleanProperty(false);
		this.deleteFlag = new SimpleBooleanProperty(false);
	}


	public BooleanProperty editFlagProperty() {
		return editFlag;
	}

	public BooleanProperty deleteFlagProperty() {
		return deleteFlag;
	}



	@FXML
	public void init(){

		/// Binding of rectangle values to

		/// Bidirectional binding of StringProperty and IntegerProperty
		Bindings.bindBidirectional(xCoordTextField.textProperty(),
			rectangle.xProperty(),
			new NumberStringConverter());

		Bindings.bindBidirectional(yCoordTextField.textProperty(),
			rectangle.yProperty(),
			new NumberStringConverter());

		Bindings.bindBidirectional(widthTextField.textProperty(),
			rectangle.widthProperty(),
			new NumberStringConverter());

		Bindings.bindBidirectional(heightTextField.textProperty(),
			rectangle.heightProperty(),
			new NumberStringConverter());

		Bindings.bindBidirectional(valueTextField.textProperty(),
			rectangle.valueProperty(),
			new NumberStringConverter());

	}



	@FXML
	private void setEditButton(){
		this.editFlag.setValue(true);
	}

	@FXML
	private void setDeleteButton(){
		this.deleteFlag.setValue(true);
	}
}

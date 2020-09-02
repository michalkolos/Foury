package foury.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class EditingRectangles {

	List<EditingRectangle> rectangleList;
	ObservableList<EditingRectangle>observableRectangleList;

	ObservableIntegerValue selectedRectangle;

	public EditingRectangles() {
		this.rectangleList = new ArrayList<>();
		this.observableRectangleList = FXCollections.observableList(rectangleList);
		this.selectedRectangle = new SimpleIntegerProperty(-1);
	}


	public ObservableList<EditingRectangle> getObservableList() {
		return observableRectangleList;
	}

	public Number getSelectedRectangle() {
		return selectedRectangle.get();
	}

	public ObservableIntegerValue selectedRectangleProperty() {
		return selectedRectangle;
	}





	public void putRectangle(EditingRectangle rectangle){
		rectangleList.add(rectangle);
	}

	public void pullRectangle(){
		pullRectangle(rectanglesCount() - 1);
	}

	public void pullRectangle(int index){
		rectangleList.remove(index);
	}

	public int rectanglesCount(){
		return rectangleList.size();
	}


}

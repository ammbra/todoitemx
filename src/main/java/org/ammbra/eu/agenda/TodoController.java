package org.ammbra.eu.agenda;

import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.ammbra.eu.agenda.items.TodoItem;

public class TodoController {

	@FXML
	private TableView<TodoItem> table;

	@FXML
	private TableColumn<TodoItem, LocalDate> deadline;

	@FXML
	private TableColumn<TodoItem, ImageView> image;

	@FXML
	private TableColumn<TodoItem, Void> remove;

	@FXML
	private TextField itemTitle;

	@FXML
	private TextField itemDescription;

	@FXML
	private TextField itemURL;

	@FXML
	private TextField itemPriority;

	@FXML
	private DatePicker deadlinePicker;

	@FXML
	private Parent root;

	private final ObservableList<TodoItem> data = FXCollections.observableArrayList(
//			new URLTodoItem("Upgrade to JDK 23", "Upgrade to JDK 23", "https://openjdk.org/projects/jdk/23/", "1", LocalDate.now(), LocalDate.now().plusMonths(1)),
//			new ImageTodoItem("JavaFX Project", "A cool JavaFX Project ", new Image("file:url.png"), "10", LocalDate.now(), LocalDate.now().plusMonths(2))
	);

	@FXML
	public void initialize() {
		Callback<TableColumn<TodoItem, LocalDate>, TableCell<TodoItem, LocalDate>> dateCellFactory
				= (TableColumn<TodoItem, LocalDate> _) -> new DateEditor();

		deadline.setCellFactory(dateCellFactory);

		deadlinePicker.setValue(LocalDate.now().plusMonths(1));

		itemPriority.textProperty().addListener((_, _, newValue) -> {
			if (!newValue.matches("\\d*")) {
				itemPriority.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});

		image.setCellValueFactory(TodoController::processImage);

		remove.setCellFactory(_ -> deleteItem());

		table.setItems(data);

	}

	private static SimpleObjectProperty<ImageView> processImage(TableColumn.CellDataFeatures<TodoItem, ImageView> cellData) {
		return null;
	}

	private TableCell<TodoItem, Void> deleteItem() {
		return new TableCell<>() {
			private final Button removeButton = new Button("Remove");

			{
				removeButton.setOnAction(_ -> {
					TodoItem item = getTableView().getItems().get(getIndex());
					data.remove(item);
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(removeButton);
				}
			}
		};
	}

	public void handleTitle(TableColumn.CellEditEvent<TodoItem, String> cellEditEvent) {
	}

	public void handleDescripion(TableColumn.CellEditEvent<TodoItem, String> cellEditEvent) {
	}

	public void addItem() {
		data.add(new TodoItem());
		itemTitle.clear();
		itemDescription.clear();
		itemURL.clear();
		itemPriority.clear();
		deadlinePicker.setValue(null);
	}

}

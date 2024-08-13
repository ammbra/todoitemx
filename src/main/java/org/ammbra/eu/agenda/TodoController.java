package org.ammbra.eu.agenda;

import java.io.File;
import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.ammbra.eu.agenda.items.ImageTodoItem;
import org.ammbra.eu.agenda.items.TodoItem;
import org.ammbra.eu.agenda.items.URLTodoItem;

public class TodoController {

	@FXML
	private TableView<TodoItem> table;

	@FXML
	private TableColumn<TodoItem, String> title;

	@FXML
	private TableColumn<TodoItem, String> description;

	@FXML
	private TableColumn<TodoItem, LocalDate> createdOn;

	@FXML
	private TableColumn<TodoItem, LocalDate> deadline;
	@FXML
	private TableColumn<TodoItem, String> url;

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
	private DatePicker deadlinePicker;

	@FXML
	private Parent root;

	private final ObservableList<TodoItem> data = FXCollections.observableArrayList(
			new URLTodoItem("Upgrade to JDK 23", "Upgrade to JDK 23", "https://openjdk.org/projects/jdk/23/", LocalDate.now(), LocalDate.now().plusMonths(1)),
			new ImageTodoItem("JavaFX Project", "A cool JavaFX Project ", new Image("file:url.png"), LocalDate.now(), LocalDate.now().plusMonths(2))
	);

	@FXML
	public void initialize() {
		Callback<TableColumn<TodoItem, LocalDate>, TableCell<TodoItem, LocalDate>> dateCellFactory
				= (TableColumn<TodoItem, LocalDate> _) -> new DateEditor();
		deadline.setCellFactory(dateCellFactory);

		deadlinePicker.setValue(LocalDate.now().plusMonths(1));

		image.setCellValueFactory(cellData -> processImage(cellData));

		remove.setCellFactory(_ -> deleteItem());

		table.setItems(data);

	}

	private static SimpleObjectProperty<ImageView> processImage(TableColumn.CellDataFeatures<TodoItem, ImageView> cellData) {
		if (cellData.getValue() instanceof ImageTodoItem) {
			Image image = ((ImageTodoItem) cellData.getValue()).getImage();
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			return new SimpleObjectProperty<>(imageView);
		}
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
		cellEditEvent.getTableView().getItems().get(
				cellEditEvent.getTablePosition().getRow())
				.setTitle(cellEditEvent.getNewValue());

	}

	public void handleDescripion(TableColumn.CellEditEvent<TodoItem, String> cellEditEvent) {
		cellEditEvent.getTableView().getItems().get(
				cellEditEvent.getTablePosition().getRow()).setDescription(cellEditEvent.getNewValue());

	}

	public void addItem() {
		if (!itemURL.getText().isEmpty()) {
			data.add(new URLTodoItem(itemTitle.getText(), itemDescription.getText(), itemURL.getText(), LocalDate.now(), deadlinePicker.getValue()));
		} else {
			Stage primaryStage = (Stage) root.getScene().getWindow();
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				data.add(new ImageTodoItem(itemTitle.getText(), itemDescription.getText(), new Image(file.toURI().toString()), LocalDate.now(),
						deadlinePicker.getValue()));
			}
		}
		itemTitle.clear();
		itemDescription.clear();
		itemURL.clear();
		deadlinePicker.setValue(null);
	}

}

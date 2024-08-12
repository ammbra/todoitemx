package org.ammbra.eu.agenda;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.ammbra.eu.agenda.items.ImageTodoItem;
import org.ammbra.eu.agenda.items.TodoItem;
import org.ammbra.eu.agenda.items.URLTodoItem;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;

//import module javafx.controls;
//import module java.logging;


public class TodoFx extends Application {
	private final TableView<TodoItem> table = new TableView<>();
	private final ObservableList<TodoItem> data = FXCollections.observableArrayList(
			new URLTodoItem("JavaFX Project", "Monday", "http://example.com", LocalDate.now(), LocalDate.now().plusMonths(1)),
			new ImageTodoItem("Design Logo", "Tuesday", new Image("file:url.png"), LocalDate.now(), LocalDate.now().plusMonths(2))
	);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		vbox.setSpacing(5);

		Label label = new Label("To-Do List");
		table.setEditable(true);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

		// Columns
		TableColumn<TodoItem, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

		titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		titleColumn.setOnEditCommit(
				t -> t.getTableView().getItems().get(
						t.getTablePosition().getRow()).setTitle(t.getNewValue())
		);

		TableColumn<TodoItem, String> descriptionCol = new TableColumn<>("Description");
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		descriptionCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(
						t.getTablePosition().getRow()).setDescription(t.getNewValue())
		);

		TableColumn<TodoItem, String> createdOnCol = new TableColumn<>("CreatedOn");
		createdOnCol.setCellValueFactory(new PropertyValueFactory<>("createdOn"));


		TableColumn<TodoItem, LocalDate> deadlineCol = new TableColumn<>("Deadline");
		deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		Callback<TableColumn<TodoItem, LocalDate>, TableCell<TodoItem, LocalDate>> dateCellFactory
				= (TableColumn<TodoItem, LocalDate> _) -> new DateEditing();
		deadlineCol.setCellFactory(dateCellFactory);

		TableColumn<TodoItem, String> urlCol = new TableColumn<>("URL");
		urlCol.setCellValueFactory(cellData -> {
			if (cellData.getValue() instanceof URLTodoItem) {
				return new SimpleStringProperty(((URLTodoItem) cellData.getValue()).getUrl());
			}
			return null;
		});

		TableColumn<TodoItem, ImageView> imageCol = new TableColumn<>("Image");
		imageCol.setCellValueFactory(cellData -> {
			if (cellData.getValue() instanceof ImageTodoItem) {
				Image image = ((ImageTodoItem) cellData.getValue()).getImage();
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(50);
				imageView.setFitWidth(50);
				return new SimpleObjectProperty<>(imageView);
			}
			return null;
		});

		// Remove Button for each row
		TableColumn<TodoItem, Void> removeCol = new TableColumn<>("Remove");
		removeCol.setCellFactory(_ -> new TableCell<>() {
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
		});

		table.getColumns().addAll(titleColumn, descriptionCol, urlCol, imageCol, createdOnCol, deadlineCol, removeCol);
		table.setItems(data);

		// Add new items
		TextField titleField = new TextField("Enter title");
		TextField descriptionField = new TextField("Enter description");
		TextField urlField = new TextField("Enter URL");
		DatePicker deadlineField = new DatePicker(LocalDate.now());
		Button addImageButton = new Button("Image");

		FileChooser fileChooser = new FileChooser();

		Button addBtn = new Button("Add Task");

		addBtn.setOnAction(_ -> {
			if (!urlField.getText().isEmpty()) {
				data.add(new URLTodoItem(titleField.getText(), descriptionField.getText(), urlField.getText(), LocalDate.now(), deadlineField.getValue()));
			} else {
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					data.add(new ImageTodoItem(titleField.getText(), descriptionField.getText(), new Image(file.toURI().toString()), LocalDate.now(),
							deadlineField.getValue()));
				}
			}
			titleField.clear();
			descriptionField.clear();
			urlField.clear();
			deadlineField.setValue(null);
		});


		HBox inputBox = new HBox(4, titleField, descriptionField, urlField, deadlineField, addImageButton, addBtn);
		inputBox.setSpacing(8);
		inputBox.setPadding(new Insets(10, 10, 10, 10));


		vbox.getChildren().addAll(label, table, inputBox);


		primaryStage.setTitle("ToDo App");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}